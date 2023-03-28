package com.company.repository;

import com.company.dto.PostTitleContentDTO;
import com.company.entity.PostEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    Optional<PostEntity> findByIdAndProfileIdAndVisible(Integer id, Integer pId, Boolean visible);


    @Transactional
    @Modifying
    @Query("update PostEntity set title = :title, content = :con where id = :id")
    int updatePost(@Param("title") String title, @Param("con") String content, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update PostEntity set visible = false where id = ?1")
    int deletePost(Integer id);

    List<PostEntity> findByProfileIdOrderByCreatedDateDesc(Integer id);

    @Query("SELECT new com.company.dto.PostTitleContentDTO(p.title,p.content, p.createdDate) From PostEntity p")
    List<PostTitleContentDTO> getAllNameAndLogin();


    @Query("SELECT new com.company.dto.PostTitleContentDTO(title, content, createdDate) From PostEntity  where profileId =?1")
    List<PostTitleContentDTO> getList(Integer id);

    Optional<PostEntity> findByIdAndVisible(Integer id, Boolean visible);
}
