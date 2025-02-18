package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exeption.ExceptionIfAvailable;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    Question question;
    private Random randomMock;

    @Test
    void checkingTheOperationOfMethods_getAll_getSize_add_WithAnEmptyQuestionAdditionList() {
        String textQuestion = "Вопрос вопрос";
        String textAnswer = "Ответ ответ";
        Set<Question> objectJavaQuestionService = new HashSet<>();
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        int etalonSize = javaQuestionService.getSizeQuestions();
        javaQuestionService.add(textQuestion, textAnswer);
        assertEquals(etalonSize + 1, javaQuestionService.getSizeQuestions());
        assertEquals(textAnswer, javaQuestionService.getAll().iterator().next().getAnswer());
        assertEquals(textQuestion, javaQuestionService.getAll().iterator().next().getQuestion());
        assertEquals(1, javaQuestionService.getAll().iterator().next().getNumber());
    }
    @Test
    void checkingTheDeletionOfAnObjectUsingTheRemoveMethod(){
        String textQuestion = "Вопрос вопрос";
        String textAnswer = "Ответ ответ";
        Set<Question> objectJavaQuestionService = new HashSet<>();
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        int etalonSize = javaQuestionService.getSizeQuestions();
        javaQuestionService.remove(javaQuestionService.add(textQuestion, textAnswer));
        assertEquals(etalonSize,javaQuestionService.getSizeQuestions());
    }//Проверка метода remove(question); (создали объект и тут же удалили

    @Test
    void deletingQuestionFromTheListWhenTheListOfQuestionsIsEmpty() {
        String textQuestion = "Вопрос вопрос";
        String textAnswer = "Ответ";
        Set<Question> objectJavaQuestionService = new HashSet<>();
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        Exception exception = assertThrows(ExceptionIfAvailable.class, () -> javaQuestionService.remove(textQuestion, textAnswer));
        String actualMessage = exception.getMessage();
        String exceptedMessage = "Список вопросов пуст, сначала произведите ввод вопросов!!!";
        assertTrue(actualMessage.contains(exceptedMessage));
    }

    @Test
    void checkingTheOperationOfMethods_getSize_add_WhenReEnteringQuestionAndFillInTheList() {
        String textQuestion = "Вопрос";
        String textAnswer = "Ответ ответ";
        Set<Question> objectJavaQuestionService = new HashSet<>(test());
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        int etalonSize = javaQuestionService.getSizeQuestions();
        Exception exception = assertThrows(ExceptionIfAvailable.class, () -> javaQuestionService.add(textQuestion, textAnswer));
        String actualMessage = exception.getMessage();
        String exceptedMessage = "Строка не введена, данный вопрос в списке присутствует:  ";
        assertTrue(actualMessage.contains(exceptedMessage));
        assertEquals(etalonSize, javaQuestionService.getSizeQuestions());
    }
    @Test
    void checkingHowTheMethodWorks_add_IfThereIsNoQuestionAndTheListIsFilledOut(){
        String textQuestion = "";
        String textAnswer = "Ответ ответ";
        Set<Question> objectJavaQuestionService = new HashSet<>(test());
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        Exception exception = assertThrows(ExceptionIfAvailable.class, () -> javaQuestionService.add(textQuestion, textAnswer));
        String actualMessage = exception.getMessage();
        String exceptedMessage = "Пустая, строка повторите ввод вопроса!!!";
        assertTrue(actualMessage.contains(exceptedMessage));
    }
    @Test
    void checkingTheMethodsOperation_add_IfThereIsNoResponseAndTheListIsFilledOut(){
        String textQuestion = "Вопрос вопрос";
        String textAnswer = "";
        Set<Question> objectJavaQuestionService = new HashSet<>(test());
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        Exception exception = assertThrows(ExceptionIfAvailable.class, () -> javaQuestionService.add(textQuestion, textAnswer));
        String actualMessage = exception.getMessage();
        String exceptedMessage = "Ответ на вопрос: ";
        assertTrue(actualMessage.contains(exceptedMessage));
    }
    @Test
    void deletingQuestionFromTheListInTheAbsenceOfOne(){
        String textQuestion = "Вопрос вопрос";
        String textAnswer = "Ответ";
        Set<Question> objectJavaQuestionService = new HashSet<>(test());
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        Exception exception = assertThrows(ExceptionIfAvailable.class, () -> javaQuestionService.remove(textQuestion, textAnswer));
        String actualMessage = exception.getMessage();
        String exceptedMessage = "Такого вопроса в списке нет. Вопрос не удален ";
        assertTrue(actualMessage.contains(exceptedMessage));
    }

    @Test
    void checkingRemovingQuestionFromTheListAndMakingTheMethodWork_getSize(){
        String textQuestion = "Вопрос";
        String textAnswer = "Ответ";
        Set<Question> objectJavaQuestionService = new HashSet<>(test());
        JavaQuestionService javaQuestionService = new JavaQuestionService(objectJavaQuestionService);
        int etalonSize = javaQuestionService.getSizeQuestions();
        javaQuestionService.remove(textQuestion, textAnswer);
      Optional optional=(javaQuestionService.getAll().stream().filter(o -> o.getQuestion().equalsIgnoreCase(textQuestion.trim())).findAny());
       assertEquals(etalonSize-1, javaQuestionService.getSizeQuestions());
       assertTrue(optional.isEmpty());
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
