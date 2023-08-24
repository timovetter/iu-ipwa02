import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Named
@ApplicationScoped
public class ApplicationService {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghost_net");

    public ApplicationService() {}

    public void addUser(User user) {
        EntityManager manager = emf.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(user);
        transaction.commit();

        manager.close();
    }
}
