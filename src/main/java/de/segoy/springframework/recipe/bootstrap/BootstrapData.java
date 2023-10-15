package de.segoy.springframework.recipe.bootstrap;

import de.segoy.springframework.recipe.domain.*;
import de.segoy.springframework.recipe.repositories.CategoryRepository;
import de.segoy.springframework.recipe.repositories.RecipeRepository;
import de.segoy.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    public BootstrapData(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Recipe guacamole = new Recipe();

        guacamole.setDescription("Guacat");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setSource("simplyrecipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirection("Cut Avocados, Mash the avocado flesh, add remaining ingredients");

        UnitOfMeasure uOMTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure uOMunit = unitOfMeasureRepository.findByDescription("Unit").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();


        Ingredient avocado = new Ingredient();
        avocado.setAmount(new BigDecimal(2));
        avocado.setDescription("ripe Avocado");
        avocado.setUnitOfMeasure(uOMunit);
        avocado.setRecipe(guacamole);

        Ingredient salt = new Ingredient();
        salt.setDescription("kosher salt, plus more to taste");
        salt.setAmount(new BigDecimal(0.25));
        salt.setUnitOfMeasure(teaspoon);
        salt.setRecipe(guacamole);

        Ingredient lime = new Ingredient();
        lime.setAmount(new BigDecimal(1));
        lime.setDescription("fresh lime or lemon juice");
        lime.setUnitOfMeasure(uOMTablespoon);
        lime.setRecipe(guacamole);

        Ingredient onion = new Ingredient();
        onion.setDescription("minced red onion or thunly sliced green onion");
        onion.setAmount(new BigDecimal(2));
        onion.setUnitOfMeasure(uOMTablespoon);
        onion.setRecipe(guacamole);

        Ingredient chili = new Ingredient();
        chili.setDescription("serrano or jalapeno chilis, stems and seeds removed, minced");
        chili.setAmount(new BigDecimal(2));
        chili.setUnitOfMeasure(uOMunit);
        chili.setRecipe(guacamole);

        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("cilantro (leaves and tender stems), finely chopped");
        cilantro.setAmount(new BigDecimal(2));
        cilantro.setUnitOfMeasure(uOMTablespoon);
        cilantro.setRecipe(guacamole);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setDescription("freshly ground black pepper");
        blackPepper.setUnitOfMeasure(pinch);
        blackPepper.setAmount(new BigDecimal(1));
        blackPepper.setRecipe(guacamole);

        Ingredient tomato = new Ingredient();
        tomato.setAmount(new BigDecimal(0.5));
        tomato.setDescription("ripe tomato, chopped (optional)");
        tomato.setUnitOfMeasure(uOMunit);
        tomato.setRecipe(guacamole);

        Ingredient radish = new Ingredient();
        radish.setDescription("Red radish or jicama slices for garnish (optional)");
        radish.setAmount(new BigDecimal(1));
        radish.setUnitOfMeasure(uOMunit);
        radish.setRecipe(guacamole);

        guacamole.getIngredients().add(avocado);
        guacamole.getIngredients().add(salt);
        guacamole.getIngredients().add(lime);
//        guacamole.getIngredients().add(onion);
//        guacamole.getIngredients().add(chili);
//        guacamole.getIngredients().add(blackPepper);
//        guacamole.getIngredients().add(cilantro);
//        guacamole.getIngredients().add(tomato);
//        guacamole.getIngredients().add(radish);

        log.debug("Still no idead why i can't add ingredients that share a unit of measure");
        Notes notes = new Notes();
        notes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        notes.setRecipe(guacamole);
        guacamole.setNotes(notes);

        guacamole.getCategories().add(categoryRepository.findByDescription("Mexican").get());

        guacamole.setDifficulty(Difficulty.EASY);

        recipeRepository.save(guacamole);

    }
}
