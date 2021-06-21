package com.crbn.task.apis;

import com.crbn.task.entities.Customers;
import com.crbn.task.daos.DaoCustomers;
import com.crbn.task.model.request.RequestLogin;
import com.crbn.task.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/task/customers")
public class ApiCustomers {

    @Autowired
    DaoCustomers daoCustomers;

    @PostMapping(value = "/login", produces = "application/json")
    public Response login(@RequestBody @Valid @NotNull RequestLogin requestLogin) {
        return daoCustomers.login(requestLogin);
    }

    @PostMapping(value = "/insert", produces = "application/json")
    public Response insert(@RequestBody @Valid Customers customers) {
        return daoCustomers.insert(customers);
    }

    @PostMapping(value = "/update", produces = "application/json")
    public Response update(@RequestBody @Valid Customers customers) {
        return daoCustomers.update(customers);
    }

    @PostMapping(value = "/delete/{id}", produces = "application/json")
    public Response delete(@PathVariable @Min(1) long id) {
        return daoCustomers.delete(id);
    }

    @GetMapping(value = "/selectById", produces = "application/json")
    public Response selectById(@RequestParam @Min(1) long id) {
        return daoCustomers.selectById(id);
    }

    @GetMapping(value = "/selectBy", produces = "application/json")
    public Response selectPagination(@RequestParam String name,
                                     @RequestParam String surname,
                                     @RequestParam String username,
                                     @RequestParam Integer limit,
                                     @RequestParam Integer ascending,
                                     @RequestParam Integer page,
                                     @RequestParam(required = false) String orderBy) {
        return daoCustomers.selectPagination(name, surname, username, limit, ascending, page - 1, orderBy);
    }

}

