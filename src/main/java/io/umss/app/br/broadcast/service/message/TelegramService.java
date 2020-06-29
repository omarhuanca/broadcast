package io.umss.app.br.broadcast.service.message;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.core.message.Message;
import io.umss.app.br.broadcast.dao.message.broadcastmessage.RBroadcastMessageRepository;
import io.umss.app.br.broadcast.dao.message.message.RMessageRepository;
import io.umss.app.br.broadcast.service.ClassStatus;
import io.umss.app.br.broadcast.service.Pagination;
import io.umss.app.br.broadcast.service.TelegramEnum;

/**
 * TelegramService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class TelegramService extends TelegramLongPollingBot {

    @Value("${message.telegram.bot.name}")
    private String botName;

    @Value("${message.telegram.bot.token}")
    private String botToken;

    @Autowired
    RBroadcastMessageRepository broadcastMessageRepository;

    @Autowired
    RMessageRepository messageRepository;

    @Override
    public void onUpdateReceived(Update update) {
        if (!StringUtils.isBlank(botName) && !StringUtils.isBlank(botToken)) {
            String command = update.getMessage().getText();
            Long categoryId = this.matchCommand(command);
            List<BroadcastMessage> listBroadcastMessage = broadcastMessageRepository.getAllObjects(
                    Optional.ofNullable(ClassStatus.ENABLE.getCode()), Optional.of(categoryId), Optional.empty(),
                    Pagination.DEFAULT_PAGESIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            for (BroadcastMessage broadcastMessage : listBroadcastMessage) {
                SendMessage sendMessage = new SendMessage();
                Message message = messageRepository.getObjectById(Optional.of(broadcastMessage.getMessage().getUid()));

                try {
                    if (null == message || StringUtils.isBlank(message.getBody())) {
                        throw new InvalidParameterException(String.format("The message not exist"));
                    }
                    sendMessage.setText(message.getBody());
                    sendMessage.setChatId(update.getMessage().getChatId());
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Long matchCommand(String command) {
        Long response = 0L;
        if (!StringUtils.isBlank(command)) {
            if (TelegramEnum.COMMAND_CAREER_ADVICE.getCode().equals(command)) {
                response = Long.valueOf(TelegramEnum.COMMAND_CAREER_ADVICE.getValue());
            }
            if (TelegramEnum.COMMAND_FACULTATIVE_COUNCIL.getCode().equals(command)) {
                response = Long.valueOf(TelegramEnum.COMMAND_FACULTATIVE_COUNCIL.getValue());
            }
            if (TelegramEnum.COMMAND_UNIVERSITY_COUNCIL.getCode().equals(command)) {
                response = Long.valueOf(TelegramEnum.COMMAND_UNIVERSITY_COUNCIL.getValue());
            }
            if (TelegramEnum.COMMAND_CAREER_DIRECTOR.getCode().equals(command)) {
                response = Long.valueOf(TelegramEnum.COMMAND_CAREER_DIRECTOR.getValue());
            }
            if (TelegramEnum.COMMAND_EXCLUSIVE_TEACHER.getCode().equals(command)) {
                response = Long.valueOf(TelegramEnum.COMMAND_EXCLUSIVE_TEACHER.getValue());
            }
            if (TelegramEnum.COMMAND_ACADEMIC_COORDINATOR.getCode().equals(command)) {
                response = Long.valueOf(TelegramEnum.COMMAND_ACADEMIC_COORDINATOR.getValue());
            }
        }

        return response;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}