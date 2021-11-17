package rikkeisoft.com.itemsale.helper;

import org.modelmapper.ModelMapper;
import rikkeisoft.com.itemsale.dto.ItemDTO;
import rikkeisoft.com.itemsale.model.Item;

public class Helper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static Item mapToItem(ItemDTO itemDTO){
        Item item = modelMapper.map(itemDTO,Item.class);
        return item;
    }
}
