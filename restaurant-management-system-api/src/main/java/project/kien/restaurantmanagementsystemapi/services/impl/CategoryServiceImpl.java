package project.kien.restaurantmanagementsystemapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.dtos.request.CategoryReqDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.CategoryResDto;
import project.kien.restaurantmanagementsystemapi.entities.Category;
import project.kien.restaurantmanagementsystemapi.exceptions.ResourceNotFoundException;
import project.kien.restaurantmanagementsystemapi.mapper.CategoryMapper;
import project.kien.restaurantmanagementsystemapi.repositories.CategoryRepository;
import project.kien.restaurantmanagementsystemapi.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResDto> findAll() {
        return categoryRepository.findAll().stream().map(category -> categoryMapper.toResDto(category)).collect(Collectors.toList());
    }

    @Override
    public boolean create(CategoryReqDto dto) {
        boolean isAdded = false;

        if (dto != null) {
            Category newCategory = categoryMapper.toEntity(dto);
            categoryRepository.save(newCategory);
            isAdded = true;
        }

        return isAdded;
    }

    @Override
    public boolean update(Integer categoryId, CategoryReqDto dto) {
        boolean isUpdated = false;

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category",
                        "Cannot find Category type with id: " + categoryId));
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            category.setName(dto.getName());
            isUpdated = true;
        }
        categoryRepository.save(category);
        return isUpdated;
    }

    @Override
    public boolean delete(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category",
                        "Cannot find Category type with id: " + id));
        categoryRepository.delete(category);
        return true;
    }

    @Override
    public Page<CategoryResDto> search(String name, Boolean isItemAvailable, Pageable pageable) {
        if (name != null && !name.isEmpty() && isItemAvailable != null) {
            Page<Category> page = categoryRepository.findByNameLikeAndByItemsIsAvailable("%" + name + "%", isItemAvailable.booleanValue(), pageable);
            List list = page
                    .map(category -> categoryMapper.toResDto(category))
                    .stream()
                    .collect(Collectors.toList());
            return new PageImpl<>(list, pageable, page.getTotalElements());
        } else if (isItemAvailable != null) {
            Page<Category> page = categoryRepository.findAllAndByItemsIsAvailable(isItemAvailable.booleanValue(), pageable);
            List list = page
                    .map(category -> categoryMapper.toResDto(category))
                    .stream()
                    .collect(Collectors.toList());
            return new PageImpl<>(list, pageable, page.getTotalElements());
        } else if (name != null && !name.isEmpty()) {
            Page<Category> page = categoryRepository.findByNameLike("%" + name + "%", pageable);
            List list = page
                    .map(category -> categoryMapper.toResDto(category))
                    .stream()
                    .collect(Collectors.toList());
            return new PageImpl<>(list, pageable, page.getTotalElements());
        } else {
            Page<Category> page = categoryRepository.findAll(pageable);
            List list = page
                    .map(category -> categoryMapper.toResDto(category))
                    .stream()
                    .collect(Collectors.toList());
            return new PageImpl<>(list, pageable, page.getTotalElements());
        }
    }
}
