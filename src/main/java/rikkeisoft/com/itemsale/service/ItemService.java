package rikkeisoft.com.itemsale.service;

import org.springframework.data.domain.Page;
import rikkeisoft.com.itemsale.dto.ItemDTO;
import rikkeisoft.com.itemsale.exception.ItemNotFoundException;
import rikkeisoft.com.itemsale.model.Item;

public interface ItemService {
    Page<Item> getAllItem(String keyword, Integer pageNo, Integer pageSize,String sort);
    Item getItem(Integer id) throws ItemNotFoundException;
    Item updateItem(Integer id, ItemDTO itemDTO) throws ItemNotFoundException;
}
