//: guru.springframework.bootstrap.RecipeBootstrap.java


package guru.springframework.bootstrap;


import guru.springframework.domain.*;
import guru.springframework.domain.builders.IngredientBuilder;
import guru.springframework.domain.builders.RecipeBuilder;
import guru.springframework.repositories.ICategoryRepository;
import guru.springframework.repositories.IIngredientRepository;
import guru.springframework.repositories.IRecipeRepository;
import guru.springframework.repositories.IUnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;


@Component
public class RecipeBootstrap implements
        ApplicationListener<ContextRefreshedEvent> {

    private final IRecipeRepository recipeRepository;
    private final ICategoryRepository categoryRepository;
    private final IIngredientRepository ingredientRepository;
    private final IUnitOfMeasureRepository unitOfMeasureRepository;

    private final RecipeBuilder recipeBuilder;
    private final IngredientBuilder ingredientBuilder;

    @Autowired
    public RecipeBootstrap(IRecipeRepository recipeRepository,
            ICategoryRepository categoryRepository,
            IIngredientRepository ingredientRepository,
            IUnitOfMeasureRepository unitOfMeasureRepository,
            RecipeBuilder recipeBuilder, IngredientBuilder ingredientBuilder) {

        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeBuilder = recipeBuilder;
        this.ingredientBuilder = ingredientBuilder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<Category> allCategories = this.categoryRepository.findAll();
        Map<String, Category> categoryMap = new HashMap<>();
        allCategories.forEach(category ->
                categoryMap.put(category.getDescription(), category));

        List<UnitOfMeasure> allUoms = this.unitOfMeasureRepository.findAll();
        Map<String, UnitOfMeasure> uomMap = new HashMap<>();
        allUoms.forEach(uom -> uomMap.put(uom.getDescription(), uom));

        this.recipeBuilder
                .setDescription("Perfect Guacamole")
                .setPrepTime(10)
                .setCookTime(30)
                .setServings(4)
                .setUrl("http://www.simplyrecipes.com/recipes/" +
                        "7_layer_bean_dip/")
                .setDirections("1 Cut avocado, remove flesh \n2 Mash with a " +
                        "fork \n3 Add salt, lime juice, and the rest \n4 Cover " +
                        "with plastic and chill to store Variations")
                .setDifficulty(Difficulty.MODERATE);

        Set<Category> categories = new HashSet<>();
        categories.add(categoryMap.get("Mexican"));
        this.recipeBuilder.setCategories(categories);

        Recipe perfectGuacamole = this.recipeBuilder.build();

        Set<Ingredient> ingredients = new HashSet<>();
        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("Ripe avocados")
                .setAmount(BigDecimal.valueOf(2))
                .setUom(uomMap.get("Each"))
                .setRecipe(perfectGuacamole);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("Kosher salt")
                .setAmount(BigDecimal.valueOf(0.5))
                .setUom(uomMap.get("Teaspoon"))
                .setRecipe(perfectGuacamole);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("Fresh lime juice or lemon " +
                    "juice")
                .setAmount(BigDecimal.valueOf(1))
                .setUom(uomMap.get("Tablespoon"))
                .setRecipe(perfectGuacamole);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("Minced red onion or thinly " +
                    "sliced green onion")
                .setAmount(BigDecimal.valueOf(2))
                .setUom(uomMap.get("Tablespoon"))
                .setRecipe(perfectGuacamole);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        perfectGuacamole.setIngredients(ingredients);

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 " +
                "cup of salsa and mix it in with your mashed avocados.\nFeel " +
                "free to experiment! One classic Mexican guacamole has " +
                "pomegranate seeds and chunks of peaches in it (a Diana " +
                "Kennedy favorite). Try guacamole with added pineapple, " +
                "mango, or strawberries.\nThe simplest version of guacamole " +
                "is just mashed avocados with salt. Don't let the lack of " +
                "availability of other ingredients stop you from making " +
                "guacamole.\nTo extend a limited supply of avocados, add " +
                "either sour cream or cottage cheese to your guacamole dip. " +
                "Purists may be horrified, but so what? It tastes great." +
                "\n\n\nRead more: http://www.simplyrecipes.com/recipes/" +
                "perfect_guacamole/#ixzz4jvoun5ws");
        guacNotes.setRecipe(perfectGuacamole);
        perfectGuacamole.setNotes(guacNotes);

        this.recipeRepository.save(perfectGuacamole);

        this.recipeBuilder.clear();
        this.recipeBuilder.setDescription("Spicy Grilled Chicken Tacos")
                .setPrepTime(20)
                .setCookTime(15)
                .setServings(6)
                .setUrl("http://www.simplyrecipes.com/recipes/" +
                        "spicy_grilled_chicken_tacos/")
                .setDirections("1 Prepare a gas or charcoal grill for " +
                        "medium-high, direct heat \n2 Make the marinade and " +
                        "coat the chicken \n3 Grill the chicken \n4 Warm the " +
                        "tortillas \n5 Assemble the tacos")
                .setDifficulty(Difficulty.HARD);

        categories = new HashSet<>();
        categories.add(categoryMap.get("Mexican"));
        this.recipeBuilder.setCategories(categories);

        Recipe chickenTacos = this.recipeBuilder.build();

        ingredients = new HashSet<>();
        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("Ancho chili powder")
                .setAmount(BigDecimal.valueOf(2))
                .setUom(uomMap.get("Tablespoon"))
                .setRecipe(chickenTacos);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("Dried oregano")
                .setAmount(BigDecimal.valueOf(1))
                .setUom(uomMap.get("Teaspoon"))
                .setRecipe(chickenTacos);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("Dried cumin")
                .setAmount(BigDecimal.valueOf(1))
                .setUom(uomMap.get("Teaspoon"))
                .setRecipe(chickenTacos);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("sugar")
                .setAmount(BigDecimal.valueOf(1))
                .setUom(uomMap.get("Teaspoon"))
                .setRecipe(chickenTacos);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("salt")
                .setAmount(BigDecimal.valueOf(0.5))
                .setUom(uomMap.get("Teaspoon"))
                .setRecipe(chickenTacos);
        ingredients.add(this.ingredientBuilder.build());

        this.ingredientBuilder.clear();
        this.ingredientBuilder.setDescription("Finely chopped clove garlic")
                .setAmount(BigDecimal.valueOf(1))
                .setUom(uomMap.get("Each"))
                .setRecipe(chickenTacos);
        ingredients.add(this.ingredientBuilder.build());

        chickenTacos.setIngredients(ingredients);

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: " +
                "Everything goes better in a tortilla.\nAny and every kind " +
                "of leftover can go inside a warm tortilla, usually with a " +
                "healthy dose of pickled jalapenos. I can always sniff out " +
                "a late-night snacker when the aroma of tortillas heating " +
                "in a hot pan on the stove comes wafting through the house." +
                "\nToday’s tacos are more purposeful – a deliberate meal " +
                "instead of a secretive midnight snack!\nFirst, I marinate " +
                "the chicken briefly in a spicy paste of ancho chile powder, " +
                "oregano, cumin, and sweet orange juice while the grill is " +
                "heating. You can also use this time to prepare the taco " +
                "toppings.\nGrill the chicken, then let it rest while you " +
                "warm the tortillas. Now you are ready to assemble the tacos " +
                "and dig in. The whole meal comes together in about 30 " +
                "minutes!\n\n\nRead more: http://www.simplyrecipes.com/" +
                "recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacoNotes.setRecipe(chickenTacos);
        chickenTacos.setNotes(tacoNotes);

        this.recipeRepository.save(chickenTacos);

    }// End of onApplicationEvent(...)

}///~