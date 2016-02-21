package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.dao.GymDao;
import cz.fi.muni.pa165.seminar.pkmnleague.dao.TrainerDao;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Role;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.exceptions.PokemonLeagueServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link TrainerService}.
 * This class is part of the service module of the application
 * that provides the implementation of the business logic.
 *
 * @author dhanak @domhanak on 11/26/15.
 */
@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerDao trainerDao;

    @Autowired
    private GymDao gymDao;

    @Autowired
    private PokemonService pokemonService;


    @Override
    public void create(Trainer trainer) {
        trainerDao.save(trainer);
    }

    @Override
    public Trainer findById(int id) {
        return trainerDao.findById(id);
    }

    @Override
    public List<Trainer> findAll() {
        return trainerDao.findAll();
    }

    @Override
    public void delete(Trainer trainer) {
        trainerDao.delete(trainer);
    }

    @Override
    public boolean isGymLeader(Trainer trainer) {
        for (Gym g : gymDao.findAll()) {
            if (trainer.isGymLeaderAtGym(g)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Role getRoleForTrainer(Trainer trainer) {
        if (isGymLeader(trainer)) return Role.ROLE_GYMLEADER;
        return Role.ROLE_TRAINER;
    }

    @Override
    public List<Pokemon> findTrainersPokemons(Trainer trainer) {
        if (trainer == null) {
            throw new PokemonLeagueServiceException("Can't find Pokemons for Trainer that is null");
        }

        List<Pokemon> pokemons = pokemonService.findAll();
        List<Pokemon> result = new ArrayList<>();
        for (Pokemon p : pokemons) {
            if (p.getTrainer().equals(trainer)) {
                result.add(p);
            }
        }

        return result;
    }

    @Override
    public Trainer findByEmail(String email) {
        return trainerDao.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Trainer trainer = trainerDao.findByEmail(s);

        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(getRoleForTrainer(trainer).toString()));

        return buildUserForAuthentication(trainer, auths);
    }

    private User buildUserForAuthentication(Trainer trainer, List<GrantedAuthority> authorities) {
        return new User(trainer.getEmail(), trainer.getPassword(), authorities);
    }

}
