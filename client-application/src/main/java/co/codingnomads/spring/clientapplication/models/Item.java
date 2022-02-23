package co.codingnomads.spring.clientapplication.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    private Long itemId;
    private String name;
    private String description;
}
