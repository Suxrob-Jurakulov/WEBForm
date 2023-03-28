package com.company.controller;

import com.company.dto.CommentContentCreatedDateDTO;
import com.company.dto.CommentDTO;
import com.company.service.CommentService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CommentDTO dto, @RequestHeader("Authorization") String token) {
        Integer pId = JwtUtil.decode(token);
        commentService.create(pId, dto);
        return ResponseEntity.ok().body("Comment created");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody CommentDTO dto,
                                    @RequestHeader("Authorization") String token) {
        Integer pId = JwtUtil.decode(token);
        commentService.update(pId, id, dto);
        return ResponseEntity.ok().body("UPDATED");
    }

    @GetMapping("/list/{post_id}")
    public ResponseEntity<?> list(@PathVariable("post_id") Integer post_id) {
        List<CommentContentCreatedDateDTO> list = commentService.list(post_id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listByProfile(@RequestHeader("Authorization") String token) {
        Integer profile_id = JwtUtil.decode(token);
        List<CommentContentCreatedDateDTO> list = commentService.listByProfile(profile_id);
        return ResponseEntity.ok().body(list);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, @RequestHeader("Authorization") String token) {
        Integer owner_id = JwtUtil.decode(token);
        commentService.delete(owner_id, id);
        return ResponseEntity.ok().body("DELETED");
    }

}
