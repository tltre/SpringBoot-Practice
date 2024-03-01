package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> findAll();

    void insertUser(User user);
}
