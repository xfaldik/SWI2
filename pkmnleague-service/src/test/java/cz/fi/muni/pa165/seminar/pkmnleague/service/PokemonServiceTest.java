package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.dao.PokemonDao;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PokemonServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private PokemonDao pokemonDao;

    @Autowired
    @InjectMocks
    private PokemonService pokemonService;

    private Trainer trainerRed;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        trainerRed = new Trainer("Red", "red@pkmnadventures.net", "", new Date(0));
    }

    @Test
    public void findAll() {
        when(pokemonDao.findAll()).thenReturn(new ArrayList<>(createTeam().values()));
        assertEquals(pokemonService.findAll().size(), 6);
    }

    @Test
    public void findById() {
        when(pokemonDao.findById(1)).thenReturn(createTeam().get("pikachu"));
        assertEquals(pokemonService.findById(1), new Pokemon(trainerRed, 25, "Pikachu", PokemonType.ELECTRIC, 88));
    }

    private Map<String, Pokemon> createTeam() {
        Map<String, Pokemon> team = new HashMap<>();
        team.put("pikachu", new Pokemon(trainerRed, 25, "Pikachu", PokemonType.ELECTRIC, 88));
        team.put("lapras", new Pokemon(trainerRed, 131, "Lapras", PokemonType.WATER, PokemonType.ICE, 80));
        team.put("snorlax", new Pokemon(trainerRed, 143, "Snorlax", PokemonType.NORMAL, 88));
        team.put("venusaur", new Pokemon(trainerRed, 3, "Venusaur", PokemonType.GRASS, PokemonType.POISON, 84));
        team.put("charizard", new Pokemon(trainerRed, 6, "Charziard", PokemonType.FIRE, PokemonType.FLYING, 84));
        team.put("blastoise", new Pokemon(trainerRed, 9, "Blastoise", PokemonType.WATER, 84));
        return team;
    }
}
