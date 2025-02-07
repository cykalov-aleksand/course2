package pro.sky.java.course2.examinerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfNoAvailable;
import pro.sky.java.course2.examinerservice.service.ExamineServicelmpl;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExamineServicelmpl examineServicelmpl;

    public ExamController(ExamineServicelmpl examineServicelmpl) {
        this.examineServicelmpl = examineServicelmpl;
    }

    @ExceptionHandler(ExceptionIfNoAvailable.class)
    public ResponseEntity<String> noNunberQuestion
            (ExceptionIfNoAvailable e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("В хранилище нет такого количества вопросов повторите ввод.");
    }

    @GetMapping("/exam/get/{amount}")
    public Collection getQuestions(@PathVariable("amount") int amount) {

        return examineServicelmpl.getQuestions(amount);


    }
}
