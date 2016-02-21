package cz.fi.muni.pa165.seminar.pkmnleague.service;

import cz.fi.muni.pa165.seminar.pkmnleague.dao.BadgeDao;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Badge;
import cz.fi.muni.pa165.seminar.pkmnleague.domain.Trainer;
import cz.fi.muni.pa165.seminar.pkmnleague.exceptions.PokemonLeagueServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhanak @domhanak on 11/25/15.
 */
@Service
public class BadgeServiceImpl implements BadgeService {

    @Inject
    private BadgeDao badgeDao;

    @Override
    public void createBadge(Badge badge) {
        badgeDao.save(badge);
    }

    @Override
    public Badge findById(int id) {
        return badgeDao.findById(id);
    }

    @Override
    public List<Badge> findAll() {
        return badgeDao.findAll();
    }

    @Override
    public void delete(Badge badge) {
        badgeDao.delete(badge);
    }

    @Override
    public List<Badge> getAllBadgesByTrainer(Trainer trainer) {
        if (trainer == null) {
            throw new PokemonLeagueServiceException("Can't find Badges for Trainer that is null.");
        }

        List<Badge> badges = badgeDao.findAll();
        if (badges.isEmpty() || badges == null) {
            throw new PokemonLeagueServiceException("No badges in database found.");
        }

        List<Badge> result = new ArrayList<>();
        for (Badge b : badges) {
            if (b.getTrainer().equals(trainer)) {
                result.add(b);
            }
        }

        return result;
    }

}
