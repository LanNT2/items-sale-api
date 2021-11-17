package rikkeisoft.com.itemsale.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rikkeisoft.com.itemsale.dto.ItemDTO;
import rikkeisoft.com.itemsale.exception.ItemNotFoundException;
import rikkeisoft.com.itemsale.helper.Helper;
import rikkeisoft.com.itemsale.model.Item;
import rikkeisoft.com.itemsale.repository.ItemRepository;
import rikkeisoft.com.itemsale.service.ItemService;
import rikkeisoft.com.itemsale.util.SortUtil;

import java.time.Instant;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<Item> getAllItem(String keyword, Integer pageNo, Integer pageSize, String sort) {

        if (keyword == null || "".equals(keyword)) {
            return itemRepository.findAll(PageRequest.of(pageNo - 1, pageSize, SortUtil.getSort(sort)));
        } else {
            return itemRepository.relativeSearching(keyword, PageRequest.of(pageNo - 1, pageSize));
        }
    }

    @Override
    public Item getItem(Integer id) throws ItemNotFoundException {
        Optional<Item> itemOpt = itemRepository.findById(id);
        if (itemOpt.isPresent()) {
            return itemOpt.get();
        }
        throw new ItemNotFoundException();
    }

    @Override
    public Item updateItem(Integer id, ItemDTO itemDTO) throws ItemNotFoundException {
        Optional<Item> itemOpt = itemRepository.findById(id);
        if (itemOpt.isPresent()) {
            Item item = Helper.mapToItem(itemDTO);
            item.setCreatedAt(itemOpt.get().getCreatedAt());
            item.setUpdatedAt(Instant.now());
            itemRepository.save(item);
            return item;
        }
        throw new ItemNotFoundException();
    }
}
