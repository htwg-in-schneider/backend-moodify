package de.htwg.in.schneider.moodify.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.model.User;
import de.htwg.in.schneider.moodify.backend.model.Role;
import de.htwg.in.schneider.moodify.backend.model.Difficulty;
import de.htwg.in.schneider.moodify.backend.model.Challenge;
import de.htwg.in.schneider.moodify.backend.model.Visionboard;
import de.htwg.in.schneider.moodify.backend.model.Review;
import de.htwg.in.schneider.moodify.backend.repository.ChallengeRepository;
import de.htwg.in.schneider.moodify.backend.repository.VisionboardRepository;
import de.htwg.in.schneider.moodify.backend.repository.UserRepository;
import de.htwg.in.schneider.moodify.backend.repository.ReviewRepository;
import de.htwg.in.schneider.moodify.backend.model.VisionboardImages;


import java.util.Arrays;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;


@Configuration
public class DataLoader{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    public CommandLineRunner loadData(ChallengeRepository repository, VisionboardRepository visionboardRepository, UserRepository userRepository, ReviewRepository reviewRepository) {

        return args -> {

            loadInitialUsers(userRepository);

            LOGGER.info("Loading or updating initial data...");
            loadInitialData(repository, visionboardRepository, reviewRepository);
        };
    }

    private void loadInitialData(ChallengeRepository challengeRepository, VisionboardRepository visionboardRepository, ReviewRepository reviewRepository) {

        Challenge waterChallenge = new Challenge();
        waterChallenge.setTitle("Wasser trinken");
        waterChallenge.setDescription("Trinke heute mindestens 2L Wasser über den gesamten Tag.");
        waterChallenge.setDifficulty(Difficulty.MITTEL);
        waterChallenge.setCategory(Category.MOTIVATION);
        LOGGER.info("Initial data loaded successfully");


        Challenge pomodoro = new Challenge();
        pomodoro.setTitle("Pomodoro Fokus");
        pomodoro.setDescription("Arbeite 25 Minuten konzentriert an einer Aufgabe ohne Ablenkung.");
        pomodoro.setDifficulty(Difficulty.MITTEL);
        pomodoro.setCategory(Category.MOTIVATION);
        LOGGER.info("Initial data loaded successfully");

  
        Challenge gratitudeChallenge = new Challenge();
        gratitudeChallenge.setTitle("Dankbarkeit zeigen");
        gratitudeChallenge.setDescription("Schreibe heute drei Dinge auf, für die du dankbar bist.");
        gratitudeChallenge.setDifficulty(Difficulty.EASY);
        gratitudeChallenge.setCategory(Category.MOTIVATION);

        Challenge walkChallenge = new Challenge();
        walkChallenge.setTitle("Spaziergang im Freien");
        walkChallenge.setDescription("Gehe heute mindestens 20 Minuten an der frischen Luft spazieren.");
        walkChallenge.setDifficulty(Difficulty.EASY);
        walkChallenge.setCategory(Category.ENTSPANNUNG);

        Challenge breathingChallenge = new Challenge();
        breathingChallenge.setTitle("Atemübung");
        breathingChallenge.setDescription("Nimm dir 5 Minuten Zeit für bewusstes Atmen und Entspannen.");
        breathingChallenge.setDifficulty(Difficulty.EASY);
        breathingChallenge.setCategory(Category.ENTSPANNUNG);

        Challenge journalChallenge = new Challenge();
        journalChallenge.setTitle("Tagebuch schreiben");
        journalChallenge.setDescription("Reflektiere deinen Tag und schreibe mindestens 5 Minuten über deine Gedanken.");
        journalChallenge.setDifficulty(Difficulty.MITTEL);
        journalChallenge.setCategory(Category.MOTIVATION);

        Challenge digitalDetox = new Challenge();
        digitalDetox.setTitle("Digital Detox");
        digitalDetox.setDescription("Verzichte für eine Stunde komplett auf Social Media.");
        digitalDetox.setDifficulty(Difficulty.MITTEL);
        digitalDetox.setCategory(Category.ABLENKUNG);

        Challenge focusChallenge = new Challenge();
        focusChallenge.setTitle("Fokuszeit");
        focusChallenge.setDescription("Arbeite 30 Minuten lang konzentriert an einer Aufgabe ohne Unterbrechung.");
        focusChallenge.setDifficulty(Difficulty.MITTEL);
        focusChallenge.setCategory(Category.FOKUS);

        Challenge complimentChallenge = new Challenge();
        complimentChallenge.setTitle("Kompliment machen");
        complimentChallenge.setDescription("Mache heute einer Person ein ehrliches Kompliment.");
        complimentChallenge.setDifficulty(Difficulty.EASY);
        complimentChallenge.setCategory(Category.MOTIVATION);

        Challenge earlySleep = new Challenge();
        earlySleep.setTitle("Früher schlafen");
        earlySleep.setDescription("Gehe heute mindestens 30 Minuten früher ins Bett als gewöhnlich.");
        earlySleep.setDifficulty(Difficulty.MITTEL);
        earlySleep.setCategory(Category.ENTSPANNUNG);

        Challenge creativeChallenge = new Challenge();
        creativeChallenge.setTitle("Kreativ werden");
        creativeChallenge.setDescription("Zeichne, male oder schreibe heute 15 Minuten lang etwas Kreatives.");
        creativeChallenge.setDifficulty(Difficulty.MITTEL);
        creativeChallenge.setCategory(Category.MOTIVATION);

        Challenge cleanDesk = new Challenge();
        cleanDesk.setTitle("Arbeitsplatz aufräumen");
        cleanDesk.setDescription("Räume deinen Schreibtisch oder einen Bereich deines Zimmers auf.");
        cleanDesk.setDifficulty(Difficulty.EASY);
        cleanDesk.setCategory(Category.FOKUS);


        saveChallengeIfNotExists(challengeRepository, waterChallenge);
        saveChallengeIfNotExists(challengeRepository, pomodoro);

        saveChallengeIfNotExists(challengeRepository, gratitudeChallenge);
        saveChallengeIfNotExists(challengeRepository, walkChallenge);
        saveChallengeIfNotExists(challengeRepository, breathingChallenge);
        saveChallengeIfNotExists(challengeRepository, journalChallenge);
        saveChallengeIfNotExists(challengeRepository, digitalDetox);
        saveChallengeIfNotExists(challengeRepository, focusChallenge);
        saveChallengeIfNotExists(challengeRepository, complimentChallenge);
        saveChallengeIfNotExists(challengeRepository, earlySleep);
        saveChallengeIfNotExists(challengeRepository, creativeChallenge);
        saveChallengeIfNotExists(challengeRepository, cleanDesk);

        
        Challenge savedWater = challengeRepository.findByTitle("Wasser trinken").get();
Challenge savedPomodoro = challengeRepository.findByTitle("Pomodoro Fokus").get();

Review review1 = new Review();
review1.setText("Hat mir geholfen, mehr Wasser zu trinken.");
review1.setChallenge(savedWater);

Review review2 = new Review();
review2.setText("Einfache Challenge für den Alltag.");
review2.setChallenge(savedWater);

Review review3 = new Review();
review3.setText("Mit der Pomodoro-Technik konnte ich mich besser konzentrieren.");
review3.setChallenge(savedPomodoro);

Review review4 = new Review();
review4.setText("Sehr motivierend und effektiv.");
review4.setChallenge(savedPomodoro);

reviewRepository.saveAll(Arrays.asList(review1, review2, review3, review4));



        Visionboard vs1 = new Visionboard();
        vs1.setTitle("Reise2026");
        vs1.setCreatedAt(LocalDateTime.now());
        vs1.setCategory(Category.MOTIVATION);

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
        vs2.setCategory(Category.ABLENKUNG);

        VisionboardImages img4 = new VisionboardImages();
        img4.setImageUrl("/images/future.jpg");
        img4.setXPosition(20);
        img4.setYPosition(40);
        img4.setWidth(200);
        img4.setHeight(150);
        img4.setVisionboard(vs2);

        VisionboardImages img5 = new VisionboardImages();
        img5.setImageUrl("/images/job.jpg");
        img5.setXPosition(250);
        img5.setYPosition(80);
        img5.setWidth(180);
        img5.setHeight(120);
        img5.setVisionboard(vs2);

        VisionboardImages img6 = new VisionboardImages();
        img6.setImageUrl("/images/time.jpg");
        img6.setXPosition(120);
        img6.setYPosition(250);
        img6.setWidth(220);
        img6.setHeight(160);
        img6.setVisionboard(vs2);

        vs2.setImages(Arrays.asList(img4, img5, img6));
        LOGGER.info("Initial data loaded successfully");


        visionboardRepository.saveAll(Arrays.asList(vs1, vs2));


    

    }

