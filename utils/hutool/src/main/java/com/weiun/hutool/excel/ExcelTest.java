package com.weiun.hutool.excel;

import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William
 * @Date 2019/4/23
 * @Description 表格工具测试
 */
public class ExcelTest {

    public static void main(String[] args) {

        ExcelReader reader = ExcelUtil.getReader("E:\\Gtown\\工行积分\\需求\\积分商城商品0424.xlsx");
        Map<String, String> alias = new HashMap<>();
        alias.put("结果信息","result");
        alias.put("商品编码（*）","code");
        reader.setHeaderAlias(alias);
        List<Map<String, Object>> maps = reader.readAll();
        List<Content> contents = reader.readAll(Content.class);
        System.out.println(JSONUtil.parse(contents).toString());
        reader.close();
        System.out.println(JSONUtil.parse(maps).toString());
        ExcelWriter writer = ExcelUtil.getWriter("E:\\Gtown\\工行积分\\需求\\积分商城商品0424.xlsx");
        maps.get(0).put("结果信息","Success");
        System.out.println(JSONUtil.parse(maps).toString());
        StyleSet styleSet = writer.getStyleSet();
        CellStyle headCellStyle = styleSet.getHeadCellStyle();
        headCellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        Font font = writer.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_NORMAL);
        headCellStyle.setFont(font);
        writer.setSheet(0);
        writer.write(maps);
        writer.close();
    }

}
