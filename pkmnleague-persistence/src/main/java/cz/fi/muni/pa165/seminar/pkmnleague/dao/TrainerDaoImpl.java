package cz.fi.muni.pa165.seminar.pkmnleague.dao;


import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.utils.DaoLayerException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Pavel Kouřil
 */
@Repository
public class TrainerDaoImpl implements TrainerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Trainer findById(int id) {
        try {
            return entityManager.find(Trainer.class, id);
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public void save(Trainer trainer) {
        if (findById(trainer.getId()) != null) {
            try {
                entityManager.merge(trainer);
            } catch (Exception e) {
                throw new DaoLayerException(e.getMessage());
            }
        } else {
            try {
                entityManager.persist(trainer);
            } catch (Exception e) {
                throw new DaoLayerException(e.getMessage());
            }
        }
    }

    @Override
    public void delete(Trainer trainer) {
        try {
            entityManager.remove(findById(trainer.getId()));
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<Trainer> findAll() {
        try {
            return entityManager.createQuery("select t from Trainer t", Trainer.class).getResultList();
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public Trainer findByEmail(String email) {
        try {
            return entityManager.createQuery("select t from Trainer t where t.email = ?1", Trainer.class).setParameter(1, email).getSingleResult();
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

}
