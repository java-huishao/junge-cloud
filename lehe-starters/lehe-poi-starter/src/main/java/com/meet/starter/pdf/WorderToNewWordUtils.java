package com.lehe.starter.pdf;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author houqj
 * 2019-11-20 16:34
 */
public class WorderToNewWordUtils {
    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     *
     * @param document  模板存放地址
     * @param tableList 需要插入的表格信息集合
     * @return 成功返回true, 失败返回false
     */
    public static XWPFDocument changWord(boolean hasPic, XWPFDocument document,
                                         Map<String, String> textMap, List<String[]> tableList) {

        //模板转换默认成功
        boolean changeFlag = true;
        //获取docx解析对象

//        changeDoMajor(document, textMap);

        //解析替换文本段落对象
        WorderToNewWordUtils.changeText(document, textMap);
        //解析替换表格对象
        WorderToNewWordUtils.changeTable(hasPic, document, textMap, tableList);
        return document;
    }

    private static void changeDoMajor(XWPFDocument document, Map<String, String> textMap) {
        XWPFTable table = document.getTableArray(0);
        //向下复制一行
        XWPFTableRow row = table.getRow(11);
        XWPFTableCell cell = row.getCell(1);
        System.out.println(cell);

    }

    public static XWPFDocument changeTableData(List<Map<String, String>> units, List<Map<String, String>> workers, List<Map<String, String>> progress, XWPFDocument document) throws InterruptedException {
        //获取表格对象集合
        XWPFTable table = document.getTableArray(0);
        XWPFTable table2 = document.getTableArray(2);
        XWPFTable table3 = document.getTableArray(4);
        int unitIndex = 9;
        int progressIndex = 5;
        int workerIndex = 13;

        document = changeUnits(document, table, units);
        document = changeProgress(document, table2, progress, progressIndex);
        document = changeWorkers(document, table3, workers, workerIndex);
        return document;
    }

