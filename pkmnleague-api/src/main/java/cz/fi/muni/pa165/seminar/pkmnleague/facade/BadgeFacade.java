package cz.fi.muni.pa165.seminar.pkmnleague.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeDTO;

import java.util.List;

/**
 * An interface that defines a facade access to the Badge Service.
 *
 * @author Oldrich Faldik
 */
public interface BadgeFacade {

    /**
     * Create new Badge belonging to Trainer
     *
     * @param b BadgeCreateDTO - information about new Badge
     */
    void createBadge(BadgeCreateDTO b);

    /**
     * Delete existing Badge
     *
     * @param badgeId ID of Bagde
     */
    void deleteBadge(int badgeId);

    /**
     * Gets all existing Badges
     *
     * @return List of BadgeDTOs
     */
    List<BadgeDTO> getAllBadges();

    /**
     * Gets existing Badge by ID
     *
     * @param id
     * @return BadgeDTO
     */
    BadgeDTO getBadgeWithId(int id);

    /**
     * Gets existing Badges by Trainer ID
     *
     * @param trainerId
     * @return List of BadgeDTOs
     */
    List<BadgeDTO> getBadgesByTrainer(int trainerId);

    /**
     * Gets existing Badges by Gym ID
     *
     * @param gymId
     * @return List of BadgeDTOs
     */
    List<BadgeDTO> getBadgesByGym(int gymId);

}
