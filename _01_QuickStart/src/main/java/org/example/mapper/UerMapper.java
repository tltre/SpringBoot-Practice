package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.User;

import java.util.List;

// 接口类，用于操作user表，需要添加注解@Mapper
@Mapper
public interface UerMapper {
    public List<User> findAll();
}
