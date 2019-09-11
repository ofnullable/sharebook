package org.slam.publicshare.book.dto.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.book.domain.Category;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveCategoryRequest {

    @NotBlank
    private String name;

    private SaveCategoryRequest(String name) {
        this.name = name;
    }

    public static SaveCategoryRequest of(String name) {
        return new SaveCategoryRequest(name);
    }

    public Category toEntity() {
        return Category.of(name);
    }

}
