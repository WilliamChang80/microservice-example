package com.micro.productservice.Controller;

import com.micro.productservice.Dto.BaseResponse;
import com.micro.productservice.Dto.ItemRequest;
import com.micro.productservice.Dto.ItemResponse;
import com.micro.productservice.Enum.HttpResponse;
import com.micro.productservice.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public BaseResponse createItem(@RequestBody ItemRequest itemRequest) {
        itemService.createItem(itemRequest);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").build();
    }

    @PutMapping("/item/{id}")
    public BaseResponse updateItem(@RequestBody ItemRequest itemRequest, @PathVariable Long id) {
        itemService.updateItem(itemRequest, id);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").build();
    }

    @DeleteMapping("/item/{id}")
    public BaseResponse deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").build();
    }

    @GetMapping("/items")
    public BaseResponse getAllItems() {
        List<ItemResponse> items = itemService.getAllItems();
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").data(items).build();
    }

    @GetMapping("/items/user/{userId}")
    public BaseResponse getItemsByUserId(@PathVariable Long userId) {
        List<ItemResponse> items = itemService.getItemsByUserId(userId);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").data(items).build();
    }

    @GetMapping("/items/category/{categoryId}")
    public BaseResponse getItemsByCategory(@PathVariable Long categoryId) {
        List<ItemResponse> items = itemService.getItemsByCategory(categoryId);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").data(items).build();
    }

    @GetMapping("/item/{id}")
    public BaseResponse getItemById(@PathVariable Long id) {
        ItemResponse item = itemService.getItemById(id);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").data(item).build();
    }
}
