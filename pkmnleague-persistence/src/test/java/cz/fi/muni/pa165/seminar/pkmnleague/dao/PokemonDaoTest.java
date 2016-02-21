package cz.fi.muni.pa165.seminar.pkmnleague.dao;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Pokemon;
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
 * @author Oldrich Faldik
 */
@ContextConfiguration(classes = EmbeddedDerbyDatabase.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PokemonDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PokemonDao pokemonDao;

    @Test
    public void testSave() {
        Trainer t = new Trainer("Brock", "brock@kanto.jp", "", new Date(0));

        Pokemon pokemon = new Pokemon(t, 1, "testPokemon", PokemonType.BUG, 0);

        pokemonDao.save(pokemon);

        Pokemon result = pokemonDao.findById(pokemon.getId());
        assertEquals(pokemon, result);

        pokemon.setLevel(10);
        pokemonDao.save(pokemon);

        Pokemon resultUpdate = pokemonDao.findById(pokemon.getId());
        assertEquals(resultUpdate.getLevel(), 10);
    }

    @Test
    public void testDelete() {
        Trainer t = new Trainer("Brock", "brock@kanto.jp", "", new Date(0));

        Pokemon pokemon = new Pokemon(t, 1, "testPokemon", PokemonType.BUG, 0);

        pokemonDao.save(pokemon);
        pokemonDao.delete(pokemon);

        assertEquals(0, pokemonDao.findAll().size());
    }

    @Test
    public void testFindAll() {
        Trainer t = new Trainer("Brock", "brock@kanto.jp", "", new Date(0));

        Pokemon pokemon = new Pokemon(t, 1, "testPokemon", PokemonType.BUG, 0);

        pokemonDao.save(pokemon);

        assertEquals(1, pokemonDao.findAll().size());
    }

}