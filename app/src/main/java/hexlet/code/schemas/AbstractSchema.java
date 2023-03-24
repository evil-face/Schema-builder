package hexlet.code.schemas;

import hexlet.code.rules.IRule;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSchema {
    private final List<IRule> rules = new ArrayList<>();
    abstract boolean isValid(Object o);

    protected final void addRule(IRule newRule) {
        this.rules.add(newRule);
    }

    protected final List<IRule> getRules() {
        return this.rules;
    }
}
