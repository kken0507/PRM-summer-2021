package project.kien.restaurantmanagementsystemapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWithoutItemsResDto implements Serializable {

    private Integer id;

    @NotNull(message = "{category.name.notNull}")
    @Length(max = 50, message = "{category.name.length}")
    private String name;

}
