package co.codingnomads.spring.itemmicroservice.repository;

import co.codingnomads.spring.itemmicroservice.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String name);
}
