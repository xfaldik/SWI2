package cz.fi.muni.pa165.seminar.pkmnleague.mvc.controllers;

import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.BadgeFacade;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.TrainerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@Controller
@RequestMapping("/")
public class HomepageController {

    @Autowired
    private TrainerFacade trainerFacade;

    @Autowired
    private BadgeFacade badgeFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, Principal principal) {
        TrainerDTO trainer = trainerFacade.getTrainerWithEmail(principal.getName());
        model.addAttribute("trainer", trainer);
        List<BadgeDTO> badges = badgeFacade.getBadgesByTrainer(trainer.getId());
        model.addAttribute("badges", badges);
        model.addAttribute("badgeCount", badges.size());
        return "home";
    }

}
