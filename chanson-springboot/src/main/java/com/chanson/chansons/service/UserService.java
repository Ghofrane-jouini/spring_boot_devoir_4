package com.chanson.chansons.service;

import com.chanson.chansons.model.Role;
import com.chanson.chansons.model.User;

public interface UserService {
    void deleteAllUsers();
    void deleteAllRoles();
    User saveUser(User user);
    User findUserByUsername(String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
}
