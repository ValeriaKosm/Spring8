package com.example.springlab2;


import com.example.springlab2.user.User;
import com.example.springlab2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userv;

    @GetMapping("")
    public String showHomePage(Model model){
        model.addAttribute("new_User_user1", new User());
        return "index";
    }
    @GetMapping("/register.html")
    public String test2(Model model){
        model.addAttribute("new_User_user1",new User());
        return "register";
    }

    @GetMapping("/welcome.html")
    public String test3(){
        return "welcome";
    }

    @PostMapping("/check")
    public String check1(Model model, User user) {
        List<User> users = userv.listAll();
        if (user.getName().equals("admin") && user.getPassword().equals("admin")) {
            model.addAttribute("listUsers", users);
            return "users";
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName()) && (users.get(i).getPassword().equals(user.getPassword()))) {
                model.addAttribute("listUsers", users.get(i));
                return "welcome";
            }
        }
        return "index";
    }
    @PostMapping("/add")
    public String add1(User user){
        List<User> users = userv.listAll();
        user.setIdUser(users.size()+1);
        for(int i = 0; i<users.size();i++){
            if (users.get(i).getName().equals(user.getName()) || (users.get(i).getPassword().equals(user.getPassword()))||(users.get(i).getEmail().equals(user.getEmail()))) {
                return "register";
            }
        }
        userv.save(user);
        return "index";
    }
    @GetMapping("/users/logOut")
    public String logOut(){
        return "index";
    }
}
