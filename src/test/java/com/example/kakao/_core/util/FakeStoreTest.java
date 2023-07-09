package com.example.kakao._core.util;

import com.example.kakao.cart.Cart;
import com.example.kakao.order.Order;
import com.example.kakao.order.item.Item;
import com.example.kakao.product.Product;
import com.example.kakao.product.option.Option;
import com.example.kakao.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class FakeStoreTest {
    protected User newUser(String username){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .email(username+"@nate.com")
                .password(passwordEncoder.encode("meta1234!"))
                .username(username)
                .roles(username.equals("admin") ? "ROLE_ADMIN" : "ROLE_USER")
                .build();
    }

    protected Product newProduct(String productName, int imageNumber, int price) {
        return Product.builder()
                .productName(productName)
                .description("")
                .image("/images/" + imageNumber + ".jpg")
                .price(price)
                .build();
    }

    protected Option newOption(Product product, String optionName, int price) {
        return Option.builder()
                .product(product)
                .optionName(optionName)
                .price(price)
                .build();
    }

    protected Cart newCart(User user, Option option, Integer quantity){
        return Cart.builder()
                .user(user)
                .option(option)
                .quantity(quantity)
                .price(option.getPrice() * quantity)
                .build();
    }

    protected Item newItem(Cart cart, Order order){
        return Item.builder()
                .order(order)
                .option(cart.getOption())
                .quantity(cart.getQuantity())
                .price(cart.getOption().getPrice() * cart.getQuantity())
                .build();
    }

    protected Order newOrder(User user){
        return Order.builder()
                .user(user)
                .id(1)
                .build();
    }
}
