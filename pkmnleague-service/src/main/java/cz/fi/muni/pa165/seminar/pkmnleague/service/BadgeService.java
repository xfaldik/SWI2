package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;

import java.util.List;

/**
 * An interface that defines a service access to the {@link Badge} entity.
 *
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
public interface BadgeService {

    void createBadge(Badge badge);

    Badge findById(int id);

    List<Badge> findAll();

    void delete(Badge gym);

    /**
     * Gets all Badges belonging to Trainer
     *
     * @param trainer Trainer whose badges we want
     * @return List of all Trainers badges.
     */
    List<Badge> getAllBadgesByTrainer(Trainer trainer);

}
