package az.perfect.msbookingapp.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<E,T>{

    E toEnt(T t);
    T toDto(E e);
}
