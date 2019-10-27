package me.ofnullable.sharebook.book.utils;

import me.ofnullable.sharebook.book.domain.Category;
import me.ofnullable.sharebook.book.dto.category.SaveCategoryRequest;

import java.util.List;

public class CategoryUtils {

    public static Category buildCategory() {
        return Category.of("운영체제");
    }

    public static SaveCategoryRequest buildSaveCategoryRequest() {
        return SaveCategoryRequest.of("운영체제");
    }

    public static List<Category> buildCategoryList() {
        return List.of(buildCategory(), buildCategory(), buildCategory());
    }

}
