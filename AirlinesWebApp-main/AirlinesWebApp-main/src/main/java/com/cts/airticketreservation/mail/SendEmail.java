package com.cts.airticketreservation.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.airticketreservation.dao.BookingsDao;
import com.cts.airticketreservation.model.Bookings;

public class SendEmail {

	@Autowired
	BookingsDao bdao;

	Session session;

	// Sending ticket booked mail with booking information
	public void send(String email, String pnr_no, ArrayList<Bookings> booking_list, String cost) {
		// message with passenger name,flight number,class and seat number
		StringBuilder message = new StringBuilder();
		// message with from,to,date,time,status,booking date
		StringBuilder message1 = new StringBuilder();

		for (Bookings list : booking_list) {
			message.append(list.toString1());
		}

		int i = 0;
		for (Bookings list : booking_list) {
			if (i == 0)
				message1.append(list.toString2());
			i++;
		}

		session = SetMailProperties(email);

		try {
			MimeMessage Mailmessage = new MimeMessage(session);
			Mailmessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			Mailmessage.setSubject("Airticket Booked!");
			Mailmessage.setText("Your PNR is : " + pnr_no + "\n\n" + message.toString() + message1
					+ "Total Amount Paid is " + cost);
			Transport.send(Mailmessage);
			System.out.println("Mail successfully sent");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Caught exception = " + e.getMessage());
		}

	}

	// Mailing OTP for email verification during password recovery
	public String sendOTPmail(String email) {

		session = SetMailProperties(email);
		int randomPIN = (int) (Math.random() * 9000) + 1000;
		String OTP = "" + randomPIN;

		try {
			MimeMessage Mailmessage = new MimeMessage(session);
			Mailmessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			Mailmessage.setSubject("Password Recovery");
			Mailmessage.setText("Your OTP is : " + OTP);

			Transport.send(Mailmessage);
			System.out.println("Mail successfully sent");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Caught exception = " + e.getMessage());

		}
		return OTP;
	}

	// Password Recovery
	public void sendCredentialsMail(String username, String password, String email) {

		session = SetMailProperties(email);

		try {
			MimeMessage Mailmessage = new MimeMessage(session);
			Mailmessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			Mailmessage.setSubject("AIRLINES Credentials!");
			Mailmessage.setText("Your USERNAME is : " + username + "\n\n" + "Your PASSWORD is : " + password);

			Transport.send(Mailmessage);
			System.out.println("Mail successfully sent");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Caught exception = " + e.getMessage());

		}

	}

	// Setting mail properties
	public Session SetMailProperties(String email) {
		final String user = "nisargagowda9699@gmail.com";
		final String password = "Nandhinisa12@3";

		{

			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.fallback", "false");

			session = Session.getInstance(props, new javax.mail.Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(user, password);

				}

			});
		}
		return session;
	}

}
