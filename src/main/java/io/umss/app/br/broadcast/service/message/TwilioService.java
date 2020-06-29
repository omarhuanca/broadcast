package io.umss.app.br.broadcast.service.message;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.core.message.Subscription;
import io.umss.app.br.broadcast.dao.message.message.RMessageRepository;
import io.umss.app.br.broadcast.dao.message.subscriber.RSubscriberRepository;

/**
 * TwilioService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class TwilioService {

    @Autowired
    RMessageRepository messageRepository;

    @Autowired
    RSubscriberRepository subscriberRepository;

    @Value("${twilio.sms.account.sid}")
    private String accountSid;

    @Value("${twilio.sms.auth.token}")
    private String authToken;

    @Value("${twilio.sms.twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendSMS(List<BroadcastMessage> listBroadcastMessage, List<Subscription> listSubscription) {
        if (!StringUtils.isBlank(accountSid) && !StringUtils.isBlank(authToken)
                && !StringUtils.isBlank(twilioPhoneNumber)) {
            Twilio.init(accountSid, authToken);
            for (Subscription subscription : listSubscription) {
                Subscriber subscriber = subscriberRepository
                        .getObjectById(Optional.ofNullable(subscription.getSubscriber().getUid()));
                for (BroadcastMessage broadcastMessage : listBroadcastMessage) {
                    try {
                        io.umss.app.br.broadcast.core.message.Message content = messageRepository
                                .getObjectById(Optional.ofNullable(broadcastMessage.getMessage().getUid()));
                        Message.creator(new PhoneNumber(subscriber.getCellphone().toString()),
                                new PhoneNumber(twilioPhoneNumber.toString()), content.getBody()).create();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}