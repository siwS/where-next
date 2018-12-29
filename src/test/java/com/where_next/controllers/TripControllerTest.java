package com.where_next.controllers;

import com.where_next.repositories.TripRepository;
import com.where_next.services.TripService;
import com.where_next.utils.TripUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.where_next.utils.TripUtil.FIRST_TRIP_NAME;
import static com.where_next.utils.TripUtil.TRIP_ID_1;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TripController.class)
public class TripControllerTest {

    private static final String GET_TRIP_ENDPOINT_PATH = "/trip";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TripService tripService;

    @MockBean
    TripRepository tripRepository;

    @BeforeEach
    public void setup() {
        TripUtil.setupTripServiceMock(tripService);
    }

    @Test
    public void getTripByName() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get(GET_TRIP_ENDPOINT_PATH).param("name", FIRST_TRIP_NAME)
                        .accept(APPLICATION_JSON_UTF8));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(FIRST_TRIP_NAME)));

        verify(tripService, times(1)).getTripByName(FIRST_TRIP_NAME);
        verifyNoMoreInteractions(tripService);
    }

    @Test
    public void getTripById() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get(GET_TRIP_ENDPOINT_PATH).param("id", "1")
                        .accept(APPLICATION_JSON_UTF8));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(FIRST_TRIP_NAME)));

        verify(tripService, times(1)).getTripById(TRIP_ID_1);
        verifyNoMoreInteractions(tripService);
    }

    @Test
    public void getTripByNameAndId() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get(GET_TRIP_ENDPOINT_PATH).param("name", FIRST_TRIP_NAME)
                        .param("id", "1")
                        .accept(APPLICATION_JSON_UTF8));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(FIRST_TRIP_NAME)));

        verify(tripService, times(1)).getTripByName(FIRST_TRIP_NAME);
        verifyNoMoreInteractions(tripService);
    }
}
