package com.crbn.task.repos;

import com.crbn.task.entities.view.ViewOrders;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RepoViewOrders extends CrudRepository<ViewOrders, Long> {
    List<ViewOrders> findAllByStatus(int status);

    List<ViewOrders> findAllByIdCustomer(long idCustomer);

    List<ViewOrders> findAllByFinishedDateBetweenAndStatus(LocalDateTime startDate, LocalDateTime finishedDate, int status);
}
