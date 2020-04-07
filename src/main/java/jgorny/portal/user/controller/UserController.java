package jgorny.portal.user.controller;


import jgorny.portal.user.controller.model.GetUserResponse;
import jgorny.portal.user.serviece.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("{login}")
    public ResponseEntity<GetUserResponse> getUser(){
        //TODO CONTROLLER LIKE IN BRANCH THEN ADD ORDERS, TEST THEM
        //TODO, SAME RELATION LIKE
        //TODO BRANCH --> CATEGORIES, ORDER ONLY HAVE ID IF I AM ABLE ADD DATE
        //TODO ENTITY
    }


}
