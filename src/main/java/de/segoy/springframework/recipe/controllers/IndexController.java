package de.segoy.springframework.recipe.controllers;

import de.segoy.springframework.recipe.domain.Category;
import de.segoy.springframework.recipe.domain.UnitOfMeasure;
import de.segoy.springframework.recipe.repositories.CategoryRepository;
import de.segoy.springframework.recipe.repositories.RecipeRepository;
import de.segoy.springframework.recipe.repositories.UnitOfMeasureRepository;
import de.segoy.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    public String getIndexPage(Model model){

        model.addAttribute("recipes", recipeService.getRecipesFromDb());

        log.debug("getting index Page");
        return "index";
    }
}
