package org.example.back_end.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.PlaceOrderDTO;
import org.example.back_end.entity.Item;
import org.example.back_end.entity.PlaceOrder;
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
    private final ItemRepository itemRepository;

    @Override
    public void saveOrder(PlaceOrderDTO dto) {

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        if (item.getItemQty() < dto.getOrderQty()) {
            throw new RuntimeException("Not Enough Stock");
        }

        double totalPrice = item.getItemPrice() * dto.getOrderQty();

        item.setItemQty(item.getItemQty() - dto.getOrderQty());
        itemRepository.save(item);

        PlaceOrder order = new PlaceOrder(
                dto.getOrderId(),
                dto.getCustomerId(),
                dto.getItemId(),
                dto.getOrderQty(),
                totalPrice
        );

        placeOrderRepository.save(order);
    }

    @Override
    public void updateOrder(PlaceOrderDTO dto) {

        PlaceOrder existingOrder = placeOrderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        item.setItemQty(item.getItemQty() + existingOrder.getOrderQty());

        if (item.getItemQty() < dto.getOrderQty()) {
            throw new RuntimeException("Not Enough Stock");
        }

        double totalPrice = item.getItemPrice() * dto.getOrderQty();

        item.setItemQty(item.getItemQty() - dto.getOrderQty());
        itemRepository.save(item);

        existingOrder.setCustomerId(dto.getCustomerId());
        existingOrder.setItemId(dto.getItemId());
        existingOrder.setOrderQty(dto.getOrderQty());
        existingOrder.setOrderPrice(totalPrice);

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

        Item item = itemRepository.findById(order.getItemId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        item.setItemQty(item.getItemQty() + order.getOrderQty());
        itemRepository.save(item);

        placeOrderRepository.deleteById(id);
    }
}
