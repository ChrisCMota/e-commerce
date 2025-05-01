package com.christian.ecommerce.mapper;

import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    Category dtoToCategory(CategoryDTO categoryDTO);

    CategoryDTO categoryToDto(Category category);

    List<CategoryDTO> listCategoryToDto(List<Category> categories);
}
