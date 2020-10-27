package com.micro.productservice.Service.Impl;


import com.micro.productservice.Dto.ItemRequest;
import com.micro.productservice.Dto.ItemResponse;
import com.micro.productservice.Entity.Category;
import com.micro.productservice.Entity.Item;
import com.micro.productservice.Repository.CategoryRepository;
import com.micro.productservice.Repository.ItemRepository;
import com.micro.productservice.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    private CategoryRepository categoryRepository;


    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createItem(@RequestBody ItemRequest itemRequest) {
        Category category = categoryRepository.findById(itemRequest.getCategoryId()).orElse(null);
        Item item = Item.builder().name(itemRequest.getName()).category(category).description(itemRequest.
                getDescription()).price(itemRequest.getPrice()).userId(itemRequest.getUserId()).build();
        itemRepository.save(item);
    }

    @Override
    public void updateItem(@RequestBody ItemRequest itemRequest, Long id) {
        Category category = categoryRepository.findById(itemRequest.getCategoryId()).orElse(null);
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new EntityNotFoundException("Item Not Found");
        }
        item.setName(itemRequest.getName()).setCategory(category).setDescription(itemRequest.getDescription())
                .setPrice(itemRequest.getPrice()).setUserId(itemRequest.getUserId());
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new EntityNotFoundException("Item Not Found");
        }
        itemRepository.delete(item);
    }

    @Override
    public List<ItemResponse> getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponse> itemResponses = items.stream().map(this::convertItemToResponse).collect(Collectors.toList());
        return itemResponses;
    }

    @Override
    public List<ItemResponse> getItemsByUserId(Long userId) {
        List<Item> items = itemRepository.findAllByUserId(userId);
        List<ItemResponse> itemResponses = items.stream().map(this::convertItemToResponse).collect(Collectors.toList());
        return itemResponses;
    }

    @Override
    public List<ItemResponse> getItemsByCategory(Long categoryId) {
        List<Item> items = itemRepository.findAllByCategoryId(categoryId);
        List<ItemResponse> itemResponses = items.stream().map(this::convertItemToResponse).collect(Collectors.toList());
        return itemResponses;
    }

    @Override
    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new EntityNotFoundException("Item Not Found");
        }
        return convertItemToResponse(item);
    }

    private ItemResponse convertItemToResponse(Item item) {
        return ItemResponse.builder().id(item.getId()).description(item.getDescription())
                .name(item.getName()).price(item.getPrice()).build();
    }
}
