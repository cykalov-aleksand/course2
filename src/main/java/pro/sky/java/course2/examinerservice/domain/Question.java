package pro.sky.java.course2.examinerservice.domain;

import java.util.Objects;

public class Question {
    private final int number;
    private final String question;
    private final String answer;

    public Question(int number, String question, String answer) {
        this.number=number;
        this.question = question;
        this.answer = answer;
    }


    public int getNumber() {
        return number;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Question question1 = (Question) object;
        return Objects.equals(question, question1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(question);
    }


}

