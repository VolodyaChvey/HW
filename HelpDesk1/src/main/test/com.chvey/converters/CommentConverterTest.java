package com.chvey.converters;

import com.chvey.domain.Comment;
import com.chvey.dto.CommentDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class CommentConverterTest {
    @InjectMocks
    CommentConverter commentConverter;
    @Mock
    UserConverter userConverter;
    @Mock
    TicketConverter ticketConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toDto() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setText("Comment");
        comment.setDate(LocalDateTime.now());


        when(userConverter.toDto(any())).thenReturn(null);
        when(ticketConverter.toDto(any())).thenReturn(null);

        CommentDto commentDto = commentConverter.toDto(comment);

        assertEquals(comment.getId(), commentDto.getId());
        assertEquals(comment.getText(), commentDto.getText());
        assertEquals(comment.getDate().toString(), commentDto.getDate());
    }

    @Test
    public void toDtoWithNullFields() {
        Comment comment = new Comment();
        comment.setId(null);
        comment.setText(null);
        comment.setDate(null);

    }
}