    private void loadInitialUsers(UserRepository userRepository) {

        upsertUser(userRepository, "kardln12@icloud.com", "kardln12@icloud.com", "kardln12@icloud.com", "Kardelen2004", "auth0|6a287d49f70895fb97028c48", Role.ADMIN); 
        upsertUser(userRepository, "kardelenkantar49@gmail.com", "kardelenkantar49@gmail.com", "kardelenkantar49@gmail.com", "Karege21", "auth0|6a288b314bd6301a73f57805", Role.USER);
        }


        private void upsertUser(UserRepository userRepository, String username, String name, String email, String password, String oauthId, Role role) {
        Optional<User> existing = userRepository.findByEmail(email);
        if (existing.isPresent()) {
            User u = existing.get();
            u.setUsername(username);
            u.setName(name);
            u.setEmail(email);
            u.setPassword(password);
            u.setOauthId(oauthId);
            u.setRole(role);
            userRepository.save(u);
            LOGGER.info("Updated existing {} user with email={}", role, email);
        } else {
            User u1 = new User();
            u1.setUsername(username);
            u1.setName(name);
            u1.setEmail(email);
            u1.setPassword(password);
            u1.setOauthId(oauthId);
            u1.setRole(role);
            userRepository.save(u1);
            LOGGER.info("Created new {} user with email={}", role, email);
        }
    }


    private void saveChallengeIfNotExists(ChallengeRepository challengeRepository, Challenge challenge) {
    if (!challengeRepository.existsByTitle(challenge.getTitle())) {
        challengeRepository.save(challenge);
    }
}

}