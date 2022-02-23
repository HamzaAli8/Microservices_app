package co.codingnomads.spring.clientapplication.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
}
