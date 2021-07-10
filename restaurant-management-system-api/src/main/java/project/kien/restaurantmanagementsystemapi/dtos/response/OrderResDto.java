package project.kien.restaurantmanagementsystemapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.kien.restaurantmanagementsystemapi.dtos.common.AuditDto;
import project.kien.restaurantmanagementsystemapi.dtos.common.OrderDetailDto;
import project.kien.restaurantmanagementsystemapi.dtos.common.OrderStatusDto;
import project.kien.restaurantmanagementsystemapi.entities.Session;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResDto extends AuditDto {

    @NotNull(message = "{order.id.notNull}")
    private Integer id;

    private Session session;

    private Set<OrderDetailDto> orderDetails;

    private Set<OrderStatusDto> orderStatus;

    private OrderStatusDto curOrderStatus;
}
