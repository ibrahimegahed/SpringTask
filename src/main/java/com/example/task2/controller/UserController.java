package com.example.task2.controller;

import com.example.task2.DTO.OrderDTO;
import com.example.task2.DTO.UserDTO;
import com.example.task2.entity.User;
import com.example.task2.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@AllArgsConstructor
@RequestMapping(path="api/v1/user")
@RestController
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId")  UUID userId){
        return ResponseEntity.ok(userService.getUserById(userId));
        //return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    @GetMapping("/{userId}/order")
    public ResponseEntity<List<OrderDTO>> getOrderByUserId(@PathVariable UUID userId ){
        return ResponseEntity.ok(userService.getOrderByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return  ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){

        if (!userService.isUsernameAvailable(user.getUsername())) {
          //  return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username is already registered");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is already registered");
        }
       return ResponseEntity.ok(userService.addUser(user));

    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId){
        return  ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping(path="{userId}")
    public  ResponseEntity<UserDTO> updateUser(@PathVariable("userId") UUID userId,@RequestBody User user){

       return ResponseEntity.ok(userService.updateUser(userId,user)) ;
    }
}
