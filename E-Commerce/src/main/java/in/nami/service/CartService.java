package in.nami.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nami.model.Cart;
import in.nami.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartItems() {
        return cartRepository.findAll();
    }

    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long id) {
        cartRepository.deleteById(id);
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }
}

