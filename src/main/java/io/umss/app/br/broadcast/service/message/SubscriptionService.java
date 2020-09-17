package io.umss.app.br.broadcast.service.message;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.core.message.Category;
import io.umss.app.br.broadcast.core.message.ClassChannel;
import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.core.message.Subscription;
import io.umss.app.br.broadcast.dao.message.category.RCategoryRepository;
import io.umss.app.br.broadcast.dao.message.subscriber.RSubscriberRepository;
import io.umss.app.br.broadcast.dao.message.subscription.RSubscriptionRepository;
import io.umss.app.br.broadcast.service.ClassChannelEnum;
import io.umss.app.br.broadcast.service.ClassStatus;
import io.umss.app.br.broadcast.service.EmailService;
import io.umss.app.br.broadcast.service.Pagination;
import io.umss.app.br.broadcast.util.AElog;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * SubscriptionService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class SubscriptionService {

    private final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Value("${crontab.expression}")
    private String crontabExpression;

    @Autowired
    RSubscriptionRepository repository;

    @Autowired
    ClassChannelService classChannelService;

    @Autowired
    RSubscriberRepository subscriberRepository;

    @Autowired
    RCategoryRepository categoryRepository;

    @Autowired
    TwilioService twilioService;

    @Autowired
    EmailService emailService;

    @Autowired
    BroadcastMessageService broadcastMessageService;

    public Subscription getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Subscription> getAllObjects(Optional<Integer> status, Optional<Long> classChannelId,
            Optional<Long> subscriberId, Optional<Long> categoryId, Integer pageSize, Integer pageNumber)
            throws RepositoryException {
        return repository.getAllObjects(status, classChannelId, subscriberId, categoryId, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<Long> classChannelId,
            Optional<Long> subscriberId, Optional<Long> categoryId) throws RepositoryException {
        return repository.getCountAllObjects(status, classChannelId, subscriberId, categoryId);
    }

    public Subscription save(Subscription object) throws RepositoryException {
        this.verifyForeingKey(object);

        return repository.save(object);
    }

    public Subscription update(Subscription object) throws RepositoryException {
        this.verifyForeingKey(object);

        return repository.update(object);
    }

    private void verifyForeingKey(Subscription object) {
        ClassChannel classChannel = classChannelService
                .getObjectById(Optional.ofNullable(object.getClassChannel().getUid()));
        if (null == classChannel || ClassStatus.DISABLE.getCode().equals(classChannel.getStatus())) {
            throw new InvalidParameterException("The class channel id does not exist or status is disable.");
        }

        Subscriber subscriber = subscriberRepository
                .getObjectById(Optional.ofNullable(object.getSubscriber().getUid()));
        if (null == subscriber || ClassStatus.DISABLE.getCode().equals(subscriber.getStatus())) {
            throw new InvalidParameterException("The subscriber id does not exist or status is disable.");
        }

        Category category = categoryRepository.getObjectById(Optional.ofNullable(object.getCategory().getUid()));
        if (null == category || ClassStatus.DISABLE.getCode().equals(category.getStatus())) {
            throw new InvalidParameterException("The category id does not exist or status is disable.");
        }
    }

    public void delete(Subscription object) throws RepositoryException {
        repository.delete(object);
    }

    public List<Subscription> sendMessageSMS(Optional<Long> categoryId) {
        List<Subscription> listSubscription = new ArrayList<>();

        List<ClassChannel> listClassChannel = classChannelService.getAllObjects(
                Optional.of(ClassStatus.ENABLE.getCode()), Optional.of(ClassChannelEnum.SMS.getCode()),
                Pagination.DEFAULT_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());
        ClassChannel classChannel = listClassChannel.stream()
                .filter(item -> ClassChannelEnum.SMS.getCode().equalsIgnoreCase(item.getName())).findAny().orElse(null);

        if (null != classChannel) {
            listSubscription = repository.getAllObjects(Optional.of(ClassStatus.ENABLE.getCode()),
                    Optional.of(classChannel.getUid()), Optional.empty(), categoryId,
                    Pagination.MAX_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            List<BroadcastMessage> listBroadcastMessage = broadcastMessageService.getAllObjects(
                    Optional.of(ClassStatus.ENABLE.getCode()), categoryId, Optional.empty(),
                    Pagination.MAX_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            twilioService.sendSMS(listBroadcastMessage, listSubscription);
        }

        return listSubscription;
    }

    public List<Subscription> sendMessageMail(Optional<Long> categoryId) {
        List<Subscription> listSubscription = new ArrayList<>();

        List<ClassChannel> listClassChannel = classChannelService.getAllObjects(
                Optional.of(ClassStatus.ENABLE.getCode()), Optional.of(ClassChannelEnum.EMAIL.getCode()),
                Pagination.DEFAULT_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());
        ClassChannel classChannel = listClassChannel.stream()
                .filter(item -> ClassChannelEnum.EMAIL.getCode().equalsIgnoreCase(item.getName())).findAny()
                .orElse(null);

        if (null != classChannel) {

            listSubscription = repository.getAllObjects(Optional.of(ClassStatus.ENABLE.getCode()),
                    Optional.ofNullable(classChannel.getUid()), Optional.empty(), categoryId,
                    Pagination.MAX_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            List<BroadcastMessage> listBroadcastMessage = broadcastMessageService.getAllObjects(
                    Optional.of(ClassStatus.ENABLE.getCode()), categoryId, Optional.empty(),
                    Pagination.MAX_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            emailService.sendMail(listBroadcastMessage, listSubscription);
        }

        return listSubscription;
    }

    @Scheduled(cron = "${crontab.expression}")
    public List<Subscription> sendAllMessageMail() {
        if (!StringUtils.isBlank(crontabExpression)) {
            AElog.info1(logger, "Running - Job scheduled for " + formatter.format(LocalDateTime.now()));
        }

        List<Subscription> listSubscription = new ArrayList<>();

        List<ClassChannel> listClassChannel = classChannelService.getAllObjects(
                Optional.of(ClassStatus.ENABLE.getCode()), Optional.of(ClassChannelEnum.EMAIL.getCode()),
                Pagination.DEFAULT_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());
        ClassChannel classChannel = listClassChannel.stream()
                .filter(item -> ClassChannelEnum.EMAIL.getCode().equalsIgnoreCase(item.getName())).findAny()
                .orElse(null);

        if (null != classChannel) {

            listSubscription = repository.getAllObjects(Optional.of(ClassStatus.ENABLE.getCode()),
                    Optional.ofNullable(classChannel.getUid()), Optional.empty(), Optional.empty(),
                    Pagination.MAX_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            List<BroadcastMessage> listBroadcastMessage = broadcastMessageService.getAllObjects(
                    Optional.of(ClassStatus.ENABLE.getCode()), Optional.empty(), Optional.empty(),
                    Pagination.MAX_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            emailService.sendMail(listBroadcastMessage, listSubscription);
        }

        return listSubscription;
    }
}