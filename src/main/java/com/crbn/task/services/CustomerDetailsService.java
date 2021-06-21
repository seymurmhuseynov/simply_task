package com.crbn.task.services;

import com.crbn.task.entities.Customers;
import com.crbn.task.exceptions.NotFoundException;
import com.crbn.task.repos.RepoCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    RepoCustomers repoCustomers;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customers> user = repoCustomers.findByUsername(username);
        if (user.isPresent()) {
            return user.map(users -> new User(users.getUsername(), users.getPassword(), new ArrayList<>())).orElse(null);
        } else {
            throw new NotFoundException("Data not found");
        }
    }

    public Customers localSelectUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customers customers = localSelectByUsername(userDetails.getUsername());
        if (customers != null) {
            return customers;
        } else {
            return null;
        }
    }

    public Customers localSelectByUsername(String username) {
        return repoCustomers.findByUsername(username).orElseThrow(null);
    }

}
