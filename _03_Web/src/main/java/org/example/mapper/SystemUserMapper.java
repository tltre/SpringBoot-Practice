package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.SystemUser;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SystemUserMapper {
    SystemUser login(SystemUser user);
}
