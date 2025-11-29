package co.edu.unicauca.service_service.service;

import co.edu.unicauca.service_service.entity.Category;
import co.edu.unicauca.service_service.exception.CategoryNotFoundException;
import co.edu.unicauca.service_service.exception.CategoryNameAlreadyExistsException;
import co.edu.unicauca.service_service.exception.IllegalRequestException;
import co.edu.unicauca.service_service.infra.dto.request.CategoryRequestDTO;
import co.edu.unicauca.service_service.infra.dto.response.CategoryResponseDTO;
import co.edu.unicauca.service_service.infra.mapper.CategoryMapper;
import co.edu.unicauca.service_service.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new CategoryNameAlreadyExistsException(request.getName());
        }

        Category category = CategoryMapper.toEntity(request);
        try {
            String name = category.getName().substring(0, 1).toUpperCase(Locale.ROOT) + category.getName().substring(1);
            category.setName(name);
        }catch (Exception e){
            throw new IllegalRequestException("Category name not valid");
        }

        categoryRepository.save(category);

        return CategoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponseDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(name));

        return CategoryMapper.toResponse(category);
    }
}
