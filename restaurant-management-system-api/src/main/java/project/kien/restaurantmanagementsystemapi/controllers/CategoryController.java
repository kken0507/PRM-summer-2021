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
import project.kien.restaurantmanagementsystemapi.dtos.request.CategoryReqDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.CategoryResDto;
import project.kien.restaurantmanagementsystemapi.services.CategoryService;
import project.kien.restaurantmanagementsystemapi.utils.ApiPageable;
import project.kien.restaurantmanagementsystemapi.utils.constants.ConstantUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("/all")
    public List<CategoryResDto> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "This API create new category")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody(required = true) CategoryReqDto newCategory) {

        boolean bool = service.create(newCategory);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to create new category");

        return new ResponseEntity(bool ? ConstantUtil.CREATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "This API update a category by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<?> create(@NotNull @PathVariable("categoryId") int id, @Valid @RequestBody(required = true) CategoryReqDto category) {

        boolean bool = service.update(id, category);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to update the category");

        return new ResponseEntity(bool ? ConstantUtil.UPDATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "This API update a category by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/delete/{categoryId}")
    public ResponseEntity<?> delete(@NotNull @PathVariable("categoryId") int id) {

        boolean bool = service.delete(id);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to delete the category");

        return new ResponseEntity(bool ? ConstantUtil.DELETE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "This API search a category contains a name and filter by items status")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @GetMapping("/search")
    @ApiPageable
    public Page<CategoryResDto> search(@RequestParam(required = false, value = "name") String name, @RequestParam(required = false, value = "isItemsAvailable") Boolean isItemsAvailable, Pageable pageable) {
        return service.search(name, isItemsAvailable, pageable);
    }

}
