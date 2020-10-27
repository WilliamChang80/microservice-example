package com.micro.productservice.Repository;

import com.micro.productservice.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByCategoryId(Long categoryId);

    List<Item> findAllByUserId(Long userId);
}
