package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfAvailable;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServicelmpl implements ExaminerService {
    QuestionServices questionServices;

    public ExaminerServicelmpl(QuestionServices questionServices) {
        this.questionServices = questionServices;
    }

    public Set<Question> getQuestions(int amount) {
        if (amount > questionServices.getSizeQuestions()) {
            throw new ExceptionIfAvailable("В хранилище нет такого количества вопросов. Повторите ввод.");
        }
        Set<Question> setQuestion = new HashSet<>();
        for (int variable = 1; variable <= amount; variable++) {
            if (!setQuestion.add(questionServices.getRandomQuestion())) {
                variable--;
            }
        }
        return setQuestion;
    }
}