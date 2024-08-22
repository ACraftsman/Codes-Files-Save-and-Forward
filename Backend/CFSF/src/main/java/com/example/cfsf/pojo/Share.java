package com.example.cfsf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("share")
public class Share {
    @TableId(type = IdType.AUTO)
    @JsonIgnore
    private Integer id;
    private int shareUser;
    private int type;
    private CodeType codeType;
    private String fileUrl;
    private String codes;

}
