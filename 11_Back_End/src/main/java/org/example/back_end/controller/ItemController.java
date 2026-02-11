package org.example.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.ItemDTO;
import org.example.back_end.entity.Item;
import org.example.back_end.service.custom.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    public final ItemService itemService;

    @PostMapping
    public void saveItem(@RequestBody ItemDTO itemDTO){
        itemService.saveItem(itemDTO);
    }

    @PutMapping
    public  void updateItem(@RequestBody ItemDTO itemDTO){
        itemService.updateItem(itemDTO);
    }

    @GetMapping
    public List<Item> getItem(){
        return itemService.getItemData();
    }

    @DeleteMapping
    public  void deleteItem(@RequestBody ItemDTO itemDTO){
        itemService.deleteItem(itemDTO);
    }
}
