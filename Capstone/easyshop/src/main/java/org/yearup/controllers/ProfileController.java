package org.yearup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProductDao;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController {
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @GetMapping
    public ResponseEntity<Profile> getProfileById(Principal principal){
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        var profileAcquired = profileDao.getByUserId(userId);

        return new ResponseEntity<>(profileAcquired, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(Principal principal, @RequestBody Profile profile){
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        var profileUpdated = profileDao.updateProfile(profile, userId);
        return new ResponseEntity<>(profileUpdated, HttpStatus.OK);
    }
}