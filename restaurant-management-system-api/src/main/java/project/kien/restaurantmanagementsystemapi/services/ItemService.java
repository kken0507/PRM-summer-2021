package project.kien.restaurantmanagementsystemapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.kien.restaurantmanagementsystemapi.dtos.request.ItemReqDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.ItemResDto;

import java.util.List;

public interface ItemService {
    List<ItemResDto> findAll();

    boolean create(ItemReqDto dto);

    boolean update(Integer itemId, ItemReqDto dto);

    boolean delete(Integer itemId);

    Page<ItemResDto> search(String name, Boolean isItemAvailable, Pageable pageable);
}
