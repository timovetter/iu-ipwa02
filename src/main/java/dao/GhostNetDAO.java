package dao;

import entities.GhostNet;

import javax.persistence.*;
import java.util.List;

public class GhostNetDAO {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghost_net");

    public void add (GhostNet ghostNet) {
        EntityManager manager = emf.createEntityManager();
        EntityTransaction t = manager.getTransaction();

        t.begin();
        manager.persist(ghostNet);
        t.commit();

        manager.close();
    }

    public List<GhostNet> findAll() {
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createQuery("SELECT g FROM GhostNet g");
        List<GhostNet> list = query.getResultList();
        manager.close();
        return list;
    }

    public void update(GhostNet ghostNet) {
        EntityManager manager = emf.createEntityManager();
        EntityTransaction t = manager.getTransaction();

        t.begin();
        manager.merge(ghostNet);
        t.commit();

        manager.close();
    }
}
