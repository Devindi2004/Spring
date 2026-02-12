package org.example.back_end.service.impl;

import org.example.back_end.dto.CustomerDTO;
import org.example.back_end.dto.ItemDTO;
import org.example.back_end.entity.Customer;
import org.example.back_end.entity.Item;
import org.example.back_end.repository.CustomerRepository;
import org.example.back_end.repository.ItemRepository;
import org.example.back_end.service.custom.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemRepository.save(
                new Item(
                        itemDTO.getItemId(),
                        itemDTO.getItemName(),
                        itemDTO.getItemQty(),
                        itemDTO.getItemPrice()
                ));

    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        itemRepository.save(
                new Item(
                        itemDTO.getItemId(),
                        itemDTO.getItemName(),
                        itemDTO.getItemQty(),
                        itemDTO.getItemPrice()
                ));

    }

    @Override
    public List<Item> getItemData() {
        List<Item> itemList = itemRepository.findAll();

        return itemList;
    }

    @Override
    public void deleteItem(ItemDTO itemDTO) {
        itemRepository.deleteById(itemDTO.getItemId());
    }
}
