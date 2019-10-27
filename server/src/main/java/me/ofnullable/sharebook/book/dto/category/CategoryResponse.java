package me.ofnullable.sharebook.book.dto.category;

import lombok.Getter;
import me.ofnullable.sharebook.book.domain.Category;

@Getter
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

}
