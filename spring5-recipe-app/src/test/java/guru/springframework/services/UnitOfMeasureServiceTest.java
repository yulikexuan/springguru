//: guru.springframework.services.UnitOfMeasureServiceTest.java


package guru.springframework.services;


import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.IUnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


public class UnitOfMeasureServiceTest {

    @Mock
    private IUnitOfMeasureRepository unitOfMeasureRepository;

    private UnitOfMeasureToUnitOfMeasureCommand uomcConverter;

    private UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        this.uomcConverter = new UnitOfMeasureToUnitOfMeasureCommand();
        MockitoAnnotations.initMocks(this);
        this.unitOfMeasureService = new UnitOfMeasureService(
                this.unitOfMeasureRepository, this.uomcConverter);
    }

    @Test
    public void findAll() throws Exception {

        // Given
        List<UnitOfMeasure> unitOfMeasures = new ArrayList<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        uom1.setDescription(UUID.randomUUID().toString());

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        uom2.setDescription(UUID.randomUUID().toString());

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription(UUID.randomUUID().toString());
        uom3.setId(3L);

        unitOfMeasures.add(uom1);
        unitOfMeasures.add(uom2);
        unitOfMeasures.add(uom3);

        when(this.unitOfMeasureRepository.findAll())
                .thenReturn(unitOfMeasures);

        // When
        Set<UnitOfMeasureCommand> commands = this.unitOfMeasureService
                .findAllUnitOfMeasureCommands();

        // Then
        assertThat(commands.size(), is(3));
    }

}///:~