package org.javaforever.myareas.utils;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;

public final class POIPPTUtil {
	public static BufferedImage getBufferedImage(byte[] image) {
		BufferedImage bi = null;
		try {
			InputStream buffin = new ByteArrayInputStream(image);
			bi = ImageIO.read(buffin);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}

	private static boolean containsImage(List<Boolean> isImages) {
		for (Boolean isImage : isImages) {
			if (isImage)
				return true;
		}
		return false;
	}
	
	public static void exportPPTWithImage(OutputStream out, String pptName, List<String> headers,
			List<List<Object>> contents, List<Boolean> isImages) throws Exception {
		XMLSlideShow ppt = new XMLSlideShow();
		for (int i=0; i< contents.size();i+=5) {
			int start = i;
			int end = i+5 >contents.size()?contents.size():i+5;
			exportPPTWithImageSlide(ppt,headers,contents.subList(start, end),isImages);
		}
		ppt.write(out);
	}
	
	public static void exportPPTWithImageSlide(XMLSlideShow ppt, List<String> headers,
			List<List<Object>> contents, List<Boolean> isImages) throws Exception {
		
		XSLFSlide slide = ppt.createSlide();//创建幻灯片
		
		XSLFTable table = slide.createTable();//创建表格
		table.setAnchor(new Rectangle2D.Double(10, 30, 0, 0));
		XSLFTableRow tableRow0 = table.addRow(); //创建表格行
		for(int j = 0; j < headers.size(); j++) {
			XSLFTableCell tableCell = tableRow0.addCell();//创建表格单元格
			XSLFTextParagraph p = tableCell.addNewTextParagraph();
			p.setTextAlign(TextAlign.CENTER);
			XSLFTextRun tr = p.addNewTextRun();
			tr.setText(String.valueOf(headers.get(j)));
			tr.setBold(true);
			
			tableCell.setBorderWidth(TableCell.BorderEdge.left,1);
			tableCell.setBorderWidth(TableCell.BorderEdge.top,1);
			tableCell.setBorderWidth(TableCell.BorderEdge.right,1);
			tableCell.setBorderWidth(TableCell.BorderEdge.bottom,1);
			tableCell.setBorderColor(TableCell.BorderEdge.bottom,Color.BLACK);
			tableCell.setBorderColor(TableCell.BorderEdge.left,Color.BLACK);
			tableCell.setBorderColor(TableCell.BorderEdge.top,Color.BLACK);
			tableCell.setBorderColor(TableCell.BorderEdge.right,Color.BLACK);
		}
		tableRow0.setHeight(60);
		for(int i = 0; i < contents.size(); i++) {
		XSLFTableRow tableRow = table.addRow(); //创建表格行
		for(int j = 0; j < contents.get(i).size(); j++) {
			XSLFTableCell tableCell = tableRow.addCell();//创建表格单元格
			XSLFTextParagraph p = tableCell.addNewTextParagraph();
			XSLFTextRun tr = p.addNewTextRun();
			if (!isImages.get(j)) tr.setText(String.valueOf(contents.get(i).get(j)));
			else {
				if (contents.get(i).get(j)!=null) {
					XSLFPictureData pictureData = ppt.addPicture((byte[])contents.get(i).get(j), XSLFPictureData.PictureType.PNG);
					XSLFPictureShape pic = slide.createPicture(pictureData);
					pic.setAnchor(getPicRect(isImages,i,j,(byte[])contents.get(i).get(j)));
				}
			}

			p.setTextAlign(TextAlign.CENTER);
			tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

			tableCell.setBorderWidth(TableCell.BorderEdge.left,1);
			tableCell.setBorderWidth(TableCell.BorderEdge.top,1);
			tableCell.setBorderWidth(TableCell.BorderEdge.right,1);
			tableCell.setBorderWidth(TableCell.BorderEdge.bottom,1);
			tableCell.setBorderColor(TableCell.BorderEdge.bottom,Color.BLACK);
			tableCell.setBorderColor(TableCell.BorderEdge.left,Color.BLACK);
			tableCell.setBorderColor(TableCell.BorderEdge.top,Color.BLACK);
			tableCell.setBorderColor(TableCell.BorderEdge.right,Color.BLACK);
	
		}
		tableRow.setHeight(90);
	}
		
	int colsWidth = 700 / countUnits(isImages);
	for (int i=0;i<isImages.size();i++) {
		if (isImages.get(i)) table.setColumnWidth(i, colsWidth*3);
		else table.setColumnWidth(i, colsWidth*2);
	}

	}
	
	public static int countUnits(List<Boolean> isImages) {
		int count = 0;
		for (Boolean b:isImages) {
			if (b) count +=3;
			else count += 2;
		}
		return count;
	}
	
	public static int countCurrentUnits(List<Boolean> isImages,int pos) {
		int count = 0;
		int cur = 0;
		
		for (Boolean b:isImages) {
			if (cur < pos) {
				if (b) count +=3;
				else count += 2;
				cur ++;
			}else {
				return count;
			}
		}
		return count;
	}
	
	public static Rectangle2D getPicRect(List<Boolean> isImages,int row, int col,byte[] imagebytes) {
		int unitCounts = countUnits(isImages);
		double unitWidth = 700.0/unitCounts;
		double curX = countCurrentUnits(isImages,col);
		Map<String,Integer> widHei = getImageWidthHeight(imagebytes);
		int imageWidth = widHei.get("width");
		int imageHeight = widHei.get("height");
		if (imageHeight == 0) return new Rectangle2D.Double(curX*unitWidth, 90+90*row,0 , 0);
		double width = 90.0/(double) imageHeight * imageWidth;
		double offsetx = (3* unitWidth - width)/2;
		Rectangle2D rect = new Rectangle2D.Double(curX*unitWidth+offsetx, 90+90*row,width , 90);
		return rect;
	}
	
	public static Map<String,Integer> getImageWidthHeight(byte[] imagebytes){
		Map<String,Integer> results = new TreeMap<>();

		BufferedImage bi = getBufferedImage(imagebytes);
		if (bi == null) {
			results.put("width", 0);
			results.put("height", 0);
			return results;
		}
		Integer width = bi.getWidth();
		Integer height = bi.getHeight();
		results.put("width", width);
		results.put("height", height);
		return results;
	}
}
