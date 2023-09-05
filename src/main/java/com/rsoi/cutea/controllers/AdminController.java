package com.rsoi.cutea.controllers;

import com.rsoi.cutea.models.Product;
import com.rsoi.cutea.models.User;
import com.rsoi.cutea.models.enums.Role;
import com.rsoi.cutea.services.OrderService;
import com.rsoi.cutea.services.ProductService;
import com.rsoi.cutea.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("products", productService.listProducts(null, null));
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @GetMapping("/admin/orders")
    public String allOrdersPage(Model model, Principal principal) {
        var orders = orderService.getAllOrders();
        var summaryRevenue = 0.0f;
        for (var order : orders) summaryRevenue += order.getOrderPrice();

        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("orders", orders);
        model.addAttribute("revenue", summaryRevenue);

        return "all-orders";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                Product product) throws IOException {
        var savedProduct = productService.saveProduct(product, file1, file2, file3);
        return "redirect:/product/" + savedProduct.getId();
    }

    @PostMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }
}