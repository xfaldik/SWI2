package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.dao.BadgeDao;
import cz.fi.muni.pa165.seminar.pkmnleague.dao.TrainerDao;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
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
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author dhanak @domhanak on 11/26/15.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BadgeServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BadgeDao badgeDao;

    @Autowired
    @InjectMocks
    BadgeService badgeService;

    private Trainer trainer;
    private Trainer trainerLeader;
    private Trainer trainerLeader2;
    private Gym gym;
    private Gym gym2;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        trainer = new Trainer("Brock", "brock@kanto.jp", "", new java.sql.Date(0));

        trainerLeader = new Trainer("Brock", "brock@kanto.jp", "", new java.sql.Date(0));
        trainerLeader2 = new Trainer("Brock", "brock@kanto.jp", "", new java.sql.Date(0));

        gym = new Gym("Brno", PokemonType.FIRE, trainerLeader);
        gym2 = new Gym("Praha", PokemonType.WATER, trainerLeader2);
    }

    @Test
    public void testFindById() throws Exception {
        when(badgeDao.findById(1)).thenReturn(createBadgeCollection().get("Fire"));
        assertEquals(badgeService.findById(1), new Badge(trainer, gym));
    }

    @Test
    public void testFindByAll() throws Exception {
        when(badgeDao.findAll()).thenReturn(new ArrayList<>(createBadgeCollection().values()));
        assertEquals(badgeService.findAll().size(), 2);
    }

    private Map<String, Badge> createBadgeCollection() {
        Map<String, Badge> team = new HashMap<>();
        team.put("Fire", new Badge(trainer, gym));
        team.put("Water", new Badge(trainer, gym2));
        return team;
    }

}
