package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.dao.GymDao;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.exceptions.PokemonLeagueServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@Service
public class GymServiceImpl implements GymService {

    @Inject
    private GymDao gymDao;

    @Override
    public void create(Gym gym) {
        gymDao.save(gym);
    }

    @Override
    public Gym findById(int id) {
        return gymDao.findById(id);
    }

    @Override
    public List<Gym> findAll() {
        return gymDao.findAll();
    }

    @Override
    public void delete(Gym gym) {
        gymDao.delete(gym);
    }

    @Override
    public Badge awardBadgeToTrainer(Gym gym, Trainer trainer) {
        if (gym == null) {
            throw new IllegalArgumentException("Gym can't be null");
        }
        if (trainer == null) {
            throw new IllegalArgumentException("Trainer can't be null");
        }
        if (gym.getLeader().equals(trainer)) {
            throw new PokemonLeagueServiceException("You can't award badge to a gym leader.");
        }
        for (Badge b : trainer.getBadges()) {
            if (b.getGym().equals(gym)) {
                throw new PokemonLeagueServiceException("Trainer already has badge from this gym.");
            }
        }

        return new Badge(trainer, gym);
    }

    @Override
    public void update(Gym gym, String city, PokemonType type) {
        gym.setCity(city);
        gym.setType(type);
        gymDao.save(gym);
    }

}
