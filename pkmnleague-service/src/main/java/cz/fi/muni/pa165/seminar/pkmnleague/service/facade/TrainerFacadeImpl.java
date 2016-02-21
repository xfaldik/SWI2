package cz.fi.muni.pa165.seminar.pkmnleague.service.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.TrainerFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BadgeService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BeanMappingService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.GymService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Zuzana Goldmannova
 */
@Service
@Transactional
public class TrainerFacadeImpl implements TrainerFacade {

    @Inject
    private TrainerService trainerService;

    @Inject
    private GymService gymService;

    @Inject
    private BadgeService badgeService;

    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public int createTrainer(TrainerCreateDTO t) {
        String encodedPassword = passwordEncoder.encode(t.getPassword());
        Trainer newTrainer = new Trainer(t.getFullName(), t.getEmail(), encodedPassword, t.getDateOfBirth());
        trainerService.create(newTrainer);
        return newTrainer.getId();
    }

    @Override
    public void deleteTrainer(int trainerId) {
        trainerService.delete(trainerService.findById(trainerId));
    }

    @Override
    public List<TrainerDTO> getAllTrainers() {
        return beanMappingService.mapTo(trainerService.findAll(), TrainerDTO.class);
    }

    @Override
    public TrainerDTO getTrainerWithId(int id) {
        return beanMappingService.mapTo(trainerService.findById(id), TrainerDTO.class);
    }

    @Override
    public List<TrainerDTO> getTrainersByCountOfBages(int badgesCount) {
        List<Trainer> trainers = trainerService.findAll();
        List<Trainer> trainersBadges = new ArrayList<Trainer>();
        for (Trainer trainer : trainers) {
            if (badgeService.getAllBadgesByTrainer(trainer).size() == badgesCount) {
                trainersBadges.add(trainerService.findById(trainer.getId()));
            }
        }
        final List<TrainerDTO> dtot = beanMappingService.mapTo(trainersBadges, TrainerDTO.class);
        return dtot;
    }

    @Override
    public void changeDateOfBirth(Date newDateOfBirth, int trainerId) {
        Trainer mappedTrainer = beanMappingService.mapTo(trainerId, Trainer.class);
        mappedTrainer.setDateOfBirth((java.sql.Date) newDateOfBirth);
    }

    @Override
    public TrainerDTO getTrainerWithEmail(String email) {
        return beanMappingService.mapTo(trainerService.findByEmail(email), TrainerDTO.class);
    }

    @Override
    public List<TrainerDTO> getBadgeAbleTrainers(String name) {
        TrainerDTO gymTrainer = this.getTrainerWithEmail(name);
        List<Trainer> trainers = trainerService.findAll();
        List<Trainer> trainersBadges = new ArrayList<Trainer>();
        List<Trainer> trainersBadgesFinal = new ArrayList<Trainer>();
        List<Badge> badges = badgeService.findAll();

        for (Trainer trainer : trainers) {
            if (gymTrainer.getId() != trainer.getId()) {
                trainersBadges.add(trainerService.findById(trainer.getId()));
            }
        }

        for (Trainer trainerBadge : trainersBadges) {
            boolean hasBadge = false;
            for (Badge badge : badges) {
                if (badge.getTrainer().getId() == trainerBadge.getId() && badge.getGym().getId() == gymTrainer.getGym().getId()) {
                    hasBadge = true;
                }

            }
            if (hasBadge == false) {
                trainersBadgesFinal.add(trainerService.findById(trainerBadge.getId()));
            }

        }


        final List<TrainerDTO> dtot = beanMappingService.mapTo(trainersBadgesFinal, TrainerDTO.class);
        return dtot;
    }

}
