package Auxilium.BackEnd;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This class is used to make PDF reports.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class PDF
{
	Document document = new Document(PageSize.A4, 50, 50, 50, 50);

	

	public PDF(String docName, String[] images)
	{
		openDocument(docName);
		fillInDocument(docName, images);
	}

	private void fillInDocument(String docName, String[] images)
	{
		Image image1 = null;
		Image chart = null;
		String text = "Auxilium Reports for : " + Database.getDate();
		try
		{
			image1 = Image.getInstance(PDF.class
					.getResource("/Auxilium/Images/logoSmall.png"));
			
		} catch (BadElementException e1)
		{
			JOptionPane
			.showMessageDialog(null,
					"Error " + e1,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (MalformedURLException e1)
		{
			JOptionPane
			.showMessageDialog(null,
					"Error " + e1,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1)
		{
			JOptionPane
			.showMessageDialog(null,
					"Error " + e1,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		try
		{
			document.add(image1);
			// document.add(chart);
		} catch (DocumentException e1)
		{
			JOptionPane
			.showMessageDialog(null,
					"Error " + e1,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

		Paragraph title1 = new Paragraph("Auxilium Reports",
				FontFactory.getFont("c:/windows/fonts/impact.ttf",
						BaseFont.WINANSI, 18, Font.NORMAL, new BaseColor(0, 0,
								255)));

		title1.setAlignment(Element.ALIGN_CENTER);
		Chapter chapter1 = new Chapter(title1, 1);

		chapter1.setNumberDepth(0);

		Paragraph title11 = new Paragraph(docName, FontFactory.getFont(
				FontFactory.TIMES, 16, Font.BOLD, new BaseColor(255, 0, 0)));

		Section section1 = chapter1.addSection(title11);

		Paragraph sectionText = new Paragraph(text);

		section1.add(sectionText);

		try
		{
			document.add(chapter1);
			
		} catch (DocumentException e)
		{
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		for(int i = 0 ; i < images.length ; i++)
		{
			try
			{
				section1 = chapter1.addSection(images[i]);

				document.add(section1);
				chart = Image.getInstance(images[i]+".png");
				document.add(chart);
				
			} catch (BadElementException e)
			{
				JOptionPane
				.showMessageDialog(null,
						"Error " + e,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (MalformedURLException e)
			{
				JOptionPane
				.showMessageDialog(null,
						"Error " + e,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e)
			{
				JOptionPane
				.showMessageDialog(null,
						"Error " + e,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (DocumentException e)
			{
				JOptionPane
				.showMessageDialog(null,
						"Error " + e,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
		}
		
		closeDocument();
	}

	
	private void openDocument(String docName)
	{
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(docName + ".pdf"));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (DocumentException e)
		{
			e.printStackTrace();
		}

		document.open();
	}

	
	public void closeDocument()
	{
		document.close();
	}
}
