package org.example;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

public class GenExcelReport {

    public static void main(String[] args) throws IOException {
        // Excel文档 -1--N-> Sheet  -1--N-> Row  -1--N-> Cell

        // Excel 文档对象
        Workbook wb;
        boolean flag = true;
        if (flag) {
            // HSSF － 提供读写Microsoft Excel XLS格式档案的功能
            wb = new HSSFWorkbook();
        } else {
            // XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能
            wb = new XSSFWorkbook();
        }
        // 在Excel文档中创建Sheet
        Sheet sheet = wb.createSheet("sheet_name");
        // 创建辅助器
        CreationHelper createHelper = wb.getCreationHelper();
        // 在Sheet中创建指定行序号的引用
        Row row = sheet.createRow(0);
        // 在Row中创建指定列序号的Cell（单元格）引用
        Cell cell = row.createCell(0);
        // 设置Cell的内容,Cell支持多种类型
        cell.setCellValue(1);
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(true);
        row.createCell(3).setCellValue("Hello Excel.");
        // 设置超链接单元格样式
        CellStyle hlink_style = wb.createCellStyle();
        // 设置字体
        Font hlink_font = wb.createFont();
        hlink_font.setUnderline(Font.U_SINGLE);
        hlink_font.setColor(IndexedColors.BLUE.getIndex());
        hlink_style.setFont(hlink_font);
        // 设置超链接
        cell = row.createCell(4);
        cell.setCellValue("Baidu Site");
        Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress("https://www.baidu.com/");
        cell.setHyperlink(link);
        cell.setCellStyle(hlink_style);
        // 设置日期单元格样式
        CellStyle date_style = wb.createCellStyle();
        // 设置日期样式
        date_style.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        // 指定单元格应用样式
        cell = row.createCell(5);
        cell.setCellValue(new Date());
        cell.setCellStyle(date_style);
        cell = row.createCell(6);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(date_style);

        // 设置富文本对象
        RichTextString richString = createHelper.createRichTextString("rich text");
        cell = row.createCell(7);
        cell.setCellValue(richString);

        // 打开指定路径的文件输出流
        try  (OutputStream fileOut = new FileOutputStream("reports/workbook.xls")) {
            // Excel文档内容输出到指定输出流
            wb.write(fileOut);
        }
    }

    private static final void iterateWorkbook(Workbook wb) {
        // 迭代所有Sheet
        for (Sheet sheet : wb ) {
            // 迭代当前sheet所有行
            for (Row row : sheet) {
                // 迭代当前行所有cell
                for (Cell cell : row) {
                    // TODO 操作每个cell
                }
            }
        }
    }
}
