package co.codingnomads.spring.usermicroservice.service;

import co.codingnomads.spring.usermicroservice.models.User;
import co.codingnomads.spring.usermicroservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public User getUserById(Long id){

        Optional<User> optional;

        if((optional = userRepository.findById(id)).isEmpty()){

            return null;
        }else{

            return optional.get();
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createNewUser(User newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public User updateUser(User updatedUser) {
        User user = userRepository.findByUsername(updatedUser.getUsername());
        BeanUtils.copyProperties(updatedUser, user);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    private void checkPassword(String password){

        if(password == null){

            throw new IllegalStateException("You must set a password");
        }
        if(password.length() <= 6){

            throw new IllegalStateException("Password is too short and must be longer than 6 characters");
        }
    }


    public List<User> getAllUsers(){

        return userRepository.findAll();

    }

}
