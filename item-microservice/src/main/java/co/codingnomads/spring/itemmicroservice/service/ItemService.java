package co.codingnomads.spring.itemmicroservice.service;

import co.codingnomads.spring.itemmicroservice.models.Item;
import co.codingnomads.spring.itemmicroservice.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;


    public Item getItemById(Long id){

        Optional<Item> optional;

        if((optional = itemRepository.findById(id)).isEmpty()){

            return null;
        }else {
            return optional.get();
        }
    }

    public List<Item> getAllItems(){

        return itemRepository.findAll();
    }


    public void deleteItemById(Long id){

        itemRepository.deleteById(id);
    }

    public Item updateItem(Item updateItem){

        Item item = itemRepository.findByName(updateItem.getName());
        BeanUtils.copyProperties(updateItem, item);
        return itemRepository.save(item);
    }

    public Item insertNewItem(Item newItem){

        newItem.setName(newItem.getName());
        newItem.setDescription(newItem.getDescription());
        return itemRepository.save(newItem);
    }
}
