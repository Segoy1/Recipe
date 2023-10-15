package de.segoy.springframework.recipe.controllers;

import de.segoy.springframework.recipe.domain.Recipe;
import de.segoy.springframework.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(recipeService);
        indexController = new IndexController(recipeService);
        MockitoAnnotations.initMocks(model);
    }

    @Test
    void getIndexPage() {
        Recipe recipe = new Recipe();
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        when(recipeService.getRecipesFromDb()).thenReturn(recipes);
//        when(model.addAttribute(recipes)).thenReturn(model);

        assertEquals("index", indexController.getIndexPage(model));
        verify(recipeService, times(1)).getRecipesFromDb();
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());

    }
}