package cz.fi.muni.pa165.seminar.pkmnleague.mvc.controllers;

import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerCreateDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.facade.TrainerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    final static Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @ModelAttribute("trainer")
    public TrainerCreateDTO getTrainer() {
        return new TrainerCreateDTO();
    }

    @Autowired
    private TrainerFacade trainerFacade;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(sdf, true));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String create(Model model) {
        log.info("registration visited");
        model.addAttribute("createTrainer", new TrainerCreateDTO());
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute TrainerCreateDTO trainer, ModelMap model, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.info("submitted registration: {}", trainer);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.info("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.info("FieldError: {}", fe);
            }
            redirectAttributes.addFlashAttribute("alert_failure", "There are some errors with submitted data.");
            return "registration";
        }

        trainerFacade.createTrainer(trainer);
        redirectAttributes.addFlashAttribute("alert_success", "You sign up. You can log in now!");

        return "redirect:/login";
    }

}
