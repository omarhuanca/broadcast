package io.umss.app.br.broadcast.service.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.core.message.Category;
import io.umss.app.br.broadcast.core.message.ClassChannel;
import io.umss.app.br.broadcast.core.message.Subscription;
import io.umss.app.br.broadcast.dao.message.category.RCategoryRepository;
import io.umss.app.br.broadcast.service.ClassChannelEnum;
import io.umss.app.br.broadcast.service.ClassStatus;
import io.umss.app.br.broadcast.service.EmailService;
import io.umss.app.br.broadcast.service.Pagination;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * CategoryService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
public class CategoryService {

    @Autowired
    RCategoryRepository repository;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    BroadcastMessageService broadcastMessageService;

    @Autowired
    ClassChannelService classChannelService;

    @Autowired
    TwilioService twilioService;

    @Autowired
    EmailService emailService;

    public Category getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Category> getAllObjects(Optional<Integer> status, Optional<String> name, Integer pageSize,
            Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, name, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> name) throws RepositoryException {
        return repository.getCountAllObjects(status, name);
    }

    public Category save(Category object) throws RepositoryException {
        return repository.save(object);
    }

    public Category update(Category object) throws RepositoryException {
        return repository.update(object);
    }

    public void delete(Category object) throws RepositoryException {
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
            listSubscription = subscriptionService.getAllObjects(Optional.of(ClassStatus.ENABLE.getCode()),
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

            listSubscription = subscriptionService.getAllObjects(Optional.of(ClassStatus.ENABLE.getCode()),
                    Optional.ofNullable(classChannel.getUid()), Optional.empty(), categoryId,
                    Pagination.MAX_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            List<BroadcastMessage> listBroadcastMessage = broadcastMessageService.getAllObjects(
                    Optional.of(ClassStatus.ENABLE.getCode()), categoryId, Optional.empty(),
                    Pagination.MAX_PAGE_SIZE.getCode(), Pagination.DEFAULT_PAGE.getCode());

            emailService.sendMail(listBroadcastMessage, listSubscription);
        }

        return listSubscription;
    }
}