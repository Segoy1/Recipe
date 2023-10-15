package de.segoy.springframework.recipe.services;

import de.segoy.springframework.recipe.commands.RecipeCommand;
import de.segoy.springframework.recipe.converters.RecipeCommandToRecipe;
import de.segoy.springframework.recipe.converters.RecipeToRecipeCommand;
import de.segoy.springframework.recipe.domain.Recipe;
import de.segoy.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {


    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe commandToRecipe;
    private final RecipeToRecipeCommand recipeToCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe commandToRecipe, RecipeToRecipeCommand recipeToCommand) {
        this.recipeRepository = recipeRepository;
        this.commandToRecipe = commandToRecipe;
        this.recipeToCommand = recipeToCommand;
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

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command){
        Recipe detachedRecipe = commandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe"+ savedRecipe.getId());
        return recipeToCommand.convert(savedRecipe);
    }
}
