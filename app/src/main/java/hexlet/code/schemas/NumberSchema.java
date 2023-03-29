package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        setIsRequiredOn();
        Predicate<Object> required = o -> o instanceof Integer;
        addFirstRule(required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positive = o -> (int) o > 0;
        addRule(positive);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Object> contains = o -> (int) o >= min && (int) o <= max;
        addRule(contains);
        return this;
    }
}
