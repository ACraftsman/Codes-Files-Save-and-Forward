package com.example.cfsf.dto;

import com.example.cfsf.pojo.Share;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShareResponse extends Share {
    public ShareResponse(Share share) {
        this.setId(share.getId());
        this.setType(share.getType());
        this.setCodeType(share.getCodeType());
        this.setFileUrl(share.getFileUrl());
        this.setCodes(share.getCodes());
        this.setShareUser(share.getShareUser());
    }
    private LocalDateTime expiredTime;

}
