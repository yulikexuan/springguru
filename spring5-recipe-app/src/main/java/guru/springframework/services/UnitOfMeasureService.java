//: guru.springframework.services.UnitOfMeasureService.java


package guru.springframework.services;


import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.repositories.IUnitOfMeasureRepository;
import guru.springframework.repositories.reactive.IUnitOfMeasureReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UnitOfMeasureService implements IUnitOfMeasureService {

    private final IUnitOfMeasureReactiveRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand uomcConverter;

    @Autowired
    public UnitOfMeasureService(
            IUnitOfMeasureReactiveRepository unitOfMeasureRepository,
            UnitOfMeasureToUnitOfMeasureCommand uomcConverter) {

        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.uomcConverter = uomcConverter;
    }

    @Override
    public Flux<UnitOfMeasureCommand> findAllUnitOfMeasureCommands() {

        return this.unitOfMeasureRepository
                .findAll()
                .map(this.uomcConverter::convert);

//        return StreamSupport
//                .stream(this.unitOfMeasureRepository.findAll().spliterator(),
//                        false)
//                .map(this.uomcConverter::convert)
//                .collect(Collectors.toSet());
    }

}///~