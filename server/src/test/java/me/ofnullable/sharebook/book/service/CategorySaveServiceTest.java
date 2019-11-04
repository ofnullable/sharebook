package me.ofnullable.sharebook.book.service;

import me.ofnullable.sharebook.book.domain.Category;
import me.ofnullable.sharebook.book.dto.category.SaveCategoryRequest;
import me.ofnullable.sharebook.book.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static me.ofnullable.sharebook.book.utils.CategoryUtils.buildCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class CategorySaveServiceTest {

    @InjectMocks
    private CategorySaveService categorySaveService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 등록")
    void save_category() {
        var category = buildCategory();
        given(categoryRepository.save(any(Category.class)))
                .willReturn(category);

        var result = categorySaveService.save(SaveCategoryRequest.of("운영체제"));

        assertEquals(result.getName(), category.getName());
    }

}
