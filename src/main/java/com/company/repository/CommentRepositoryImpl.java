package com.company.repository;

import com.company.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(CommentDTO dto) {
        String sql = "insert into comment(content, visible, profile_id, post_id) values(?, ?, ?, ?)";

        PreparedStatementSetter setter = ps -> {
            ps.setString(1, dto.getContent());
            ps.setBoolean(2, true);
            ps.setInt(3, dto.getProfile_id());
            ps.setInt(4, dto.getPost_id());
        };
     jdbcTemplate.update(sql, setter);
    }

    public void update(CommentDTO dto) {
        String sql = "update comment set content = ?";
        PreparedStatementSetter setter = ps -> {
            ps.setString(1, dto.getContent());
        };
        jdbcTemplate.update(sql, setter);
    }

    public Boolean isCommentExist(Integer id, Integer owner_id) {
        String sql = "select count(*) from comment where visible = '" + true + "' and id = '" + id + "' and profile_id =" + owner_id;
        return jdbcTemplate.queryForObject(sql, Boolean.class);
    }

    public List<CommentDTO> listByPostId(Integer post_id) {
        String sql = "select * from comment where visible = '" + true + "' and post_id =" + post_id;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CommentDTO.class));
    }

    public List<CommentDTO> listByProfileId(Integer profile_id) {
        String sql = "select * from comment where visible = '" + true + "' and profile_id =" + profile_id;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CommentDTO.class));
    }

    public void changeVisible(Integer id) {
        String sql = "update comment set visible = '" + false + "' where id =" + id;
        jdbcTemplate.update(sql);
    }
}
