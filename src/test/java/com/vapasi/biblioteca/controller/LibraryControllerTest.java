package com.vapasi.biblioteca.controller;

import com.vapasi.biblioteca.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers= LibraryController.class)
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService booksService;

    @Test
    void shouldExpectOKStatusWhileFetchingAllBooks() throws Exception {
        mockMvc.perform(get("/api/v1/library/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(booksService, times(1)).getAllBooks();
    }

    @Test
    void shouldExpectNotFoundErrorWhenUrlIsWrong () throws Exception {
        mockMvc.perform(get("/api/v1/library")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(booksService, times(0)).getAllBooks();
    }

    @Test
    void shouldExpectOKStatusWhileFilteringBooksByStatus() throws Exception {
        mockMvc.perform(get("/api/v1/library/books?status=Available")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(booksService, times(1)).filterByStatus("Available");
    }
}
