package cz.fi.muni.pa165.seminar.pkmnleague.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymEditDTO;

import java.util.List;

/**
 * An interface that defines a facade access to the Gym Service.
 *
 * @author Oldrich Faldik
 */
public interface GymFacade {

    /**
     * Create new Gym
     *
     * @param g GymCreateDTO - information about new Gym
     */
    void createGym(GymCreateDTO g);

    /**
     * Edit existing Gym
     *
     * @param g GymEditDTO - information about Gym
     */
    void editGym(GymEditDTO g);

    /**
     * Delete existing Gym
     *
     * @param gymId ID of Gym
     */
    void deleteGym(int gymId);

    /**
     * Gets all existing Gyms
     *
     * @return List of GymDTOs
     */
    List<GymDTO> getAllGyms();

    /**
     * Gets existing Gym by ID
     *
     * @param id
     * @return GymDTO
     */
    GymDTO getGymWithId(int id);

    /**
     * Change city of existing Gym
     *
     * @param newCity
     * @param gymId
     */
    void changeCity(String newCity, int gymId);

}
