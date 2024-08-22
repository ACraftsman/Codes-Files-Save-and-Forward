package com.example.cfsf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cfsf.pojo.Share;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ShareMapper extends BaseMapper<Share> {
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Share entity);
}
