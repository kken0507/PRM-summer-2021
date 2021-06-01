package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.*;
import project.kien.restaurantmanagementsystemapi.dtos.common.OrderStatusDto;
import project.kien.restaurantmanagementsystemapi.entities.OrderStatus;

import java.util.Set;

@Mapper(uses = {OrderMapper.class}, componentModel = "spring")
public interface OrderStatusMapper {

    @Mappings({
            @Mapping(target = "customerOrder", qualifiedByName = "noOrderStatus"),
    })
    OrderStatusDto toDto(OrderStatus entity);

    @Named("noOrder")
    @Mapping(target = "customerOrder", ignore = true)
    OrderStatusDto toDtoWithoutOrder(OrderStatus entity);

    @Named("noOrder")
    @IterableMapping(qualifiedByName = "noOrder")
    Set<OrderStatusDto> tolistDtoWithoutOrder(Set<OrderStatus> entity);
}
