package rikkeisoft.com.itemsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rikkeisoft.com.itemsale.model.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserId(Integer userId);
}
