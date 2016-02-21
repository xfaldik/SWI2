package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.utils.DaoLayerException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of GymDao interface.
 *
 * @author Zuzana Goldmannova
 */
@Repository
public class GymDaoImpl implements GymDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Gym findById(int id) {
        try {
            return entityManager.find(Gym.class, id);
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public void save(Gym gym) {
        if (findById(gym.getId()) != null) {
            try {
                entityManager.merge(gym);
            } catch (Exception e) {
                throw new DaoLayerException(e.getMessage());
            }
        } else {
            try {
                entityManager.persist(gym);
            } catch (Exception e) {
                throw new DaoLayerException(e.getMessage());
            }
        }
    }

    @Override
    public void delete(Gym gym) {
        try {
            entityManager.remove(findById(gym.getId()));
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<Gym> findAll() {
        try {
            return entityManager.createQuery("select g from Gym g", Gym.class).getResultList();
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

}
