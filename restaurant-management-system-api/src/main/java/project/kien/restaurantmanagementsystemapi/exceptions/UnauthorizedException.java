package project.kien.restaurantmanagementsystemapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnauthorizedException extends RuntimeException
{
    private String message;
}