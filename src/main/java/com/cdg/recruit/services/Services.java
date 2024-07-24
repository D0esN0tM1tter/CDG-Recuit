package com.cdg.recruit.services;

import java.util.List;
import java.util.Optional;

public interface Services <T , I> {

    public T create(T dto) ;
    public Optional<T> findOne(I id) ;
    public List<T> findAll() ;
    public Optional<T> update(I id , T dto ) ;
    public Optional<T> delete(I id) ;

}
