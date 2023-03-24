package hexlet.code.rules;

public final class RequiredRule implements IRule {
    @Override
    public boolean validate(Object o) {
        return o instanceof String s && !s.isEmpty();
    }
}
