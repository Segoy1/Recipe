package de.segoy.springframework.recipe.services;

import de.segoy.springframework.recipe.converters.RecipeCommandToRecipe;
import de.segoy.springframework.recipe.converters.RecipeToRecipeCommand;
import de.segoy.springframework.recipe.domain.Recipe;
import de.segoy.springframework.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe commandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(recipeRepository);
        MockitoAnnotations.openMocks(commandToRecipe);
        MockitoAnnotations.openMocks(recipeToCommand);
        recipeService = new RecipeServiceImpl(recipeRepository, commandToRecipe, recipeToCommand);
    }

    @Test
    void getRecipesFromDb() {

        Recipe recipe = new Recipe();
        recipe.setDescription("Test");
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);

        List<Recipe> recipeSet = recipeService.getRecipesFromDb();

        assertEquals(1, recipeSet.size());
        assertEquals("Test", recipeService.getRecipesFromDb().get(0).getDescription());
        verify(recipeRepository, times(2)).findAll();
    }
}