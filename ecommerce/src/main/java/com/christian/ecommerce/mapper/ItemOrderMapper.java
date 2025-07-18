package com.christian.ecommerce.mapper;

import com.christian.ecommerce.dto.ItemOrderDTO;
import com.christian.ecommerce.model.ItemOrder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemOrderMapper {

    ItemOrderDTO itemToDto(ItemOrder item);

    ItemOrder ItemDtoToItem(ItemOrderDTO itemDTO);

    List<ItemOrderDTO> itemListToDtoList(List<ItemOrder> items);

    List<ItemOrder> itemDtoToItemList(List<ItemOrderDTO> itemDTOS);
}
