import javax.persistence.*;
import java.util.List;

public class GhostNetDAO {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghost_net");

    public void add (GhostNet ghostNet) {
        EntityManager manager = emf.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(ghostNet);
        transaction.commit();

        manager.close();
    }

    public List<GhostNet> findAll() {
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createQuery("SELECT g FROM GhostNet g");
        List<GhostNet> list = query.getResultList();
        manager.close();
        return list;
    }
}
