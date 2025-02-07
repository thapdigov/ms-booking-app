package az.perfect.msbookingapp.mapper;
public interface Mapper<E, D> {

    E toEnt(D d);

    D toDto(E e);
}
