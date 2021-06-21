package com.crbn.task.apis;

import com.crbn.task.daos.DaoAddress;
import com.crbn.task.entities.Address;
import com.crbn.task.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/task/address")
public class ApiAddress {

    @Autowired
    DaoAddress daoAddress;

    @PostMapping(value = "/insert", produces = "application/json")
    public Response insert(@RequestBody @Valid Address address) {
        return daoAddress.insert(address);
    }

    @GetMapping(value = "/selectById", produces = "application/json")
    public Response selectById(@RequestParam @Min(1) long id) {
        return daoAddress.selectById(id);
    }

    @GetMapping(value = "/selectByIdCustomer", produces = "application/json")
    public Response selectByIdCustomer(@RequestParam @Min(1) long idCustomer) {
        return daoAddress.selectByIdCustomer(idCustomer);
    }

}
