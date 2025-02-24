package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Component;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class TestPageJavaQuestions {
    private final Set<Question> questions;
    private final JavaQuestionService javaQuestionService;

    public TestPageJavaQuestions(Set<Question> questions, JavaQuestionService javaQuestionService) {
        this.questions = questions;
        this.javaQuestionService = javaQuestionService;
    }

    public Set<Question> addTest() {
        questions.addAll(test());
        for (Question variable : questions) {
            javaQuestionService.add(variable);
        }
        return questions.stream().collect(Collectors.toCollection(() -> new TreeSet<>(new NewComporator())));
    }

    public Set<Question> removeTest() {
        Set<Question> removeList = new HashSet<>();
        for (Question variable : test()) {
            Optional<Question> optional = questions.stream().filter(element -> element
                    .getQuestion().equals(variable.getQuestion())).findFirst();
            optional.ifPresent(question -> removeList.add(javaQuestionService.remove(question)));
        }
        return removeList;
    }


    private Set<Question> test() {
        Question[] questions = new Question[]{
                new Question(1, "Из перечисленных ниже вариантов выберите тот, который лучше всего подходит под раскрытие аналогии переменной.", "Коробка. Ящик"),
                new Question(2, "Что такое переменная?", "Область в памяти компьютера для хранения данных, которой можно присвоить имя."),
                new Question(3, "Какая команда позволяет вывести результат написания кода в консоль?", "Sydtem.out.println();"),
                new Question(4, "Что такое инициализация переменной?", "Присвоение какого-то значения переменной."),
                new Question(5, "Что такое сборка мусора в Java?", "Автоматический процесс определения ненужных объектов и их удаления."),
                new Question(6, "Какие подходы используются для обнаружения мусора?", "reference counting, tracing"),
                new Question(7, "Какой алгоритм предполагает деление памяти на from-space и to-space?", "copyng collectors."),
                new Question(8, "Как называется метод в Java для явного вызова сборщика мусора?", "System.gc()"),
                new Question(9, "Какие задачи выполняет метод finalize()?", "Выполняет действия перед удалением объекта."),
                new Question(10, "Для чего используются слабые, мягкие и фантомные ссылки в Java?", "Для тонкой настройки управления памятью."),
                new Question(11, "Какой сборщик мусора в Java подходит для серверных приложений с высокими требованиями к задержке и большим объемом данных?", "G1 GC"),
                new Question(12, "Какие инструменты используются для мониторинга и профилирования работы сборщика мусора?", "VisualVM, JConsole."),
                new Question(13, "Какой из подходов обнаружения мусора может вызвать утечки памяти из-за циклических зависимостей?", "reference counting."),
                new Question(14, "Какие параметры командной строки для JVM используются для настройки сборщика мусора?", "-XX:UseG1GC;  -XX:+UseSerialGC"),
                new Question(15, "Что делает метод System.gc()?", "Просит JVM запустить сборку мусора"),
                new Question(16, "Какие типы сборщиков мусора существуют в Java HotSpot VM?", "Serial GC; Parallel GC; Concurrent Mark Sweep (CMS); Shenandoah GC"),
                new Question(17, "Вопрос", "Ответ")
        };
        return stream(questions).collect(Collectors.toSet());
    }
}
