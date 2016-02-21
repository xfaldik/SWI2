package cz.fi.muni.pa165.seminar.pkmnleague.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BadgeService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BeanMappingService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.GymService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.PokemonService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.seminar.pkmnleague.service.facade.GymFacadeImpl;
import cz.fi.muni.pa165.seminar.pkmnleague.service.facade.PokemonFacadeImpl;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author dhanak @domhanak on 1/24/16.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PokemonFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    BadgeService badgeServiceMock;

    @Mock
    TrainerService trainerServiceMock;

    @Mock
    PokemonService pokemonServiceMock;

    @Mock
    private BeanMappingService beanMappingServiceMock;

    @InjectMocks
    PokemonFacade pokemonFacade = new PokemonFacadeImpl();

    private Trainer trainer;
    private Trainer trainerLeader;

    private Pokemon pokemon1;
    private Pokemon pokemon2;


    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        trainer = new Trainer("Janko", "Hrasko", "Sedi", new Date(Calendar.DATE));
        trainerLeader = new Trainer("Janko", "Vodca", "Modry", new Date(Calendar.DATE));

        pokemon1 = new Pokemon(trainer, 1, "bug", PokemonType.BUG, 10);
        pokemon2 = new Pokemon(trainerLeader, 2, "fire", PokemonType.FIGHTNING, 10);

    }

    @Test
    public void testFindAllPokemonsFacade() throws Exception {
        PokemonDTO dto1 = new PokemonDTO();
        PokemonDTO dto2 = new PokemonDTO();

        doReturn(toList(new Pokemon[]{pokemon1, pokemon2})).when(pokemonServiceMock).findAll();
        doReturn(toList(new PokemonDTO[]{dto1, dto2})).when(beanMappingServiceMock)
                .mapTo(Matchers.anyListOf(Pokemon.class), (Class<?>) Matchers.any(Class.class));

        Collection<PokemonDTO> pokemonDTOs = pokemonFacade.getAllPokemons();
        Assert.assertEquals(pokemonDTOs.size(), 2);

        verify(pokemonServiceMock).findAll();
        verifyNoMoreInteractions(pokemonServiceMock);
    }

    @Test
    public void testGetPokemonByIdFacade() throws Exception {
        PokemonDTO dto = new PokemonDTO();
        dto.setId(1);

        doReturn(pokemon1).when(pokemonServiceMock).findById(1);
        doReturn(dto).when(beanMappingServiceMock).mapTo(Matchers.any(Pokemon.class),
                (Class<?>) Matchers.any(Class.class));

        PokemonDTO pokemonDTO = pokemonFacade.getPokemonWithId(1);
        Assert.assertEquals(pokemonDTO.getId(), 1);

        verify(pokemonServiceMock).findById(1);
        verify(beanMappingServiceMock).mapTo(pokemon1, PokemonDTO.class);
        verifyNoMoreInteractions(pokemonServiceMock);
    }

}

