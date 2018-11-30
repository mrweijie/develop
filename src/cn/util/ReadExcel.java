///**
// * @author imaj
// * Excel数据导入数据库
// * @version 1.0
// */
//package cn.util;
//
//
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ReadExcel  {
//    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
//    public List readExcel(MultipartFile file) {
//        try {
//            // jxl提供的Workbook类
//            Workbook wb = Workbook.getWorkbook(file.getInputStream());
//            // Excel的页签数量
//            int sheet_size = wb.getNumberOfSheets();
//            for (int index = 0; index < sheet_size; index++) {
//                List<List> outerList=new ArrayList<List>();
//                // 每个页签创建一个Sheet对象
//                Sheet sheet = wb.getSheet(index);
//                // sheet.getRows()返回该页的总行数
//                for (int i = 0; i < sheet.getRows(); i++) {
//                    List innerList=new ArrayList();
//                    // sheet.getColumns()返回该页的总列数
//                    for (int j = 0; j < sheet.getColumns(); j++) {
//                        String cellinfo = sheet.getCell(j, i).getContents();
//                        if(cellinfo.isEmpty()){
//                            continue;
//                        }
//                        innerList.add(cellinfo);
//                        System.out.print(cellinfo);
//                    }
//                    outerList.add(i, innerList);
//                    System.out.println();
//                }
//                return outerList;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (BiffException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void poiExcel(MultipartFile file){
//        try{
//            HSSFWorkbook xwb = new HSSFWorkbook(file.getInputStream());  //利用poi读取excel文件流
//            HSSFSheet st = xwb.getSheetAt(0);  //读取sheet的第一个工作表
//            int rows=st.getLastRowNum();//总行数
//            int cols;//总列数
//            for(int i=0;i<rows;i++){
//                HSSFRow row=st.getRow(i);//读取某一行数据
//                if(row!=null){
//                    //获取行中所有列数据
//                    cols=row.getLastCellNum();
//                    for(int j=0;j<cols;j++){
//                        HSSFCell cell=row.getCell((short) j);
//                        if(cell==null){
//                            System.out.print("   ");
//                        }else{
//                            //判断单元格的数据类型
//                            System.out.println(cell.getCellType());
//                            switch (cell.getCellType()) {
//                                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
//                                    System.out.print(cell.getNumericCellValue() + "   ");
//                                    break;
//                                case HSSFCell.CELL_TYPE_STRING: // 字符串
//                                    System.out.print(cell.getStringCellValue() + "   ");
//                                    break;
//                                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
//                                    System.out.println(cell.getBooleanCellValue() + "   ");
//                                    break;
//                                case HSSFCell.CELL_TYPE_FORMULA: // 公式
//                                    System.out.print(cell.getCellFormula() + "   ");
//                                    break;
//                                case HSSFCell.CELL_TYPE_BLANK: // 空值
//                                    System.out.println("");
//                                    break;
//                                case HSSFCell.CELL_TYPE_ERROR: // 故障
//                                    System.out.println("故障");
//                                    break;
//                                default:
//                                    System.out.print("未知类型   ");
//                                    break;
//                            }
//                        }
//                    }
//                }
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//    }
//}
