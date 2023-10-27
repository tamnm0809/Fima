package com.fima.service.impl;

import com.fima.entity.Users;
import com.fima.repository.UsersRepository;
import com.fima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Page<Users> getAllUsersPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 13);
        return usersRepository.findAll(pageable);
    }

    @Override
    public Users addUsers(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public void updateUsers(Users users) {
        usersRepository.save(users);
    }

    @Override
    public void deleteUsers(String id_users) {
        usersRepository.deleteById(id_users);
    }

    @Override
    public Optional<Users> getUsersById(String id_users) {
        return usersRepository.findById(id_users);
    }

    @Override
    public Page<Users> findByNameLike(String name, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 13);
        return usersRepository.findAllByNameLike(name, pageable);
    }

    @Override
    public Users findByName(String name) {
        return usersRepository.findByName(name);
    }
}
