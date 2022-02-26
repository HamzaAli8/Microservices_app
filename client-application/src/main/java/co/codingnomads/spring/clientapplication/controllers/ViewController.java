package co.codingnomads.spring.clientapplication.controllers;

import co.codingnomads.spring.clientapplication.models.Cart;
import co.codingnomads.spring.clientapplication.models.CartItem;
import co.codingnomads.spring.clientapplication.models.Item;
import co.codingnomads.spring.clientapplication.models.User;
import co.codingnomads.spring.clientapplication.services.CartService;
import co.codingnomads.spring.clientapplication.services.ItemService;
import co.codingnomads.spring.clientapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;


    @GetMapping("/items")
    public String displayItems(Model model, Principal principal){

        User user = userService.getUserByUserName(principal.getName());
        model.addAttribute("items", itemService.getAllItems());
        model.addAttribute(user);
        return "item-list";
    }



    @GetMapping("/cart")
    public String getCart(Model model, Principal principal){

        User user = userService.getUserByUserName(principal.getName());
        Cart cart = cartService.getCartByUserId(user.getId());

        List <CartItem> cartItems = cart.getItems();

        for(CartItem c : cartItems){
            Item item = itemService.getItemById(c.getItemId());
            c.setDescription(item.getDescription());
            c.setName(item.getName());
        }

            model.addAttribute("cartItems", cartItems);

            return "cart";


    }



    @GetMapping("/user")
    public String getUser(Model model, Principal principal){

        model.addAttribute("user", userService.getUserByUserName(principal.getName()));

        return "user";
    }

    @GetMapping("/register")
    public String register(Model model){
        User user = User.builder().build();
        model.addAttribute("user", user);


        return "register";
    }

    @PostMapping(value = "/register")
    public String saveUser(@ModelAttribute("user") User user){

        userService.createUser(user);


        return "redirect:success";
    }


    @PostMapping("/add/{itemId}")
    public String addCart(@PathVariable Long itemId, Model model, Principal principal){

        User user = userService.getUserByUserName(principal.getName());
        cartService.getCartByUserId(user.getId());
        cartService.addNewCartItem(user.getId(),itemId);

        model.addAttribute("user", user);

        return "redirect:/cart";

    }


    @PostMapping("/remove/{id}")
    public String removeCartItem(@PathVariable Long id, Model model, Principal principal){

        User user = userService.getUserByUserName(principal.getName());
        cartService.deleteCartItem(user.getId(), id);

        model.addAttribute("user", user);

        return "redirect:/cart";
    }

    @PostMapping("/update/{id}/{amount}")
    public String updateItemAmount(@PathVariable Long id , Integer amount, Model model, Principal principal){

        User user = userService.getUserByUserName(principal.getName());
        cartService.updateItemAmount(user.getId(), id, amount);

        model.addAttribute("user", user);


        return "redirect:/cart";
    }

    @GetMapping
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/success")
    public String successPage(){


        return "success";
    }


}
