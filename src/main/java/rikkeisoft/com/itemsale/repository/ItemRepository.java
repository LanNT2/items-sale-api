package rikkeisoft.com.itemsale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rikkeisoft.com.itemsale.model.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Query(value = "SELECT * from `item` WHERE LOWER(name) LIKE %?1% and is_deleted=?2", nativeQuery = true)
    Page<Item> relativeSearching(String name,Integer isDeleted,Pageable pageable);

    Page<Item> findByIsDeleted(Integer isDeleted,Pageable pageable);

    Optional<Item> findAllByIdAndIsDeleted(Integer id, Integer isDeleted);

    Optional<Item> findByNameAndIsDeleted(String name, Integer isDeleted);


}
