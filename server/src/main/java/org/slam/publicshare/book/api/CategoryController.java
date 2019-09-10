package org.slam.publicshare.book.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.dto.category.CategoryResponse;
import org.slam.publicshare.book.dto.category.SaveCategoryRequest;
import org.slam.publicshare.book.service.CategoryFindService;
import org.slam.publicshare.book.service.CategorySaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFindService categoryFindService;
    private final CategorySaveService categorySaveService;

    @GetMapping("/categories")
    public List<CategoryResponse> findAll() {
        var display = true;
        return categoryFindService.findAllByDisplay(display).stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/category")
    public CategoryResponse save(@Valid SaveCategoryRequest dto) {
        return new CategoryResponse(categorySaveService.save(dto));
    }

}
