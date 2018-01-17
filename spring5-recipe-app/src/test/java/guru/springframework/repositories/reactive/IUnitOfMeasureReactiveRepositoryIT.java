//: guru.springframework.repositories.reactive.IUnitOfMeasureReactiveRepositoryIT.java


package guru.springframework.repositories.reactive;


import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataMongoTest
public class IUnitOfMeasureReactiveRepositoryIT {

    @Autowired
    private IUnitOfMeasureReactiveRepository uomReactiveRepository;

    @Before
    public void setUp() {
        this.uomReactiveRepository.deleteAll().block();
    }

    @Test
    public void able_To_Save_New_UOM() {

        // Given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("teaspoon");

        this.uomReactiveRepository.save(uom).then().block();

        // When
        Long count = this.uomReactiveRepository.count().block();

        // Then
        assertThat(count, is(1L));
    }

}///:~