package org.example.Pr23.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.Pr23.dto.UserDTO;
import org.example.Pr23.models.User;
import org.example.Pr23.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login-page";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> list = userService.findAll();
        model.addAttribute("users", list);
        return "users";
    }

    @GetMapping("/register")
    public String showRegForm(Model model) {
        UserDTO u = new UserDTO();
        model.addAttribute("user", u);
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(@Valid UserDTO userDTO, Model model, BindingResult bindingResult) {
        User exist = userService.findUserByEmail(userDTO.getEmail());
        if (exist != null && exist.getEmail() != null && !exist.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", null, "There is already someone registered with this email");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "/register";
        }
        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }
}
