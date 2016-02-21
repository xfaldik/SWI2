package cz.fi.muni.pa165.seminar.pkmnleague.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;

import java.util.Date;
import java.util.List;

/**
 * An interface that defines a facade access to the Trainer Service.
 *
 * @author Oldrich Faldik
 */
public interface TrainerFacade {

    /**
     * Create new Trainer
     *
     * @param t TrainerCreateDTO - information about new Trainer
     * @return ID of new Trainer.
     */
    int createTrainer(TrainerCreateDTO t);

    /**
     * Delete existing Trainer
     *
     * @param trainerId ID of Trainer
     */
    void deleteTrainer(int trainerId);

    /**
     * Gets all existing Trainers
     *
     * @return List of TrainerDTOs
     */
    List<TrainerDTO> getAllTrainers();

    /**
     * Gets Bagde Able Trainers
     *
     * @return List of TrainerDTOs
     */
    List<TrainerDTO> getBadgeAbleTrainers(String name);

    /**
     * Gets existing Trainer by ID
     *
     * @param id
     * @return TrainerDTO
     */
    TrainerDTO getTrainerWithId(int id);

    /**
     * Gets all existing Trainers, who have collected amount of Badges
     *
     * @param badgesCount
     * @return List of TrainerDTOs
     */
    List<TrainerDTO> getTrainersByCountOfBages(int badgesCount);

    void changeDateOfBirth(Date newDateOfBirth, int trainerId);

    TrainerDTO getTrainerWithEmail(String email);

}
