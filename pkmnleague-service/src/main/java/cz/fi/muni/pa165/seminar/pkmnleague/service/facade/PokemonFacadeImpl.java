package cz.fi.muni.pa165.seminar.pkmnleague.service.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.PokemonFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BeanMappingService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.PokemonService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zuzana Goldmannova
 */
@Service
@Transactional
public class PokemonFacadeImpl implements PokemonFacade {

    @Inject
    private PokemonService pokemonService;

    @Inject
    private TrainerService trainerService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createPokemon(PokemonCreateDTO p) {
        Pokemon mappedPokemon = new Pokemon(trainerService.findById(p.getTrainer().getId()), p.getSpeciesId(), p.getSpeciesName(), p.getPrimaryType(), p.getLevel());
        if (p.getSecondaryType() != null) {
            mappedPokemon.setSecondaryType(p.getSecondaryType());
        }
        pokemonService.create(mappedPokemon);
    }

    @Override
    public void deletePokemon(int pokemonId) {
        pokemonService.delete(pokemonService.findById(pokemonId));
    }

    @Override
    public List<PokemonDTO> getAllPokemons() {
        return beanMappingService.mapTo(pokemonService.findAll(), PokemonDTO.class);

    }

    @Override
    public PokemonDTO getPokemonWithId(int id) {
        return beanMappingService.mapTo(pokemonService.findById(id), PokemonDTO.class);
    }

    @Override
    public List<PokemonDTO> getPokemonsByTrainer(int trainerId) {
        return beanMappingService.mapTo(trainerService.findTrainersPokemons(trainerService.findById(trainerId)), PokemonDTO.class);
    }

    @Override
    public List<PokemonDTO> getPokemonsByPrimaryType(PokemonType type) {
        List<Pokemon> pokemons = pokemonService.findAll();
        List<Pokemon> pokemonsPrimType = new ArrayList<Pokemon>();
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getPrimaryType().name().equals(type.name())) {
                pokemonsPrimType.add(pokemon);
            }
        }
        List<PokemonDTO> dtop = beanMappingService.mapTo(pokemonsPrimType, PokemonDTO.class);
        return dtop;
    }

    @Override
    public List<PokemonDTO> getPokemonsBySecondaryType(PokemonType type) {
        List<Pokemon> pokemons = pokemonService.findAll();
        List<Pokemon> pokemonsSecType = new ArrayList<Pokemon>();
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getSecondaryType().name().equals(type.name())) {
                pokemonsSecType.add(pokemon);
            }
        }
        List<PokemonDTO> dtop = beanMappingService.mapTo(pokemonsSecType, PokemonDTO.class);
        return dtop;
    }

    @Override
    public List<PokemonDTO> getPokemonsByLevel(int level) {
        List<Pokemon> pokemons = pokemonService.findAll();
        List<Pokemon> pokemonsLevel = new ArrayList<Pokemon>();
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getLevel() == level) {
                pokemonsLevel.add(pokemon);
            }
        }
        List<PokemonDTO> dtop = beanMappingService.mapTo(pokemonsLevel, PokemonDTO.class);
        return dtop;
    }

    @Override
    public List<PokemonDTO> getPokemonsBySpeciesName(String speciesName) {
        List<Pokemon> pokemons = pokemonService.findAll();
        List<Pokemon> pokemonsSpecies = new ArrayList<Pokemon>();
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getSpeciesName().equals(speciesName)) {
                pokemonsSpecies.add(pokemon);
            }
        }
        List<PokemonDTO> dtop = beanMappingService.mapTo(pokemonsSpecies, PokemonDTO.class);
        return dtop;
    }

    @Override
    public void changeSpeciesName(String newSpeciesName, int pokemonId) {
        Pokemon mappedPokemon = beanMappingService.mapTo(pokemonId, Pokemon.class);
        mappedPokemon.setSpeciesName(newSpeciesName);
    }

    @Override
    public void changeNickName(String newNickName, int pokemonId) {
        Pokemon mappedPokemon = beanMappingService.mapTo(pokemonId, Pokemon.class);
        mappedPokemon.setNickname(newNickName);
        
        pokemonService.update(pokemonService.findById(pokemonId),newNickName);
    }

    @Override
    public void changeLevel(int newLevel, int pokemonId) {
        Pokemon mappedPokemon = beanMappingService.mapTo(pokemonId, Pokemon.class);
        mappedPokemon.setLevel(newLevel);
    }

    @Override
    public void changeTrainer(TrainerDTO newTrainer, int pokemonId) {
        Pokemon mappedPokemon = beanMappingService.mapTo(pokemonId, Pokemon.class);
        mappedPokemon.setTrainer(trainerService.findById(newTrainer.getId()));
    }

    @Override
    public void levelUpPokemonWithId(int id) {
        pokemonService.levelUpPokemon(pokemonService.findById(id));
    }

}
