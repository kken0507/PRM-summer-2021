package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import project.kien.restaurantmanagementsystemapi.dtos.common.SessionDto;
import project.kien.restaurantmanagementsystemapi.entities.Session;

@Mapper(uses = {OrderMapper.class, OrderStatusMapper.class, OrderDetailMapper.class}, componentModel = "spring")
public interface SessionMapper {

    @Mappings({
            @Mapping(target = "orders", qualifiedByName = "noSession"),
    })
    SessionDto toDto(Session entity);


}
