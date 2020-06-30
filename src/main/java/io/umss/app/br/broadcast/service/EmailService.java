package io.umss.app.br.broadcast.service;

import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.core.message.Message;
import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.core.message.Subscription;
import io.umss.app.br.broadcast.dao.message.message.RMessageRepository;
import io.umss.app.br.broadcast.dao.message.subscriber.RSubscriberRepository;

/**
 * EmailService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class EmailService {

    @Value("${email.message.subject}")
    private String messageSubject;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    RMessageRepository messageRepository;

    @Autowired
    RSubscriberRepository subscriberRepository;

    public void sendMail(List<BroadcastMessage> listBroadcastMessage, List<Subscription> listSubscription) {
        if (!StringUtils.isBlank(messageSubject)) {
            for (Subscription subscription : listSubscription) {
                Subscriber subscriber = subscriberRepository
                        .getObjectById(Optional.ofNullable(subscription.getSubscriber().getUid()));
                for (BroadcastMessage broadcastMessage : listBroadcastMessage) {
                    try {
                        Message message = messageRepository
                                .getObjectById(Optional.ofNullable(broadcastMessage.getMessage().getUid()));

                        MimeMessage mimeMessage = mailSender.createMimeMessage();
                        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");

                        mimeMessageHelper.setTo(subscriber.getEmail());
                        mimeMessageHelper.setSubject(messageSubject.toString());
                        mimeMessage.setContent(this.buildContent(message.getBody()), "text/html");

                        mailSender.send(mimeMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String buildContent(String body) {
        String response = new String();
        if (!StringUtils.isBlank(body)) {
            response = response + "<html>\n" + "<head>\n"
                    + "    <meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\">\n" + "</head>\n"
                    + "<table cellspacing=\"0\" cellpadding=\"10\" style=\"color:#666;font:13px Arial;line-height:1.4em;width:100%;\">\n"
                    + "  <tbody>\n" + "        <tr>\n"
                    + "            <td style=\"color:#4D90FE;font-size:22px;border-bottom: 2px solid #4D90FE;\">" + "\n"
                    + "            </td>\n" + "        </tr>\n" + "        <tr>\n"
                    + "            <td style=\"color:#777;font-size:16px;padding-top:5px;\">" + "\n" + "      </td>\n"
                    + "        </tr>\n" + "        <tr>\n" + "            <td>";
            response = response + body;
            response = response + "</td>\n" + "        </tr>\n" + "        <tr>\n"
                    + "            <td style=\"padding:15px 20px;text-align:right;padding-top:5px;border-top:solid 1px #dfdfdf\">\n"
                    + "                <a href=\"http://www.hum.umss.edu.bo/\"></a>\n" + "            </td>\n"
                    + "        </tr>\n" + "    </tbody>\n" + "</table>\n" + "</body>\n" + "</html>\n" + "";
        }

        return response;
    }
}