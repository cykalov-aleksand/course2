package pro.sky.java.course2.examinerservice.service;

import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;

public interface QuestionServices {
    Object add(String question, String answer);
    Question add(Question question);
    Question remove(String question, String answer);
    Collection<Question> getAll();
    Question getRandomQuestion();
    int getSizeQuestions();
}
