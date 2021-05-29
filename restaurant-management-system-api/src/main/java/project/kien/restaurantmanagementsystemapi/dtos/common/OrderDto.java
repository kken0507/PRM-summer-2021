package project.kien.restaurantmanagementsystemapi.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.kien.restaurantmanagementsystemapi.entities.Session;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @NotNull(message = "{order.id.notNull}")
    private Integer id;


    private Session session;

    private Set<OrderDetailDto> orderDetails;

    private Set<OrderStatusDto> orderStatus;
}
