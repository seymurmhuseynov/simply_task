package com.crbn.task.repos;

import com.crbn.task.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface RepoAddress extends CrudRepository<Address,Long> {
}
