package pro.sky.java.course2.examinerservice.service;

import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Comparator;

public class NewComporator implements Comparator<Question> {

    @Override
    public int compare(Question o1, Question o2) {
        return Integer.compare(o1.getNumber(), o2.getNumber());
    }
}
