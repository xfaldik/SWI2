package cz.fi.muni.pa165.seminar.pkmnleague.mvc.controllers;

import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.GymEditDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.BadgeFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.GymFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.TrainerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

/**
 * @author dhanak @domhanak on 12/18/15.
 */
@Controller
@RequestMapping("/gym")
public class GymController {

    final static Logger log = LoggerFactory.getLogger(PokemonController.class);

    @ModelAttribute("gym")
    public GymCreateDTO getGym() {
        return new GymCreateDTO();
    }

    @ModelAttribute("gymEdited")
    public GymCreateDTO getGymEdited() {
        return new GymCreateDTO();
    }

    @Autowired
    private GymFacade gymFacade;

    @Autowired
    private BadgeFacade badgeFacade;

    @Autowired
    private TrainerFacade trainerFacade;

    /**
     * Lists all gyms.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String gyms(Model model, Principal principal) {
        log.info("Gyms = {}", gymFacade.getAllGyms());
        model.addAttribute("gyms", gymFacade.getAllGyms());
        model.addAttribute("user", trainerFacade.getTrainerWithEmail(principal.getName()));
        model.addAttribute("beatenGyms", trainerFacade.getTrainerWithEmail(principal.getName()));
        return "gym/list";
    }

    /**
     * Hands out a badge to a trainer.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/badge", method = RequestMethod.GET)
    public String badge(Model model, Principal principal) {
        log.debug("Hand out badge");
        model.addAttribute("createBadge", new BadgeCreateDTO());
        log.info("Trainers = {}", trainerFacade.getAllTrainers());
        model.addAttribute("trainers", trainerFacade.getBadgeAbleTrainers(principal.getName()));
        return "gym/badge";
    }

    @RequestMapping(value = "/badge", method = RequestMethod.POST)
    public String badge(@Valid @ModelAttribute("badge") BadgeCreateDTO badge,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        UriComponentsBuilder uriBuilder, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alert_failure", "Some data were not filled!");
            return "redirect:" + uriBuilder.path("/gym/badge").build();
        }

        badge.setGymId(trainerFacade.getTrainerWithEmail(principal.getName()).getGym().getId());

        badgeFacade.createBadge(badge);

        redirectAttributes.addFlashAttribute("alert_success", "Badge was successfully handed out.");

        return "redirect:" + uriBuilder.path("/gym/badge").build();
    }

    /**
     * Runs a new page with a form for new gym.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        log.debug("Create new gym");
        model.addAttribute("createGym", new GymCreateDTO());
        return "gym/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("gym") GymCreateDTO gym, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Principal principal, HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alert_failure", "Some data were not filled!");
            return "redirect:" + uriBuilder.path("/gym/create").build();
        }
        gym.setLeader(trainerFacade.getTrainerWithEmail(principal.getName()));
        gymFacade.createGym(gym);

        session.invalidate();
        return "redirect:/login?role";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, Principal principal) {
        log.debug("Edit your gym");
        TrainerDTO trainer = trainerFacade.getTrainerWithEmail(principal.getName());

        model.addAttribute("editGym", trainer.getGym());
        return "gym/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("editGym") GymEditDTO gym, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Principal principal, HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alert_failure", "Some data were not filled!");
            return "redirect:" + uriBuilder.path("/gym/edit").build();
        }

        gymFacade.editGym(gym);
        return "redirect:/gym/list";
    }

}
