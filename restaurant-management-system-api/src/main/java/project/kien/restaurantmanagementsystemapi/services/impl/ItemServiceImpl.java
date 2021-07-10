package project.kien.restaurantmanagementsystemapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.dtos.request.ItemReqDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.ItemResDto;
import project.kien.restaurantmanagementsystemapi.entities.Category;
import project.kien.restaurantmanagementsystemapi.entities.Item;
import project.kien.restaurantmanagementsystemapi.exceptions.ResourceNotFoundException;
import project.kien.restaurantmanagementsystemapi.mapper.ItemMapper;
import project.kien.restaurantmanagementsystemapi.repositories.CategoryRepository;
import project.kien.restaurantmanagementsystemapi.repositories.ItemRepository;
import project.kien.restaurantmanagementsystemapi.services.ItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<ItemResDto> findAll() {
        return itemRepository.findAll().stream().map(item -> itemMapper.toResDto(item)).collect(Collectors.toList());
    }

    @Override
    public boolean create(ItemReqDto dto) {
        boolean isAdded = false;

        if (dto != null) {
            Item newItem = itemMapper.toEntity(dto);
            if (dto.getCategoryId() != null) {
                Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());
                if (categoryOpt.isPresent()) {
                    newItem.setCategory(categoryOpt.get());
                }
            }
            itemRepository.save(newItem);
            isAdded = true;
        }

        return isAdded;
    }

    @Override
    public boolean update(Integer itemId, ItemReqDto dto) {
        boolean isUpdated = false;

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item",
                        "Cannot find Item type with id: " + itemId));
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            item.setName(dto.getName());
            isUpdated = true;
        }
        if (dto.getPrice() != null) {
            item.setPrice(dto.getPrice());
            isUpdated = true;
        }
        if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            item.setDescription(dto.getDescription());
            isUpdated = true;
        }
        if (dto.getIsAvailable() != null) {
            item.setAvailable(dto.getIsAvailable().booleanValue());
            isUpdated = true;
        }
        if (dto.getCategoryId() != null) {
            Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());
            if (categoryOpt.isPresent()) {
                item.setCategory(categoryOpt.get());
                isUpdated = true;
            }
        }

        itemRepository.save(item);
        return isUpdated;
    }

    @Override
    public boolean delete(Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item",
                        "Cannot find Item type with id: " + itemId));
        itemRepository.delete(item);
        return true;
    }

    @Override
    public Page<ItemResDto> search(String name, Boolean isItemAvailable, Pageable pageable) {
        if (name != null && !name.isEmpty() && isItemAvailable != null) {
            Page<Item> page = itemRepository.findByNameLikeAndIsAvailable("%" + name + "%", isItemAvailable.booleanValue(), pageable);
            List list = page
                    .map(item -> itemMapper.toResDto(item))
                    .stream()
                    .collect(Collectors.toList());
            return new PageImpl<>(list, pageable, page.getTotalElements());
        } else if (isItemAvailable != null) {
            Page<Item> page = itemRepository.findAllByIsAvailable(isItemAvailable.booleanValue(), pageable);
            List list = page
                    .map(item -> itemMapper.toResDto(item))
                    .stream()
                    .collect(Collectors.toList());
            return new PageImpl<>(list, pageable, page.getTotalElements());
        } else if (name != null && !name.isEmpty()) {
            Page<Item> page = itemRepository.findByNameLike("%" + name + "%", pageable);
            List list = page
                    .map(item -> itemMapper.toResDto(item))
                    .stream()
                    .collect(Collectors.toList());
            return new PageImpl<>(list, pageable, page.getTotalElements());
        } else {
            Page<Item> page = itemRepository.findAll(pageable);
            List list = page
                    .map(item -> itemMapper.toResDto(item))
                    .stream()
                    .collect(Collectors.toList());
            return new PageImpl<>(list, pageable, page.getTotalElements());
        }
    }

    @Override
    public List<ItemResDto> searchToList(String name, Boolean isItemAvailable) {
        if (name != null && !name.isEmpty() && isItemAvailable != null) {
            List<Item> page = itemRepository.findByNameLikeAndIsAvailable("%" + name + "%", isItemAvailable.booleanValue());
            List list = page
                    .stream()
                    .map(item -> itemMapper.toResDto(item))
                    .collect(Collectors.toList());
            return list;
        } else if (isItemAvailable != null) {
            List<Item> page = itemRepository.findAllByIsAvailable(isItemAvailable.booleanValue());
            List list = page
                    .stream()
                    .map(item -> itemMapper.toResDto(item))
                    .collect(Collectors.toList());
            return list;
        } else if (name != null && !name.isEmpty()) {
            List<Item> page = itemRepository.findByNameLike("%" + name + "%");
            List list = page
                    .stream()
                    .map(item -> itemMapper.toResDto(item))
                    .collect(Collectors.toList());
            return list;
        } else {
            List<Item> page = itemRepository.findAll();
            List list = page
                    .stream()
                    .map(item -> itemMapper.toResDto(item))
                    .collect(Collectors.toList());
            return list;
        }
    }
}
