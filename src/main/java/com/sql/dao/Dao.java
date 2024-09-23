package com.sql.dao;


import java.util.List;
import java.util.Optional;

public interface Dao<K,E> {

    List<E> findAll();

    void save(E entity);

    void update(E entity);

    void deleteById(K id);

    Optional<E> findById(K id);


}
 //   void  join(E entity); Не стандартный запрос, а реализация его тут обязывает использрвать его в другом красе,
    //   который имплементирован отсюда

