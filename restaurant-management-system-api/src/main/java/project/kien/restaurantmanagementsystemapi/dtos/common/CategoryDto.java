package project.kien.restaurantmanagementsystemapi.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {

    private Integer id;

    @NotNull(message = "{category.name.notNull}")
    @Length(max = 50, message = "{category.name.length}")
    private String name;

    private Set<ItemDto> items;

}
