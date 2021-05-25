package project.kien.restaurantmanagementsystemapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* Is thrown when an invalid request is received
*   eg: request to deactivate an already inactive account
* */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidRequestException extends RuntimeException
{
    private String message;
}
