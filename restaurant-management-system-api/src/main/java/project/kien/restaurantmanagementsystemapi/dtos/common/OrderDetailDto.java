package project.kien.restaurantmanagementsystemapi.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    @NotNull(message = "{orderDetail.id.notNull}")
    private Integer id;

    private Integer quantity;

    private OrderDto customerOrder;

    private ItemDto item;
}
