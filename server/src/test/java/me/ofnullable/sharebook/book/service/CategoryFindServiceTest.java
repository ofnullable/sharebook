package me.ofnullable.sharebook.book.service;

import me.ofnullable.sharebook.book.exception.NoSuchCategoryException;
import me.ofnullable.sharebook.book.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static me.ofnullable.sharebook.book.utils.CategoryUtils.buildCategory;
import static me.ofnullable.sharebook.book.utils.CategoryUtils.buildCategoryList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CategoryFindServiceTest {

    @InjectMocks
    private CategoryFindService categoryFindService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("전체 카테고리 요청")
    public void find_category_is_display() {
        given(categoryRepository.findAllByDisplayOrderByName(true))
                .willReturn(buildCategoryList());

        var result = categoryFindService.findAllByDisplay(true);

        assertEquals(result.size(), 3);
    }

    @Test
    @DisplayName("카테고리가 존재하는 경우")
    public void find_category_by_name() {
        given(categoryRepository.findByIdAndDisplayIsTrue(any(Long.class)))
                .willReturn(Optional.of(buildCategory()));

        var result = categoryFindService.findCategoryById(1L);

        assertEquals(result.getName(), "운영체제");
        assertFalse(result.isDisplay());
    }

    @Test
    @DisplayName("카테고리가 존재하지 않는 경우 - NoSuchCategoryException")
    public void find_category_by_invalid_name() {
        given(categoryRepository.findByIdAndDisplayIsTrue(any(Long.class)))
                .willReturn(Optional.empty());

        assertThrows(NoSuchCategoryException.class, () -> categoryFindService.findCategoryById(0L));
    }

}
