package rikkeisoft.com.itemsale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rikkeisoft.com.itemsale.constant.CommonConstant;
import rikkeisoft.com.itemsale.model.Item;
import rikkeisoft.com.itemsale.util.DateTimeUtil;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemDTO {
    private Integer id;

    private String name;

    private String description;

    private Float price;

    private String imageUrl;

    private String createdAt;

    private String updatedAt;

    private String deletedAt;

    private Integer isDeleted;

    public static ItemDTO mapItem(Item item) {
        ItemDTO itemDTO = ItemDTO.builder()
                .id(item.getId())
                .createdAt(DateTimeUtil.instantToString(item.getCreatedAt(), CommonConstant.FORMAT_DATETIME))
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .imageUrl(item.getImageUrl())
                .isDeleted(item.getDeleted())
                .build();
        if (item.getUpdatedAt() != null)
            itemDTO.setUpdatedAt(DateTimeUtil.instantToString(item.getUpdatedAt(), CommonConstant.FORMAT_DATETIME));
        if (item.getDeletedAt() != null)
            itemDTO.setDeletedAt(DateTimeUtil.instantToString(item.getDeletedAt(), CommonConstant.FORMAT_DATETIME));
        return itemDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}


