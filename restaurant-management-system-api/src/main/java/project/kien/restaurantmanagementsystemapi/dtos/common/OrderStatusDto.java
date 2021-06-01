package project.kien.restaurantmanagementsystemapi.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import project.kien.restaurantmanagementsystemapi.enums.OrderEnum;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDto {
    @NotNull(message = "{orderStatus.id.notNull}")
    private Integer id;

    @NotNull(message = "{orderStatus.status.notNull}")
    @Length(max = 50, message = "{orderStatus.status.length}")
    private OrderEnum status;


    private OrderDto customerOrder;

    @Length(max = 500, message = "{orderStatus.content.length}")
    private String content;
}
