package web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.models.User;
import web.services.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;
    private static final String REDIRECT_USERS_LIST_PAGE = "redirect:/users";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ModelAndView indexUsers(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", userService.indexUsers());
        return modelAndView;
    }

    @GetMapping("/user/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/new";
    }

    @PostMapping("/users")

    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        System.out.println(bindingResult.hasErrors());
        userService.add(user);
        return REDIRECT_USERS_LIST_PAGE;
    }

    @GetMapping("/user/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/edit";
    }

    @PatchMapping("/user/{id}")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult,
                           @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        userService.edit(user);
        return REDIRECT_USERS_LIST_PAGE;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.delete(user);
        return REDIRECT_USERS_LIST_PAGE;
    }
}
