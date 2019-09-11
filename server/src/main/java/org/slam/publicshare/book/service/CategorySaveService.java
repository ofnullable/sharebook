package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.Category;
import org.slam.publicshare.book.dto.category.SaveCategoryRequest;
import org.slam.publicshare.book.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategorySaveService {

    private final CategoryRepository categoryRepository;

    public Category save(SaveCategoryRequest dto) {
        return categoryRepository.save(dto.toEntity());
    }

}
