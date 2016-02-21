package cz.fi.muni.pa165.seminar.pkmnleague.rest.controllers;

import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.TrainerFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.seminar.pkmnleague.rest.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.seminar.pkmnleague.rest.exceptions.ResourceNotModifiedException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@RestController
@RequestMapping("/trainers")
public class TrainerController {

    @Inject
    private TrainerFacade trainerFacade;

    /**
     * curl -i -X GET http://localhost:8080/pa165/rest/trainers
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TrainerDTO> getAllTrainers() {
        return trainerFacade.getAllTrainers();
    }

    /**
     * curl -i -X GET http://localhost:8080/pa165/rest/trainers/{id}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TrainerDTO getTrainer(@PathVariable int id) throws Exception {
        TrainerDTO trainerDTO = trainerFacade.getTrainerWithId(id);
        if (trainerDTO != null) {
            return trainerDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * curl -i -X GET http://localhost:8080/pa165/rest/trainers/{id}/pokemon
     */
    @RequestMapping(value = "/{id}/pokemon", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Set<PokemonDTO> getPokemon(@PathVariable int id) throws Exception {
        TrainerDTO trainerDTO = trainerFacade.getTrainerWithId(id);
        if (trainerDTO != null) {
            return trainerDTO.getPokemon();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * curl -i -X DELETE http://localhost:8080/pa165/rest/trainers/{id}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTrainer(@PathVariable int id) throws Exception {
        TrainerDTO t = trainerFacade.getTrainerWithId(id);
        if (t == null) {
            throw new ResourceNotFoundException();
        }
        try {
            trainerFacade.deleteTrainer(id);
        } catch (Exception e) {
            throw new ResourceNotModifiedException();
        }
    }

    /**
     * curl -i -X POST -H "Content-Type: application/json" --data '{"fullName":"test","email":"foo@foo.foo","password":"abc","dateOfBirth":"1990-01-01"}' http://localhost:8080/pa165/rest/trainers
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TrainerDTO createTrainer(@RequestBody TrainerCreateDTO trainer) throws Exception {
        try {
            int id = trainerFacade.createTrainer(trainer);
            return trainerFacade.getTrainerWithId(id);
        } catch (ConstraintViolationException ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

}
