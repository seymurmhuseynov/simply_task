package com.crbn.task.repos;

import com.crbn.task.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RepoProducts extends CrudRepository<Products,Long> {
    Optional<Products> findAllByName(String name);

    @Query(value = "SELECT * FROM products WHERE " +
            "deleted = FALSE " +
            "AND CASE WHEN :name != '' THEN name like concat('%', :name, '%') ELSE TRUE END ", nativeQuery = true)
    Page<Products> findAllByQuery(String name, Pageable pageable);

}
