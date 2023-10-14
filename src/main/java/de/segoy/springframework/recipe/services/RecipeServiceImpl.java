package de.segoy.springframework.recipe.services;

import de.segoy.springframework.recipe.domain.Recipe;
import de.segoy.springframework.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {


    RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository repository) {
        this.recipeRepository = repository;
    }

    @Override
    public List<Recipe> getRecipesFromDb() {
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }
}
