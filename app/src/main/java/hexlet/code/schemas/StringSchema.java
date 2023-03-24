package hexlet.code.schemas;

import hexlet.code.rules.ContainsRule;
import hexlet.code.rules.IRule;
import hexlet.code.rules.MinLengthRule;
import hexlet.code.rules.RequiredRule;

public final class StringSchema extends AbstractSchema {

    @Override
    public boolean isValid(Object o) {
        for (IRule rule : getRules()) {
            if (!rule.validate(o)) {
                return false;
            }
        }
        return true;
    }

    public StringSchema required() {
        addRule(new RequiredRule());
        return this;
    }

    public StringSchema minLength(int length) {
        addRule(new MinLengthRule(length));
        return this;
    }

    public StringSchema contains(String substring) {
        addRule(new ContainsRule(substring));
        return this;
    }
}
