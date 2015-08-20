package com.unstruct.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class PoiTest {

	public static String ppt2String(File file) {
		return null;
	}

	public static String xls2String(File file) {
		// TODO Auto-generated method stub

		return null;
	}

	public static void createHSSF() {

		Workbook wb = new XSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet1 = wb.createSheet("new sheet");
		Sheet sheet2 = wb.createSheet("second sheet");
		// 创建一行并放一些单元格到该行中，行的索引是以0开始的
		Row row = sheet1.createRow((short) 0);
		// 创建一个单元格并填充一个整数的值
		Cell cell = row.createCell(0);
		cell.setCellValue(1);

		// 链式写法
		row.createCell(1).setCellValue(1.2);
		row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
		row.createCell(3).setCellValue(true);

		cell = row.createCell(4);
		cell.setCellValue(new Date());

		// we style the second cell as a date (and time). It is important to
		// create a new cell style from the workbook otherwise you can end up
		// modifying the built in style and effecting not only this cell but
		// other cells.
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/MM/dd hh:mm"));
		cell = row.createCell(5);
		// cell.setCellValue(new Date());
		Date date = new Date();

		cell.setCellValue(createHelper.createRichTextString(date.toString()));
		cell.setCellStyle(cellStyle);

		// you can also set date as java.util.Calendar
		cell = row.createCell(6);
		cell.setCellValue(Calendar.getInstance());
		cell.setCellStyle(cellStyle);

		row.createCell(7).setCellType(XSSFCell.CELL_TYPE_ERROR);

		// ===========================1

		row = sheet1.createRow((short) 1);
		row.setHeightInPoints(30);

		createCell(wb, row, (short) 0, XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.VERTICAL_BOTTOM);
		createCell(wb, row, (short) 1, XSSFCellStyle.ALIGN_CENTER_SELECTION, XSSFCellStyle.VERTICAL_BOTTOM);
		createCell(wb, row, (short) 2, XSSFCellStyle.ALIGN_FILL, XSSFCellStyle.VERTICAL_CENTER);
		createCell(wb, row, (short) 3, XSSFCellStyle.ALIGN_GENERAL, XSSFCellStyle.VERTICAL_CENTER);
		createCell(wb, row, (short) 4, XSSFCellStyle.ALIGN_JUSTIFY, XSSFCellStyle.VERTICAL_JUSTIFY);
		createCell(wb, row, (short) 5, XSSFCellStyle.ALIGN_LEFT, XSSFCellStyle.VERTICAL_TOP);
		createCell(wb, row, (short) 6, XSSFCellStyle.ALIGN_RIGHT, XSSFCellStyle.VERTICAL_TOP);

		// ------------------------2

		row = sheet1.createRow(2);

		// Create a cell and put a value in it.
		cell = row.createCell(1);
		cell.setCellValue(4);

		// Style the cell with borders all around.
		CellStyle style = wb.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLUE.getIndex());
		style.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cell.setCellStyle(style);

		// ============================
		row = sheet1.createRow((short) 3);

		// Aqua background
		CellStyle style2 = wb.createCellStyle();
		style2.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
		style2.setFillPattern(CellStyle.ALIGN_FILL);
		cell = row.createCell((short) 1);
		cell.setCellValue("X");
		cell.setCellStyle(style2);

		// Orange "foreground", foreground being the fill foreground not the
		// font color.
		style2 = wb.createCellStyle();
		style2.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell = row.createCell((short) 2);
		cell.setCellValue("X");
		cell.setCellStyle(style2);

		// =================
		row = sheet1.createRow((short) 4);
		cell = row.createCell((short) 1);
		cell.setCellValue("This is a test of merging");

		sheet1.addMergedRegion(new CellRangeAddress(4, // first row (0-based)
				5, // last row (0-based)
				1, // first column (0-based)
				6 // last column (0-based)
		));

		// ======================
		row = sheet1.createRow(6);

		// Create a new font and alter it.
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 24);
		font.setFontName("Courier New");
		font.setItalic(true);
		font.setStrikeout(true);

		// Fonts are set into a style so create a new one to use.
		CellStyle style3 = wb.createCellStyle();
		style3.setFont(font);

		// Create a cell and put a value in it.
		cell = row.createCell(1);
		cell.setCellValue("This is a test of fonts");
		cell.setCellStyle(style3);

		// =============

		row = sheet1.createRow(7);
		cell = row.createCell(2);
		cell.setCellValue("Use \n with word wrap on to create a new line");

		// to enable newlines you need set a cell styles with wrap=true
		CellStyle cs = wb.createCellStyle();
		cs.setWrapText(true);
		cell.setCellStyle(cs);

		// increase row height to accomodate two lines of text
		row.setHeightInPoints((2 * sheet1.getDefaultRowHeightInPoints()));

		// adjust column width to fit the content
		sheet1.autoSizeColumn((short) 2);

		// =====================

		DataFormat format = wb.createDataFormat();
		short rowNum = 8;
		short colNum = 0;

		row = sheet1.createRow(rowNum++);
		cell = row.createCell(colNum);
		cell.setCellValue(11111.25);
		CellStyle style5 = wb.createCellStyle();
		style5.setDataFormat(format.getFormat("0.0"));
		cell.setCellStyle(style);

		row = sheet1.createRow(rowNum++);
		cell = row.createCell(colNum);
		cell.setCellValue(111111.25);
		style5 = wb.createCellStyle();
		style5.setDataFormat(format.getFormat("#,###.0000"));
		cell.setCellStyle(style);

		// =================
		wb.setPrintArea(0, "$A$1:$C$2");

		// Alternatively:
		wb.setPrintArea(0, // sheet index
				0, // start column
				1, // end column
				0, // start row
				0 // end row
		);

		// ==========

		try {
			FileOutputStream fos = new FileOutputStream("/home/spark/input/workbook.xlsx");
			wb.write(fos);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void creatXssf() throws IOException {
		FileOutputStream fos = new FileOutputStream("/home/spark/input/workbook.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("custom XSSF colors");

		XSSFCellStyle style1 = wb.createCellStyle();
		style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 0, 128)));
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);

		wb.write(fos);
		fos.close();

	}

	public static void createHssfFile() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = sheet.createRow((short) 0);
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("Default Palette");

		// apply some colors from the standard palette,
		// as in the previous examples.
		// we'll use red text on a lime background

		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.LIME.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);

		cell.setCellStyle(style);

		// save with the default palette
		FileOutputStream out = new FileOutputStream("/home/spark/input/default_palette.xls");
		wb.write(out);
		out.close();

		// now, let's replace RED and LIME in the palette
		// with a more attractive combination
		// (lovingly borrowed from freebsd.org)

		cell.setCellValue("Modified Palette");

		// creating a custom palette for the workbook
		HSSFPalette palette = wb.getCustomPalette();

		// replacing the standard red with freebsd.org red
		palette.setColorAtIndex(HSSFColor.RED.index, (byte) 153, // RGB red
																	// (0-255)
				(byte) 0, // RGB green
				(byte) 0 // RGB blue
		);
		// replacing lime with freebsd.org gold
		palette.setColorAtIndex(HSSFColor.LIME.index, (byte) 255, (byte) 204, (byte) 102);

		HSSFFooter footer = sheet.getFooter();
		footer.setRight("Page personal " + HSSFFooter.page() + " of " + HSSFFooter.numPages());

		sheet.setSelected(true);
		sheet.setZoom(3, 4);

		for (int i = 0; i < 20; i++) {
			row = sheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue("" + i);
		}
		// Shift rows 6 - 11 on the spreadsheet to the top (rows 0 - 5)
		// 把第6-11行向上移动5行
		sheet.shiftRows(5, 10, -5);
		
		
		PrintSetup ps = sheet.getPrintSetup();  
		  
	    sheet.setAutobreaks(true);  
	  
	    ps.setFitHeight((short)1);  
	    ps.setFitWidth((short)1);  
	    
	    
	    

		ExcelExtractor excelExtractor = new ExcelExtractor((HSSFWorkbook) wb);
		excelExtractor.setFormulasNotResults(true);
		excelExtractor.setIncludeSheetNames(true);
		excelExtractor.setIncludeBlankCells(false);
		excelExtractor.setIncludeHeadersFooters(true);
		String text = excelExtractor.getText();

		System.out.println(text);

		// save with the modified palette
		// note that wherever we have previously used RED or LIME, the
		// new colors magically appear
		out = new FileOutputStream("/home/spark/input/modified_palette.xls");
		wb.write(out);
		out.close();

	}

	private static void createCell(Workbook wb, Row row, short column, short halign, short valign) {
		Cell cell = row.createCell(column);
		cell.setCellValue(new XSSFRichTextString("Align It"));
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(halign);
		cellStyle.setVerticalAlignment(valign);
		cell.setCellStyle(cellStyle);

	}

	public static void getContentXssf(){
		InputStream is = null;
		try {
			is = new FileInputStream("/home/spark/input/workbook.xlsx");
//			Workbook wb = new XSSFWorkbook(is);
			Workbook wb=WorkbookFactory.create(is);
			InputStream is2 = new FileInputStream("/home/spark/input/workbook.xls");
//			Workbook hssfWb = new HSSFWorkbook(is2);
			Workbook hssfWb=WorkbookFactory.create(is2);
			// Sheet sheet=wb.getSheet("new sheet");
			Sheet sheet = wb.getSheetAt(0);
			for (Iterator riter = sheet.iterator(); riter.hasNext();) {
				Row row = (Row) riter.next();
				for (Iterator citer = row.iterator(); citer.hasNext();) {
					Cell cell = (Cell) citer.next();

				}

			}

			for (Row row : sheet) {
				for (Cell cell : row) {
					CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
					System.out.print(cellRef.formatAsString());
					System.out.print("-");

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						System.out.println(cell.getRichStringCellValue().getString());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							System.out.println(cell.getDateCellValue());
						} else {
							System.out.println(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println(cell.getCellFormula());
						break;
					default:
						System.out.println("");
					}
				}
			}
			System.out.println("=============");
			ExcelExtractor excelExtractor = new ExcelExtractor((HSSFWorkbook) hssfWb);
			excelExtractor.setFormulasNotResults(true);
			excelExtractor.setIncludeSheetNames(true);
			excelExtractor.setIncludeBlankCells(false);
			excelExtractor.setIncludeHeadersFooters(false);
			String text = excelExtractor.getText();
			System.out.println(text);
			System.out.println("=============");

			try {
				is = new FileInputStream("/home/spark/input/workbook.xlsx");
				wb = WorkbookFactory.create(is);
				sheet = wb.getSheetAt(0);
				Row row = sheet.getRow(0);
				Cell cell = row.getCell(3);
				if (cell == null)
					cell = row.createCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue("a test aaaaaaaaaaa");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			XSSFExcelExtractor extractor = new XSSFExcelExtractor((XSSFWorkbook) wb);
			extractor.setFormulasNotResults(true);
			extractor.setIncludeSheetNames(true);
			extractor.setIncludeHeadersFooters(false);
			extractor.setIncludeTextBoxes(false);
			extractor.setIncludeCellComments(true);
			System.out.println(extractor.getText());

			System.out.println("====================");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static String pdf2String(File file) {
		String result = "";
		FileInputStream fis = null;
		PDDocument document = null;
		try {
			fis = new FileInputStream(file);
			PDFParser parse = new PDFParser(fis);
			parse.parse();
			document = parse.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			result = stripper.getText(document);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String doc2String(File file) {

		StringBuilder result = new StringBuilder();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			WordExtractor extractor=new WordExtractor(fis);
			result.append(extractor.getText());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	public static void createXwpf() throws IOException {

		XWPFDocument doc = new XWPFDocument();

		XWPFParagraph p1 = doc.createParagraph();
		p1.setAlignment(ParagraphAlignment.CENTER);
		p1.setBorderBottom(Borders.DOUBLE);
		p1.setBorderTop(Borders.DOUBLE);

		p1.setBorderRight(Borders.DOUBLE);
		p1.setBorderLeft(Borders.DOUBLE);
		p1.setBorderBetween(Borders.SINGLE);

		p1.setVerticalAlignment(TextAlignment.TOP);

		XWPFRun r1 = p1.createRun();
		r1.setBold(true);
		r1.setText("The quick brown fox");
		r1.setBold(true);
		r1.setFontFamily("Courier");
		r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
		r1.setTextPosition(100);

		XWPFParagraph p2 = doc.createParagraph();
		p2.setAlignment(ParagraphAlignment.RIGHT);

		// BORDERS
		p2.setBorderBottom(Borders.DOUBLE);
		p2.setBorderTop(Borders.DOUBLE);
		p2.setBorderRight(Borders.DOUBLE);
		p2.setBorderLeft(Borders.DOUBLE);
		p2.setBorderBetween(Borders.SINGLE);

		XWPFRun r2 = p2.createRun();
		r2.setText("jumped over the lazy dog");
		r2.setStrike(true);
		r2.setFontSize(20);

		XWPFRun r3 = p2.createRun();
		r3.setText("and went away");
		r3.setStrike(true);
		r3.setFontSize(20);
		r3.setSubscript(VerticalAlign.SUPERSCRIPT);

		XWPFParagraph p3 = doc.createParagraph();
		p3.setWordWrap(true);
		p3.setPageBreak(true);

		// p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
		p3.setAlignment(ParagraphAlignment.BOTH);
		p3.setSpacingLineRule(LineSpacingRule.EXACT);

		p3.setIndentationFirstLine(600);

		XWPFRun r4 = p3.createRun();
		r4.setTextPosition(20);
		r4.setText("To be, or not to be: that is the question: " + "Whether 'tis nobler in the mind to suffer "
				+ "The slings and arrows of outrageous fortune, " + "Or to take arms against a sea of troubles, "
				+ "And by opposing end them? To die: to sleep; ");
		r4.addBreak(BreakType.PAGE);
		r4.setText("No more; and by a sleep to say we end " + "The heart-ache and the thousand natural shocks "
				+ "That flesh is heir to, 'tis a consummation " + "Devoutly to be wish'd. To die, to sleep; "
				+ "To sleep: perchance to dream: ay, there's the rub; " + ".......");
		r4.setItalic(true);
		// This would imply that this break shall be treated as a simple line
		// break, and break the line after that word:

		XWPFRun r5 = p3.createRun();
		r5.setTextPosition(-10);
		r5.setText("For in that sleep of death what dreams may come");
		r5.addCarriageReturn();
		r5.setText("When we have shuffled off this mortal coil," + "Must give us pause: there's the respect"
				+ "That makes calamity of so long life;");
		r5.addBreak();
		r5.setText("For who would bear the whips and scorns of time,"
				+ "The oppressor's wrong, the proud man's contumely,");

		r5.addBreak(BreakClear.ALL);
		r5.setText(
				"The pangs of despised love, the law's delay," + "The insolence of office and the spurns" + ".......");

		FileOutputStream fos = new FileOutputStream("/home/spark/input/simple.docx");
		doc.write(fos);
		fos.close();
	}

	public static void main(String[] args) {
		System.out.println("-----------------");
		try {
			// createXwpf();
			// createHSSF();
			// creatXssf();
			//createHssfFile();
			 getContentXssf();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
