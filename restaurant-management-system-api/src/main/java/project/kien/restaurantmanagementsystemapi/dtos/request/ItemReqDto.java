package project.kien.restaurantmanagementsystemapi.dtos.request;

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
public class ItemReqDto implements Serializable {
    @NotNull(message = "{item.name.notNull}")
    @Length(max = 50, message = "{item.name.length}")
    private String name;

    @NotNull(message = "{item.price.notNull}")
    private Double price;

    @Length(max = 500, message = "{item.description.length}")
    private String description;

    @NotNull(message = "{item.isAvailable.notNull}")
    private Boolean isAvailable;

    private Integer categoryId;
}
