package com.crbn.task.repos;

import com.crbn.task.entities.Orders;
import org.springframework.data.repository.CrudRepository;

public interface RepoOrders extends CrudRepository<Orders,Long> {
}
