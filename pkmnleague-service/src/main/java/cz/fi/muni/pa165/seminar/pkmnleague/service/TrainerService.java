package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Role;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * An interface that defines a service access to the {@link Trainer} entity.
 *
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
public interface TrainerService extends UserDetailsService {

    void create(Trainer trainer);

    Trainer findById(int id);

    Trainer findByEmail(String email);

    List<Trainer> findAll();

    void delete(Trainer trainer);

    /**
     * Finds out if the given trainer is GymLeader.
     *
     * @param trainer
     * @return
     */
    boolean isGymLeader(Trainer trainer);

    List<Pokemon> findTrainersPokemons(Trainer trainer);

    Role getRoleForTrainer(Trainer trainer);

}