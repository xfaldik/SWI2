package cz.fi.muni.pa165.seminar.pkmnleague.service.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.BadgeFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BadgeService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BeanMappingService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.GymService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zuzana Goldmannová
 */
@Service
@Transactional
public class BadgeFacadeImpl implements BadgeFacade {

    @Inject
    private BadgeService badgeService;

    @Inject
    private GymService gymService;

    @Inject
    private TrainerService trainerService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createBadge(BadgeCreateDTO b) {
        Badge newBadge = new Badge(trainerService.findById(b.getTrainerId()), gymService.findById(b.getGymId()));
        badgeService.createBadge(newBadge);
    }

    @Override
    public void deleteBadge(int badgeId) {
        badgeService.delete(badgeService.findById(badgeId));
    }

    @Override
    public List<BadgeDTO> getAllBadges() {
        return beanMappingService.mapTo(badgeService.findAll(), BadgeDTO.class);
    }

    @Override
    public BadgeDTO getBadgeWithId(int id) {
        return beanMappingService.mapTo(badgeService.findById(id), BadgeDTO.class);
    }

    @Override
    public List<BadgeDTO> getBadgesByTrainer(int trainerId) {
        final List<Badge> badges = badgeService.getAllBadgesByTrainer(trainerService.findById(trainerId));
        final List<BadgeDTO> dtob = beanMappingService.mapTo(badges, BadgeDTO.class);
        return dtob;
    }

    @Override
    public List<BadgeDTO> getBadgesByGym(int gymId) {
        List<Badge> badges = badgeService.findAll();
        List<Badge> badgesByGym = new ArrayList<Badge>();
        for (Badge badge : badges) {
            if (badge.getGym().getId() == gymId) {
                badgesByGym.add(badge);
            }
        }

        final List<BadgeDTO> dtob = beanMappingService.mapTo(badgesByGym, BadgeDTO.class);
        return dtob;
    }

}
