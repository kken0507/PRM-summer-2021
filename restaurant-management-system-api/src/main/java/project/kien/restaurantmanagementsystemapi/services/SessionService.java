package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;

public interface SessionService {
    OpenSessionResponseDto openSession(OpenSessionRequestDto request);
}
