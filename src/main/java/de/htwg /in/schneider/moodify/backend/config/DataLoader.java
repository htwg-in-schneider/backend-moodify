package de.htwg.in.schneider.moodify.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.model.Product;
import de.htwg.in.schneider.moodify.backend.repository.ProductRepository;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
public class DataLoader{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    public CommandLineRunner loadData(ProductRepository repository) {

        return args -> {
            if (repository.count() == 0) { 
                LOGGER.info("Database is empty. Loading initial data...");
                loadInitialData(repository);
            } else {
                LOGGER.info("Database already contains data. Skipping data loading.");
            }
        };
    }

    private void loadInitialData(ProductRepository productRepository) {

        Product waterChallenge = new Product();
        waterChallenge.setTitle("Wasser trinken");
        waterChallenge.setDescription("Trinke heute mindestens 2L Wasser über den gesamten Tag.");
        waterChallenge.setCategory(Category.GESUNDHEIT);
        LOGGER.info("Initial data loaded successfully");


        Product pomodoro = new Product();
        pomodoro.setTitle("Pomodoro Fokus");
        pomodoro.setDescription("Arbeite 25 Minuten konzentriert an einer Aufgabe ohne Ablenkung.");
        pomodoro.setCategory(Category.KONZENTRATION);
        LOGGER.info("Initial data loaded successfully");


        productRepository.saveAll(Arrays.asList(waterChallenge, pomodoro));
    }

}