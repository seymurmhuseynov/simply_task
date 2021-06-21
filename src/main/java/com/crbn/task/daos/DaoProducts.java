package com.crbn.task.daos;

import com.crbn.task.entities.Products;
import com.crbn.task.enums.EnumExceptionsMessage;
import com.crbn.task.exceptions.AlreadyExistException;
import com.crbn.task.exceptions.NotFoundException;
import com.crbn.task.model.response.Response;
import com.crbn.task.model.response.ResponsePagination;
import com.crbn.task.repos.RepoProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DaoProducts {

    @Autowired
    RepoProducts repoProducts;


    public Response insert(Products products) {
        if (repoProducts.findAllByName(products.getName()).isPresent())
            throw new AlreadyExistException(EnumExceptionsMessage.DATA_ALREADY_EXIST.getMessage());
        repoProducts.save(products.setDeleted(false));
        return new Response();

    }

    public Response update(Products products) {
        Optional<Products> oldProduct = repoProducts.findById(products.getId());
        if (oldProduct.isPresent()) {
            repoProducts.save(
                    oldProduct.get()
                            .setName(products.getName())
                            .setPrice(products.getPrice())
            );
            return new Response();
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response delete(long id){
        Optional<Products> product=repoProducts.findById(id);
        if(product.isPresent()){
            repoProducts.save(product.get().setDeleted(true));
            return new Response();
        }else{
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response selectById(long id) {
        Optional<Products> product = repoProducts.findById(id);
        if (product.isPresent()) {
            return new Response().setResponse(product);
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response selectPagination(String name, Integer limit, Integer ascending, Integer page, String orderBy) {
        Sort sort;
        if (ascending == 0) sort = Sort.by(orderBy != null ? orderBy : "id").descending();
        else sort = Sort.by(orderBy).ascending();
        Pageable pageable = PageRequest.of(page != null ? page : 0, limit != null ? limit : 20, sort);
        Page<Products> pages = repoProducts.findAllByQuery(name, pageable);
        if (pages != null && pages.getTotalElements() > 0) {
            return new Response().setResponse(new ResponsePagination()
                    .setDataList(pages.getContent()).setTotal(pages.getTotalElements()).setPageSize(pages.getTotalPages()));
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

}
