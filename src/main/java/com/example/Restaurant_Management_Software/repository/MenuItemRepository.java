package com.example.Restaurant_Management_Software.repository;



import com.example.Restaurant_Management_Software.model.MenuCategory;
import com.example.Restaurant_Management_Software.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategory(MenuCategory category);
    List<MenuItem> findByAvailableTrue();
    List<MenuItem> findByNameContainingIgnoreCase(String name);

    Optional<MenuItem> findByIdAndAvailableTrue(Long menuItemId);
}