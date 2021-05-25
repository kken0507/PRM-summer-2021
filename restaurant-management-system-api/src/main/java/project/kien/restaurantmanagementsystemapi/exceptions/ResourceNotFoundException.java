package project.kien.restaurantmanagementsystemapi.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;

    private String message;

}
