package io.umss.app.br.broadcast.service.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.umss.app.br.broadcast.core.message.Message;
import io.umss.app.br.broadcast.dao.message.message.RMessageRepository;
import io.umss.app.br.broadcast.service.Pagination;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @InjectMocks
    private MessageService instance;

    @Mock
    private RMessageRepository repository;

    @Test
    public void test01() {
        final Long instanceId = new Long(1000L);
        final Optional<Integer> status = Optional.of(1);
        final int pageSize = Pagination.DEFAULT_PAGE_SIZE.getCode();
        final int page = Pagination.DEFAULT_PAGE.getCode();

        List<Message> list = new ArrayList<>();
        Message object = new Message();
        object.setUid(instanceId);
        list.add(object);

        when(this.repository.getAllObjects(status, Optional.empty(), Optional.empty(), Optional.empty(), pageSize, page)).thenReturn(list);
        List<Message> response = this.instance.getAllObjects(status, Optional.empty(), Optional.empty(), Optional.empty(), pageSize, page);

        assertEquals(response.get(0).getUid(), instanceId);
    }

    @Test
    public void test02() {
        final int totalExpected = 80;
        final Optional<Integer> status = Optional.of(1);

        when(this.repository.getCountAllObjects(status, Optional.empty(), Optional.empty(), Optional.empty())).thenReturn(totalExpected);
        int response = this.instance.getCountAllObjects(status, Optional.empty(), Optional.empty(), Optional.empty());

        assertEquals(response, totalExpected);
    }

    @Test
    public void test03() {
        try {
            final Integer status = 1;
            final String title = "TitleA";
            final String body = "BodyA";
            final Message object = new Message();
            object.setStatus(status);
            object.setTitle(title);
            object.setBody(body);

            this.repository.save(object);

            verify(this.repository, times(1)).save(object);
        } catch (Exception e) {
            fail();
        }
    }
}
