package io.ondemand.dal.repository;

import com.avaje.ebean.Query;
import io.ondemand.dal.repository.exception.ValidationException;
import io.ondemand.dal.repository.util.ValidationIntent;

/**
 * Interface for repository functions
 *
 * @author rvbiljouw
 */
public interface Repository<T> {

    /**
     * Persists a bean to the database
     *
     * @param bean bean
     */
    void save(T bean);

    /**
     * Updates a bean in the database
     *
     * @param bean bean
     */
    void update(T bean);

    /**
     * Refreshes a bean object with information from the database
     *
     * @param bean live bean
     */
    void refresh(T bean);

    /**
     * Removes a bean from the database
     *
     * @param bean live bean
     */
    void remove(T bean);

    /**
     * Validates a bean to see if it matches contract.
     *
     * @param bean bean
     */
    void validate(T bean, ValidationIntent intent) throws ValidationException;

    /**
     * Create a query object
     *
     * @return query
     */
    Query<T> createQuery();

    /**
     * Get the total object count in the repository
     *
     * @return object count
     */
    int count();

}
