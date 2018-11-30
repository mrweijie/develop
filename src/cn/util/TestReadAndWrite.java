package cn.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class TestReadAndWrite  {
    public static void main(String[] args) throws IOException {
        String path = "d:/";
        String fileName = "readExcel";
        String fileType = "xls";
        TestReadAndWrite testReadAndWrite = new TestReadAndWrite();
        testReadAndWrite.writer(path, fileName, fileType);
//        read(path, fileName, fileType);
    }
    public void writer(String path, String fileName,String fileType) throws IOException {
        //创建工作文档对象
        Workbook wb = null;
        if (fileType.equals("xls")) {
            wb = new HSSFWorkbook();
        }
        else if(fileType.equals("xlsx"))
        {
            wb = new XSSFWorkbook();
        }
        else
        {
            System.out.println("您的文档格式不正确！");
        }
        //创建sheet对象
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        //循环写入行数据
        for (int i = 0; i < 5; i++) {
            Row row = (Row) sheet1.createRow(i);
            //循环写入列数据
            for (int j = 0; j < 8; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("测试"+j);
            }
        }
        //创建文件流
        OutputStream stream = new FileOutputStream(path+fileName+"."+fileType);
        //写入数据
        wb.write(stream);
        //关闭文件流
        stream.close();
    }
    public void read(MultipartFile file) throws IOException
    {
        //获得文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        InputStream stream = file.getInputStream();
        Workbook wb = null;
        if (suffix.equals(".xls")) {
            wb = new HSSFWorkbook(stream);
        }
        else if (suffix.equals(".xlsx")) {
            wb = new XSSFWorkbook(stream);
        }
        else {
            System.out.println("您输入的excel格式不正确");
        }
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            for (Cell cell : row) {
                switch (cell.getCellTypeEnum()){
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue()+"  ");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue()+"  ");
                        break;
                }
            }
            System.out.println();
        }
    }
}
