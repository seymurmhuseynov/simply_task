package com.crbn.task.repos;

import com.crbn.task.entities.view.ViewAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoViewAddress extends CrudRepository<ViewAddress,Long> {

    List<ViewAddress> findAllByIdCustomer(long idCustomer);

}
