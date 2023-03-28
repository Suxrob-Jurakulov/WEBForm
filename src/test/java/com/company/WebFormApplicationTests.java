package com.company;

import com.company.dto.CommentContentCreatedDateDTO;
import com.company.dto.PostDTO;
import com.company.dto.PostTitleContentDTO;
import com.company.entity.ProfileEntity;
import com.company.repository.PostRepository;
import com.company.service.CommentService;
import com.company.service.PostService;
import com.company.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebFormApplicationTests {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentService commentService;

    @Test
    void contextLoads() {

        List<CommentContentCreatedDateDTO> list = commentService.list(1);
        System.out.println(list);
    }

}
