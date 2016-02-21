package cz.fi.muni.pa165.seminar.pkmnleague.mvc.controllers;

import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonEditDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.exceptions.PokemonLeagueServiceException;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.PokemonFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.TrainerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhanak @domhanak on 12/18/15.
 */
@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    final static Logger log = LoggerFactory.getLogger(PokemonController.class);

    @ModelAttribute("pokemon")
    public PokemonCreateDTO getPokemon() {
        return new PokemonCreateDTO();
    }
    
    @ModelAttribute("pokemonEdited")
    public PokemonEditDTO getPokemonEdited() {
        return new PokemonEditDTO();
    }

    @Autowired
    PokemonFacade pokemonFacade;

    @Autowired
    private TrainerFacade trainerFacade;

    /**
     * Lists all pokemon of logged trainer.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String pokemons(Model model, Principal principal) {
        log.info("Pokemons = {}", pokemonFacade.getAllPokemons());

        String trainerName = principal.getName();

        List<PokemonDTO> pokemonDTOs = pokemonFacade.getAllPokemons();
        List<PokemonDTO> result = new ArrayList<>();
        for (PokemonDTO p : pokemonDTOs) {
            if (p.getTrainer().getEmail().equals(trainerName)) {
                result.add(p);
            }
        }

        model.addAttribute("pokemons", result);
        return "pokemon/list";
    }

    @RequestMapping(value = "/level-up/{id}", method = RequestMethod.GET)
    public String levelUp(@PathVariable int id, RedirectAttributes redirectAttributes, Principal principal) {
        if (!trainerFacade.getTrainerWithEmail(principal.getName()).equals(pokemonFacade.getPokemonWithId(id).getTrainer())) {
            redirectAttributes.addFlashAttribute("alert_danger", "You can't modify Pokémon you don't own!");
            return "redirect:/pokemon/list";
        }

        try {
            pokemonFacade.levelUpPokemonWithId(id);
        } catch (PokemonLeagueServiceException ex) {
            redirectAttributes.addFlashAttribute("alert_warning", "Pokémon can't have level bigger than 100!");
            return "redirect:/pokemon/list";
        }

        redirectAttributes.addFlashAttribute("alert_info", "Pokemon was successfully leveled up!");
        return "redirect:/pokemon/list";
    }

    /**
     * Runs a new page with a form for new pokemons.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        log.debug("Create new pokemon");
        model.addAttribute("createPokemon", new PokemonCreateDTO());
        return "pokemon/create";
    }

    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable int id){
        log.debug("Edit existing pokemon");
        PokemonEditDTO pok=new PokemonEditDTO();
        
        PokemonDTO existPok=pokemonFacade.getPokemonWithId(id);
        pok.setId(id);
        pok.setNickname(existPok.getNickname());
        model.addAttribute("editPokemon", pok);
        return "pokemon/edit";
    }
    
    
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("pokemonEdited") PokemonEditDTO pokemon, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alert_failure", "Some data were not filled!");
            return "redirect:" + uriBuilder.path("/pokemon/edit").build();
        }
        
        pokemonFacade.changeNickName(pokemon.getNickname(), pokemon.getId());

        redirectAttributes.addFlashAttribute("alert_success", "Pokemon was successfully edited.");

        return "redirect:" + uriBuilder.path("/pokemon/list").build();

    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("pokemon") PokemonCreateDTO pokemon, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alert_failure", "Some data were not filled!");
            return "redirect:" + uriBuilder.path("/pokemon/create").build();
        }
        pokemon.setTrainer(trainerFacade.getTrainerWithEmail(principal.getName()));
        pokemonFacade.createPokemon(pokemon);

        redirectAttributes.addFlashAttribute("alert_success", "Pokemon was successfully created.");

        return "redirect:" + uriBuilder.path("/pokemon/list").build();

    }
    
    
    
    
    
}
