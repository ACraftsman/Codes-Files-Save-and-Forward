package com.example.cfsf.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String picUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
