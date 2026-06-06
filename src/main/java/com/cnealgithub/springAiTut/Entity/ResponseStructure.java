package com.cnealgithub.springAiTut.Entity;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Data
public class ResponseStructure {
    private String question;
    private String answer;
}
