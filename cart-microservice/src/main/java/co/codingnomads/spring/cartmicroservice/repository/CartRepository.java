package co.codingnomads.spring.cartmicroservice.repository;

import co.codingnomads.spring.cartmicroservice.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart findByUserId(Long userId);
}
