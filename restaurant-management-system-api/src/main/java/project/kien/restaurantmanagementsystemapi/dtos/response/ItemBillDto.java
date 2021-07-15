package project.kien.restaurantmanagementsystemapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.kien.restaurantmanagementsystemapi.dtos.common.ItemDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemBillDto {

    private Integer quantity;

    private ItemDto item;
    
}
