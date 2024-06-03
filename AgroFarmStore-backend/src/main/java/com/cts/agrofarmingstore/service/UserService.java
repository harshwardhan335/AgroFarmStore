package com.cts.agrofarmingstore.service;

import com.cts.agrofarmingstore.exception.ResourceNotFoundException;
import com.cts.agrofarmingstore.model.User;
import com.cts.agrofarmingstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviceImpl.userServiceImpl;

import java.util.List;
import java.util.Optional;
// Service class containing business logic implementing all methods of userServiceImpl
@Service
public class UserService implements userServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // Creating instance of user Repository
    @Autowired
    UserRepository userRepository;

    // Method for fetching all users
    @Override
    public List<User> getAllUsers() {
        logger.info("Getting all users");
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            logger.error("No user exists");
            throw new ResourceNotFoundException("No user exists");
        }
        return userList;
    }

    // Method for fetching User by id
    @Override
    public User getUserById(Long id) {
        logger.info("Getting user by ID: " + id);
        Optional<User> user = userRepository.findById(id);
        try {
            return user.get();
        } catch (Exception e) {
            logger.error("User with the id: "+id+" does not exist.");
            throw new ResourceNotFoundException("User with the id: " + id + " does not exist.");
        }
    }

    // Method for adding User to the database
    @Override
    public User addUser(User userObj) {
        logger.info("Adding user");
        if (userObj.getId() == null) {
            List<User> userList = getAllUsers();
            for(User u:userList)
            {
                if(u.getEmailId().equals(userObj.getEmailId()))
                {
                    logger.error("User already exists with :" +u.getEmailId());
                    throw new ResourceNotFoundException("User Already registered");
                }
            }
            userObj.setTotalOrder(0);
            User user = userRepository.save(userObj);
            return user;
        } else {
            Long id = userObj.getId();
            getUserById(id);
            return userRepository.save(userObj);
        }
    }

    // Method for deleting User from our database
    @Override
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: " + id);
        try {
            userRepository.findById(id).get();
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("User with id: "+id+" does not exist");
            throw new ResourceNotFoundException("User with id: " + id + " does not exist");
        }
    }

    // Method for updating User in our database
    @Override
    public User updateUser(User user) {
        logger.info("Updating user with ID: " + user.getId());
        Long id = user.getId();
        getUserById(id);
        user.setTotalOrder(userRepository.findById(user.getId()).get().getTotalOrder());
        return userRepository.save(user);
    }

    // Method for logging user in the web app
    @Override
    public User logIn(String email, String password) {
        logger.info("Logging in user with email: " + email);
        List<User> users = getAllUsers();

        for (User user : users) {
            if (user.getEmailId().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        logger.error("Your password or email is incorrect");
        throw new ResourceNotFoundException("Your password or email is incorrect");
    }
}
