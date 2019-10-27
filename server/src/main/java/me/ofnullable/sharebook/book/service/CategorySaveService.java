package me.ofnullable.sharebook.book.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.book.domain.Category;
import me.ofnullable.sharebook.book.dto.category.SaveCategoryRequest;
import me.ofnullable.sharebook.book.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategorySaveService {

    private final CategoryRepository categoryRepository;

    public Category save(SaveCategoryRequest dto) {
        return categoryRepository.save(dto.toEntity());
    }

}
