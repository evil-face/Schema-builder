package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        setIsRequiredOn();
        Predicate<Object> required = o -> o instanceof String s && !s.isEmpty();
        addRule(required);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<Object> minLengthRule = o -> o instanceof String s && s.length() >= length;
        addRule(minLengthRule);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<Object> contains = o -> o instanceof String s && s.contains(substring);
        addRule(contains);
        return this;
    }
}
