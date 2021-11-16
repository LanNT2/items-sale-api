package rikkeisoft.com.itemsale.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rikkeisoft.com.itemsale.model.Item;
import rikkeisoft.com.itemsale.repository.ItemRepository;
import rikkeisoft.com.itemsale.service.ItemService;
import rikkeisoft.com.itemsale.util.SortUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<Item> getAllItem(String keyword, Integer pageNo, Integer pageSize,String sort) {

        if (keyword == null || "".equals(keyword)){
            return  itemRepository.findAll(PageRequest.of(pageNo-1, pageSize, SortUtil.getSort(sort)));
        }else{
            return itemRepository.relativeSearching(keyword, PageRequest.of(pageNo-1, pageSize));
        }
    }
}
