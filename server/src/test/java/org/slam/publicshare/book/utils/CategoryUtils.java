package org.slam.publicshare.book.utils;

import org.slam.publicshare.book.domain.Category;
import org.slam.publicshare.book.dto.category.SaveCategoryRequest;

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
