package org.example.back_end.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.OrderDetailDTO;
import org.example.back_end.dto.OrderHistoryDetailDTO;
import org.example.back_end.dto.PlaceOrderDTO;
import org.example.back_end.dto.PlaceOrderHistoryDTO;
import org.example.back_end.entity.Customer;
import org.example.back_end.entity.Item;
import org.example.back_end.entity.OrderDetail;
import org.example.back_end.entity.PlaceOrder;
import org.example.back_end.repository.CustomerRepository;
import org.example.back_end.repository.ItemRepository;
import org.example.back_end.repository.PlaceOrderRepository;
import org.example.back_end.service.custom.PlaceOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService {

    private final PlaceOrderRepository placeOrderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    @Override
    public void saveOrder(PlaceOrderDTO dto) {

        Customer customer = customerRepository
                .findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        PlaceOrder order = new PlaceOrder();
        order.setCustomer(customer);
        order.setDate(LocalDate.now().toString());

        List<OrderDetail> details = new ArrayList<>();

        for (OrderDetailDTO itemDto : dto.getItems()) {

            Item item = itemRepository
                    .findById(itemDto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item Not Found"));

            item.setItemQty(item.getItemQty() - itemDto.getQty());
            itemRepository.save(item);

            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setItem(item);
            detail.setQty(itemDto.getQty());
            detail.setUnitPrice(itemDto.getUnitPrice());

            details.add(detail);
        }

        order.setOrderDetails(details);
        placeOrderRepository.save(order);
    }

    @Override
    public List<PlaceOrderHistoryDTO> getOrderData() {

        List<PlaceOrder> orders = placeOrderRepository.findAll();

        return orders.stream().map(order -> {

            List<OrderHistoryDetailDTO> items =
                    order.getOrderDetails().stream()
                            .map(detail -> new OrderHistoryDetailDTO(
                                    detail.getItem().getItemName(),
                                    detail.getQty(),
                                    detail.getUnitPrice(),
                                    detail.getItem().getItemQty()
                            ))
                            .toList();

            double total = order.getOrderDetails().stream()
                    .mapToDouble(detail -> detail.getQty() * detail.getUnitPrice())
                    .sum();

            return new PlaceOrderHistoryDTO(
                    order.getOrderId(),
                    order.getDate(),
                    order.getCustomer().getCName(),
                    items,
                    total
            );

        }).toList();
    }

    @Override
    public void deleteOrderById(int id) {

        PlaceOrder order = placeOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        for (OrderDetail detail : order.getOrderDetails()) {
            Item item = detail.getItem();
            item.setItemQty(item.getItemQty() + detail.getQty());
            itemRepository.save(item);
        }

        placeOrderRepository.deleteById(id);
    }

    @Override
    public void updateOrder(PlaceOrderDTO dto) {

        deleteOrderById(dto.getOrderId());
        saveOrder(dto);
    }
}
