package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.utils.EmbeddedDerbyDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author dhanak @domhanak on 10/29/15.
 */
@ContextConfiguration(classes = EmbeddedDerbyDatabase.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GymDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private GymDao gymDao;

    private Gym testGym;
    private Gym deleteGym;
    private Pokemon testPokemon;
    private Trainer testTrainer;

    @BeforeMethod
    public void setUp() throws Exception {
        testTrainer = new Trainer("Brock", "brock@kanto.jp", "", new Date(0));

        testPokemon = new Pokemon(testTrainer, 1, "Bulbasaur", PokemonType.GRASS, 1);

        testGym = new Gym("Name", PokemonType.DARK, testTrainer);
        deleteGym = new Gym("Delete", PokemonType.DARK, testTrainer);
        gymDao.save(deleteGym);
    }

    @Test
    public void testCreate() {
        gymDao.save(testGym);

        Gym result = gymDao.findById(testGym.getId());
        assertEquals(testGym, result);

        testGym.setCity("Pewter");
        gymDao.save(testGym);

        Gym resultUpdate = gymDao.findById(testGym.getId());
        assertEquals(resultUpdate.getCity(), "Pewter");
    }

    @Test
    public void testDelete() throws Exception {
        Gym result = gymDao.findById(deleteGym.getId());
        assertNotNull(result);
        
        gymDao.delete(deleteGym);

        Gym nullresult = gymDao.findById(deleteGym.getId());
        assertNull(nullresult);
    }

    @Test
    public void testFindGymById() throws Exception {
        gymDao.save(testGym);

        int id = testGym.getId();

        Gym result = gymDao.findById(id);
        assertEquals(result, testGym);
    }

    @Test
    public void testFindAllGyms() throws Exception {
        List<Gym> gymList = gymDao.findAll();
        assertEquals(gymList.size(), 1);
    }

}
