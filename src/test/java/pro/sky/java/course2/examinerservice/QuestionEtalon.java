package pro.sky.java.course2.examinerservice;

import java.util.Objects;

public class QuestionEtalon {
        private final int number;
        private final String question;
        private final String answer;

        public QuestionEtalon(int number, String question, String answer) {
            this.number = number;
            this.question = question;
            this.answer = answer;
        }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        QuestionEtalon that = (QuestionEtalon) object;
        return number == that.number && Objects.equals(question, that.question) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, question, answer);
    }

    @Override
    public String toString() {
        return "QuestionEtalon{" +
                "number=" + number +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
