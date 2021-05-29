package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.*;
import project.kien.restaurantmanagementsystemapi.dtos.common.OrderDetailDto;
import project.kien.restaurantmanagementsystemapi.entities.OrderDetail;

import java.util.Set;

@Mapper(uses = {OrderMapper.class, ItemMapper.class}, componentModel = "spring")
public interface OrderDetailMapper {

    @Mappings({
            @Mapping(target = "order", qualifiedByName = "noOrderDetails"),
    })
    OrderDetailDto toDto(OrderDetail entity);

    @Named("noOrder")
    @Mappings({
            @Mapping(target = "order", ignore = true)

    })
    OrderDetailDto toDtoWithoutOrder(OrderDetail entity);

    @Named("noOrder")
    @IterableMapping(qualifiedByName = "noOrder")
    Set<OrderDetailDto> tolistDtoWithoutOrder(Set<OrderDetail> entity);

}
