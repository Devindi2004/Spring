package org.example.back_end.service.custom;

import org.example.back_end.dto.CustomerDTO;
import org.example.back_end.dto.ItemDTO;
import org.example.back_end.entity.Customer;
import org.example.back_end.entity.Item;

import java.util.List;

public interface ItemService {
    public void saveItem(ItemDTO itemDTO);
    public void updateItem(ItemDTO itemDTO);

    List<Item> getItemData();

    void deleteItem(ItemDTO itemDTO);
}
