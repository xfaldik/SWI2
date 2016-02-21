package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.dao.PokemonDao;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
import cz.fi.muni.pa165.seminar.pkmnleague.exceptions.PokemonLeagueServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@Service
public class PokemonServiceImpl implements PokemonService {

    @Inject
    private PokemonDao pokemonDao;

    @Override
    public Pokemon findById(int id) {
        return pokemonDao.findById(id);
    }

    @Override
    public List<Pokemon> findAll() {
        return pokemonDao.findAll();
    }

    @Override
    public void delete(Pokemon pokemon) {
        pokemonDao.delete(pokemon);
    }

    @Override
    public void create(Pokemon pokemon) {
        pokemonDao.save(pokemon);
    }

    public void update(Pokemon pokemon, String newNickName) {
        pokemon.setNickname(newNickName);
        pokemonDao.save(pokemon);
    }

    @Override
    public void levelUpPokemon(Pokemon pokemon) {
        if (pokemon.getLevel() == 100) {
            throw new PokemonLeagueServiceException("Pokémon can't have level bigger than 100.");
        }
        pokemon.setLevel(pokemon.getLevel() + 1);
        pokemonDao.save(pokemon);
    }

}
