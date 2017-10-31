//: guru.springframework.controllers.IndexController.java

package guru.springframework.controllers;


import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.ICategoryRepository;
import guru.springframework.repositories.IUnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
public class IndexController {

    private ICategoryRepository categoryRepository;
    private IUnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IndexController(ICategoryRepository categoryRepository,
                           IUnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional =
                this.categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional =
                this.unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category ID is " + categoryOptional.get().getId());
        System.out.println("UOM ID is " + unitOfMeasureOptional.get().getId());

        return "index";
    }

}///~