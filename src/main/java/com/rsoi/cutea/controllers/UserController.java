package com.rsoi.cutea.controllers;

import com.rsoi.cutea.models.User;
import com.rsoi.cutea.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "profile";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }

    @GetMapping("/user/order/{user}")
    public String orderPage(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        return "make-order";
    }

    @GetMapping("/user/orders-history/{user}")
    public String ordersHistoryPage(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        return "orders-history";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @PostMapping("/user/order/{user}")
    public String createOrder(@PathVariable User user, Model model) {
        if (!user.getCart().isEmpty()) {
            user.makeOrder();
            userService.save(user);
            return "redirect:/user/order/" + user.getId();
        }
        else {
            model.addAttribute("user", user);
            model.addAttribute("error", "В корзине отсутствуют товары");
            return "make-order";
        }
    }

    @GetMapping("/user/{user}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        return "user-info";
    }
}
