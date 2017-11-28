//: guru.springframework.services.IUnitOfMeasureService.java


package guru.springframework.services;


import guru.springframework.commands.UnitOfMeasureCommand;


import java.util.Set;


public interface IUnitOfMeasureService {

    Set<UnitOfMeasureCommand> findAllUnitOfMeasureCommands();

}///:~