//: guru.springframework.domain.builders.IModelBuilder.java


package guru.springframework.domain.builders;


public interface IModelBuilder<T> {
    T build();

    void clear();
}///:~