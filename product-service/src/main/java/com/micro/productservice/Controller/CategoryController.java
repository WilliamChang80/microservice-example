package com.micro.productservice.Controller;


import com.micro.productservice.Dto.BaseResponse;
import com.micro.productservice.Dto.CategoryRequest;
import com.micro.productservice.Entity.Category;
import com.micro.productservice.Enum.HttpResponse;
import com.micro.productservice.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private CategoryService categoryService;
    KafkaTemplate<String, String> kafkaTemplate;
    final String topicName = "product";

    @Autowired
    public CategoryController(CategoryService categoryService, KafkaTemplate kafkaTemplate) {
        this.categoryService = categoryService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

    @GetMapping("/categories")
    public BaseResponse getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        sendMessage(categoryList.toString());
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success")
                .data(categoryList).build();
    }

    @PostMapping("/category")
    public BaseResponse createCategory(@RequestBody CategoryRequest request) {
        categoryService.createCategory(request);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").build();
    }
}
