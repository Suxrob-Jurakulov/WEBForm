package com.company.repository;

import com.company.dto.CommentContentCreatedDateDTO;
import com.company.entity.CommentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
    Optional<CommentEntity> findByIdAndProfileId(Integer id, Integer pId);


    @Transactional
    @Modifying
    @Query("update CommentEntity set content = ?1  where id = ?2")
    void updateComment(String content, Integer id);

    @Transactional
    @Modifying
    @Query("update CommentEntity set visible = false  where id = ?2")
    void delete(Integer id);

    List<CommentEntity> findByPostId(Integer postId);

    @Query("select new com.company.dto.CommentContentCreatedDateDTO(content, createdDate) from " +
            "CommentEntity where postId = ?1 and visible = true")
    List<CommentContentCreatedDateDTO> list(Integer postId);

    @Query("select new com.company.dto.CommentContentCreatedDateDTO(content, createdDate) from " +
            "CommentEntity where profileId = ?1 and visible = true")
    List<CommentContentCreatedDateDTO> listByProfile(Integer postId);

}
