import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaEmail {
	// Envia email- protocolo SMTP
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String destinatario =  "correodestinatario@gmail.com"; //A quien le quieres escribir.
	    String asunto = "Pruebas envio de correo con Java";
	    String cuerpo = ":) HOLA rt";

	    enviarConGMail(destinatario, asunto, cuerpo);
	    System.out.println("MENSAJE ENVIADO CORRECTAMENTE");

	}

	private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		// TODO Auto-generated method stub
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "tucuenta";  //Para la dirección nomcuenta@gmail.com TUCUENTA

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "password"); // Aquí pones la contraseña de tu cuenta
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
		
	}

}
