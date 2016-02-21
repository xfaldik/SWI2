package cz.fi.muni.pa165.seminar.pkmnleague.mvc.controllers;

import cz.fi.muni.pa165.seminar.pkmnleague.facade.TrainerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@Controller
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerFacade trainerFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getTrainers(Model model) {
        model.addAttribute("trainers", trainerFacade.getAllTrainers());
        return "trainer/list";
    }

}
