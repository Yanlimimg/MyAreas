package org.javaforever.myareas.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

public final class WordUtil {
	private static boolean containsImage(List<Boolean> isImages) {
		for (Boolean isImage : isImages) {
			if (isImage) return true;
		}
		return false;
	}

	public static void exportWordWithImage(OutputStream out, String sheetName, List<String> headers, List<List<Object>> contents,List<Boolean> isImages)
			throws Exception {
		Font fontChinese = new Font(Font.NORMAL, 12, Font.NORMAL);
		Font fontChineseBold = new Font(Font.NORMAL, 12, Font.BOLD);
		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		Document document = new Document(rectPageSize, 50, 50, 50, 50);
		if (headers.size() > 10||containsImage(isImages)) {
			document.setPageSize(rectPageSize.rotate());
		}
		RtfWriter2.getInstance(document, out);
		document.open();
		
		Color headback = new Color(180,180,180);
		Color whiteback = new Color(255,255,255);

		Table table = new Table(headers.size());
		float cellWidth = (rectPageSize.getHeight()-200)/(headers.size()+2*countImages(isImages));
		List<Float> list = buildCellWidthList(isImages,cellWidth);
		float [] cellWidthArr = new float[list.size()];
		for (int i=0;i<list.size();i++) {
			cellWidthArr[i] = list.get(i);
		}
		table.setWidths(cellWidthArr);
		//table.setLockedWidth(true);
		writeRow(table, fontChineseBold, headback, headers);

		for (List<Object> data : contents) {
			writeRowWithImage(table, fontChinese, whiteback,data,cellWidth,isImages);
		}
		document.add(table);
		document.close();
	}
	
	private static List<Float> buildCellWidthList(List<Boolean> isImages,float cellWidth) {
		List<Float> result = new ArrayList<>();
		for (boolean isImage:isImages) {
			if (isImage) result.add(3*cellWidth);
			else result.add(cellWidth);
		}
		return result;
	}

	private static int countImages(List<Boolean> isImages) {
		int retVal = 0;
		for (Boolean b:isImages) {
			retVal ++;
		}
		return retVal;
	}

	private static int getPercent(float h,float w, float cellWidth) {
		float p2 =  3.0f*cellWidth / w * 100.0f*1.7f;
		return Math.round(p2);
	}
	
	protected static void writeRow(Table table, Font fontChinese, Color color,List<String> data) throws Exception{
		for (int j = 0; j < data.size(); j++) {
			Cell cell = new Cell();
			cell.setBackgroundColor(color);
			cell.addElement(new Paragraph(data.get(j),fontChinese));
			table.addCell(cell);
		}
	}
	
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
	
	protected static void writeRowWithImage(Table table, Font fontChinese, Color color,List<Object> data,float cellWidth, List<Boolean> isImages)  throws Exception{
			for (int j = 0; j < data.size(); j++) {
			Cell cell = new Cell();
			cell.setBackgroundColor(color);
			if (data.get(j) instanceof String)cell.addElement(new Paragraph((String)data.get(j),fontChinese));
			else if (isImages.get(j)) {
				if (data.get(j)!=null) {
					BufferedImage bi = getBufferedImage((byte[])data.get(j));
					if (bi!=null) {
						Image image = Image.getInstance((byte[])data.get(j));
						if (image!=null && image.getWidth()>0&& image.getHeight()>0) {
							cell.setVerticalAlignment(Element.ALIGN_CENTER);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							image.scalePercent(getPercent(image.getHeight(),image.getWidth(),cellWidth));
							cell.add(image);
						}
					}
				}
			}
			table.addCell(cell);
		}
	}
}
