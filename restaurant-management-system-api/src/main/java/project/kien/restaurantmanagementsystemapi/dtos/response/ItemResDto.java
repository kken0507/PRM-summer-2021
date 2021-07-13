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
public class ItemResDto implements Serializable {
    private Integer id;

    @NotNull(message = "{item.name.notNull}")
    @Length(max = 50, message = "{category.name.length}")
    private String name;

    @NotNull(message = "{item.price.notNull}")
    private Double price;

    @Length(max = 500, message = "{item.description.length}")
    private String description;

    @NotNull(message = "{item.isAvailable.notNull}")
    private Boolean isAvailable;

    @Length(max = 500, message = "{item.img.length}")
    private String img;

    private CategoryWithoutItemsResDto category;
}
