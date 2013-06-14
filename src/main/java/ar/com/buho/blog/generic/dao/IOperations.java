package ar.com.buho.blog.generic.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface IOperations<T extends Serializable> {

    T findById(final long id);
    
    List<T> findAll();
    
    List<T> findAll(HashMap<String, String> options);

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

}
