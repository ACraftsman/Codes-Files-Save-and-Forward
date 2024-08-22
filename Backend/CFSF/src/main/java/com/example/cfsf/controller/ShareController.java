package com.example.cfsf.controller;

import com.example.cfsf.dto.ShareRequest;
import com.example.cfsf.dto.ShareResponse;
import com.example.cfsf.pojo.Result;
import com.example.cfsf.pojo.Share;
import com.example.cfsf.service.ShareService;
import com.example.cfsf.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RequestMapping("/share")
@RestController

public class ShareController {
    @Autowired
    private ShareService shareService;
    @PostMapping("/uploadCodes")
    public Result<String> addShare(@RequestBody ShareRequest share) {
        try {
            String shareCode = shareService.addShare(share.getType(),share.getCodeType(),null,share.getCodes(),share.getDuration());
            return Result.success(shareCode);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }

    @PutMapping("/uploadFiles")
    public Result<String> uploadFiles(MultipartFile[] files, int duration) {
        List<String> urlList = new ArrayList<>();
        List<String> originalFilenameList = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String originalFilename = file.getOriginalFilename();
                String fileName = UUID.randomUUID().toString().replace("-", "") + originalFilename.substring(originalFilename.lastIndexOf("."));
                originalFilenameList.add(originalFilename);
                try {
                    urlList.add(OssUtil.uploadFile(fileName, file.getInputStream()));
                } catch (Exception e) {
                    return Result.error(e.getMessage());

                }
            }
            String urlListString = urlList.stream().map(url -> "\"" + url + "\"").toList().toString();
            String originalFilenameListString = originalFilenameList.stream().map(name -> "\"" + name + "\"").toList().toString();
            String shareCode = shareService.addShare(1, null, urlListString, originalFilenameListString, duration);
            return Result.success(shareCode);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/getShare")
    public Result<Share> getShare(String shareCode) {
        try {
            Share share = shareService.getShare(shareCode.toUpperCase());
            return Result.success(share);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
