//: guru.springframework.repositories.IUnitOfMeasureRepositoryIT.java


package guru.springframework.repositories;


import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


/*
 * @DataJpaTest can be used if want to test JPA applications.
 *
 *   - By default it will configure an in-memory embedded database, scan for
 *     @Entity classes and configure Spring Data JPA repositories
 *
 *   - Regular @Component beans will not be loaded into the ApplicationContext
 *
 *   - Data JPA tests are transactional and rollback at the end of each test by
 *     default
 *
 *     - To disable this feature, can disable transaction management for a test
 *       or for the whole class as follows:
 *
 *       @Transactional(propagation = Propagation.NOT_SUPPORTED)
 *
 *   - Data JPA tests may also inject a TestEntityManager bean which provides
 *     an alternative to the standard JPA EntityManager specifically designed
 *     for tests
 *
 *   - To run tests against a real database you can use the
 *     @AutoConfigureTestDatabase annotation:
 *
 *     @AutoConfigureTestDatabase(replace=Replace.NONE)
 */
@Ignore
@DataJpaTest
@RunWith(SpringRunner.class)
public class IUnitOfMeasureRepositoryIT {

    @Autowired
    private IUnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void should_Have_Teaspoon_UOM() throws Exception {

        // Given
        String uom = "Teaspoon";

        // When
        Optional<UnitOfMeasure> uomOptional =
                this.unitOfMeasureRepository.findByDescription(uom);

        // Then
        assertThat(uom, is(uomOptional.get().getDescription()));
    }

    @Test
    public void should_Have_Tablespoon_UOM() throws Exception {

        // Given
        String uom = "Tablespoon";

        // When
        Optional<UnitOfMeasure> uomOptional =
                this.unitOfMeasureRepository.findByDescription(uom);

        // Then
        assertThat(uom, is(uomOptional.get().getDescription()));
    }

}///:~