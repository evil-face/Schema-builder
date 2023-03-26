package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private boolean isRequiredOn = false;
    private final List<Predicate<Object>> rules = new ArrayList<>();

    public final boolean isValid(Object o) {
        if (o == null && !getIsRequiredOn()) {
            return true;
        }

        for (Predicate<Object> rule : getRules()) {
            if (!rule.test(o)) {
                return false;
            }
        }
        return true;
    }
    protected final void addRule(Predicate<Object> newRule) {
        this.rules.add(newRule);
    }

    protected final List<Predicate<Object>> getRules() {
        return this.rules;
    }

    protected final void setIsRequiredOn() {
        this.isRequiredOn = true;
    }

    protected final boolean getIsRequiredOn() {
        return this.isRequiredOn;
    }
}
