package com.company.service;

import com.company.dto.PostDTO;
import com.company.entity.PostEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void create(PostDTO dto, Integer pId) {
        PostEntity entity = new PostEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setProfileId(pId);
        postRepository.save(entity);
    }

    public void isValidPost(PostDTO postDTO) {
        if (postDTO.getTitle() == null) {
            throw new BadRequestException("Title required");
        }
        if (postDTO.getContent() == null) {
            throw new BadRequestException("Context is empty");
        }
    }

    public void update(Integer id, PostDTO dto, Integer pId) {
        isValidPost(dto);

        Optional<PostEntity> optional = postRepository.findByIdAndProfileIdAndVisible(dto.getId(), pId, true);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Post not found");
        }

        postRepository.updatePost(dto.getTitle(), dto.getContent(), id);
    }

    public List<PostDTO> listByProfile(Integer pId) {
        List<PostEntity> list = postRepository.findByProfileIdOrderByCreatedDateDesc(pId);
        List<PostDTO> dtoList = new LinkedList<>();
        for (PostEntity entity : list) {
            PostDTO dto = new PostDTO();
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void deletePost(Integer owner_id, Integer id) {
        Optional<PostEntity> optional = postRepository.findByIdAndProfileIdAndVisible(id, owner_id, true);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        postRepository.deletePost(id);
    }
}
