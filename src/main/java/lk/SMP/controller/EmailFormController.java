package lk.SMP.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailFormController {

    public AnchorPane root;
    public JFXComboBox cmbSupplierName;
    @FXML
    private TextArea txtMassage;

    @FXML
    private TextField txtEmail;

    @FXML
    void btnSendOnAction(ActionEvent event) throws MessagingException {
        String recipientEmail = txtEmail.getText();
        sendEmail(recipientEmail);
    }

    private void sendEmail(String recipientEmail) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myEmail = "nprabushitha@gmail.com";
        String myPassword = "hcyf jimm jxck yobc ";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail , myPassword);
            }
        });

        Message message = prepareMessage(session,myEmail,recipientEmail,txtMassage.getText());
        if (message!=null) {
            new Alert(Alert.AlertType.INFORMATION,"Send Email Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Send Email Failure").show();
        }
        Transport.send(message);

    }

    private Message prepareMessage(Session session, String myEmail, String recipientEmail, String msg) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{
                    new InternetAddress(recipientEmail)
            });

            message.setSubject("Message");
            message.setText(msg);

            return message;
        }catch (Exception e) {
            Logger.getLogger(EmailFormController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }

    public void cmbSupplierName(ActionEvent actionEvent) {

    }
}