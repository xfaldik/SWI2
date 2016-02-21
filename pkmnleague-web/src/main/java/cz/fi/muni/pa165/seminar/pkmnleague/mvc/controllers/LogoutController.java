package cz.fi.muni.pa165.seminar.pkmnleague.mvc.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author olda
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    @RequestMapping(method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

}
