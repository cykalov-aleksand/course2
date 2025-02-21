package pro.sky.java.course2.examinerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfAvailable;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import java.util.Collection;
import java.util.Set;


@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @ExceptionHandler(ExceptionIfAvailable.class)
    public ResponseEntity<String> noSuchProductHandler
            (ExceptionIfAvailable e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @GetMapping(path = "/remove")
    public Question remove(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return javaQuestionService.remove(question, answer);
    }

    @GetMapping(path = "/add")
    public Question add(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping(path = "/addtest")
    public Collection<Question> addTest() {
        return javaQuestionService.addTest();
    }

    @GetMapping(path = "/removetest")
    public Set<Question> removeTest() {
        return javaQuestionService.removeTest();
    }

    @GetMapping(path = "/removeall")
    public String removeAll() {
        return javaQuestionService.removeAll();
    }

    @GetMapping
    public Collection<Question> getAll() {
        return javaQuestionService.getAll();
    }

    @GetMapping(path = "/getRandomQuestion")
    public Question getRandomQuestion() {
        return javaQuestionService.getRandomQuestion();
    }
}