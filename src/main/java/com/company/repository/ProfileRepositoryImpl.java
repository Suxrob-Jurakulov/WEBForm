//package com.company.repository;
//
//import com.company.dto.ProfileDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class ProfileRepositoryImpl {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public void create(ProfileDTO dto) {
//        String sql = "insert into profile(name, surname, login, password) values(?, ?, ?, ?)";
//
//        PreparedStatementSetter setter = ps -> {
//            ps.setString(1, dto.getName());
//            ps.setString(2, dto.getSurname());
//            ps.setString(3, dto.getLogin());
//            ps.setString(4, dto.getPassword());
//        };
//        jdbcTemplate.update(sql, setter);
//    }
//
//    public boolean isLoginExist(String login) {
//        String sql = "select count(*) from profile where login = '" + login + "'";
//        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
//        return count == 1;
//    }
//
//    public ProfileDTO isProfileExist(Integer id) {
//        String sql = "select * from profile where id =" + id;
//        List<ProfileDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProfileDTO.class));
//
//        if (list.size() != 0) {
//            return list.get(0);
//        }
//        return null;
//    }
//
//    public ProfileDTO auth(String login, String password) {
//        String sql = "select * from profile where login = ? and password = ?";
//
//        PreparedStatementSetter setter = ps -> {
//            ps.setString(1, login);
//            ps.setString(2, password);
//        };
//        List<ProfileDTO> list = jdbcTemplate.query(sql, setter, new BeanPropertyRowMapper<>(ProfileDTO.class));
//
//        if (list.size() != 0) {
//            return list.get(0);
//        }
//        return null;
//    }
//
//
//    public void editProfile(Integer id, ProfileDTO dto) {
//        String sql = "update profile set name = ?, surname = ?, login = ?, password = ? where id =" + id;
//        PreparedStatementSetter setter = ps -> {
//            ps.setString(1, dto.getName());
//            ps.setString(2, dto.getSurname());
//            ps.setString(3, dto.getLogin());
//            ps.setString(4, dto.getPassword());
//        };
//        jdbcTemplate.update(sql, setter);
//    }
//
//}
