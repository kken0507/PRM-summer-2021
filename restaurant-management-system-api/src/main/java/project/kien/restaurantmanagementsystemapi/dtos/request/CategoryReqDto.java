package project.kien.restaurantmanagementsystemapi.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryReqDto {
    @NotNull(message = "{category.name.notNull}")
    @Length(max = 50, message = "{category.name.length}")
    private String name;
}
