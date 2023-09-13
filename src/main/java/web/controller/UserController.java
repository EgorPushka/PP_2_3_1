package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping(value = "/user")
    public ModelAndView indexUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user");
        return modelAndView;
    }

    @GetMapping(value = "/edit")
    public ModelAndView editUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/edit");
        return modelAndView;
    }
}
