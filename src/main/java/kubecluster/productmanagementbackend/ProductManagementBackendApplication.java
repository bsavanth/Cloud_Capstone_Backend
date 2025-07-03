package kubecluster.productmanagementbackend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ProductManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagementBackendApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner printAllBeans(ApplicationContext ctx) {
//        return args -> {
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames); // Optional: Sort for readability
//            System.out.println("=== List of Beans Created by Spring Boot ===");
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//        };
//    }


}
