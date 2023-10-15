package de.segoy.springframework.recipe.services;

import de.segoy.springframework.recipe.commands.RecipeCommand;
import de.segoy.springframework.recipe.domain.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipesFromDb();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
