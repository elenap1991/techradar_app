package com.techradar.polls.techradar_polls.controller;

import com.techradar.polls.techradar_polls.dto.ErrorDetailsResponseDto;
import com.techradar.polls.techradar_polls.dto.ErrorResponseDto;
import com.techradar.polls.techradar_polls.dto.ResponsePollDto;
import com.techradar.polls.techradar_polls.dto.ResponseTechDto;
import com.techradar.polls.techradar_polls.model.Category;
import com.techradar.polls.techradar_polls.service.TechnologiesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestController //2. 2 > 1. 2 is more than 1
@RequestMapping("/api")
public class ApiTechnologyController {

    @Autowired
    private TechnologiesService technologiesService;

    @Operation(
            summary = "Добавление результата опроса"
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная операция, возвращен список технологий", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ResponseTechDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Некорректные параметры запроса", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetailsResponseDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponseDto.class)) }) })

    @GetMapping("/technology")
    public ResponseEntity getTechnologies(@RequestParam(required = false) @Parameter(description = "Категория технологии") String category,
                                          @RequestParam(required = false) @Parameter(description = "Секция технологии") String section) {
        try {
            return technologiesService.getTechnologies(category, section);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponseDto(INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR"), INTERNAL_SERVER_ERROR);
        }
    }
}
