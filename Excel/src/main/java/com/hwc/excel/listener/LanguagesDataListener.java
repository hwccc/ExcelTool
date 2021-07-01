package com.hwc.excel.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.hwc.excel.LanguagesTool;
import com.hwc.excel.bean.LanguagesData;
import com.hwc.excel.util.TestFileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 语种读取类
 *
 * @author Jiaju Zhuang
 */
public class LanguagesDataListener extends AnalysisEventListener<LanguagesData> {

    private List<LanguagesData> list = new ArrayList<>();

    public static String readStr;

    public LanguagesDataListener() {
        readStr = TestFileUtil.read(LanguagesTool.oldStringFilePath);
    }


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(LanguagesData data, AnalysisContext context) {
        System.out.println("解析到一条数据:" + JSON.toJSONString(data));
        ReadRowHolder readRowHolder = context.readRowHolder();
        Integer rowIndex = readRowHolder.getRowIndex();
//                System.out.println("rowIndex:" + rowIndex);
        String chinese = data.getChinese();
        StringBuilder chineseSb = new StringBuilder();
        chineseSb.append(">").append(chinese).append("<");
        String translation = data.getTranslation();
        StringBuilder translationSb = new StringBuilder();
        translationSb.append(">").append(translation).append("<");
        if (readStr != null) {
            int intIndex = readStr.indexOf(chineseSb.toString());
            if (intIndex == -1 || translation == null) {
                System.out.println("没有找到字符串");
                data.setMatch("N");
            } else {
                if (intIndex > 0) {
                    System.out.println("找到字符串");
                    StringBuilder sb = new StringBuilder(readStr);
                    sb.replace(intIndex, intIndex + chineseSb.toString().length(), translationSb.toString());
                    readStr = sb.toString();
                    data.setMatch("P");
                }
            }
        }
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("所有数据解析完成！");
        if (list.size() > 0) {
            System.out.println("开始创建翻译转换之后的表");
            ExcelWriter excelWriter = null;
            try {
                excelWriter = EasyExcel.write(LanguagesTool.newXlsxFilePath, LanguagesData.class).build();
                WriteSheet writeSheet = EasyExcel.writerSheet("翻译").build();
                excelWriter.write(list, writeSheet);
            } finally {
                if (excelWriter != null) {
                    excelWriter.finish();
                }
            }
            System.out.println("开始");
            if (readStr != null) {
                System.out.println("开始创建新的string文件");
                TestFileUtil.write(LanguagesTool.newStringFilePath, readStr);
                System.out.println("结束");
            }
        }
    }

}
