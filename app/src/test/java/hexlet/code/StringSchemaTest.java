package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class StringSchemaTest {
    private StringSchema stringSchema;

    @BeforeEach
    public void setup() {
        Validator v = new Validator();
        stringSchema = v.string();
    }

    @Test
    public void stringSchemaTestBasicCase() {
        assertThat(
                stringSchema.isValid(""))
                .isTrue();

        assertThat(
                stringSchema.isValid(0))
                .isTrue();

        assertThat(
                stringSchema.isValid(null))
                .isTrue();
    }

    @Test
    public void stringSchemaTestRequiredCase() {
        stringSchema.required();

        assertThat(
                stringSchema.isValid("Hello"))
                .isTrue();

        assertThat(
                stringSchema.isValid(""))
                .isFalse();

        assertThat(
                stringSchema.isValid(null))
                .isFalse();
    }

    @Test
    public void stringSchemaTestMinLengthCase() {
        stringSchema.required();

        assertThat(
                stringSchema.minLength(3).isValid("Hello"))
                .isTrue();

        assertThat(
                stringSchema.minLength(10).isValid("Too small"))
                .isFalse();

        assertThat(
                stringSchema.minLength(10).isValid(null))
                .isFalse();
    }

    @Test
    public void stringSchemaTestContainsCase() {
        stringSchema.required().contains("abo");

        assertThat(
                stringSchema.isValid("Bibaboba"))
                .isTrue();

        assertThat(
                stringSchema.isValid("No biba, no boba"))
                .isFalse();

        assertThat(
                stringSchema.isValid(null))
                .isFalse();
    }

    @Test
    public void stringSchemaTestComplexCase() {
        // without 'required'
        stringSchema.contains("Nano").minLength(5);

        assertThat(
                stringSchema.isValid(null))
                .isTrue();

        assertThat(
                stringSchema.isValid("NanoPotato"))
                .isTrue();

        assertThat(
                stringSchema.isValid("BigPotato"))
                .isFalse();

        assertThat(
                stringSchema.isValid(100))
                .isFalse();

        // added 'required'
        stringSchema.required();

        assertThat(
                stringSchema.isValid("PotatoNanoPotato"))
                .isTrue();

        assertThat(
                stringSchema.isValid("PotatoBigPotato"))
                .isFalse();

        assertThat(
                stringSchema.isValid(null))
                .isFalse();

        assertThat(
                stringSchema.isValid(0))
                .isFalse();
    }
}
