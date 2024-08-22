package com.example.cfsf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cfsf.dto.ShareResponse;
import com.example.cfsf.mapper.LinkMapper;
import com.example.cfsf.mapper.ShareMapper;
import com.example.cfsf.pojo.CodeType;
import com.example.cfsf.pojo.Link;
import com.example.cfsf.pojo.Share;
import com.example.cfsf.pojo.User;
import com.example.cfsf.service.ShareService;
import com.example.cfsf.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private LinkMapper linkMapper;
    @Value("${cfsf.share.codeNum}")
    int codeNum;
    @Override
    public String addShare(int type, CodeType code_type, String file_url, String code,int duration) {
        Share _share = new Share();
        _share.setType(type);
        if(type==0){
            _share.setCodeType(code_type);
        }else {
            _share.setFileUrl(file_url);
        }
        _share.setCodes(code);
        _share.setShareUser(-1);
        if(shareMapper.insert(_share)==1){
            int id = _share.getId();
            String shareCode = CodeUtil.generateCode(codeNum);
            while(isExist(shareCode)){
                shareCode = CodeUtil.generateCode(codeNum);
            }
            Link _link = new Link();
            _link.setShareCode(shareCode);
            _link.setShareId(id);
            _link.setCreateTime(LocalDateTime.now());
            _link.setExpiredTime(LocalDateTime.now().plusSeconds(duration));
            if(linkMapper.insert(_link)==1){
                return shareCode;
            }else{
                throw new RuntimeException("分享失败");
            }
        }else{
            throw new RuntimeException("分享失败");
        }
    }

    @Override
    public Boolean isExist(String code) {
        if(linkMapper.selectOne(new QueryWrapper<Link>().eq("share_code", code))!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Share getShare(String code) {
        Link _link = linkMapper.selectOne(new QueryWrapper<Link>().eq("share_code", code));
        if(_link!=null){
            ShareResponse _share = new ShareResponse(shareMapper.selectById(_link.getShareId()));
            _share.setExpiredTime(_link.getExpiredTime());
            return _share;

        }else{
            throw new RuntimeException("分享不存在");
        }
    }
}