    public static XWPFDocument changeProgress(XWPFDocument document, XWPFTable table, List<Map<String, String>> progress, int progressIndex) {
        //职称证书的位置下标：9
        int unitPos = 4;
        //向下复制一行
        XWPFTableRow Baserow = table.getRow(unitPos);
        //获取表格对象集合
        if (null != progress && progress.size() == 0) {
            table.removeRow(unitPos);
        }
        if (null != progress && progress.size() > 0) {
            if (progress.size() == 1) {
                Map<String, String> unit = progress.get(0);
                XWPFTableCell cell0 = Baserow.getCell(0);
                List<XWPFParagraph> p0 = cell0.getParagraphs();
                for (XWPFParagraph paragraph : p0) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {

                        String r = run.toString();
                        run.setText(unit.get("dateBefor") + "至" + unit.get("dateBehind"), 0);
                    }
                }
                XWPFTableCell cell1 = Baserow.getCell(1);
                List<XWPFParagraph> p1 = cell1.getParagraphs();
                for (XWPFParagraph paragraph : p1) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("content"), 0);
                    }
                }
            } else {
                for (Map<String, String> unit : progress) {
                    XWPFTableCell cell0 = Baserow.getCell(0);
                    List<XWPFParagraph> p0 = cell0.getParagraphs();
                    for (XWPFParagraph paragraph : p0) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("dateBefor") + "至" + unit.get("dateBehind"), 0);
                        }
                    }
                    XWPFTableCell cell1 = Baserow.getCell(1);
                    List<XWPFParagraph> p1 = cell1.getParagraphs();
                    for (XWPFParagraph paragraph : p1) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("content"), 0);
                        }
                    }
                    boolean b = table.addRow(Baserow, unitPos);
                }
                table.removeRow(unitPos);
            }
        }
        return document;
    }

    public static XWPFDocument changeWorkers(XWPFDocument document, XWPFTable table, List<Map<String, String>> workers, int workerIndex) {
//        Integer unitIndex = 9;
//        Integer wokerIndex = 9-1+units+14;
        //职称证书的位置下标：9
        int unitPos = workerIndex;
        //向下复制一行
        XWPFTableRow Baserow = table.getRow(unitPos);
        //获取表格对象集合
        if (null != workers && workers.size() == 0) {
            table.removeRow(unitPos);
        }
        if (null != workers && workers.size() > 0) {
            if (workers.size() == 1) {
                Map<String, String> unit = workers.get(0);
                XWPFTableCell cell0 = Baserow.getCell(0);
                List<XWPFParagraph> p0 = cell0.getParagraphs();
                for (XWPFParagraph paragraph : p0) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("name"), 0);
                    }
                }
                XWPFTableCell cell1 = Baserow.getCell(1);
                List<XWPFParagraph> p1 = cell1.getParagraphs();
                for (XWPFParagraph paragraph : p1) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("major"), 0);
                    }
                }
                XWPFTableCell cell2 = Baserow.getCell(2);
                List<XWPFParagraph> p2 = cell2.getParagraphs();
                for (XWPFParagraph paragraph : p2) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("level"), 0);
                    }
                }
                XWPFTableCell cell3 = Baserow.getCell(3);
                List<XWPFParagraph> p3 = cell3.getParagraphs();
                for (XWPFParagraph paragraph : p3) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("education"), 0);
                    }
                }
                XWPFTableCell cell4 = Baserow.getCell(4);
                List<XWPFParagraph> p4 = cell4.getParagraphs();
                for (XWPFParagraph paragraph : p4) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("misson"), 0);
                    }
                }
                XWPFTableCell cell5 = Baserow.getCell(5);
                List<XWPFParagraph> p5 = cell5.getParagraphs();
                for (XWPFParagraph paragraph : p5) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("unit"), 0);
                    }
                }
            } else {
                for (Map<String, String> unit : workers) {
                    XWPFTableCell cell0 = Baserow.getCell(0);
                    List<XWPFParagraph> p0 = cell0.getParagraphs();
                    for (XWPFParagraph paragraph : p0) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("name"), 0);
                        }
                    }
                    XWPFTableCell cell1 = Baserow.getCell(1);
                    List<XWPFParagraph> p1 = cell1.getParagraphs();
                    for (XWPFParagraph paragraph : p1) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("major"), 0);
                        }
                    }
                    XWPFTableCell cell2 = Baserow.getCell(2);
                    List<XWPFParagraph> p2 = cell2.getParagraphs();
                    for (XWPFParagraph paragraph : p2) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("level"), 0);
                        }
                    }
                    XWPFTableCell cell3 = Baserow.getCell(3);
                    List<XWPFParagraph> p3 = cell3.getParagraphs();
                    for (XWPFParagraph paragraph : p3) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("education"), 0);
                        }
                    }
                    XWPFTableCell cell4 = Baserow.getCell(4);
                    List<XWPFParagraph> p4 = cell4.getParagraphs();
                    for (XWPFParagraph paragraph : p4) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("misson"), 0);
                        }
                    }
                    XWPFTableCell cell5 = Baserow.getCell(5);
                    List<XWPFParagraph> p5 = cell5.getParagraphs();
                    for (XWPFParagraph paragraph : p5) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("unit"), 0);
                        }
                    }
                    boolean b = table.addRow(Baserow, unitPos);
                }
                table.removeRow(unitPos);
                table.removeRow(table.getRows().size() - 1);
            }
        }
        return document;
    }

    public static XWPFDocument changeUnits(XWPFDocument document, XWPFTable table, List<Map<String, String>> units) {
        //职称证书的位置下标：9
        int unitPos = 9;
        //向下复制一行
        XWPFTableRow Baserow = table.getRow(9);
        //获取表格对象集合
        if (null != units && units.size() == 0) {
            table.removeRow(unitPos);
        }
        if (null != units && units.size() > 0) {
            if (units.size() == 1) {
                Map<String, String> unit = units.get(0);
                XWPFTableCell cell0 = Baserow.getCell(0);
                List<XWPFParagraph> p0 = cell0.getParagraphs();
                for (XWPFParagraph paragraph : p0) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("company"), 0);
                    }
                }
                XWPFTableCell cell1 = Baserow.getCell(1);
                List<XWPFParagraph> p1 = cell1.getParagraphs();
                for (XWPFParagraph paragraph : p1) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("companyRepresent"), 0);
                    }
                }
                XWPFTableCell cell2 = Baserow.getCell(2);
                List<XWPFParagraph> p2 = cell2.getParagraphs();
                for (XWPFParagraph paragraph : p2) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("proPerson"), 0);
                    }
                }
                XWPFTableCell cell3 = Baserow.getCell(3);
                List<XWPFParagraph> p3 = cell3.getParagraphs();
                for (XWPFParagraph paragraph : p3) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("mobile"), 0);
                    }
                }
                XWPFTableCell cell4 = Baserow.getCell(4);
                List<XWPFParagraph> p4 = cell4.getParagraphs();
                for (XWPFParagraph paragraph : p4) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String r = run.toString();
                        run.setText(unit.get("email"), 0);
                    }
                }
            } else {
                for (Map<String, String> unit : units) {
                    XWPFTableCell cell0 = Baserow.getCell(0);
                    List<XWPFParagraph> p0 = cell0.getParagraphs();
                    for (XWPFParagraph paragraph : p0) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("company"), 0);
                        }
                    }
                    XWPFTableCell cell1 = Baserow.getCell(1);
                    List<XWPFParagraph> p1 = cell1.getParagraphs();
                    for (XWPFParagraph paragraph : p1) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("companyRepresent"), 0);
                        }
                    }
                    XWPFTableCell cell2 = Baserow.getCell(2);
                    List<XWPFParagraph> p2 = cell2.getParagraphs();
                    for (XWPFParagraph paragraph : p2) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("proPerson"), 0);
                        }
                    }
                    XWPFTableCell cell3 = Baserow.getCell(3);
                    List<XWPFParagraph> p3 = cell3.getParagraphs();
                    for (XWPFParagraph paragraph : p3) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("proPersonPhone"), 0);
                        }
                    }
                    XWPFTableCell cell4 = Baserow.getCell(4);
                    List<XWPFParagraph> p4 = cell4.getParagraphs();
                    for (XWPFParagraph paragraph : p4) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(unit.get("email"), 0);
                        }
                    }
                    boolean b = table.addRow(Baserow, unitPos);
                }
                table.removeRow(unitPos);
            }
        }
        return document;
    }

    public static XWPFDocument changeTableRows(List<Map<String, String>> titleCerts, List<Map<String, String>> regCerts, XWPFDocument document) throws InterruptedException {
        //职称证书的位置下标：7
        int titleCertPos = 6;
        int qualPos = 7;
        //获取表格对象集合
        XWPFTable table = document.getTableArray(0);
        //向下复制一行
        XWPFTableRow row = table.getRow(titleCertPos);
        XWPFTableRow row1 = table.getRow(qualPos);

        if (titleCerts.size() == 0) {
            table.removeRow(6);
        } else if (titleCerts.size() == 1) {
            Map<String, String> titleCert = titleCerts.get(0);
            //职称类型
            XWPFTableCell cell1 = row.getCell(1);
            List<XWPFParagraph> p1 = cell1.getParagraphs();
            for (XWPFParagraph paragraph : p1) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    run.setText(titleCert.get("titleCertType"), 0);
                }
            }
            //职称类型
            XWPFTableCell cell3 = row.getCell(3);
            List<XWPFParagraph> p3 = cell3.getParagraphs();
            for (XWPFParagraph paragraph : p3) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    run.setText(titleCert.get("titleCertNum"), 0);
                }
            }
            //职称类型
            XWPFTableCell cell5 = row.getCell(5);
            List<XWPFParagraph> p5 = cell5.getParagraphs();
            for (XWPFParagraph paragraph : p5) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    run.setText(titleCert.get("certificateOrg") + "\r\n\r\n" + titleCert.get("titleCertDate"), 0);
                }
            }
        } else {
            for (int i = 0; i < titleCerts.size(); i++) {
                Map<String, String> titleCert = titleCerts.get(i);
                //职称类型
                XWPFTableCell cell1 = row.getCell(1);
                List<XWPFParagraph> p1 = cell1.getParagraphs();
                for (XWPFParagraph paragraph : p1) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        run.setText(titleCert.get("titleCertType"), 0);
                    }
                }
                //职称类型
                XWPFTableCell cell3 = row.getCell(3);
                List<XWPFParagraph> p3 = cell3.getParagraphs();
                for (XWPFParagraph paragraph : p3) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        run.setText(titleCert.get("titleCertNum"), 0);
                    }
                }
                //职称类型
                XWPFTableCell cell5 = row.getCell(5);
                List<XWPFParagraph> p5 = cell5.getParagraphs();
                for (XWPFParagraph paragraph : p5) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        run.setText(titleCert.get("certificateOrg") + "\r\n\r\n" + titleCert.get("titleCertDate"), 0);
                    }
                }
                boolean b = table.addRow(row, titleCertPos);
                System.out.println("新增执业资格多行:" + i + "==>" + b);
            }
            table.removeRow(6);
        }



        /*执业资格证书*/
        if (regCerts.size() == 0) {
            table.removeRow(qualPos + titleCerts.size());
        } else if (regCerts.size() == 1) {
            Map<String, String> regCert = regCerts.get(0);
            XWPFTableCell cell1 = row1.getCell(1);
            List<XWPFParagraph> p1 = cell1.getParagraphs();
            for (XWPFParagraph paragraph : p1) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    run.setText(regCert.get("regCertType"), 0);
                }
            }
            XWPFTableCell cell3 = row1.getCell(3);
            List<XWPFParagraph> p3 = cell3.getParagraphs();
            for (XWPFParagraph paragraph : p3) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    run.setText(regCert.get("regCertNum"), 0);
                }
            }
            XWPFTableCell cell5 = row1.getCell(5);
            List<XWPFParagraph> p5 = cell5.getParagraphs();
            for (XWPFParagraph paragraph : p5) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    run.setText(regCert.get("certificateOrg") + "\r\n\r\n" + regCert.get("titleCertDate"), 0);
                }
            }
        } else {
            for (int i = 0; i < regCerts.size(); i++) {
                Map<String, String> regCert = regCerts.get(i);
                XWPFTableCell cell1 = row1.getCell(1);
                List<XWPFParagraph> p1 = cell1.getParagraphs();
                for (XWPFParagraph paragraph : p1) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        run.setText(regCert.get("regCertType"), 0);
                    }
                }
                XWPFTableCell cell3 = row1.getCell(3);
                List<XWPFParagraph> p3 = cell3.getParagraphs();
                for (XWPFParagraph paragraph : p3) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        run.setText(regCert.get("regCertNum"), 0);
                    }
                }
                XWPFTableCell cell5 = row1.getCell(5);
                List<XWPFParagraph> p5 = cell5.getParagraphs();
                for (XWPFParagraph paragraph : p5) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        run.setText(regCert.get("certificateOrg") + "\r\n\r\n" + regCert.get("titleCertDate"), 0);
                    }
                }
                boolean b = table.addRow(row1, qualPos + titleCerts.size());
                System.out.println("执业资格多行:" + i + "==>" + b);
            }
        }

        //插入一个新行
