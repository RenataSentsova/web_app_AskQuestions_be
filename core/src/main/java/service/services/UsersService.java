package service.services;

import service.models.User;

import java.util.List;


public interface UsersService {
    String getLoginUserById(Long id);
    Long getUserIdByUsername(String username);
    List<User> findAll();
    User findOne(Long userId);
    void makeNonActive(String login);
}
