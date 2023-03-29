package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    public MapSchema required() {
        setIsRequiredOn();
        Predicate<Object> required = o -> o instanceof Map;
        addFirstRule(required);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeOf = o -> ((Map) o).size() == size;
        addRule(sizeOf);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shapeRules) {
        if (shapeRules == null) {
            return this;
        }

        for (var ruleSet : shapeRules.entrySet()) {
            String name = ruleSet.getKey();
            BaseSchema rule = ruleSet.getValue();
            Predicate<Object> shape = o -> o instanceof Map m && rule.isValid(m.get(name));
            addRule(shape);
        }

        return this;
    }
}
