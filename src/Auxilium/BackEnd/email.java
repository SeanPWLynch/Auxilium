package Auxilium.BackEnd;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class email
{
	private final static String username = "emailToUseForSending";
	private final static String password = "emailPassword";

	public static void newTicketConfirmMail(String to, String ticketmessage,
			String subject)
	{

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator()
				{
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(username, password);
					}
				});

		try
		{

			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(ticketmessage);

			MimeMultipart multipart = new MimeMultipart();

			BodyPart bp2 = new MimeBodyPart();

			bp2.setContent(ticketmessage, "text/plain");
			multipart.addBodyPart(bp2);

			BodyPart bodyPart = new MimeBodyPart();
			String str = "<html><img src=\"cid:image_cid\"></html>";
			// Set the MIME-type to HTML.
			bodyPart.setContent(str, "text/html");

			// Add the HTML bodypart to the multipart.
			multipart.addBodyPart(bodyPart);

			// Create another bodypart to include the image attachment.
			bodyPart = new MimeBodyPart();

			// Read image from file system.
			//DataSource ds = new FileDataSource(email.class.getResource("/Auxilium/Images/logoSmall.png").getFile());
			//System.out.println(ds.getName());
			//bodyPart.setDataHandler(new DataHandler(ds));

			// Set the content-ID of the image attachment.
			// Enclose the image CID with the lesser and greater signs.
			bodyPart.setHeader("Content-ID", "<image_cid>");

			// Add image attachment to multipart.
			// message.setText(ticketmessage);
			//multipart.addBodyPart(bodyPart);

			// Add multipart content to message.
			message.setContent(multipart);

			// message.setText(ticketmessage);

			Transport.send(message);

			System.out.println("Email Sent");

		} catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}

	}

	public static void emailReportToAdmins()
	{
		String to = Database.getDefaultAdminEmail();
		String ticketmessage = "The Auxilium Statistics Report has been created and emailed to you as an attachment. The file is called ' AuxiliumReport.pdf' \n\nPlease do not reply to this email. Have a good day.\n\n";
		String subject = "Auxilium Report";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator()
				{
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(username, password);
					}
				});

		try
		{

			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(ticketmessage);

			MimeMultipart multipart = new MimeMultipart();

			BodyPart bp2 = new MimeBodyPart();

			bp2.setContent(ticketmessage, "text/plain");
			multipart.addBodyPart(bp2);

			BodyPart bodyPart = new MimeBodyPart();
			String str = "<html><img src=\"cid:image_cid\"></html>";
			// Set the MIME-type to HTML.
			bodyPart.setContent(str, "text/html");

			// Add the HTML bodypart to the multipart.
			multipart.addBodyPart(bodyPart);

			// Create another bodypart to include the image attachment.
			bodyPart = new MimeBodyPart();

			// Read image from file system.
			try
			{
				
		               
				
			//	DataSource ds = new FileDataSource(email.class.getResource("/Auxilium/Images/logoSmall.png").getFile());
			//	System.out.println(ds.getName());
			///	bodyPart.setFileName(email.class.getResource("/Auxilium/Images/logoSmall.png").getFile());
			//	bodyPart.setDataHandler(new DataHandler(ds));
				System.out.println("Added image");
			} catch (Exception e)
			{
				JOptionPane
				.showMessageDialog(null,
						"Error " + e,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			// Set the content-ID of the image attachment.
			// Enclose the image CID with the lesser and greater signs.
			bodyPart.setHeader("Content-ID", "<image_cid>");

			// Add image attachment to multipart.
			// message.setText(ticketmessage);
			MimeBodyPart report = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds = new FileDataSource("AuxiliumReport.pdf");
			report.setDataHandler(new DataHandler(fds));
			report.setFileName(fds.getName());
			multipart.addBodyPart(report);
			//multipart.addBodyPart(bodyPart);

			// Add multipart content to message.
			message.setContent(multipart);

			// message.setText(ticketmessage);

			Transport.send(message);

			System.out.println("Email Sent");

		} catch (MessagingException e)
		{
			JOptionPane.showMessageDialog(null, "Error " + e, "Error",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}
}
