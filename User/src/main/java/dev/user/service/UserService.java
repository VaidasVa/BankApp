package dev.user.service;

import dev.user.model.User;
import dev.user.repository.UserRepository;
import dev.user.repository.dao.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    UserRepository repository;
    ModelMapper mapper = new ModelMapper();

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers(){
        List<UserDAO> repoUsers = repository.findAll();
        List<User> users = new ArrayList<>();
        for (UserDAO userDAO : repoUsers) {
            users.add(mapper.map(userDAO, User.class));
        }
        return users;
    }

    public boolean existsByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public User addUser(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        } else {
            repository.save(mapper.map(user, UserDAO.class));
            return mapper.map(repository.findByEmail(user.getEmail()).get(), User.class);
        }
    }
}
