package co.codingnomads.spring.clientapplication.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    private Long id;
    private Long itemId;
    private Integer amount;
    private String name;
    private String description;
}
