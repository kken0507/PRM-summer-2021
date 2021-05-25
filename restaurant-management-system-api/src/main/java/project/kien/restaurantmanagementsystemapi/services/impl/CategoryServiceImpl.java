package project.kien.restaurantmanagementsystemapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.dtos.res.CategoryResDto;
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
    
}
