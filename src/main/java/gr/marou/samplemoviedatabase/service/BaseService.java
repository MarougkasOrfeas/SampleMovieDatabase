package gr.marou.samplemoviedatabase.service;

import java.util.List;

/**
 * Interface representing a base service providing common operations for services in the Sample Movie Database (SMDB) application.
 *
 * @param <T> the type of the entity managed by this service
 * @param <D> the type of the DTO used by this service
 * @param <K> the type of the entity's identifier
 */
public interface BaseService<T, D, K>{

    T create (final D dto);

    List<T> createAll(final List<D> dtos);

    List<D> read();

    D read (K id);

    void update(D dto);

    void deleteById(K id);

    boolean exists(T entity);

}