//                XWPFTableRow row = table.insertNewTableRow(7);
//                for (int k = 0; k < 6; k++) {
//                    XWPFTableCell cell = row.createCell();//根据String数组第一条数据的长度动态创建列
//                    CTTrPr ctTrPr = row.getCtRow().addNewTrPr();
//                    CTHeight ctHeight = ctTrPr.addNewTrHeight();
//                    ctHeight.setVal(BigInteger.valueOf(624));
//                    CTTc cttc = cell.getCTTc();
//                    CTTcPr ctTcPr = cttc.addNewTcPr();
//                    CTTblWidth ctTblWidth = ctTcPr.addNewTcW();
//                    ctTblWidth.setW(new BigInteger("8800"));
//                    ctTblWidth.setType(STTblWidth.DXA);
//                }
        document.setTable(0, table);
        return document;
    }

    /**
     * 替换段落文本
     *
     * @param document docx解析对象
     * @param textMap  需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, String> textMap) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            if (checkText(text)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    //替换模板原来位置
                    run.setText(changeValue(run.toString(), textMap), 0);
                }
            }
        }

    }

    /**
     * 替换表格对象方法
     *
     * @param document  docx解析对象
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     */
    public static void changeTable(boolean hasPic, XWPFDocument document, Map<String, String> textMap,
                                   List<String[]> tableList) {
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (int i = 0; i < tables.size(); i++) {
            //只处理行数大于等于2的表格，且不循环表头
            XWPFTable table = tables.get(i);
            List<XWPFTableRow> rows = table.getRows();
            if (rows.size() > 1) {
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if (checkText(table.getText())) {
                    //遍历表格,并替换模板
                    eachTable(hasPic, rows, textMap);
                } else {
                    System.out.println("插入" + table.getText());
                    insertTable(table, tableList);
                }
            }
        }
    }


    /**
     * 遍历表格
     *
     * @param rows    表格行对象
     * @param textMap 需要替换的信息集合
     */
    public static void eachTable(boolean hasPic, List<XWPFTableRow> rows, Map<String, String> textMap) {
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            //如果用户有头像，把照片二字去掉
            for (XWPFTableCell cell : cells) {
                if (hasPic) {
                    if ("照片".equals(cell.getText())) {
                        List<XWPFParagraph> paragraphs = cell.getParagraphs();
                        for (XWPFParagraph paragraph : paragraphs) {
                            List<XWPFRun> runs = paragraph.getRuns();
                            for (XWPFRun run : runs) {
                                run.setText("", 0);
                            }
                        }
                    }
                }
                //判断单元格是否需要替换
                if (checkText(cell.getText())) {
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            String r = run.toString();
                            run.setText(changeValue(r, textMap), 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table     需要插入数据的表格
     * @param tableList 插入数据集合
     */
    public static void insertTable(XWPFTable table, List<String[]> tableList) {
        if (null != tableList) {
            //创建行,根据需要插入的数据添加新行，不处理表头
            for (int i = 1; i < tableList.size(); i++) {
                XWPFTableRow row = table.createRow();
            }
            //遍历表格插入数据
            List<XWPFTableRow> rows = table.getRows();
            for (int i = 1; i < rows.size(); i++) {
                XWPFTableRow newRow = table.getRow(i);
                List<XWPFTableCell> cells = newRow.getTableCells();
                for (int j = 0; j < cells.size(); j++) {
                    XWPFTableCell cell = cells.get(j);
                    cell.setText(tableList.get(i - 1)[j]);
                }
            }
        }


    }


    /**
     * 判断文本中时候包含$
     *
     * @param text 文本
     * @return 包含返回true, 不包含返回false
     */
    public static boolean checkText(String text) {
        boolean check = text.indexOf("$") != -1;
        return check;

    }

    /**
     * 匹配传入信息集合与模板
     *
     * @param value   模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static String changeValue(String value, Map<String, String> textMap) {
        Set<Map.Entry<String, String>> textSets = textMap.entrySet();
        for (Map.Entry<String, String> textSet : textSets) {
            //匹配模板与替换值 格式${key}
            String key = "${" + textSet.getKey() + "}";
            System.out.println("key====>" + key);
            System.out.println("value====>" + value);
            if (value.indexOf(key) != -1) {
                value = textSet.getValue();
            }
        }
        //模板未匹配到区域替换为空
        if (checkText(value)) {
            value = "";
        }
        return value;
    }

    /**
     * 因POI 3.8自带的BUG 导致添加进的图片不显示，只有一个图片框，将图片另存为发现里面的图片是一个PNG格式的透明图片
     * 这里自定义添加图片的方法
     * 往Run中插入图片(解决在word中不显示的问题)
     *
     * @param run
     * @param blipId               图片的id
     * @param id               图片的类型
     * @param width       图片的宽
     * @param height      图片的高
     * @author lgj
     */
    public static void addPictureToRun(XWPFRun run, String blipId, int id, int width, int height) {
        final int EMU = 9525;
        width *= EMU;
        height *= EMU;

        CTInline inline = run.getCTR().addNewDrawing().addNewInline();

        String picXml = "" +
                "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">" +
                "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                "         <pic:nvPicPr>" +
                "            <pic:cNvPr id=\"" + id + "\" name=\"Generated\"/>" +
                "            <pic:cNvPicPr/>" +
                "         </pic:nvPicPr>" +
                "         <pic:blipFill>" +
                "            <a:blip r:embed=\"" + blipId + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>" +
                "            <a:stretch>" +
                "               <a:fillRect/>" +
                "            </a:stretch>" +
                "         </pic:blipFill>" +
                "         <pic:spPr>" +
                "            <a:xfrm>" +
                "               <a:off x=\"0\" y=\"0\"/>" +
                "               <a:ext cx=\"" + width + "\" cy=\"" + height + "\"/>" +
                "            </a:xfrm>" +
                "            <a:prstGeom prst=\"rect\">" +
                "               <a:avLst/>" +
                "            </a:prstGeom>" +
                "         </pic:spPr>" +
                "      </pic:pic>" +
                "   </a:graphicData>" +
                "</a:graphic>";

        //CTGraphicalObjectData graphicData = inline.addNewGraphic().addNewGraphicData();
        XmlToken xmlToken = null;
        try {
            xmlToken = XmlToken.Factory.parse(picXml);
        } catch (XmlException xe) {
            xe.printStackTrace();
        }
        inline.set(xmlToken);
        //graphicData.set(xmlToken);

        inline.setDistT(0);
        inline.setDistB(0);
        inline.setDistL(0);
        inline.setDistR(0);

        CTPositiveSize2D extent = inline.addNewExtent();
        extent.setCx(width);
        extent.setCy(height);

        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
        docPr.setId(id);
        docPr.setName("Picture " + id);
        docPr.setDescr("Generated");
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        String imgUrl = "E:\\test.jpeg";
        //模板文件地址
        String inputUrl = "E:\\wd-work\\周计划\\专家导出pdf\\expert-mb.docx";
        //新生产的模板文件
        String outputUrl = "E:\\wd-work\\周计划\\专家导出pdf\\expert-1.docx";

        Map<String, String> testMap = new HashMap<String, String>();
        testMap.put("name", "小明");
        testMap.put("sex", "男");
        testMap.put("address", "软件园");
        testMap.put("phone", "88888888");
        testMap.put("email", "123abc@qq.com");

        List<String[]> testList = new ArrayList<String[]>();
        testList.add(new String[]{"1", "1AA", "1BB", "1CC"});
        testList.add(new String[]{"2", "2AA", "2BB", "2CC"});
        testList.add(new String[]{"3", "3AA", "3BB", "3CC"});
        testList.add(new String[]{"4", "4AA", "4BB", "4CC"});
        FileInputStream fileInputStream = new FileInputStream(new File(""));
        XWPFDocument document = new XWPFDocument(fileInputStream);
        document = WorderToNewWordUtils.changWord(true, document, testMap, testList);
        //生成多行测试
        //WorderToNewWordUtils.changeTableRows(List<Map<String,String>> titleCerts,List<Map<String,String>>, 2, document);


        XWPFTable table = document.getTableArray(0);
        XWPFTableRow xwpfTableRow = table.getRow(0);
        List<XWPFTableCell> tableCells = xwpfTableRow.getTableCells();
        XWPFTableCell xwpfTableCell = tableCells.get(6);
        List<XWPFParagraph> paragraphs = xwpfTableCell.getParagraphs();
        XWPFParagraph xwpfParagraph = paragraphs.get(0);

        String picId = document.addPictureData(new FileInputStream(imgUrl), XWPFDocument.PICTURE_TYPE_JPEG);

        addPictureToRun(xwpfParagraph.createRun(), picId, XWPFDocument.PICTURE_TYPE_JPEG, 100, 100);


        //生成新的word
        File file = new File(outputUrl);
        FileOutputStream stream = new FileOutputStream(file);
        document.write(stream);
        stream.close();
    }

    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = XWPFDocument.PICTURE_TYPE_PICT;
        if (picType != null) {
            if ("png".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_PNG;
            } else if ("dib".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_DIB;
            } else if ("emf".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_EMF;
            } else if ("jpg".equalsIgnoreCase(picType) || "jpeg".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_JPEG;
            } else if ("wmf".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }


}
