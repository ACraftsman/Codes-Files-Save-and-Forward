package com.example.cfsf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cfsf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User entity);
}
