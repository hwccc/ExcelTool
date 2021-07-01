package com.hwc.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.hwc.excel.bean.CellDataReadDemoData;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取头
 *
 * @author Jiaju Zhuang
 */
public class CellDataDemoHeadDataListener extends AnalysisEventListener<CellDataReadDemoData> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<CellDataReadDemoData> list = new ArrayList<CellDataReadDemoData>();

    @Override
    public void invoke(CellDataReadDemoData data, AnalysisContext context) {
        System.out.println("解析到一条数据:{}" + JSON.toJSONString(data));
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        System.out.println("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println("{}条数据，开始存储数据库！" + list.size());
        System.out.println("存储数据库成功！");
    }
}
