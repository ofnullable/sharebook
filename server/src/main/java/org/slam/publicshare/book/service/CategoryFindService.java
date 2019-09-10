package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.Category;
import org.slam.publicshare.book.exception.NoSuchCategoryException;
import org.slam.publicshare.book.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryFindService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAllByDisplay(boolean display) {
        return categoryRepository.findAllByDisplayOrderByName(display);
    }

    public Category findByName(String name) {
        return categoryRepository.findByNameAndDisplayIsTrue(name)
                .orElseThrow(() -> new NoSuchCategoryException(name));
    }

}
