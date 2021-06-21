package com.crbn.task.apis;

import com.crbn.task.daos.DaoProducts;
import com.crbn.task.entities.Products;
import com.crbn.task.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@RestController
@RequestMapping("/task/products")
public class ApiProducts {

    @Autowired
    DaoProducts daoProducts;

    @PostMapping(value = "/insert", produces = "application/json")
    public Response insert(@RequestBody @Valid Products products) {
        return daoProducts.insert(products);
    }

    @PostMapping(value = "/update", produces = "application/json")
    public Response update(@RequestBody @Valid Products products) {
        return daoProducts.update(products);
    }

    @PostMapping(value = "/delete/{id}", produces = "application/json")
    public Response delete(@PathVariable @Min(1) long id) {
        return daoProducts.delete(id);
    }

    @GetMapping(value = "/selectById", produces = "application/json")
    public Response selectById(@RequestParam @Min(1) long id) {
        return daoProducts.selectById(id);
    }

    @GetMapping(value = "/selectBy", produces = "application/json")
    public Response selectPagination(@RequestParam String name,
                                     @RequestParam Integer limit,
                                     @RequestParam Integer ascending,
                                     @RequestParam Integer page,
                                     @RequestParam(required = false) String orderBy) {
        return daoProducts.selectPagination(name, limit, ascending, page - 1, orderBy);
    }

}

