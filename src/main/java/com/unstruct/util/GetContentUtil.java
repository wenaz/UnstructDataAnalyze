package com.unstruct.util;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class GetContentUtil {

	public static String getContent(File file) {
		String fileName=file.getName();
		
		
		if(fileName.toLowerCase().endsWith(".txt")){
			return txt2String(file);
		}else if(fileName.toLowerCase().endsWith(".xls")){
			return xls2String(file);
		}else if(fileName.toLowerCase().endsWith(".xlsx")){
			return xlsx2String(file);
		}else if(fileName.toLowerCase().endsWith(".doc")){
			return doc2String(file);
		}else if(fileName.toLowerCase().endsWith(".docx")){
			return docx2String(file);
		}else if(fileName.toLowerCase().endsWith(".ppt")){
			return ppt2String(file);
		}else if(fileName.toLowerCase().endsWith(".pptx")){
			return pptx2String(file);
		}else if(fileName.toLowerCase().endsWith(".pdf")){
			return pdf2String(file);
		}else{
			System.out.println("file:["+fileName+"] getContent error");
			return null;
		}
	}

	
	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String s = null;
			while ((s = br.readLine()) != null) {
				result.append(s + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result.toString();
	}

	public static String ppt2String(File file) {
		StringBuilder result = new StringBuilder();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			SlideShow ss = new SlideShow(new HSLFSlideShow(fis));

			Slide[] slides = ss.getSlides();
			for (int i = 0; i < slides.length; i++) {
				TextRun[] t = slides[i].getTextRuns();
				for (int j = 0; j < t.length; j++) {
					result.append(t[j].getText());
				}
				result.append(slides[i].getTitle());
			}

			/*
			 * PowerPointExtractor extractor=new PowerPointExtractor(fis);
			 * result.append(extractor.getText(true, true));
			 */
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	public static String pptx2String(File file) {
		StringBuilder result = new StringBuilder();
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);

			XMLSlideShow ppt = new XMLSlideShow(is);
			is.close();
			// Get the document's embedded files. 待用
			List<PackagePart> embeds = ppt.getAllEmbedds();

			for (PackagePart p : embeds) {
				String type = p.getContentType();
				String name = p.getPartName().getName(); // typically file name

				InputStream pIs = p.getInputStream();
				// make sense of the part data
				pIs.close();

			}

			// Get the document's embedded files. 待用
			List<XSLFPictureData> images = ppt.getAllPictures();
			for (XSLFPictureData data : images) {
				PackagePart p = data.getPackagePart();

				String type = p.getContentType();
				String name = data.getFileName();

				InputStream pIs = p.getInputStream();
				// make sense of the image data
				pIs.close();

			}

			Dimension pageSize = ppt.getPageSize(); // size of the canvas in
													// points

			for (XSLFSlide slide : ppt.getSlides()) {
				for (XSLFShape shape : slide) {
					if (shape instanceof XSLFTextShape) {
						XSLFTextShape txShape = (XSLFTextShape) shape;
						// System.out.println(txShape.getText());
						result.append(txShape.getText());
					} else if (shape instanceof XSLFPictureShape) {
						XSLFPictureShape pShape = (XSLFPictureShape) shape;
						XSLFPictureData pData = pShape.getPictureData();
						// System.out.println(pData.getFileName());
						result.append(pData.getFileName());
					} else {
						System.out.println("Process me: " + shape.getClass());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}

	public static String xls2String(File file) {
		String result = "";
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
			ExcelExtractor extractor = new ExcelExtractor(wb);
			extractor.setFormulasNotResults(true);
			extractor.setIncludeSheetNames(true);
			extractor.setIncludeBlankCells(false);
			extractor.setIncludeHeadersFooters(true);
			result = extractor.getText();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String xlsx2String(File file) {
		String result = "";
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			XSSFExcelExtractor extractor = new XSSFExcelExtractor(wb);
			extractor.setFormulasNotResults(true);
			extractor.setIncludeSheetNames(true);
			extractor.setIncludeHeadersFooters(true);
			extractor.setIncludeTextBoxes(true);
			extractor.setIncludeCellComments(true);
			result = extractor.getText();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		return result;
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
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
			WordExtractor extractor = new WordExtractor(fis);
			result.append(extractor.getText());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	public static String docx2String(File file) {
		StringBuilder result = new StringBuilder();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			XWPFDocument document = new XWPFDocument(fis);
			XWPFWordExtractor extractor = new XWPFWordExtractor(document);
			result.append(extractor.getText());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	
}
