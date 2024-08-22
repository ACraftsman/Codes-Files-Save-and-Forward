package com.example.cfsf.service;


import com.example.cfsf.dto.ShareResponse;
import com.example.cfsf.pojo.CodeType;
import com.example.cfsf.pojo.Share;

public interface ShareService {
    String addShare(int type, CodeType code_type, String file_url, String code,int duration);
    Boolean isExist(String code);

    Share getShare(String code);
}
