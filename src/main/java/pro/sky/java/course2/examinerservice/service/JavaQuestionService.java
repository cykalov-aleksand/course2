package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfAvailable;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionServices {
    private final Set<Question> questions;

    public JavaQuestionService(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        JavaQuestionService that = (JavaQuestionService) object;
        return Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(questions);
    }

    public Question add(String question, String answer) {
        String formQuestion = formString(question);
        String formAnswer = formString(answer);
        Optional<Question> first = (questions.stream()
                .filter(o -> o.getQuestion().equalsIgnoreCase(formQuestion) || (o.getAnswer().equalsIgnoreCase(formAnswer))).findAny());
        return first.orElseGet(() -> add(new Question(questions.size() + 1, formQuestion, answer)));
    }

    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        String formQuestion = formString(question);
        Optional<Question> first = (questions.stream().filter(o -> formQuestion.equalsIgnoreCase(o.getQuestion())).findAny());
        if (first.isPresent()) {
            questions.remove(first.get());
        } else {
            first.orElseThrow(ExceptionIfAvailable::new);
        }
        return first.get();
    }

    public Collection<Question> getAll() {
        return questions;
    }
    public Question getRandomQuestion() {
        int i = 0;
        Random random = new Random();
        int randomNumber = random.nextInt(questions.size());
        for (Question variable : questions) {
            if (i == randomNumber) {
                return variable;
            }
            i++;
        }
        return null;
    }

    public int getSizeQuestions() {
        return questions.size();
    }

    public String formString(String line) {
        StringBuilder stringBuilder = new StringBuilder(line);
        stringBuilder.deleteCharAt(0);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString().trim();
    }
}
