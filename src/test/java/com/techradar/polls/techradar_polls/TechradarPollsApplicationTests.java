package com.techradar.polls.techradar_polls;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
		properties = {"PG_HOST = localhost"}
)
@AutoConfigureMockMvc
public class TechradarPollsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads(){

	}

	@Test
	public void postPollSuccess() throws Exception {
		String pollJson = "{\"tech_id\":1,\"ringResult\":\"TRIAL\"}";
		String messege = "Результат опроса успешно добавлен";

		ResultActions  result  = mockMvc.perform(post( "/poll" )
				.contentType(MediaType.APPLICATION_JSON)
				.content(pollJson));

		result.andExpect(status().isCreated())
				.andExpect(jsonPath( "$.message" ).value(messege));
	}

	@Test
	public void postPollWithErrorBadRequest() throws Exception {
		String pollJson = "{\"tech_id\":1,\"ringResultt\":\"TRIAL\"}";
		String messege = "BAD_REQUEST";

		ResultActions  result  = mockMvc.perform(post( "/poll" )
				.contentType(MediaType.APPLICATION_JSON)
				.content(pollJson));

		result.andExpect(status().isBadRequest())
				.andExpect(jsonPath( "$.message" ).value(messege));
	}

	@Test
	public void postPollWithErrorNotFound() throws Exception {
		String pollJson = "{\"tech_id\":100,\"ringResult\":\"TRIAL\"}";
		String messege = "TECHNOLOGY_NOT_FOUND";

		ResultActions  result  = mockMvc.perform(post( "/poll" )
				.contentType(MediaType.APPLICATION_JSON)
				.content(pollJson));

		result.andExpect(status().isNotFound())
				.andExpect(jsonPath( "$.message" ).value(messege));
	}


}
