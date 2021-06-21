package com.crbn.task.daos;

import com.crbn.task.entities.Address;
import com.crbn.task.entities.view.ViewAddress;
import com.crbn.task.enums.EnumExceptionsMessage;
import com.crbn.task.exceptions.NotFoundException;
import com.crbn.task.model.response.Response;
import com.crbn.task.repos.RepoAddress;
import com.crbn.task.repos.RepoViewAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DaoAddress {

    @Autowired
    RepoAddress repoAddress;

    @Autowired
    RepoViewAddress repoViewAddress;

    public Response insert(Address address) {
        Optional<Address> oldAddress=repoAddress.findById(address.getId());
        if(oldAddress.isPresent()){
            repoAddress.save(
                    oldAddress.get()
                            .setIdCustomer(address.getIdCustomer())
                            .setAddress(address.getAddress())
            );
        }else{
            repoAddress.save(address.setDeleted(false));
        }
        return new Response();
    }

    public Response selectById(long id) {
        Optional<ViewAddress> address = repoViewAddress.findById(id);
        if (address.isPresent()) {
            return new Response().setResponse(address);
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response selectByIdCustomer(long idCustomer){
        List<ViewAddress> addresses=repoViewAddress.findAllByIdCustomer(idCustomer);
        if(addresses!=null&&addresses.size()>0){
            return new Response().setResponse(addresses);
        }else{
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

}
