package com.micro.productservice.Service;


import com.micro.productservice.Dto.ItemRequest;
import com.micro.productservice.Dto.ItemResponse;

import java.util.List;

public interface ItemService {
    void createItem(ItemRequest itemRequest);

    void updateItem(ItemRequest itemRequest, Long id);

    void deleteItem(Long id);

    List<ItemResponse> getAllItems();

    List<ItemResponse> getItemsByUserId(Long userId);

    List<ItemResponse> getItemsByCategory(Long categoryId);

    ItemResponse getItemById(Long id);
}
