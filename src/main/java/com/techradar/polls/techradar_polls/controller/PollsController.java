package com.techradar.polls.techradar_polls.controller;

import com.techradar.polls.techradar_polls.dto.ErrorResponseDto;
import com.techradar.polls.techradar_polls.dto.RequestPollDto;
import com.techradar.polls.techradar_polls.dto.ResponsePollDto;
import com.techradar.polls.techradar_polls.jpa.UsersRepository;
import com.techradar.polls.techradar_polls.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestController // 1
@RequestMapping("/poll")
public class PollsController {
    @Autowired
    private PollService pollService;

    @Autowired
    private UsersRepository usersRepository;

    @Operation(
            summary = "Получение списка технологий"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Результат опроса успешно добавлен", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ResponsePollDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Технология не найдена", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponseDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponseDto.class)) }) })
    @PostMapping()
    public ResponseEntity addPoll(@RequestBody RequestPollDto requestBody, HttpServletRequest headers) {
        try {
            Long userId = (long) headers.getAttribute("X-Techradar-UserID");
            var response = pollService.savePoll(requestBody, userId);
            return response;

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto(INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR"), INTERNAL_SERVER_ERROR);
        }
    }

}
