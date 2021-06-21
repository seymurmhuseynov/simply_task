package com.crbn.task.daos;

import com.crbn.task.entities.Customers;
import com.crbn.task.enums.EnumExceptionsMessage;
import com.crbn.task.exceptions.AlreadyExistException;
import com.crbn.task.exceptions.NotFoundException;
import com.crbn.task.exceptions.WrongPasswordException;
import com.crbn.task.model.request.RequestLogin;
import com.crbn.task.model.response.Response;
import com.crbn.task.model.response.ResponsePagination;
import com.crbn.task.repos.RepoCustomers;
import com.crbn.task.services.CustomerDetailsService;
import com.crbn.task.utils.EncryptUtils;
import com.crbn.task.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DaoCustomers {

    @Autowired
    EncryptUtils encryptUtils;

    @Autowired
    CustomerDetailsService customerDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    RepoCustomers repoCustomers;


    public Response login(RequestLogin requestLogin) {
        UserDetails userDetails = customerDetailsService.loadUserByUsername(requestLogin.getUsername());
        try {
            if (userDetails.getUsername().equals(requestLogin.getUsername())) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        requestLogin.getUsername(),
                        encryptUtils.encrypt(requestLogin.getPassword()))
                );
            }
        } catch (BadCredentialsException e) {
            throw new WrongPasswordException(EnumExceptionsMessage.USER_WRONG_USERNAME_OR_PASSWORD.getMessage());
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new Response().setResponse(jwt);
    }

    public Response insert(Customers customer) {
        if (repoCustomers.findByUsernameAndDeletedFalse(customer.getUsername()).isPresent())
            throw new AlreadyExistException(EnumExceptionsMessage.DATA_ALREADY_EXIST.getMessage());
        repoCustomers.save(
                customer.setPassword(new EncryptUtils().encrypt(customer.getPassword()))
                        .setDeleted(false));
        return new Response();

    }

    public Response update(Customers customer) {
        Optional<Customers> oldCustomer = repoCustomers.findById(customer.getId());
        if (oldCustomer.isPresent()) {
            if (!oldCustomer.get().getUsername().equals(customer.getUsername()))
                if (repoCustomers.findByUsernameAndDeletedFalse(customer.getUsername()).isPresent())
                    throw new AlreadyExistException(EnumExceptionsMessage.DATA_ALREADY_EXIST.getMessage());
            repoCustomers.save(
                    oldCustomer.get()
                            .setBalance(customer.getBalance())
                            .setName(customer.getName())
                            .setSurname(customer.getSurname())
                            .setUsername(customer.getUsername())
                            .setPassword(
                                    customer.getPassword() == null || customer.getPassword().length() == 0
                                            ? oldCustomer.get().getPassword()
                                            : new EncryptUtils().encrypt(customer.getPassword())
                            )
            );
            return new Response();
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response delete(long id) {
        Optional<Customers> customer = repoCustomers.findById(id);
        if (customer.isPresent()) {
            repoCustomers.save(customer.get().setDeleted(true));
            return new Response();
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response selectById(long id) {
        Optional<Customers> customer = repoCustomers.findById(id);
        if (customer.isPresent()) {
            return new Response().setResponse(customer);
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response selectPagination(String name, String surname, String username, Integer limit, Integer ascending, Integer page, String orderBy) {
        Sort sort;
        if (ascending == 0) sort = Sort.by(orderBy != null ? orderBy : "id").descending();
        else sort = Sort.by(orderBy).ascending();
        Pageable pageable = PageRequest.of(page != null ? page : 0, limit != null ? limit : 20, sort);
        Page<Customers> pages = repoCustomers.findAllByQuery(name, surname, username, pageable);
        if (pages != null && pages.getTotalElements() > 0) {
            return new Response().setResponse(new ResponsePagination()
                    .setDataList(pages.getContent()).setTotal(pages.getTotalElements()).setPageSize(pages.getTotalPages()));
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

}
