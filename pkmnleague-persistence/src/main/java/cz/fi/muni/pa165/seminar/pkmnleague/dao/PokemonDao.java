package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;

import java.util.List;

/**
 * Data Access Object providing access to database operations for Pokemon entity
 *
 * @author Pavel Kouřil
 */
public interface PokemonDao {

    /**
     * Finds Pokemon by given ID (primary key)
     */
    Pokemon findById(int id);

    /**
     * Saves Pokemon entity to database, can either update existing Pokemon or save new one
     */
    void save(Pokemon pokemon);

    /**
     * Deletes given entity from database
     */
    void delete(Pokemon pokemon);

    /**
     * Returns all existing Pokemon entities in database
     */
    List<Pokemon> findAll();

}
