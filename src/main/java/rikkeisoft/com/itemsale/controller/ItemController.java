package rikkeisoft.com.itemsale.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkeisoft.com.itemsale.dto.ItemDTO;
import rikkeisoft.com.itemsale.exception.ItemNotFoundException;
import rikkeisoft.com.itemsale.model.Item;
import rikkeisoft.com.itemsale.service.ItemService;

@RestController
@CrossOrigin("*")
@RequestMapping("/items")
public class ItemController {
    private ItemService itemService;
    public ItemController(ItemService itemService){
        this.itemService=itemService;
    }
    @GetMapping
    public ResponseEntity<Page<Item>> getAllItem(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(name = "limit", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(name = "sort", required = false, defaultValue = "createdAt:DESC") String sort
    ) {
        Page<Item> pageItem = itemService.getAllItem(keyword, pageNo, pageSize,sort);
        return new ResponseEntity<>(pageItem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") Integer id)throws ItemNotFoundException {
        return new ResponseEntity<>(itemService.getItem(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") Integer id, @RequestBody ItemDTO item) throws ItemNotFoundException{
        return new ResponseEntity<>(itemService.updateItem(id,item),HttpStatus.OK);
    }
}
