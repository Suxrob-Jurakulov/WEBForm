package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProfileDTO dto) {
        profileService.create(dto);
        return ResponseEntity.ok().body("Successfully registration");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProfileDTO dto, @RequestHeader("Authorization") String token) {
        Integer id = JwtUtil.decode(token);
        profileService.updateProfile(id, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<ProfileDTO> all = profileService.getAll2();
        return ResponseEntity.ok().body(all);
    }

}
