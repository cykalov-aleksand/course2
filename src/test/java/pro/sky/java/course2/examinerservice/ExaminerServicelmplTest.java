package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfAvailable;
import pro.sky.java.course2.examinerservice.service.ExaminerServicelmpl;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServicelmplTest {
    private ExaminerServicelmpl examinerServicelmpl;


    @Test
    void testingTheAbsenceOfDuplicateQuestions() {
        Set<Question> objectJavaQuestionService = new HashSet<>(test());
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        Set<Question> examiner;
        examinerServicelmpl = new ExaminerServicelmpl(javaQuestionService);
        examiner = new HashSet<>(examinerServicelmpl.getQuestions(javaQuestionService.getSizeQuestions()));
        assertEquals(javaQuestionService.getSizeQuestions(), examiner.size());
    }

    @Test
    void testToCheckTheInputOfLargeNumberOfQuestions() {
        Set<Question> objectJavaQuestionService = new HashSet<>(test());
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        examinerServicelmpl = new ExaminerServicelmpl(javaQuestionService);
        Exception exception = assertThrows(ExceptionIfAvailable.class, () -> examinerServicelmpl.getQuestions(javaQuestionService.getSizeQuestions() + 1));
        String actualMessage = exception.getMessage();
        String exceptedMessage = "В хранилище нет такого количества вопросов. Повторите ввод.";
        assertTrue(actualMessage.contains(exceptedMessage));
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
