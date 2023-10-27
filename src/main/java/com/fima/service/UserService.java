package com.fima.service;

import com.fima.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<Users> getAllUsers();

    public Page<Users> getAllUsersPage(Integer pageNo);

    public Users addUsers(Users users);

    public void updateUsers(Users users);

    public void deleteUsers(String id_users);

    public Optional<Users> getUsersById(String id_users);

    public Page<Users> findByNameLike(String name, int pageNo);

    public Users findByName(String name);
}
