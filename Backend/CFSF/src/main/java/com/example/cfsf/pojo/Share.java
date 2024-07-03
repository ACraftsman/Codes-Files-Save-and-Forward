package com.example.cfsf.pojo;

import lombok.Data;

@Data
public class Share {
    private int id;
    private int shareUser;
    private int type;

    private String fileUrl;
    private String code;

}
