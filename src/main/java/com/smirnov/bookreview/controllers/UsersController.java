package com.smirnov.bookreview.controllers;

import com.smirnov.bookreview.dao.UserDao;
import com.smirnov.bookreview.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.findById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser() {
        return "users/new";
    }

    @PostMapping()
    public String create(@RequestParam("name") String name, @RequestParam("email") String email,
                         @RequestParam("password") String password, @RequestParam("type") String type) {
        User user = User.builder().withName(name).withEmail(email)
                .withPassword(password).withType(type).build();

        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.findById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @RequestParam("name") String name, @RequestParam("email") String email,
                         @RequestParam("password") String password, @RequestParam("type") String type) {

        User user = User.builder().withName(name).withEmail(email)
                .withPassword(password).withType(type).build();

        userDao.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.deleteById(id);
        return "redirect:/users";
    }
}
