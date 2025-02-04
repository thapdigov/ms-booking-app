package az.perfect.msbookingapp.mapper;

public interface Mapper<E,T>{

    E toEnt(T t);
    T toDto(E e);
}
