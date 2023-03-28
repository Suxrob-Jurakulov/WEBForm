//package com.company.repository;
//
//import com.company.dto.PostDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class PostRepositoryImpl {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public void addPost(PostDTO postDTO) {
//        String sql = "insert into post(title, context, visible, profile_id) values (?, ?, ?, ?)";
//
//        PreparedStatementSetter setter = p -> {
//            p.setString(1, postDTO.getTitle());
//            p.setString(2, postDTO.getContext());
//            p.setBoolean(3, true);
//            p.setInt(4, postDTO.getProfile_id());
//        };
//        jdbcTemplate.update(sql, setter);
//    }
//
//    public Boolean isPostExistThisUser(Integer id, Integer owner_id) {
//        String sql = "select count(id) from post where id = '" + id + "' and profile_id =" + owner_id;
//        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
//        return count == 1;
//    }
//
//    public void update(Integer id, PostDTO dto) {
//        String sql = "update post set title = ?, context = ?";
//
//        PreparedStatementSetter setter = ps -> {
//            ps.setString(1, dto.getTitle());
//            ps.setString(2, dto.getContext());
//        };
//        jdbcTemplate.update(sql, setter);
//    }
//
//    public List<PostDTO> listByProfileId(Integer id) {
//        String sql = "select * from post where visible ='" + true + "' and profile_id =" + id;
//         return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PostDTO.class));
//    }
//
//    public void changeStatusPost(Integer id, Integer owner_id) {
//        String sql = "update post set visible ='" + false + "' where id = '" + id + "' and profile_id =" + owner_id;
//        jdbcTemplate.update(sql);
//    }
//
//    public Boolean isPostActive(Integer id) {
//        String sql = "select visible from post where id =" + id;
//        return jdbcTemplate.queryForObject(sql, Boolean.class);
//    }
//
//    public Boolean isPostExist(Integer post_id) {
//        String sql = "select count(*) from post where id =" + post_id;
//        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
//        return count == 1;
//    }
//}
