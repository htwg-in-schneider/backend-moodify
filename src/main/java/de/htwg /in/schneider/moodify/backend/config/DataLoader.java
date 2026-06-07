package de.htwg.in.schneider.moodify.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.model.Difficulty;
import de.htwg.in.schneider.moodify.backend.model.Challenge;
import de.htwg.in.schneider.moodify.backend.model.Visionboard;
import de.htwg.in.schneider.moodify.backend.repository.ChallengeRepository;
import de.htwg.in.schneider.moodify.backend.repository.VisionboardRepository;
import de.htwg.in.schneider.moodify.backend.repository.VisionboardImagesRepository;
import de.htwg.in.schneider.moodify.backend.model.VisionboardImages;
import java.util.List;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;



@Configuration
public class DataLoader{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    public CommandLineRunner loadData(ChallengeRepository repository, VisionboardRepository visionboardRepository) {

        return args -> {
            if (repository.count() == 0) { 
                LOGGER.info("Database is empty. Loading initial data...");
                loadInitialData(repository, visionboardRepository);
            } else {
                LOGGER.info("Database already contains data. Skipping data loading.");
            }
        };
    }

    private void loadInitialData(ChallengeRepository challengeRepository, VisionboardRepository visionboardRepository) {

        Challenge waterChallenge = new Challenge();
        waterChallenge.setName("Wasser trinken");
        waterChallenge.setDescription("Trinke heute mindestens 2L Wasser über den gesamten Tag.");
        waterChallenge.setDifficulty(Difficulty.MITTEL);
        waterChallenge.setCategory(Category.GESUNDHEIT);
        LOGGER.info("Initial data loaded successfully");


        Challenge pomodoro = new Challenge();
        pomodoro.setName("Pomodoro Fokus");
        pomodoro.setDescription("Arbeite 25 Minuten konzentriert an einer Aufgabe ohne Ablenkung.");
        pomodoro.setDifficulty(Difficulty.MITTEL);
        pomodoro.setCategory(Category.KONZENTRATION);
        LOGGER.info("Initial data loaded successfully");


        challengeRepository.saveAll(Arrays.asList(waterChallenge, pomodoro));


        Visionboard vs1 = new Visionboard();
        vs1.setTitle("Reise2026");
        vs1.setCreatedAt(LocalDateTime.now());
        vs1.setCategory(Category.REISE);

        VisionboardImages img1 = new VisionboardImages();
        img1.setImageUrl("/images/beach.jpg");
        img1.setXPosition(20);
        img1.setYPosition(40);
        img1.setWidth(200);
        img1.setHeight(150);
        img1.setVisionboard(vs1);

        VisionboardImages img2 = new VisionboardImages();
        img2.setImageUrl("/images/plane.jpg");
        img2.setXPosition(250);
        img2.setYPosition(80);
        img2.setWidth(180);
        img2.setHeight(120);
        img2.setVisionboard(vs1);

        VisionboardImages img3 = new VisionboardImages();
        img3.setImageUrl("/images/hotel.jpg");
        img3.setXPosition(120);
        img3.setYPosition(250);
        img3.setWidth(220);
        img3.setHeight(160);
        img3.setVisionboard(vs1);

        vs1.setImages(Arrays.asList(img1, img2, img3));
        LOGGER.info("Initial data loaded successfully");


        Visionboard vs2 = new Visionboard();
        vs2.setTitle("Ziele2026");
        vs2.setCreatedAt(LocalDateTime.now());
        vs2.setCategory(Category.ZIELE);

        VisionboardImages img4 = new VisionboardImages();
        img4.setImageUrl("/images/future.jpg");
        img4.setXPosition(20);
        img4.setYPosition(40);
        img4.setWidth(200);
        img4.setHeight(150);
        img4.setVisionboard(vs1);

        VisionboardImages img5 = new VisionboardImages();
        img5.setImageUrl("/images/job.jpg");
        img5.setXPosition(250);
        img5.setYPosition(80);
        img5.setWidth(180);
        img5.setHeight(120);
        img5.setVisionboard(vs1);

        VisionboardImages img6 = new VisionboardImages();
        img6.setImageUrl("/images/time.jpg");
        img6.setXPosition(120);
        img6.setYPosition(250);
        img6.setWidth(220);
        img6.setHeight(160);
        img6.setVisionboard(vs1);

        vs2.setImages(Arrays.asList(img4, img5, img6));
        LOGGER.info("Initial data loaded successfully");


        visionboardRepository.saveAll(Arrays.asList(vs1, vs2));


    }

}