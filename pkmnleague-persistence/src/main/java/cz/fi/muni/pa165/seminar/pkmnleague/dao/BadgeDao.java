package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;

import java.util.List;

/**
 * Data Access Object providing access to database operations for Badge entity
 *
 * @author Zuzana Goldmannova
 */
public interface BadgeDao {

    /**
     * Finds and returns Badge by given ID (primary key)
     */
    public Badge findById(int id);

    /**
     * Saves Badge entity to database, can either update existing Badge or save new one
     */
    public void save(Badge badge);

    /**
     * Deletes given Badge entity from database
     */
    public void delete(Badge badge);

    /**
     * Returns all Badge entities from database
     */
    public List<Badge> findAll();

}
