package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfAvailable;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

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

    public Set<Question> addTest() {
        questions.addAll(test());
        return questions.stream().collect(Collectors.toCollection(() -> new TreeSet<>(new NewComporator())));
    }

    public String removeAll() {
        questions.clear();
        return ("Список класса JavaQuestionService пуст");
    }

    public Set<Question> removeTest() {
        Set<Question> removeList = new HashSet<>();
        for (Question variable : test()) {
            Optional<Question> optional = questions.stream().filter(o -> o.getQuestion().equals(variable.getQuestion())).findFirst();
            optional.ifPresent(question -> removeList.add(remove(question)));
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
