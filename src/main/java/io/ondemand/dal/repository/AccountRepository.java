package io.ondemand.dal.repository;

import io.ondemand.dal.model.Account;

/**
 * Functions for accessing accounts in the database.
 *
 * @author rvbiljouw
 */
public interface AccountRepository extends Repository<Account> {

    /**
     * Fetches an account by it's id
     *
     * @param id id
     * @return account or null if not found
     */
    Account getById(long id);

    /**
     * Fetches an account by it's email address
     *
     * @param email email address
     * @return account or null if not found
     */
    Account getByEmail(String email);

    /**
     * Fetches an account by it's real name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return account or null if not found
     */
    Account getByRealName(String firstName, String lastName);

}
