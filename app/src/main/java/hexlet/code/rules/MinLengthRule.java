package hexlet.code.rules;

public final class MinLengthRule implements IRule {
    private final int length;

    public MinLengthRule(int minLength) {
        this.length = minLength;
    }

    @Override
    public boolean validate(Object o) {
        return o instanceof String s && s.length() >= this.length;
    }
}
