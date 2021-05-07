package hibernate.controller;

import hibernate.dao.UserDao;
import hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class UserController {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal) {
        List<String> messages = new ArrayList<>();
        User user = userDao.getUserByName(principal.getName());
        messages.add("User name: " + user.getName());
        messages.add("User surname: " + user.getSurname());
        messages.add("User email: " + user.getEmail());
        messages.add("User role: "+ user.getRoles().stream().
                map(r->r.getRole()).collect(Collectors.toList()));
        model.addAttribute("messages", messages);
        return "user";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}