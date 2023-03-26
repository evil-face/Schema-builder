package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        setIsRequiredOn();
        Predicate<Object> required = o -> o instanceof Integer;
        addRule(required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positive = o -> o instanceof Integer i && i > 0;
        addRule(positive);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Object> contains = o -> o instanceof Integer i && i >= min && i <= max;
        addRule(contains);
        return this;
    }
}
