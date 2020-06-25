package io.umss.app.br.broadcast.service.message;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.Category;
import io.umss.app.br.broadcast.core.message.ClassChannel;
import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.core.message.Subscription;
import io.umss.app.br.broadcast.dao.message.category.RCategoryRepository;
import io.umss.app.br.broadcast.dao.message.classchannel.RClassChannelRepository;
import io.umss.app.br.broadcast.dao.message.subscriber.RSubscriberRepository;
import io.umss.app.br.broadcast.dao.message.subscription.RSubscriptionRepository;
import io.umss.app.br.broadcast.service.ClassStatus;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * SubscriptionService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
public class SubscriptionService {

    @Autowired
    RSubscriptionRepository repository;

    @Autowired
    RClassChannelRepository classChannelRepository;

    @Autowired
    RSubscriberRepository subscriberRepository;

    @Autowired
    RCategoryRepository categoryRepository;

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
        ClassChannel classChannel = classChannelRepository
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
}