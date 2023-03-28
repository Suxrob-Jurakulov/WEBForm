package com.company.controller;

import com.company.dto.PostDTO;
import com.company.service.PostService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostDTO postDTO, @RequestHeader("Authorization") String token) {
        Integer pId = JwtUtil.decode(token);
        postService.create(postDTO, pId);
        return ResponseEntity.ok().body("Post successfully created");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer postId, @RequestBody PostDTO dto,
                                    @RequestHeader("Authorization") String token) {
        Integer pId = JwtUtil.decode(token);
        postService.update(postId, dto, pId);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestHeader("Authorization") String token) {
        Integer pId = JwtUtil.decode(token);
        List<PostDTO> list = postService.listByProfile(pId);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, @RequestHeader("Authorization") String token) {
        Integer pId = JwtUtil.decode(token);
        postService.deletePost(pId, id);
        return ResponseEntity.ok().body("Successfully deleted");
    }

}
