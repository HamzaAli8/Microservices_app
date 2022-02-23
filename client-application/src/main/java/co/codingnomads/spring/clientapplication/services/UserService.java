package co.codingnomads.spring.clientapplication.services;

import co.codingnomads.spring.clientapplication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    public User getUserByUserName(String username) {

        ResponseEntity<User> response = restTemplate.getForEntity("http://USER-MICROSERVICE/user/username/" + username, User.class);
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return response.getBody();
        }else {
            return null;
        }
    }

    public User createUser(User newUser){

        ResponseEntity<User> response = restTemplate.postForEntity("http://USER-MICROSERVICE/user/register",newUser, User.class);
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return response.getBody();
        }else {
            return null;
        }
    }

    public User getUserId(Long userId){

        ResponseEntity<User> response = restTemplate.getForEntity("http://USER-MICROSERVICE/user/" + userId, User.class);
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return response.getBody();
        }else {
            return null;
        }
    }
}

