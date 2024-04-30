package com.example.task2.service;


import com.example.task2.DTO.OrderDTO;
import com.example.task2.DTO.OrderMapper;
import com.example.task2.DTO.UserDTO;
import com.example.task2.DTO.UserMapper;
import com.example.task2.entity.Order;
import com.example.task2.entity.User;
import com.example.task2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@AllArgsConstructor
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO addUser(User user) {

        try {
           return UserMapper.INSTANCE.userToUserDTO(userRepository.save(user));
        } catch (Exception ex) {
            return null;

        }

       // return null;

    }

    @Override
    public UserDTO getUserById(UUID userId) {

        Optional<User> userOptional=userRepository.findById(userId);
        if (userOptional.isEmpty())
            throw new IllegalStateException("no User exists with that id");
        User user=userOptional.get();
        return UserMapper.INSTANCE.userToUserDTO(user);


    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList;
        try{
            userList= userRepository.findAll();
        }
        catch(Exception ex){
            return  null;
        }
        List<UserDTO> userDTOList=new ArrayList<UserDTO>();
        for(User user:userList){
            userDTOList.add(UserMapper.INSTANCE.userToUserDTO(user));
        }
        return userDTOList;

    }

    @Override
    public List<OrderDTO> getOrderByUserId(UUID userId) {
        Optional<User> userOptional=userRepository.findById(userId);
        if (userOptional.isEmpty())
            throw new IllegalStateException("no User exists with that id");
        User user=userOptional.get();
       return OrderMapper.INSTANCE.orderListToOrderDTOList(user.getOrders());

    }


    @Override
    public UserDTO updateUser(UUID userId,User user) {

       User oldUser= userRepository.getReferenceById(userId);
       if(user.getUsername()!=null && !user.getUsername().isEmpty()){
           oldUser.setUsername(user.getUsername());
       }
      return  UserMapper.INSTANCE.userToUserDTO(userRepository.save(oldUser));

    }

    @Override
    public String deleteUser(UUID userId) {
        try {
            userRepository.deleteById(userId);
        }
        catch (Exception Ex){
            return Ex.getMessage();
        }
        return "user deleted successfully";
    }


    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepository.findUserByUsername(username).isEmpty();
    }
}
