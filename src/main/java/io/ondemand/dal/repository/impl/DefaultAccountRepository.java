package io.ondemand.dal.repository.impl;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import io.ondemand.dal.model.Account;
import io.ondemand.dal.repository.AccountRepository;
import io.ondemand.dal.repository.exception.ValidationException;
import io.ondemand.dal.repository.util.ValidationIntent;
import org.apache.log4j.Logger;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;

import static com.avaje.ebean.Expr.eq;

/**
 * Repository implementation for AccountRepository
 * {@see AccountRepository}
 *
 * @author rvbiljouw
 */
public final class DefaultAccountRepository implements AccountRepository {
    private final EbeanServer server;

    @Inject
    public DefaultAccountRepository(EbeanServer server) {
        this.server = server;
    }

    @Override
    public Account getById(long id) {
        return server.find(Account.class).where(eq("id", id)).findUnique();
    }

    @Override
    public Account getByEmail(String email) {
        return server.find(Account.class).where(eq("email", email)).findUnique();
    }

    @Override
    public Account getByRealName(String firstName, String lastName) {
        return server.find(Account.class).where(eq("firstName", firstName))
                .where(eq("lastName", lastName)).findUnique();
    }

    @Override
    public void save(Account bean) {
        server.save(bean);
    }

    @Override
    public void update(Account bean) {
        server.update(bean);
    }

    @Override
    public void refresh(Account bean) {
        server.refresh(bean);
    }

    @Override
    public void remove(Account bean) {
        server.delete(bean);
    }

    @Override
    public void validate(final Account bean, final ValidationIntent intent) throws ValidationException {
        Map<String, String> errors = new HashMap<String, String>() {{
            switch (intent) {
                case CREATE:
                    if (getByEmail(bean.getEmail()) != null) {
                        put("email", "E-mail is already registered.");
                    }
                    break;

                case UPDATE:
                    if (getById(bean.getId()) == null) {
                        put("id", "Account to be updated does not exist.");
                    }
                    break;
            }
        }};

        if (errors.size() > 0) {
            throw new ValidationException(errors);
        }
    }

    @Override
    public Query<Account> createQuery() {
        return server.find(Account.class);
    }

    @Override
    public int count() {
        return server.find(Account.class).findRowCount();
    }
}
