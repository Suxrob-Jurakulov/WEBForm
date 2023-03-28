package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {
    private Integer id;
    private String title;
    private String content;
    private Integer profileId;
    private boolean visible;
    private LocalDateTime createdDate;

}
