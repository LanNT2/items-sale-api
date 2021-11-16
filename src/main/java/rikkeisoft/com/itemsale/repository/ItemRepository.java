package rikkeisoft.com.itemsale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rikkeisoft.com.itemsale.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Query(value = "SELECT * from `item` WHERE LOWER(name) LIKE %?1%", nativeQuery = true)
    Page<Item> relativeSearching(String name, Pageable pageable);

    Page<Item> findAll(Pageable pageable);
}
