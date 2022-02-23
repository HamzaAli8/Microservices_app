package co.codingnomads.spring.clientapplication.services;

import co.codingnomads.spring.clientapplication.models.Cart;
import co.codingnomads.spring.clientapplication.models.CartItem;
import co.codingnomads.spring.clientapplication.models.Item;
import co.codingnomads.spring.clientapplication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartService {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    public Cart getCartByUserId (Long userId) {
        ResponseEntity<Cart> response = restTemplate.getForEntity("http://CART-MICROSERVICE/cart/" + userId , Cart.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }



    public Cart addNewCartItem(Long userId, Long itemId){

            ResponseEntity<Cart> response = restTemplate.postForEntity("http://CART-MICROSERVICE/cart/add/"+ userId +"/" + itemId,null, Cart.class);
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
                return response.getBody();
            }else {
                return null;
            }

    }


    public Cart deleteCartItem(Long userId, Long cartItemId){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Cart> entity = new HttpEntity<Cart>(headers);
        ResponseEntity<Cart> response = restTemplate.exchange("http://CART-MICROSERVICE/cart/remove/"+ userId +"/" + cartItemId, HttpMethod.DELETE,entity,Cart.class);
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return response.getBody();
        }else {
            return null;
        }
    }

    public Cart updateItemAmount(Long userId, Long cartItemId,Integer amount){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Cart> entity = new HttpEntity<Cart>(headers);
        ResponseEntity<Cart> response = restTemplate.exchange("http://CART-MICROSERVICE/cart/"+ userId +"/" + cartItemId+"/" + amount, HttpMethod.PATCH,entity,Cart.class);
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return response.getBody();
        }else {
            return null;
        }
    }




}
