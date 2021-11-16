package rikkeisoft.com.itemsale.service;

import org.springframework.data.domain.Page;
import rikkeisoft.com.itemsale.model.Item;

import java.util.List;

public interface ItemService {
    Page<Item> getAllItem(String keyword, Integer pageNo, Integer pageSize);
}
