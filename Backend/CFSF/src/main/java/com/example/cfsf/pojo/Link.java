package com.example.cfsf.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Link {
    private Integer id;
    private int shareId;
    private String shareCode;
    private LocalDateTime createTime;
    private LocalDateTime expiredTime;
}
