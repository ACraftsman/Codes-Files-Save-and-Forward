package com.example.cfsf.util;

public class CodeUtil {
    public static String generateCode(int codeNum){
        // 生成指定长度的随机字符串,由字母与数字组成
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeNum; i++) {
            int index = (int) (Math.random() * str.length());
            code.append(str.charAt(index));
        }
        return code.toString();
    }
}
