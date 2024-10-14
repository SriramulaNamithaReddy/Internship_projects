package in.nami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.nami.model.Cart;
import in.nami.service.CartService;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute("cart") Cart cart) {
        cartService.addToCart(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCartPage(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteCartItem(@PathVariable(value = "id") Long id) {
        cartService.deleteCartItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout() {
        cartService.clearCart();
        return "redirect:/";
    }
}


