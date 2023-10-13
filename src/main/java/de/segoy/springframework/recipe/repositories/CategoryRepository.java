package de.segoy.springframework.recipe.repositories;

import de.segoy.springframework.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
