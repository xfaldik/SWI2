package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
import cz.fi.muni.pa165.seminar.pkmnleague.utils.DaoLayerException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of PokemonDao interface.
 *
 * @author dhanak @domhanak on 10/28/15.
 */
@Repository
@Transactional
public class PokemonDaoImpl implements PokemonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pokemon findById(int id) {
        try {
            return entityManager.find(Pokemon.class, id);
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public void save(Pokemon pokemon) {
        if (findById(pokemon.getId()) != null) {
            try {
                entityManager.merge(pokemon);
            } catch (Exception e) {
                throw new DaoLayerException(e.getMessage());
            }
        } else {
            try {
                entityManager.persist(pokemon);
            } catch (Exception e) {
                throw new DaoLayerException(e.getMessage());
            }
        }
    }

    @Override
    public void delete(Pokemon pokemon) {
        try {
            entityManager.remove(findById(pokemon.getId()));
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<Pokemon> findAll() {
        try {
            return entityManager.createQuery("select p from Pokemon p", Pokemon.class).getResultList();
        } catch (Exception e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

}
