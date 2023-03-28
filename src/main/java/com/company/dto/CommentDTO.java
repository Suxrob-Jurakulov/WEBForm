package com.company.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {
    private Integer id;
    private String content;
    private boolean visible;
    private Timestamp created_date;
    private Integer profile_id;
    private Integer post_id;
}
