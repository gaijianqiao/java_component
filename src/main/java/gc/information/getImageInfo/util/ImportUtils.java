package gc.information.getImageInfo.util;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportUtils {

    public static final String dateFormat = "yyyy/MM/dd HH:mm:ss";
    private static Logger logger = LoggerFactory.getLogger(ImportUtils.class);

    public static <T extends CanNewSelf<T>> List<T> readExcel(File file, Integer sheetIndex, Integer beginRow, T t, List<ConvertCellsToBean> convertInfoList) {

        sheetIndex = sheetIndex == null ? 0 : sheetIndex;
        beginRow = beginRow == null ? 0 : beginRow;

        List<T> list = new ArrayList<T>();
        Workbook wb = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            wb = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(inputStream);
            int sheetNum = wb.getNumberOfSheets();
            if (sheetNum > 0) {
                Sheet sheet = wb.getSheetAt(sheetIndex);
                Integer cellNum = null;
                for (Row row : sheet) {
                    int rowIndex = row.getRowNum();
                    if (rowIndex == 0) {
                        cellNum = row.getLastCellNum() - row.getFirstCellNum();
                    }
                    if (rowIndex < beginRow) {
                        continue;
                    }
                    T newT = t.getNewSelfInstance();
                    for (int cellIndex = 0; cellIndex < cellNum; cellIndex++) {
                        String tempStr = null;
                        Cell cell = row.getCell(cellIndex);
                        if (cell == null) {
                            tempStr = null;
                        } else {
                            CellType cellType = cell.getCellType();
                            if (CellType._NONE == cellType || CellType.BLANK == cellType
                                    || CellType.ERROR == cellType) {
                                tempStr = null;
                            } else if (CellType.NUMERIC == cellType) {
                                boolean isDateCell = DateUtil.isCellDateFormatted(cell);
                                if (isDateCell) {
                                    // 日期格式
                                    Date date = cell.getDateCellValue();

                                    tempStr = String.valueOf(date.getTime());
                                } else {
                                    // 不是日期格式
                                    tempStr = cell.getStringCellValue().trim();
                                }
                            } else {
                                tempStr = cell.getStringCellValue().trim();
                            }
                        }
//                        singleRow[cellIndex]
                        convertReadInfo(cellIndex, tempStr, convertInfoList, newT);
                    }
                    list.add(newT);
                }
            }
            inputStream.close();
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    private static <T extends CanNewSelf<T>> T convertReadInfo(int index, String str, List<ConvertCellsToBean> convertInfoList, T t) {
        logger.info("start set instance info, index:{},str:{},t{}", index, str, t);
        try {
            String fieldStr = convertInfoList.get(index).getFiled();
            Field field = ReflectionUtils.findField(t.getClass(), fieldStr);
            Method method = ReflectionUtils.findMethod(t.getClass(), getSetMethodName(fieldStr),null);
            if (field != null && field.getType().equals(String.class)) {
                ReflectionUtils.invokeMethod(method, t, str);
            }
            if (field != null && field.getType().equals(Date.class)) {
                try {
                    ReflectionUtils.invokeMethod(method, t, new Date(Long.parseLong(str)));
                } catch (NumberFormatException e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
                    try {
                        ReflectionUtils.invokeMethod(method, t, simpleDateFormat.parse(str));
                    } catch (ParseException ex) {
                        logger.error("the str will not been set because convert date error,str:{},dateFormat:{}", str, dateFormat);
                    }
                }
            }
            if (field != null && field.getType().equals(BigDecimal.class)) {
                ReflectionUtils.invokeMethod(method, t, new BigDecimal(str));
            }

            if (field != null && (field.getType().equals(Integer.class)||field.getType().getName().equals("int"))) {
                ReflectionUtils.invokeMethod(method, t, Integer.parseInt(str));
            }
        } catch (Exception e) {
            logger.error("reflect happend error,message:{}", e.getMessage());
        }
        return t;
    }

    private static String captureStr(String str) {

        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    private static String getSetMethodName(String str) {
        String retStr = "set" + captureStr(str);
        return retStr;
    }


    public interface CanNewSelf<T> {
        T getNewSelfInstance();
    }


    public static class ConvertCellsToBean {
        private int cellNum;
        private String filed;

        public ConvertCellsToBean(int cellNum, String filed) {
            this.cellNum = cellNum;
            this.filed = filed;
        }

        public int getCellNum() {
            return cellNum;
        }

        public void setCellNum(int cellNum) {
            this.cellNum = cellNum;
        }

        public String getFiled() {
            return filed;
        }

        public void setFiled(String filed) {
            this.filed = filed;
        }
    }
}
