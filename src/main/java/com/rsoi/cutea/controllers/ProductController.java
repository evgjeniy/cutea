package com.rsoi.cutea.controllers;

import com.rsoi.cutea.models.Product;
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
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/")
    public String products(@RequestParam(name = "category", required = false) String category,
                           @RequestParam(name = "search", required = false) String title,
                           Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(category, title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("category", category);
        model.addAttribute("search", title);
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getProductById(id);
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }

    @PostMapping("/product/cart/add/{productId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public String addProductToCart(@PathVariable("productId") Long id, Principal principal) {
        var product = productService.getProductById(id);
        var user = userService.findByEmail(userService.getUserByPrincipal(principal).getEmail());

        user.addProductToCart(product);
        userService.save(user);

        return "redirect:/user/order/" + user.getId();
    }

    @PostMapping("/product/cart/delete/{productId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public String deleteProductFromCart(@PathVariable("productId") Long id, Principal principal) {
        var product = productService.getProductById(id);
        var user = userService.findByEmail(userService.getUserByPrincipal(principal).getEmail());

        user.deleteProductFromCart(product);
        userService.save(user);

        return "redirect:/user/order/" + user.getId();
    }
}
