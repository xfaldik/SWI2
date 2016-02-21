package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.dao.GymDao;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.exceptions.PokemonLeagueServiceException;
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
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GymServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private GymDao gymDao;

    @Autowired
    @InjectMocks
    private GymService gymService;

    private Trainer trainerRed;
    private Trainer leaderGreen;
    private Gym viridianGym;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        trainerRed = new Trainer("Red", "red@pkmnadventures.net", "", new Date(1996, 8, 8));
        leaderGreen = new Trainer("Green", "green@pkmnadventures.net", "", new Date(1996, 11, 22));
        viridianGym = new Gym("Viridian City", PokemonType.GROUND, leaderGreen);
    }

    @Test
    public void findById() {
        when(gymDao.findById(1)).thenReturn(viridianGym);
        assertEquals(gymService.findById(1), new Gym("Viridian City", PokemonType.GROUND, leaderGreen));
    }

    @Test
    public void findAll() {
        List<Gym> gyms = new ArrayList<>();
        gyms.add(viridianGym);
        when(gymDao.findAll()).thenReturn(gyms);
        assertEquals(gymService.findAll().size(), 1);
    }

    @Test
    public void awardBadgeToTrainer() {
        Badge b = gymService.awardBadgeToTrainer(viridianGym, trainerRed);
        assertEquals(trainerRed.getBadges().size(), 1);
    }

    @Test(expectedExceptions = PokemonLeagueServiceException.class)
    public void awardBadgeToGymLeader() {
        Badge b = gymService.awardBadgeToTrainer(viridianGym, leaderGreen);
    }

    @Test(expectedExceptions = PokemonLeagueServiceException.class)
    public void awardBadgeTwice() {
        Gym ceruleanGym = new Gym("Cerulean City", PokemonType.WATER, new Trainer("Misty", "misty@kanto.jp", "", new Date(1996, 4, 21)));
        gymService.awardBadgeToTrainer(ceruleanGym, trainerRed);
        gymService.awardBadgeToTrainer(ceruleanGym, trainerRed);
    }
}