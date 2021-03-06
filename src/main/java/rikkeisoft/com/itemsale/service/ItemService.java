package rikkeisoft.com.itemsale.service;

import org.springframework.data.domain.Page;
import rikkeisoft.com.itemsale.dto.ItemDTO;
import rikkeisoft.com.itemsale.dto.ItemUpdateDTO;
import rikkeisoft.com.itemsale.exception.ItemAlreadyExistException;
import rikkeisoft.com.itemsale.exception.ItemNotFoundException;

public interface ItemService {
    Page<ItemDTO> getAllItem(String keyword, Integer pageNo, Integer pageSize, String sort);

    ItemDTO getItem(Integer id) throws ItemNotFoundException;

    ItemDTO updateItem(Integer id, ItemUpdateDTO itemDTO) throws ItemNotFoundException;

    String createItem(ItemDTO itemDTO) throws ItemAlreadyExistException;

    ItemDTO deleteItem(Integer Id) throws ItemNotFoundException;
}
