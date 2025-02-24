package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfAvailable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionServices {
    private final Set<Question> questions;
    Random random;

    @Autowired
    public JavaQuestionService(Set<Question> questions) {
        this.questions = new HashSet<>(questions);
        this.random = new Random();

    }

    public JavaQuestionService(Set<Question> questions, Random random) {
        this.questions = new HashSet<>(questions);
        this.random = random;

    }

    public Question add(String question, String answer) {
        if (question.isEmpty()) {
            throw new ExceptionIfAvailable("Пустая, строка повторите ввод вопроса!!!");
        }
        Optional<Question> first = (questions.stream()
                .filter(element -> element.getQuestion().equalsIgnoreCase(question.trim())).findAny());
        if (first.isPresent()) {
            throw new ExceptionIfAvailable("Строка не введена, данный вопрос в списке присутствует:  " + first.get());
        }
        if (answer.isEmpty()) {
            throw new ExceptionIfAvailable("Ответ на вопрос: " + question + " не введен повторите ввод");
        }
        return first.orElseGet(() -> add(new Question(questions.size() + 1, question.trim(), answer.trim())));
    }

    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        } else {
            throw new ExceptionIfAvailable("Объекта с таким вопросом нет");
        }
    }

    public Question remove(String question, String answer) {
        if (getSizeQuestions() == 0) {
            throw new ExceptionIfAvailable("Список вопросов пуст, сначала произведите ввод вопросов!!!");
        }
        Optional<Question> first = Optional.ofNullable(((questions.stream().filter(o -> question.trim()
                .equalsIgnoreCase(o.getQuestion()) && ((o.getAnswer().equalsIgnoreCase(answer.trim())))).findAny())
                .orElseThrow(() -> new ExceptionIfAvailable("Такого вопроса в списке нет. Вопрос не удален "))));
        remove(first.get());
        return first.get();
    }

    public Collection<Question> getAll() {
        return questions.stream().collect(Collectors.toCollection(() -> new TreeSet<>(new NewComporator())));
    }

    public Question getRandomQuestion() {
        int i = 0;
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

}
