package org.javaforever.myareas.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public final class PDFUtil {
	private static boolean containsImage(List<Boolean> isImages) {
		for (Boolean isImage : isImages) {
			if (isImage) return true;
		}
		return false;
	}

	public static void exportPDFWithImage(OutputStream out, String sheetName, List<String> headers, List<List<Object>> contents,List<Boolean> isImages)
			throws Exception {
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
		Font fontChineseBold = new Font(bfChinese, 12, Font.BOLD);
		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		Document document = new Document(rectPageSize, 50, 50, 50, 50);
		if (headers.size() > 10||containsImage(isImages)) {
			document.setPageSize(rectPageSize.rotate());
		}
		PdfWriter.getInstance(document, out);
		document.open();
		
		BaseColor headback = new BaseColor(180,180,180);
		BaseColor whiteback = new BaseColor(255,255,255);

		PdfPTable table = new PdfPTable(headers.size());
		int imageCounts = 0;
		if (contents.size()>0) imageCounts = countImages(contents.get(0));
		float cellWidth = (rectPageSize.getHeight()-200)/(headers.size()+2*imageCounts);
		List<Float> list = buildCellWidthList(isImages,cellWidth);
		float [] cellWidthArr = new float[list.size()];
		for (int i=0;i<list.size();i++) {
			cellWidthArr[i] = list.get(i);
		}
		table.setWidths(cellWidthArr);
		//table.setLockedWidth(true);
		writeRow(table, fontChineseBold, headback, headers);

		for (List<Object> data : contents) {
			writeRowWithImage(table, fontChinese, whiteback,data,cellWidth);
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

	private static int countImages(List<Object> data ) {
		int retVal = 0;
		for (Object o:data) {
			if (o instanceof byte []) retVal ++;
		}
		return retVal;
	}

	private static int getPercent(float h,float w, float cellWidth) {
		float p2 =  3.0f*cellWidth / w * 100;
		return Math.round(p2);
	}
	
	protected static void writeRow(PdfPTable table, Font fontChinese, BaseColor color,List<String> data) throws Exception{
		for (int j = 0; j < data.size(); j++) {
			PdfPCell cell = new PdfPCell();
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
	
	protected static void writeRowWithImage(PdfPTable table, Font fontChinese, BaseColor color,List<Object> data,float cellWidth)  throws Exception{
			for (int j = 0; j < data.size(); j++) {
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(color);
			if (data.get(j) instanceof String)cell.addElement(new Paragraph((String)data.get(j),fontChinese));
			else if (data.get(j) instanceof byte[]) {
				if (data.get(j)!=null) {
					BufferedImage bi = getBufferedImage((byte[])data.get(j));
					if (bi!=null) {
						Image image = Image.getInstance(bi,null);
						if (image!=null && image.getWidth()>0&& image.getHeight()>0) {
							image.setAlignment(Image.ALIGN_CENTER);
							image.scalePercent(getPercent(image.getHeight(),image.getWidth(),cellWidth));
							cell.addElement(image);
						}
					}
				}
			}
			table.addCell(cell);
		}
	}
}
