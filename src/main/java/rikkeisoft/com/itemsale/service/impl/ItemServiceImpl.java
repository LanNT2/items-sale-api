package rikkeisoft.com.itemsale.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rikkeisoft.com.itemsale.dto.ItemDTO;
import rikkeisoft.com.itemsale.dto.ItemUpdateDTO;
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
    public Page<ItemDTO> getAllItem(String keyword, Integer pageNo, Integer pageSize, String sort) {
        Page<Item> pageItem = null;
        if (keyword == null || "".equals(keyword)) {
            pageItem = itemRepository.findByIsDeleted(0, PageRequest.of(pageNo - 1, pageSize, SortUtil.getSort(sort)));
            return Helper.mapToPageItemDTO(pageItem);
        } else {
            pageItem = itemRepository.relativeSearching(keyword, 0, PageRequest.of(pageNo - 1, pageSize));
            return Helper.mapToPageItemDTO(pageItem);
        }
    }

    @Override
    public ItemDTO getItem(Integer id) throws ItemNotFoundException {
        Optional<Item> itemOpt = itemRepository.findAllByIdAndIsDeleted(id, 0);
        if (itemOpt.isPresent()) {
            ItemDTO itemDTO = Helper.mapToItemDTO(itemOpt.get());
            return itemDTO;
        }
        throw new ItemNotFoundException();
    }

    @Override
    public ItemDTO updateItem(Integer id, ItemUpdateDTO itemUpdateDTO) throws ItemNotFoundException {
        Optional<Item> itemOpt = itemRepository.findAllByIdAndIsDeleted(id, 0);
        if (itemOpt.isPresent()) {
            Item item = Helper.mapToItem(itemUpdateDTO);
            item.setCreatedAt(itemOpt.get().getCreatedAt());
            item.setUpdatedAt(Instant.now());
            itemRepository.save(item);
            return Helper.mapToItemDTO(item);
        }
        throw new ItemNotFoundException();
    }
}
