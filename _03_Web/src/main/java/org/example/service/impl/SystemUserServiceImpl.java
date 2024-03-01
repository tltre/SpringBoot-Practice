package org.example.service.impl;

import org.example.domain.SystemUser;
import org.example.mapper.SystemUserMapper;
import org.example.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public SystemUser login(SystemUser user) {
        return systemUserMapper.login(user);
    }
}
