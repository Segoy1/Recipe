package de.segoy.springframework.recipe.services;

import de.segoy.springframework.recipe.domain.Recipe;
import de.segoy.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {


    RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository repository) {
        this.recipeRepository = repository;
    }

    @Override
    public List<Recipe> getRecipesFromDb() {
        log.debug("Service Debug");
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional =  recipeRepository.findById(id);

        return recipeOptional.orElseThrow();
    }
}
