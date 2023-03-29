package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        setIsRequiredOn();
        Predicate<Object> required = o -> o instanceof String s && !s.isEmpty();
        addFirstRule(required);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<Object> minLengthRule = o -> ((String) o).length() >= length;
        addRule(minLengthRule);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<Object> contains = o -> ((String) o).contains(substring);
        addRule(contains);
        return this;
    }
}
