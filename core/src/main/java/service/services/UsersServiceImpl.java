package service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import service.models.*;
import service.repositories.*;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;


@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserRepository usersRepository;
    @Override
    public Long getUserIdByUsername(String username){
        return usersRepository.findByUsername(username).get().getId();
    }
    @Override
    public String getLoginUserById(Long id){
        return usersRepository.findOne(id).getUsername();
    }
    @Override
    public List<User>
    findAll() {
        return usersRepository.findAll();
    }
    @Override
    public User findOne(Long userId) {
        return usersRepository.findOne(userId);
    }
    @Override
    public void makeNonActive(String login){
        User delUser = usersRepository.findByUsername(login).orElseThrow(IllegalArgumentException::new);
        delUser.setState("nonactive");
        usersRepository.save(delUser);
    }
}
