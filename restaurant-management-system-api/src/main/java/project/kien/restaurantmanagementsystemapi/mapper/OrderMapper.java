package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.*;
import project.kien.restaurantmanagementsystemapi.dtos.common.OrderDto;
import project.kien.restaurantmanagementsystemapi.entities.Order;

import java.util.Set;

@Mapper(uses = {OrderDetailMapper.class, OrderStatusMapper.class}, componentModel = "spring")
public interface OrderMapper {


    @Mappings({
            @Mapping(target = "orderDetails", qualifiedByName = "noOrder"),
            @Mapping(target = "orderStatus", qualifiedByName = "noOrder"),
    })
    OrderDto toDto(Order entity);

    @Named("noOrderSession")
    @Mappings({
            @Mapping(target = "orderDetails", qualifiedByName = "noOrder"),
            @Mapping(target = "orderStatus", qualifiedByName = "noOrder"),
            @Mapping(target = "session", ignore = true),
    })
    OrderDto toDtoWithoutOrderAndSession(Order entity);

    @Named("noSession")
    @IterableMapping(qualifiedByName = "noOrderSession")
    Set<OrderDto> tolistDtoWithoutOrderAndSession(Set<Order> entity);

    @Named("noOrderDetails")
    @Mappings({
            @Mapping(target = "orderDetails", ignore = true),
    })
    OrderDto toDtoWithoutOrderDetail(Order entity);

    @Named("noOrderStatus")
    @Mappings({
            @Mapping(target = "orderStatus", ignore = true),
    })
    OrderDto toDtoWithoutOrderStatus(Order entity);

}
