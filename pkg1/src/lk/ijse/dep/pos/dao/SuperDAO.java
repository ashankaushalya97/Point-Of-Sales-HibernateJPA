package lk.ijse.dep.pos.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;

public interface SuperDAO {

    void setEntityManager (EntityManager entityManager);
}
