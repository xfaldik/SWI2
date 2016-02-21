package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;

import java.util.List;

/**
 * Data Access Object providing access to database operations for Gym entity
 *
 * @author Oldrich Faldik
 */
public interface GymDao {

    /**
     * Finds and returns Gym by given ID (primary key)
     */
    Gym findById(int id);

    /**
     * Saves Gym entity to database, can either update existing Gym or save new one
     */
    void save(Gym gym);

    /**
     * Deletes given Gym entity from database
     */
    void delete(Gym gym);

    /**
     * Returns all Gym entitys from database
     */
    List<Gym> findAll();

}
