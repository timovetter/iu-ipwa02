package dao;

import entities.User;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghost_net");

    public void add(User user) {
        EntityManager manager = emf.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(user);
        transaction.commit();

        manager.close();
    }

    public User findWithUsername(String username) throws NoResultException {
        Map<String, String> filter = new HashMap<>();
        filter.put("username", username);

        String sqlStatement = this.buildSQL(filter);
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createQuery(sqlStatement);
        return (User) query.getSingleResult();
    }

    public User findWithUsernameAndPassword(String username, String password) throws NoResultException {
        Map<String, String> filter = new HashMap<>();
        filter.put("username", username);
        filter.put("password", password);

        String sqlStatement = this.buildSQL(filter);
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createQuery(sqlStatement);
        return (User) query.getSingleResult();
    }


    private String buildSQL(Map<String, String> filter) {
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT u FROM entities.User u");
        boolean addAND = false;
        for (var entry : filter.entrySet()) {
            if (addAND) sqlStatement.append(" AND ");
            else sqlStatement.append(" WHERE ");

            sqlStatement.append(entry.getKey());
            sqlStatement.append("='");
            sqlStatement.append(entry.getValue());
            sqlStatement.append("'");

            addAND = true;
        }
        return sqlStatement.toString();
    }
}
