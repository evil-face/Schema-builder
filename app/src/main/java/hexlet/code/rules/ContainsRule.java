package hexlet.code.rules;

public final class ContainsRule implements IRule {
    private final String substring;

    public ContainsRule(String text) {
        this.substring = text;
    }

    @Override
    public boolean validate(Object o) {
        if (this.substring == null) {
            return false;
        }
        return o instanceof String s && s.contains(this.substring);
    }
}
