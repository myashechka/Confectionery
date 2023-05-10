package app.confectionery.controller;

import app.confectionery.entity.CartRecord;
import app.confectionery.entity.Product;
import app.confectionery.entity.User;
import app.confectionery.model.ConstructorDTO;
import app.confectionery.service.CartRecordService;
import app.confectionery.service.ProductService;
import app.confectionery.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.extras.springsecurity6.auth.Authorization;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("index")
    public String index(Model model, Authentication authentication)
    {
        if (authentication != null) {
            model.addAttribute("user", (User) authentication.getPrincipal());
        }
        return "index";
    }

    @GetMapping("registration")
    public String getRegistration(Model model)
    {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("registration")
    public String postRegistration(@ModelAttribute User user, HttpServletRequest req)
    {
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String getSignIn(Model model)
    {
        model.addAttribute("user", new User());
        return "sign_in";
    }

    @PostMapping("login")
    public String postSignIn(@ModelAttribute User user, HttpServletRequest req, Model model) {

        userService.authorise(user, req);

        return "redirect:/index";
    }

    @PostMapping("login?logout")
    public String logout() {
        return "redirect:/index";
    }



}
