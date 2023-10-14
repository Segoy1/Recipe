package de.segoy.springframework.recipe.controllers;

import de.segoy.springframework.recipe.domain.Category;
import de.segoy.springframework.recipe.domain.UnitOfMeasure;
import de.segoy.springframework.recipe.repositories.CategoryRepository;
import de.segoy.springframework.recipe.repositories.RecipeRepository;
import de.segoy.springframework.recipe.repositories.UnitOfMeasureRepository;
import de.segoy.springframework.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    public String getIndexPage(Model model){

        model.addAttribute("recipes", recipeService.getRecipesFromDb());

        System.out.println("1..2...3....4.....5");
        return "index";
    }
}
