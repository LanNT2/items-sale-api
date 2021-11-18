package rikkeisoft.com.itemsale.helper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import rikkeisoft.com.itemsale.constant.CommonConstant;
import rikkeisoft.com.itemsale.dto.ItemDTO;
import rikkeisoft.com.itemsale.dto.ItemUpdateDTO;
import rikkeisoft.com.itemsale.model.Item;
import rikkeisoft.com.itemsale.util.DateTimeUtil;

public class Helper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static Item mapToItem(ItemDTO itemDTO){
        Item item = modelMapper.map(itemDTO,Item.class);
        return item;
    }
    public static Item mapToItem(ItemUpdateDTO itemUpdateDTO){
        Item item = modelMapper.map(itemUpdateDTO,Item.class);
        return item;
    }
    public static ItemDTO mapToItemDTO(Item item){
        ItemDTO itemDTO = modelMapper.map(item,ItemDTO.class);
        itemDTO.setCreatedAt(DateTimeUtil.instantToString(item.getCreatedAt(), CommonConstant.FORMAT_DATETIME));
        if(item.getUpdatedAt()!=null){
            itemDTO.setUpdatedAt(DateTimeUtil.instantToString(item.getUpdatedAt(), CommonConstant.FORMAT_DATETIME));
        }
        if(item.getDeletedAt()!=null){
            itemDTO.setDeletedAt(DateTimeUtil.instantToString(item.getDeletedAt(), CommonConstant.FORMAT_DATETIME));
        }
        return itemDTO;
    }
    public static Page<ItemDTO> mapToPageItemDTO(Page<Item> pageItem){
        Page<ItemDTO> dtoPage = pageItem.map(ItemDTO::mapItem);
        return dtoPage;
    }
}
