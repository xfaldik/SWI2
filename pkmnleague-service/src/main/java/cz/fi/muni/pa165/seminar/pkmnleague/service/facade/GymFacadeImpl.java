package cz.fi.muni.pa165.seminar.pkmnleague.service.facade;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.Gym;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymEditDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.GymFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.service.BeanMappingService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.GymService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Zuzana Goldmannova
 */
@Service
@Transactional
public class GymFacadeImpl implements GymFacade {

    @Inject
    private GymService gymService;

    @Inject
    private TrainerService trainerService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createGym(GymCreateDTO g) {
        Gym newGym = new Gym(g.getCity(), g.getType(), trainerService.findById(g.getLeader().getId()));
        gymService.create(newGym);
    }
    
    @Override
    public void editGym(GymEditDTO g) {
        Gym editGym= gymService.findById(g.getId());
        gymService.update(editGym,g.getCity(),g.getType());
    }

    @Override
    public void deleteGym(int gymId) {
        gymService.delete(gymService.findById(gymId));
    }

    @Override
    public List<GymDTO> getAllGyms() {
        return beanMappingService.mapTo(gymService.findAll(), GymDTO.class);
    }

    @Override
    public GymDTO getGymWithId(int id) {
        return beanMappingService.mapTo(gymService.findById(id), GymDTO.class);
    }

    @Override
    public void changeCity(String newCity, int gymId) {
        Gym mappedGym = beanMappingService.mapTo(gymId, Gym.class);
        mappedGym.setCity(newCity);
    }

}
