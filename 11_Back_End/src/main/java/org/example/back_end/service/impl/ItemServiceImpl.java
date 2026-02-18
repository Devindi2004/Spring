package org.example.back_end.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.ItemDTO;
import org.example.back_end.entity.Item;
import org.example.back_end.repository.ItemRepository;
import org.example.back_end.service.custom.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        System.out.println(itemDTO.getItemId()+" "+itemDTO.getItemName());
        itemRepository.save(modelMapper.map(itemDTO, Item.class));
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        if (!itemRepository.existsById(itemDTO.getItemId())) {
            throw new RuntimeException("Item not found");
        }
        itemRepository.save(modelMapper.map(itemDTO, Item.class));
    }

    @Override
    public List<Item> getItemData() {
        List<Item> allItems = itemRepository.findAll();
        return modelMapper.map(allItems, new TypeToken<List<ItemDTO>>() {}.getType());
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }
}