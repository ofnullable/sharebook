package me.ofnullable.sharebook.book.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.book.dto.category.SaveCategoryRequest;
import me.ofnullable.sharebook.book.service.CategoryFindService;
import me.ofnullable.sharebook.book.service.CategorySaveService;
import me.ofnullable.sharebook.error.ApiErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static me.ofnullable.sharebook.book.utils.CategoryUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryFindService categoryFindService;

    @Mock
    private CategorySaveService categorySaveService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(categoryController)
                .setControllerAdvice(ApiErrorHandler.class)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("전체 카테고리 조회")
    public void find_all_of_category() throws Exception {
        var categories = buildCategoryList();
        given(categoryFindService.findAllByDisplay(anyBoolean()))
                .willReturn(categories);

        mvc.perform(get("/categories"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 추가")
    public void save_category() throws Exception {
        var saveDto = buildSaveCategoryRequest();
        given(categorySaveService.save(any(SaveCategoryRequest.class)))
                .willReturn(buildCategory());

        mvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(saveDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("유효하지 않은 이름으로 카테고리 추가 - 400")
    public void save_category_invalid_name() throws Exception {
        mvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(SaveCategoryRequest.of(""))))
                .andExpect(status().isBadRequest());
    }

}
