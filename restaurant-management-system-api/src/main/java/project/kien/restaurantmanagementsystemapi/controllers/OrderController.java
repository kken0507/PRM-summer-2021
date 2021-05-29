package project.kien.restaurantmanagementsystemapi.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kien.restaurantmanagementsystemapi.dtos.common.ErrorDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.OrderDetailReqDto;
import project.kien.restaurantmanagementsystemapi.services.OrderService;
import project.kien.restaurantmanagementsystemapi.utils.constants.ConstantUtil;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService service;

    @ApiOperation(value = "This API create new order from list of items' ids")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody(required = true) List<OrderDetailReqDto> items, int sessionId) {

        boolean bool = service.create(items, sessionId);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to create new order");

        return new ResponseEntity(bool ? ConstantUtil.CREATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
