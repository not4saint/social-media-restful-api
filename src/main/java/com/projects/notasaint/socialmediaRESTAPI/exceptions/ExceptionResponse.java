package com.projects.notasaint.socialmediaRESTAPI.exceptions;

import lombok.AllArgsConstructor;


import java.time.LocalDateTime;

@AllArgsConstructor
public class ExceptionResponse {
    String requestURI;
    String message;
    LocalDateTime localDateTime;
}
