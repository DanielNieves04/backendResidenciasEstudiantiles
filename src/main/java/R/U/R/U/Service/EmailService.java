package R.U.R.U.Service;

import R.U.R.U.Entity.EmailValuesDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("resides.softcol@gmail.com");
        message.setTo("resides.softcol@gmail.com");
        message.setSubject("Prueba envio Mail Simple");
        message.setText("Tu codigo de recuperacion de contraseña es ...");

        javaMailSender.send(message);
    }

    public void sendPasswordResetEmail(String emailTo, String code) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("no-reply@tudominio.com");
            helper.setTo(emailTo);
            helper.setSubject("Código de recuperación de contraseña");

            String emailContent = "<html><body>"
                    + "<h2>Solicitud de restablecimiento de contraseña</h2>"
                    + "<p>Tu código de verificación es: <strong>" + code + "</strong></p>"
                    + "<p>Este código expirará en 15 minutos.</p>"
                    + "</body></html>";

            helper.setText(emailContent, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
