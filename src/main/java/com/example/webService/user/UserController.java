package com.example.webService.user;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController extends EntityModel{
    private final UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){

        User one = service.findOne(id);

        EntityModel<User> userModel = EntityModel.of(one);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        userModel.add(linkTo.withRel("all-users"));

        return userModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user == null){
            throw  new UserNotFoundException(String.format("ID[%s] not found" , id));
        }
    }




//    @GetMapping("/users/{id}")
//    public EntityModel<User> retrieveUser(@PathVariable int id){
//        Optional<User> user = userRepository.findById(id);
//
//        if(!user.isPresent()) {
//            throw new UserNotFoundException(String.format("ID[%S] not found", id));
//        }
//
//        EntityModel<User> userModel = new EntityModel<>(user.get());
//        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//        userModel.add(linkTo.withRel("all-users"));
//
//        return userModel;
//    }




}
