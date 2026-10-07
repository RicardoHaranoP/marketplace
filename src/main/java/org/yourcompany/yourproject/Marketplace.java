/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author 10969836996
 */
@SpringBootApplication
@EnableAsync 
public class Marketplace {
    public static void main(String[] args) {
        SpringApplication.run(Marketplace.class, args);
    }
}
