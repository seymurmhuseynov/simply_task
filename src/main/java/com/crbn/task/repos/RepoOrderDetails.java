package com.crbn.task.repos;

import com.crbn.task.entities.OrderDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface RepoOrderDetails extends CrudRepository<OrderDetails, Long> {

    @Query(value = "SELECT COALESCE(sum(price),0) FROM view_order_details" +
            " where id_order = :idOrder", nativeQuery = true)
    double sumPriceByOrder(long idOrder);

}
