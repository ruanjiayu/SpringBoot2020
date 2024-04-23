package com.fun.uncle.springboot2020.excel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2024/4/8 11:50
 * @Version: 1.0.0
 */
public class ExcelSheetCopy {


    public static void main(String[] args) {
        try {
            // 读取Excel文件
            FileInputStream fileInputStream = new FileInputStream("/Users/xian/downloads/product_template.xlsx");
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            // 获取原始sheet1的数据
            Sheet sourceSheet1 = workbook.getSheetAt(0);
            // 获取原始sheet2的数据
            Sheet sourceSheet2 = workbook.getSheetAt(1);

            // 循环复制原始sheet到目标sheet，复制5次，使得最终有7个sheet
            for (int i = 0; i < 5; i++) {
                Sheet targetSheet = workbook.cloneSheet(0); // 复制sheet1
                workbook.setSheetName(workbook.getSheetIndex(targetSheet), "NewSheet" + (i + 3)); // 重命名目标sheet
            }

            // 保存修改后的Excel文件
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/xian/Desktop/output.xlsx");
            workbook.write(fileOutputStream);

            // 关闭文件流
            fileInputStream.close();
            fileOutputStream.close();

            System.out.println("Sheets copied successfully!");

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }

}
