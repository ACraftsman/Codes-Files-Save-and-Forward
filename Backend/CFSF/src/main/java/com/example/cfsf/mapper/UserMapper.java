package com.example.cfsf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cfsf.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
