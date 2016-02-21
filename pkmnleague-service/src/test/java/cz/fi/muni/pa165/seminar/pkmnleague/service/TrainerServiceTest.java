package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.dao.GymDao;
import cz.fi.muni.pa165.seminar.pkmnleague.dao.PokemonDao;
import cz.fi.muni.pa165.seminar.pkmnleague.dao.TrainerDao;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author dhanak @domhanak on 11/26/15.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TrainerServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TrainerDao trainerDao;

    @Mock
    private GymDao gymDao;

    @Autowired
    @InjectMocks
    private TrainerService trainerService;

    @Mock
    private PokemonService pokemonService;

    private Trainer trainer;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        trainer = new Trainer("Tester", "tester@example.com", "", new Date(0));

        trainerService.create(trainer);
    }

    @Test
    public void testFindAll() {
        when(trainerDao.findAll()).thenReturn(new ArrayList<>(createTrainersTeam().values()));
        assertEquals(trainerService.findAll().size(), 2);
    }

    @Test
    public void testFindById() {
        when(trainerDao.findById(1)).thenReturn(createTrainersTeam().get("Ash"));
        assertEquals(trainerService.findById(1), new Trainer("Ash", "satoshi@kanto.jp", "", new Date(0)));
    }

    @Test
    public void testIsGymLeader() {
        Trainer leader = new Trainer("Misty", "misty@kanto.jp", "", new Date(0));
        List<Gym> gyms = new ArrayList<>();
        gyms.add(new Gym("Viridian", PokemonType.GROUND, leader));
        when(gymDao.findAll()).thenReturn(gyms);
        assertFalse(trainerService.isGymLeader(trainer));
        assertTrue(trainerService.isGymLeader(leader));
    }

    @Test
    public void testFindTrainersPokemon() throws Exception {
        when(pokemonService.findAll()).thenReturn(new ArrayList<>(createPokemonTeam().values()));
        when(trainerDao.findById(1)).thenReturn(trainer);

        // set his pokemons
        trainer.addPokemon(new HashSet<>(createPokemonTeam().values()));

        assertEquals(trainerService.findTrainersPokemons(trainer).size(), 6);
    }

    @Test
    public void testFindTrainersPokemonZero() throws Exception {
        when(pokemonService.findAll()).thenReturn(new ArrayList<>(createPokemonTeam().values()));
        Trainer noPokemonTrainer = new Trainer("Ash", "satosthi@kanto.jp", "", new Date());
        when(trainerDao.findById(1)).thenReturn(noPokemonTrainer);

        trainer.addPokemon(new HashSet<>(createPokemonTeam().values()));

        assertEquals(trainerService.findTrainersPokemons(noPokemonTrainer).size(), 0);
    }

    private Map<String, Trainer> createTrainersTeam() {
        Map<String, Trainer> team = new HashMap<>();
        team.put("Ash", new Trainer("Ash", "satoshi@kanto.jp", "", new Date(0)));
        team.put("Brock", new Trainer("Brock", "brock@kanto.jp", "", new Date(0)));
        return team;
    }

    private Map<String, Pokemon> createPokemonTeam() {
        Map<String, Pokemon> team = new HashMap<>();
        team.put("pikachu", new Pokemon(trainer, 25, "Pikachu", PokemonType.ELECTRIC, 88));
        team.put("lapras", new Pokemon(trainer, 131, "Lapras", PokemonType.WATER, PokemonType.ICE, 80));
        team.put("snorlax", new Pokemon(trainer, 143, "Snorlax", PokemonType.NORMAL, 88));
        team.put("venusaur", new Pokemon(trainer, 3, "Venusaur", PokemonType.GRASS, PokemonType.POISON, 84));
        team.put("charizard", new Pokemon(trainer, 6, "Charziard", PokemonType.FIRE, PokemonType.FLYING, 84));
        team.put("blastoise", new Pokemon(trainer, 9, "Blastoise", PokemonType.WATER, 84));
        return team;
    }
}
