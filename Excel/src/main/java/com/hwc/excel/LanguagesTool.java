package com.hwc.excel;

import com.alibaba.excel.EasyExcel;
import com.hwc.excel.bean.LanguagesData;
import com.hwc.excel.listener.LanguagesDataListener;
import com.hwc.excel.util.TestFileUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hwc
 * date:   On 2021/6/30
 */
public class LanguagesTool {

    public static final String oldXlsxFilePath = "D:\\excel\\translation.xlsx";

    public static final String newXlsxFilePath = "D:\\excel\\translation_new.xlsx";

    public static final String oldStringFilePath = "D:\\excel\\strings.xml";

    public static final String newStringFilePath = "D:\\excel\\strings_new.xml";

    @Test
    public void translation() {
        System.out.println("start");
        LanguagesDataListener languagesDataListener = new LanguagesDataListener();
        EasyExcel.read(oldXlsxFilePath, LanguagesData.class, languagesDataListener).sheet().doRead();
        System.out.println("end");
    }

}
