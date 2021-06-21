package com.crbn.task.repos;

import com.crbn.task.entities.view.ViewOrderDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoViewOrderDetails extends CrudRepository<ViewOrderDetails, Long> {

    List<ViewOrderDetails> findAllByIdOrder(long idOrder);

}
