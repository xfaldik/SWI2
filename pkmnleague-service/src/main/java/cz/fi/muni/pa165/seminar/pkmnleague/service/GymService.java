package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;

import java.util.List;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
public interface GymService {

    void create(Gym gym);

    void update(Gym gym, String city, PokemonType type);

    Gym findById(int id);

    List<Gym> findAll();

    void delete(Gym gym);

    Badge awardBadgeToTrainer(Gym gym, Trainer trainer);

}
