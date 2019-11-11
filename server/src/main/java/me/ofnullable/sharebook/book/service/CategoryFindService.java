package me.ofnullable.sharebook.book.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.book.domain.Category;
import me.ofnullable.sharebook.book.repository.CategoryRepository;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryFindService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAllByDisplay(boolean display) {
        return categoryRepository.findAllByDisplayOrderByName(display);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findByIdAndDisplayIsTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Category.class));
    }

}
