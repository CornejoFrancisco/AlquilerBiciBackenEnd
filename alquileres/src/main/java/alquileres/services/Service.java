package alquileres.services;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface Service<T, W> {
        void add(T entity);

        T getById(W id);

        List<T> getAll();

        void update(T entity);
}

