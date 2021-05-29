package project.kien.restaurantmanagementsystemapi.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailReqDto {
    @NotNull(message = "{orderDetail.itemId.notNull}")
    private Integer itemId;

    @NotNull(message = "{orderDetail.quantity.notNull}")
    @Min(value = 1, message = "{orderDetail.quantity.min}")
    @Max(value = 200, message = "{orderDetail.quantity.max}")
    private Integer quantity;
}
