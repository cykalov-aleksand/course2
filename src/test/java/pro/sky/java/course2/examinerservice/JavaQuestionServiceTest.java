package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    Question question;

    @Test
    void addingNonExistentProductToTheShoppingCart() {
        String textQuestion = "Вопрос вопрос";
        String textAnswer = "Ответ ответ";
        Set<Question> qq = new HashSet<>();
        JavaQuestionService javaQuestionService = new JavaQuestionService(qq);
        int etalonSize = javaQuestionService.getSizeQuestions();
       javaQuestionService.add(textQuestion, textAnswer);
        assertEquals(etalonSize + 1, javaQuestionService.getSizeQuestions());
        assertEquals(textAnswer, javaQuestionService.getAll().iterator().next().getAnswer());
        assertEquals(textQuestion, javaQuestionService.getAll().iterator().next().getQuestion());
        assertEquals(1, javaQuestionService.getAll().iterator().next().getNumber());
    }
}
