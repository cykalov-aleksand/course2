package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfNoAvailable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExamineServicelmpl implements ExaminerService {
    private final Collection<Question> questions;
    QuestionServices questionServices;
    public ExamineServicelmpl(Collection<Question> questions, QuestionServices questionServices) {
        this.questions = questions;
        this.questionServices = questionServices;
    }
    public Set<Question> getQuestions(int amount) {
        if(amount> questionServices.getSizeQuestions()){
            throw new ExceptionIfNoAvailable();
        }
        Set<Question> setQuestion = new HashSet<>();
        for (int variable = 0; variable < amount; variable++) {
            if (!setQuestion.add(questionServices.getRandomQuestion())) {
                variable--;
            }
        }
        return setQuestion;
        // return setQuestion.stream().collect(Collectors.toCollection(() -> new TreeSet<>(new NewComporator())));
    }

}