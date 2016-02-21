package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;

import java.util.List;

/**
 * Dao interface for Pokemon entity.
 *
 * @author dhanak @domhanak on 10/28/15.
 */
public interface TrainerDao {

    /**
     * Find trainer based on its id.
     *
     * @param id of the Trainer instance.
     */
    Trainer findById(int id);

    /**
     * Creates or updates corresponding trainer
     *
     * @param trainer instance of Trainer to create or update.
     */
    void save(Trainer trainer);

    /**
     * Deletes provided instance of Trainer.
     *
     * @param trainer instance of Trainer to delete.
     */
    void delete(Trainer trainer);

    /**
     * Find all instances of Trainer.
     *
     * @return List of Trainer instances.
     */
    List<Trainer> findAll();

    Trainer findByEmail(String email);

}
