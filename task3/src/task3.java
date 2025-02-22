import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class task3 {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Требуется 3 аргумента");
            return;
        }

        File fileValues = new File(args[0]);
        File fileTests = new File(args[1]);
        File fileReport = new File(args[2]);

        ObjectMapper mapper = new ObjectMapper();

        Welcome welcome = readFile(fileTests, new Welcome(), mapper, mapper.constructType(Welcome.class));
        PurpleValue purpleValue = readFile(fileValues, new PurpleValue(), mapper, mapper.constructType(PurpleValue.class));

        Map<Long, String> valuesMap = new HashMap<>();
        for (Test test : purpleValue.getValues()) {
            valuesMap.put(test.getID(), test.getValue());
        }

        fillValues(welcome.getTests(), valuesMap);

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(fileReport, welcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillValues(Test[] tests, Map<Long, String> valuesMap) {
        for (Test test : tests) {
            if (valuesMap.containsKey(test.getID())) {
                test.setValue(valuesMap.get(test.getID()));
            }
            if (test.getValues() != null) {
                fillValues(test.getValues(), valuesMap);
            }
        }
    }

    private static <T> T readFile(File fileName, T result, ObjectMapper mapper, JavaType javaType) {
        try {
            result = mapper.readValue(fileName, javaType);
        } catch (Exception e) {
            System.out.println("Файл не найден! " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}

class Welcome {
    private Test[] tests;

    public Test[] getTests() {
        return tests;
    }

    public void setTests(Test[] value) {
        this.tests = value;
    }
}

class PurpleValue {
    private Test[] values;

    public Test[] getValues() {
        return values;
    }

    public void setValues(Test[] value) {
        this.values = value;
    }
}

class Test {
    private long id;
    private String title;
    private String value;
    private Test[] values;

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Test[] getValues() {
        return values;
    }

    public void setValues(Test[] value) {
        this.values = value;
    }
}