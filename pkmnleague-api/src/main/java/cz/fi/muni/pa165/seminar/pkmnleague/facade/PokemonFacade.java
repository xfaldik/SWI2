package cz.fi.muni.pa165.seminar.pkmnleague.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;

import java.util.List;

/**
 * An interface that defines a facade access to the Pokemon Service.
 *
 * @author Oldrich Faldik
 */
public interface PokemonFacade {

    /**
     * Create new Pokemon
     *
     * @param p PokemonCreateDTO - information about new Pokemon
     */
    void createPokemon(PokemonCreateDTO p);

    /**
     * Delete existing Pokemon
     *
     * @param pokemonId ID of Pokemon
     */
    void deletePokemon(int pokemonId);

    /**
     * Gets all existing Pokemons
     *
     * @return List of PokemonDTOs
     */
    List<PokemonDTO> getAllPokemons();

    /**
     * Gets existing Pokemon by ID
     *
     * @param id
     * @return PokemonDTO
     */
    PokemonDTO getPokemonWithId(int id);

    /**
     * Gets existing Pokemons by Trainer ID
     *
     * @param trainerId
     * @return List of PokemonDTOs
     */
    List<PokemonDTO> getPokemonsByTrainer(int trainerId);

    /**
     * Gets existing Pokemons by Primary Type
     *
     * @param type
     * @return List of PokemonDTOs
     */
    List<PokemonDTO> getPokemonsByPrimaryType(PokemonType type);

    /**
     * Gets existing Pokemons by Secondary Type
     *
     * @param type
     * @return List of PokemonDTOs
     */
    List<PokemonDTO> getPokemonsBySecondaryType(PokemonType type);

    /**
     * Gets existing Pokemons by Level
     *
     * @param level number of level
     * @return List of PokemonDTOs
     */
    List<PokemonDTO> getPokemonsByLevel(int level);

    /**
     * Gets existing Pokemons by Species Name
     *
     * @param speciesName
     * @return List of PokemonDTOs
     */
    List<PokemonDTO> getPokemonsBySpeciesName(String speciesName);

    /**
     * Change Species Name of existing Pokemon
     *
     * @param newSpeciesName
     * @param pokemonId
     */
    void changeSpeciesName(String newSpeciesName, int pokemonId);

    /**
     * Change Nick Name of existing Pokemon
     *
     * @param newNickName
     * @param pokemonId
     */
    void changeNickName(String newNickName, int pokemonId);

    /**
     * Change Level of existing Pokemon
     *
     * @param newLevel
     * @param pokemonId
     */
    void changeLevel(int newLevel, int pokemonId);

    /**
     * Change Trainer of existing Pokemon
     *
     * @param newTrainer
     * @param pokemonId
     */
    void changeTrainer(TrainerDTO newTrainer, int pokemonId);

    void levelUpPokemonWithId(int id);

}
