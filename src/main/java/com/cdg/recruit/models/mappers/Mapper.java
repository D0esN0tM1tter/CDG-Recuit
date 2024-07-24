package com.cdg.recruit.models.mappers;

public interface Mapper<E , D > {
    public E maptoEntity(D  d) ;
    public D mapToDto(E e ) ;

}
