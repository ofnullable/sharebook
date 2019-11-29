package me.ofnullable.sharebook.book.api;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.book.dto.category.CategoryResponse;
import me.ofnullable.sharebook.book.dto.category.SaveCategoryRequest;
import me.ofnullable.sharebook.book.service.CategoryFindService;
import me.ofnullable.sharebook.book.service.CategorySaveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFindService categoryFindService;
    private final CategorySaveService categorySaveService;

    @GetMapping("/categories")
    public List<CategoryResponse> findAllCategory() {
        var display = true;
        return categoryFindService.findAllByDisplay(display).stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse saveCategory(@RequestBody @Valid SaveCategoryRequest dto) {
        return new CategoryResponse(categorySaveService.save(dto));
    }

}
