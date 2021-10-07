package com.chvey.controller;

import com.chvey.converters.CommentConverter;
import com.chvey.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentControllerTest {
    @InjectMocks
    CommentController commentController;
    @Mock
    CommentService commentService;
    @Mock
    CommentConverter commentConverter;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController)
                .build();
    }

    @Test
    public void getComments() throws Exception {
        when(commentService.getCommentsByTicketId(any())).thenReturn(java.util.Optional.of(new ArrayList<>()));
        when(commentConverter.toDto(any())).thenReturn(null);

        mockMvc.perform(get("/tickets/1/comments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveComment() throws Exception {
        when(commentService.saveComment(any(),any(),any())).thenReturn(Optional.empty());
        when(commentConverter.toDto(any())).thenReturn(null);

        mockMvc.perform(get("/tickets/1/comments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}