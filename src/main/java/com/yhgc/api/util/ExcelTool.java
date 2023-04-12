package com.yhgc.api.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * excel的工具类
 */
public class ExcelTool {

    /**
     * 需要传入哪些数据？
     *      filename:文件名称
     *      String[] heads:表格的头部
     *      List<String[]> list:表格中的数据
     *      response
     */
    public static void export(String filename,String[] heads, List<String[]> list, HttpServletResponse response) throws Exception{
        //1.创建一个Excel文件(在内存中)
        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFCellStyle style = wb.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        style.setAlignment(HorizontalAlignment.CENTER); //水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

//        style.setDataFormat(wb.createDataFormat().getFormat(
//                "yyyy/m/d"));

        //2.创建一个表
        Sheet sheet = wb.createSheet();

        sheet.setDefaultRowHeightInPoints(20);//设置缺省列高sheet.setDefaultColumnWidth(20);//设置缺省列宽

        //一.创建头部
        // 1.1 创建第一行
        Row row = sheet.createRow(0);

        // 1.2 创建相应的列(第一行的格子) - heads有多长，就有多少个格式
        for (int i = 0; i < heads.length; i++) {
            //1.3 创建格子
            Cell headCell = row.createCell(i);

            //设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
            sheet.setColumnWidth(headCell.getColumnIndex(), 256 * 16);
            //1.4 把数据放进去
            headCell.setCellValue(heads[i]);
            headCell.setCellStyle(style);
        }
        //二.创建相应的数据展示
        // 2.1 创建相应的行(从第二行开始) - list有多长，就有多少行
        for (int i = 0; i < list.size(); i++) {
            //2.2 创建这一行
            Row dataRow = sheet.createRow(i + 1);
            //2.3 创建这一行对应的列(格子) 
            // 拿到这一行的数据
            String[] strings = list.get(i);
            for (int j = 0; j < strings.length; j++) {
                Cell cell = dataRow.createCell(j);
                if(j==4){
                    Date time =new Date(strings[j]);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
                    String timeFormat = sdf.format(time);
                    strings[j]=timeFormat;
                }
                //2.4 把数据放到格子中
                cell.setCellValue(strings[j]);
                cell.setCellStyle(style);
            }
        }

        //下面设置好直接使用即可导出
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
        response.setHeader("Content-disposition", "attachment;filename="+filename);
        response.setHeader("Pragma", "No-cache");//设置不要缓存
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream); //把excel文件写到输出流中
        ouputStream.flush();
        ouputStream.close();

    }

}
