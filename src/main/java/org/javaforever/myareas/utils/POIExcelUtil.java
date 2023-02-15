package org.javaforever.myareas.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public final class POIExcelUtil {
	public static void exportExcelWorkbookWithImage(OutputStream out, String sheetName, List<String> headers,
			List<List<Object>> contents, List<Boolean> isImages) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row;
		HSSFCell cell;

		short colorIndex = 10;
		HSSFPalette palette = wb.getCustomPalette();
		Color rgb = Color.YELLOW;
		short bgIndex = colorIndex++;
		palette.setColorAtIndex(bgIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
		short bdIndex = colorIndex++;
		rgb = Color.BLACK;
		palette.setColorAtIndex(bdIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		// bdIndex 边框颜色下标值
		cellStyle.setBottomBorderColor(bdIndex);
		cellStyle.setLeftBorderColor(bdIndex);
		cellStyle.setRightBorderColor(bdIndex);
		cellStyle.setTopBorderColor(bdIndex);

		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle cellHeaderStyle = wb.createCellStyle();
		cellHeaderStyle.cloneStyleFrom(cellStyle);

		cellHeaderStyle.setFillForegroundColor(bgIndex); // bgIndex 背景颜色下标值
		cellHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		writeRow(wb, sheet, bgIndex, bdIndex, 1, cellHeaderStyle, headers);

		for (int i = 0; i < contents.size(); i++) {
			writeRowWithImage(wb, sheet, bgIndex, bdIndex, i + 2, cellStyle, contents.get(i));
		}

		boolean containsImage = containsImage(isImages);
		int rowHeight = 30;
		if (containsImage)
			rowHeight = 50;

		// 创建表格之后设置行高与列宽
		for (int i = 1; i < contents.size() + 2; i++) {
			row = sheet.getRow(i);
			row.setHeightInPoints(rowHeight);
		}
		for (int j = 1; j < headers.size() + 1; j++) {
			sheet.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(120));
		}
		wb.write(out);
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

	private static void setPicture(HSSFWorkbook workbook, HSSFSheet sheet, int rowNum, int colNum, byte[] image)
			throws Exception {
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		BufferedImage bi = getBufferedImage(image);
		if (bi != null && bi.getHeight() > 0) {
			ImageIO.write(bi, "png", byteArrayOut);

			int pdx1 = 120 - (int) ((50.0 / bi.getHeight()) * bi.getWidth());
			int dx1 = (int) (Double.valueOf(pdx1) / 120.0 * 1023.0 / 2);
			if (dx1 <= 0)
				dx1 = 0;
			int dx2 = 1023 - dx1;
			System.out.println("JerryDebug:" + pdx1 + ":dx1:" + dx1 + ":bi.width:" + bi.getWidth() + ":bi.height:"
					+ bi.getHeight());
			HSSFClientAnchor anchor = new HSSFClientAnchor(dx1, 0, dx2, 0, (short) colNum, rowNum, (short) colNum,
					rowNum + 1);
			anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
			patriarch.createPicture(anchor,
					workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
		}
	}

	protected static void writeRow(HSSFWorkbook wb, HSSFSheet sheet, short bgIndex, short bdIndex, int rowIndex,
			HSSFCellStyle cellStyle, List<String> data) {
		HSSFRow row = sheet.createRow(rowIndex);// 创建表格行
		for (int j = 0; j < data.size(); j++) {
			Cell cell = row.createCell(j + 1);// 根据表格行创建单元格
			cell.setCellStyle(cellStyle);
			cell.setCellValue(String.valueOf(StringUtil.nullTrim(data.get(j))));
		}
	}

	protected static void writeRowWithImage(HSSFWorkbook wb, HSSFSheet sheet, short bgIndex, short bdIndex,
			int rowIndex, HSSFCellStyle cellStyle, List<Object> data) throws Exception {
		HSSFRow row = sheet.createRow(rowIndex);// 创建表格行
		for (int j = 0; j < data.size(); j++) {
			Cell cell = row.createCell(j + 1);// 根据表格行创建单元格
			cell.setCellStyle(cellStyle);
			if (data.get(j) instanceof String)
				cell.setCellValue(String.valueOf(StringUtil.nullTrim((String) data.get(j))));
			else if (data.get(j) instanceof byte[])
				setPicture(wb, sheet, rowIndex, j + 1, (byte[]) data.get(j));
		}
	}

	private static boolean containsImage(List<Boolean> isImages) {
		for (Boolean isImage : isImages) {
			if (isImage)
				return true;
		}
		return false;
	}
}
