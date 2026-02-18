package org.example.back_end.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.PlaceOrderDTO;
import org.example.back_end.entity.Customer;
import org.example.back_end.entity.Item;
import org.example.back_end.entity.PlaceOrder;
import org.example.back_end.repository.CustomerRepository;
import org.example.back_end.repository.ItemRepository;
import org.example.back_end.repository.PlaceOrderRepository;
import org.example.back_end.service.custom.PlaceOrderService;
import org.springframework.stereotype.Service;

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
        // 1. Check Customer
        Customer customer = customerRepository.findById(Integer.parseInt(dto.getCustomerId()))
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        // 2. Check Item & Stock
        Item item = itemRepository.findById(Integer.valueOf(dto.getItemId()))
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        if (item.getItemQty() < dto.getOrderQty()) {
            throw new RuntimeException("Not Enough Stock! Available: " + item.getItemQty());
        }

        // 3. Update Stock
        item.setItemQty(item.getItemQty() - dto.getOrderQty());
        itemRepository.save(item);

        // 4. Save Order (Setting Entity objects, not just IDs)
        PlaceOrder order = new PlaceOrder();
        order.setOrderId(dto.getOrderId());
        order.setCustomer(customer);
        order.setItem(item);
        order.setOrderQty(dto.getOrderQty());
        order.setOrderPrice(dto.getOrderPrice());

        placeOrderRepository.save(order);
    }

    @Override
    public void updateOrder(PlaceOrderDTO dto) {
        PlaceOrder existingOrder = placeOrderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        // Revert old stock before applying new order quantity
        Item item = existingOrder.getItem();
        item.setItemQty(item.getItemQty() + existingOrder.getOrderQty());

        if (item.getItemQty() < dto.getOrderQty()) {
            throw new RuntimeException("Not Enough Stock for Update");
        }

        item.setItemQty(item.getItemQty() - dto.getOrderQty());
        itemRepository.save(item);

        existingOrder.setOrderQty(dto.getOrderQty());
        existingOrder.setOrderPrice(dto.getOrderPrice());
        placeOrderRepository.save(existingOrder);
    }

    @Override
    public List<PlaceOrder> getOrderData() {
        return placeOrderRepository.findAll();
    }

    @Override
    public void deleteOrderById(String id) {
        PlaceOrder order = placeOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        // Revert stock when order is deleted
        Item item = order.getItem();
        item.setItemQty(item.getItemQty() + order.getOrderQty());
        itemRepository.save(item);

        placeOrderRepository.deleteById(id);
    }
}