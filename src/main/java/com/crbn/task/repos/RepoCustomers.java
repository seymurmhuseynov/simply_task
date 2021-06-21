package com.crbn.task.repos;

import com.crbn.task.entities.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RepoCustomers extends CrudRepository<Customers,Long> {

    Optional<Customers> findByUsernameAndDeletedFalse(String username);
    Optional<Customers> findByUsername(String username);

    @Query(value = "SELECT * FROM customers WHERE " +
            "deleted = FALSE " +
            "AND CASE WHEN :name != '' THEN name like concat('%', :name, '%') ELSE TRUE END " +
            "AND CASE WHEN :surname != '' THEN surname like concat('%', :surname, '%') ELSE TRUE END " +
            "AND CASE WHEN :username != '' THEN username like concat('%', :username, '%') ELSE TRUE END " +
            "", nativeQuery = true)
    Page<Customers> findAllByQuery(String name, String surname, String username, Pageable pageable);

}
