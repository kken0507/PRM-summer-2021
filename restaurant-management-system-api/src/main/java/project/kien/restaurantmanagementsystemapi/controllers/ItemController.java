package project.kien.restaurantmanagementsystemapi.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.kien.restaurantmanagementsystemapi.dtos.common.ErrorDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.ItemReqDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.ItemResDto;
import project.kien.restaurantmanagementsystemapi.services.ItemService;
import project.kien.restaurantmanagementsystemapi.utils.ApiPageable;
import project.kien.restaurantmanagementsystemapi.utils.constants.ConstantUtil;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService service;

    @GetMapping("/all")
    public List<ItemResDto> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "This API create new item")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody(required = true) ItemReqDto newItem) {

        boolean bool = service.create(newItem);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to create new item");

        return new ResponseEntity(bool ? ConstantUtil.CREATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "This API update a item by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/update/{itemId}")
    public ResponseEntity<?> update(@NotNull @PathVariable("itemId") int id, @RequestBody(required = true) ItemReqDto item) {

        boolean bool = service.update(id, item);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to update the item");

        return new ResponseEntity(bool ? ConstantUtil.UPDATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "This API update a item by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/delete/{itemId}")
    public ResponseEntity<?> delete(@NotNull @PathVariable("itemId") int id) {

        boolean bool = service.delete(id);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to delete the item");

        return new ResponseEntity(bool ? ConstantUtil.DELETE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "This API search a item contains a name and filter by status")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @GetMapping("/search")
    @ApiPageable
    public Page<ItemResDto> search(@RequestParam(required = false, value = "name") String name, @RequestParam(required = false, value = "isAvailable") Boolean isAvailable, @ApiIgnore("Ignored because swagger ui shows the wrong params") Pageable pageable) {
        return service.search(name, isAvailable, pageable);
    }

    @GetMapping("/searchToList")
    public List<ItemResDto> searchToList(@RequestParam(required = false, value = "name") String name, @RequestParam(required = false, value = "isAvailable") Boolean isAvailable) {
        return service.searchToList(name, isAvailable);
    }

}
