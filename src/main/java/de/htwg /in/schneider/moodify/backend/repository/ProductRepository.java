package de.htwg.in.schneider.moodify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.htwg.in.schneider.moodify.backend.model.Category;
import java.util.List;

import de.htwg.in.schneider.moodify.backend.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long> {

List<Product> findByTitleContainingIgnoreCase(String title);

List<Product> findByCategory(Category category);

List<Product> findByTitleContainingIgnoreCaseAndCategory(String title, Category category);

}