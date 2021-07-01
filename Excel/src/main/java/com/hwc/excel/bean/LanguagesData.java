package com.hwc.excel.bean;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Data
public class LanguagesData {

    @ExcelProperty("中文")
    private String chinese;

    @ExcelProperty("Polish")
    private String translation;

    @ExcelProperty("match")
    private String match = "N";

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

}
