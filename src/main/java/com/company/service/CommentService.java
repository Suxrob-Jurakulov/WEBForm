package com.company.service;

import com.company.dto.CommentContentCreatedDateDTO;
import com.company.dto.CommentDTO;
import com.company.entity.CommentEntity;
import com.company.entity.PostEntity;
import com.company.entity.ProfileEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.CommentRepository;
import com.company.repository.PostRepository;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PostRepository postRepository;

    public void isValid(CommentDTO dto) {
        if (dto.getContent() == null) {
            throw new BadRequestException("Comment is empty");
        }
    }

    public void create(Integer pId, CommentDTO dto) {
        isValid(dto);

        Optional<ProfileEntity> optional = profileRepository.findById(pId);
        if (optional.isEmpty()) {
            throw new BadRequestException("Bunday profile mavjud emas");
        }

        Optional<PostEntity> post = postRepository.findByIdAndVisible(dto.getPost_id(), true);
        if (post.isEmpty()) {
            throw new BadRequestException("Bunday post mavjud emas");
        }
        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setPostId(dto.getPost_id());
        entity.setProfileId(pId);

        commentRepository.save(entity);
    }

    public void update(Integer owner_id, Integer id, CommentDTO dto) {
        Optional<CommentEntity> optional = commentRepository.findByIdAndProfileId(id, owner_id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Sizda bunday comment yo'q");
        }

        commentRepository.updateComment(dto.getContent(), id);
    }

    public List<CommentContentCreatedDateDTO> list(Integer post_id) {
        return commentRepository.list(post_id);
    }

    public List<CommentContentCreatedDateDTO> listByProfile(Integer profile_id) {
       return commentRepository.listByProfile(profile_id);
    }

    public void delete(Integer owner_id, Integer id) {
        Optional<CommentEntity> optional = commentRepository.findByIdAndProfileId(id, owner_id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("Bunday comment yo'q");
        }
        commentRepository.delete(id);
    }
}
