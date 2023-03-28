package com.company.repository;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

//    ProfileEntity findByLogin(String login);

    Optional<ProfileEntity> findByLogin(String login);

    List<ProfileEntity> findByVisible(Boolean visible);

    List<ProfileEntity> findByStatus(ProfileStatus status);

    List<ProfileEntity> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set name = :name, surname = :sur where id = :id")
    int updateProfile(@Param("name") String name, @Param("sur") String surname, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set status = :active")
    int active(@Param("active") ProfileStatus status);


    @Query("from ProfileEntity where name = ?1 and surname = ?2")
    int getProfileByNameAndSurname(String name, String surname);


    @Query("from ProfileEntity where status = :status")
    List<ProfileEntity> activeProfileList(@Param("status") ProfileStatus status);

    @Query("select new ProfileEntity(p.name, p.surname) from ProfileEntity p")
    List<ProfileEntity> getAllNameAndSurname();

    @Query("select new ProfileEntity(p.surname, p.login) from ProfileEntity p")
    List<ProfileEntity> getAllSurnameAndLogin();

    Optional<ProfileEntity> findByLoginAndPassword(String login, String password);
}
