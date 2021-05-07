package hibernate.controller;

import hibernate.dao.UserDao;
import hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {
    private final UserDao userService;

    @Autowired
    public HelloController(UserDao userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "admin/admin";
    }

    @GetMapping("admin/user-create")
    public String createUserForm(User user) {
        return "admin/user-create";
    }

    @PostMapping("admin/user-create")
    public String createUser(User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return "redirect:/admin";
    }


    @GetMapping("admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, ModelMap model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user-update";
    }

    @PostMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user) {
        userService.update(user, id);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

}