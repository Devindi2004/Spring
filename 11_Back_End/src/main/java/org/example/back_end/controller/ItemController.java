package org.example.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.ItemDTO;
import org.example.back_end.entity.Item;
import org.example.back_end.service.custom.ItemService;
import org.example.back_end.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> saveItem(@RequestBody ItemDTO itemDTO){
        itemService.saveItem(itemDTO);
        return new ResponseEntity<>(new APIResponse<>(201, "Item Saved Successfully", null), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<APIResponse<String>> updateItem(@RequestBody ItemDTO itemDTO){
        itemService.updateItem(itemDTO);
        return new ResponseEntity<>(new APIResponse<>(200, "Item Updated Successfully", null), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Item>>> getAllItems(){
        // Note: ItemService eke return type eka ItemDTO list ekak wenna hadala thiyenne
        return new ResponseEntity<>(new APIResponse<>(200, "Success", itemService.getItemData()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteItem(@PathVariable int id){
        itemService.deleteItem(id);
        return new ResponseEntity<>(new APIResponse<>(200, "Item Deleted Successfully", null), HttpStatus.OK);
    }
}