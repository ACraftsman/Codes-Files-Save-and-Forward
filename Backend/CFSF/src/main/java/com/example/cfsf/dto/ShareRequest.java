package com.example.cfsf.dto;

import com.example.cfsf.pojo.Share;
import lombok.Data;

@Data
public class ShareRequest extends Share {
    private Integer duration;
}
