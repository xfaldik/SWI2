package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.utils.EmbeddedDerbyDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * @author Pavel Kouřil
 */
@ContextConfiguration(classes = EmbeddedDerbyDatabase.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BadgeDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BadgeDao badgeDao;

    @Test
    public void testSave() {
        Trainer leader = new Trainer("Misty", "misty@kanto.jp", "", new Date(1996, 1, 2));
        Gym gym = new Gym("Cerulean", PokemonType.WATER, leader);
        Gym gym2 = new Gym("Pewter", PokemonType.WATER, leader);

        Trainer t = new Trainer("Ash", "satoshi@kanto.jp", "", new Date(0));

        Badge badge = new Badge(t, gym);

        badgeDao.save(badge);

        Badge resultCreate = badgeDao.findById(badge.getId());
        assertEquals(badge, resultCreate);

        badge.setGym(gym2);

        badgeDao.save(badge);
        Badge resultUpdate = badgeDao.findById(badge.getId());
        assertEquals(badge, resultUpdate);
    }

    @Test
    public void testDelete() {
        Trainer leader = new Trainer("Misty", "misty@kanto.jp", "", new Date(1996, 1, 2));
        Gym gym = new Gym("Cerulean", PokemonType.WATER, leader);

        Trainer t = new Trainer("Ash", "satoshi@kanto.jp", "", new Date(0));

        Badge badge = new Badge(t, gym);

        badgeDao.save(badge);
        badgeDao.delete(badge);

        assertEquals(0, badgeDao.findAll().size());
    }

    @Test
    public void testFindAll() {
        Trainer leader = new Trainer("Misty", "misty@kanto.jp", "", new Date(1996, 1, 2));
        Gym gym = new Gym("Cerulean", PokemonType.WATER, leader);

        Trainer t = new Trainer("Ash", "satoshi@kanto.jp", "", new Date(0));

        Badge badge = new Badge(t, gym);

        badgeDao.save(badge);

        assertEquals(1, badgeDao.findAll().size());
    }

}