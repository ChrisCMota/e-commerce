package com.christian.ecommerce.mapper;

import com.christian.ecommerce.dto.ItemOrderDTO;
import com.christian.ecommerce.model.ItemOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemOrderMapper {

    @Mapping(target = "order", ignore = true)
    ItemOrderDTO itemToDto(ItemOrder item);

    @Mapping(target = "order", ignore = true)
    ItemOrder ItemDtoToItem(ItemOrderDTO itemDTO);

    @Mapping(target = "order", ignore = true)
    List<ItemOrderDTO> itemListToDtoList(List<ItemOrder> items);

    @Mapping(target = "order", ignore = true)
    List<ItemOrder> itemDtoToItemList(List<ItemOrderDTO> itemDTOS);
}
