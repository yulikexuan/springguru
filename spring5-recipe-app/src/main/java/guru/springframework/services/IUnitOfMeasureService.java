//: guru.springframework.services.IUnitOfMeasureService.java


package guru.springframework.services;


import guru.springframework.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;


public interface IUnitOfMeasureService {

    Flux<UnitOfMeasureCommand> findAllUnitOfMeasureCommands();

}///:~