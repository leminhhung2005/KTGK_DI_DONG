/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.kiem_tra_gk_di_dong.config;

import com.example.kiem_tra_gk_di_dong.entity.Product;
import com.example.kiem_tra_gk_di_dong.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Chỉ thêm dữ liệu nếu database trống
        if (productRepository.count() == 0) {
            Product product1 = new Product();
            product1.setName("Beef and Mustard Pie");
            product1.setPrice(8.25);
            product1.setImage("https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg");
            product1.setDescription("A delicious beef and mustard pie with a golden pastry crust.");
            product1.setInstructions("Preheat the oven to 150C/300F/Gas 2. Toss the beef and flour together...");
            productRepository.save(product1);
            
            Product product2 = new Product();
            product2.setName("Chicken Curry");
            product2.setPrice(12.50);
            product2.setImage("https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg");
            product2.setDescription("Spicy and flavorful chicken curry with aromatic spices.");
            product2.setInstructions("Heat oil in a large pot. Add onions and cook until golden...");
            productRepository.save(product2);
            
            System.out.println("Sample data initialized!");
        }
    }
}