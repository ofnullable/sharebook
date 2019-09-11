package org.slam.publicshare.book.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.book.domain.Category;
import org.slam.publicshare.book.dto.category.SaveCategoryRequest;
import org.slam.publicshare.book.repository.CategoryRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.book.utils.CategoryUtils.buildCategory;


@ExtendWith(SpringExtension.class)
public class CategorySaveServiceTest {

    @InjectMocks
    private CategorySaveService categorySaveService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 등록")
    public void save_category() {
        var category = buildCategory();
        given(categoryRepository.save(any(Category.class)))
                .willReturn(category);

        var result = categorySaveService.save(SaveCategoryRequest.of("운영체제"));

        assertEquals(result.getName(), category.getName());
    }

}
