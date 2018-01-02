package com.example.demo;

import com.example.demo.models.Book;
import com.example.demo.models.Clothing;
import com.example.demo.models.Flat;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ClothingRepository;
import com.example.demo.repository.FlatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    CommandLineRunner runner(BookRepository bookRepository, FlatRepository flatRepository, ClothingRepository clothingRepository){
	    return args -> {
	        bookRepository.save(new Book("Anna Karenina", "Lev Tolstoi"));
            bookRepository.save(new Book("Vojna i mir", "Lev Tolstoi"));
            flatRepository.save(new Flat("Sliska 14", 72));
            flatRepository.save(new Flat("Krakowska 27", 30));
            clothingRepository.save(new Clothing("Zara", "Jacket"));
            clothingRepository.save(new Clothing("Pierre Cardin", "Boots"));
	    };
    }
}
