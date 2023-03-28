package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public void create(ProfileDTO dto) {
        isValid(dto);

        Optional<ProfileEntity> entity = profileRepository.findByLogin(dto.getLogin());
        if (entity.isPresent()) {
            throw new BadRequestException("Mazgi bunaqa login bor");
        }

        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName(dto.getName());
        profileEntity.setSurname(dto.getSurname());
        profileEntity.setLogin(dto.getLogin());
        profileEntity.setPassword(dto.getPassword());
        profileEntity.setStatus(ProfileStatus.ACTIVE);
        profileEntity.setVisible(Boolean.TRUE);
        profileEntity.setCreatedDate(LocalDateTime.now());

        profileRepository.save(profileEntity);
    }

    public void isValid(ProfileDTO dto) {
        if (dto.getName() == null) {
            throw new BadRequestException("Name is wrong");
        }

        if (dto.getSurname() == null) {
            throw new BadRequestException("Surname is wrong");
        }
    }

    public void isValidForLogin(String login, String password) {
        if (login == null) {
            throw new BadRequestException("Login is empty");
        }

        if (password == null) {
            throw new BadRequestException("Password is empty");
        }
    }

    public void updateProfile(Integer id, ProfileDTO dto) {
        isValid(dto);
        Optional<ProfileEntity> optional = profileRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Profile not found");
        }

        profileRepository.updateProfile(dto.getName(), dto.getSurname(), id);
    }


    public ProfileDTO getById(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Profile not found");
        }
        ProfileEntity entity = optional.get();

        ProfileDTO dto = new ProfileDTO();

        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setLogin(entity.getLogin());
        return dto;
    }


    public List<ProfileDTO> getAll2() {
        Iterable<ProfileEntity> entityList = profileRepository.findByVisible(true);
        List<ProfileDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {

            ProfileDTO dto = new ProfileDTO();

            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLogin(entity.getLogin());

            dtoList.add(dto);
        });
        return dtoList;
    }

    public void changeAll(){
        Iterable<ProfileEntity> entities = profileRepository.findAll();
        entities.forEach(profileEntity -> {
            profileEntity.setStatus(ProfileStatus.NOT_ACTIVE);
            profileRepository.save(profileEntity);
        });
    }

    public void active(){
        profileRepository.active(ProfileStatus.NOT_ACTIVE);
    }

    public List<ProfileEntity> list(){
      return profileRepository.activeProfileList(ProfileStatus.NOT_ACTIVE);
    }

    public List<ProfileEntity> list2(){
       return profileRepository.getAllNameAndSurname();

    }
}
