//: guru.springframework.bootstrap.RecipeBootStrapMySQL.java


package guru.springframework.bootstrap;


import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.ICategoryRepository;
import guru.springframework.repositories.IUnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Supplier;


@Slf4j
@Component
@Profile({"dev", "prod"})
public class RecipeBootStrapMySQL implements
        ApplicationListener<ContextRefreshedEvent> {

    private final ICategoryRepository categoryRepository;
    private final IUnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public RecipeBootStrapMySQL(ICategoryRepository categoryRepository,
            IUnitOfMeasureRepository unitOfMeasureRepository) {

        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Check if categories are available
        if (this.categoryRepository.count() == 0) {
            this.loadCategories();
            this.log.info(">>>>> Category data has been loaded.");
        }

        // Check if unit of measures are available
        if (this.unitOfMeasureRepository.count() == 0) {
            this.loadUom();
            this.log.info(">>>>> Unit of measure data has been loaded.");
        }


    }// End of onApplicationEvent(ContextRefreshedEvent event)

    private void loadCategories() {
        String[] categoryNames = {
                "American", "Italian", "Mexican", "Fast Food",
        };
        Arrays.stream(categoryNames).forEach(s -> {
            this.persistCategory(Category::new, s);
        });
    }

    private void persistCategory(Supplier<Category> categorySupplier,
                                 String desc) {
        Category category = categorySupplier.get();
        category.setDescription(desc);
        this.categoryRepository.save(category);
    }

    private void loadUom() {
        String[] uoms = {
                "Teaspoon", "Tablespoon", "Cup", "Pinch", "Ounce", "Each",
                "Dash", "Pint",
        };
        Arrays.stream(uoms).forEach(s -> {
            this.persistUom(UnitOfMeasure::new, s);
        });
    }

    private void persistUom(Supplier<UnitOfMeasure> uomSupplier, String desc) {
        UnitOfMeasure uom = uomSupplier.get();
        uom.setDescription(desc);
        this.unitOfMeasureRepository.save(uom);
    }

}///~