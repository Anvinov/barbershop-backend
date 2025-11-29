package co.edu.unicauca.service_service.infra.mapper;

import co.edu.unicauca.service_service.entity.Category;
import co.edu.unicauca.service_service.infra.dto.request.CategoryRequestDTO;
import co.edu.unicauca.service_service.infra.dto.response.CategoryResponseDTO;

public class CategoryMapper {
    public static CategoryResponseDTO toResponse(Category category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }

    public static Category toEntity(CategoryRequestDTO dto){
        Category category = new Category();

        category.setName(dto.getName());

        return category;
    }
}
