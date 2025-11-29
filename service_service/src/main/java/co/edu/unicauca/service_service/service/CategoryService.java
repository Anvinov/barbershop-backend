package co.edu.unicauca.service_service.service;

import co.edu.unicauca.service_service.infra.dto.request.CategoryRequestDTO;
import co.edu.unicauca.service_service.infra.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO request);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO getCategoryByName(String name);
}
