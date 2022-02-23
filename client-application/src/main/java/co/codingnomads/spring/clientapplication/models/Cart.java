package co.codingnomads.spring.clientapplication.models;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    private Long id;
    private Long userId;
    private List<CartItem> items;

    public void addCartItem(Long itemId) {
        items.add(CartItem.builder().itemId(itemId).amount(1).build());
    }
}
