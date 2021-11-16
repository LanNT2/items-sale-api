package rikkeisoft.com.itemsale.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkeisoft.com.itemsale.model.Item;
import rikkeisoft.com.itemsale.service.ItemService;

import java.util.List;

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
}
