package org.example.back_end.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.PlaceOrderDTO;
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
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;

    // ================= SAVE ORDER =================
    @Override
    public void saveOrder(PlaceOrderDTO dto) {

        // Check Item Exist
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        // Check Stock
        if (item.getItemQty() < dto.getOrderQty()) {
            throw new RuntimeException("Not Enough Stock");
        }

        // Reduce Stock
        item.setItemQty(item.getItemQty() - dto.getOrderQty());
        itemRepository.save(item);

        // Save Order
        PlaceOrder order = new PlaceOrder(
                dto.getOrderId(),
                dto.getCustomerId(),
                dto.getItemId(),
                dto.getOrderQty(),
                dto.getOrderPrice()
        );

        placeOrderRepository.save(order);
    }

    // ================= UPDATE ORDER =================
    @Override
    public void updateOrder(PlaceOrderDTO dto) {

        PlaceOrder existingOrder = placeOrderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        // First restore old quantity
        item.setItemQty(item.getItemQty() + existingOrder.getOrderQty());

        // Check new stock availability
        if (item.getItemQty() < dto.getOrderQty()) {
            throw new RuntimeException("Not Enough Stock");
        }

        // Reduce new quantity
        item.setItemQty(item.getItemQty() - dto.getOrderQty());
        itemRepository.save(item);

        // Update order
        existingOrder.setCustomerId(dto.getCustomerId());
        existingOrder.setItemId(dto.getItemId());
        existingOrder.setOrderQty(dto.getOrderQty());
        existingOrder.setOrderPrice(dto.getOrderPrice());

        placeOrderRepository.save(existingOrder);
    }

    // ================= GET ALL ORDERS =================
    @Override
    @Transactional
    public List<PlaceOrder> getOrderData() {
        return placeOrderRepository.findAll();
    }

    // ================= DELETE ORDER =================
    @Override
    public void deleteOrder(PlaceOrderDTO dto) {

        PlaceOrder order = placeOrderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        // Restore stock when deleting order
        Item item = itemRepository.findById(order.getItemId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        item.setItemQty(item.getItemQty() + order.getOrderQty());
        itemRepository.save(item);

        placeOrderRepository.deleteById(dto.getOrderId());
    }
}
