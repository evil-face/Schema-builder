package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class NumberSchemaTest {
    private NumberSchema numberSchema;

    @BeforeEach
    public void setup() {
        Validator v = new Validator();
        numberSchema = v.number();
    }

    @Test
    public void numberSchemaTestBasicCase() {
        assertThat(
                numberSchema.isValid(null))
                .isTrue();

        assertThat(
                numberSchema.isValid(100))
                .isTrue();

        assertThat(
                numberSchema.isValid(-100))
                .isTrue();

        assertThat(
                numberSchema.isValid("100"))
                .isTrue();
    }

    @Test
    public void numberSchemaTestRequiredCase() {
        numberSchema.required();

        assertThat(
                numberSchema.isValid(10))
                .isTrue();

        assertThat(
                numberSchema.isValid("5"))
                .isFalse();

        assertThat(
                numberSchema.isValid(null))
                .isFalse();

        assertThat(
                numberSchema.isValid(0))
                .isTrue();

        assertThat(
                numberSchema.isValid(-100))
                .isTrue();
    }

    @Test
    public void numberSchemaTestPositiveCase() {
        numberSchema.required().positive();

        assertThat(
                numberSchema.isValid(10))
                .isTrue();

        assertThat(
                numberSchema.isValid(0))
                .isFalse();

        assertThat(
                numberSchema.isValid(null))
                .isFalse();

        assertThat(
                numberSchema.isValid("5"))
                .isFalse();

        assertThat(
                numberSchema.isValid(-100))
                .isFalse();
    }

    @Test
    public void numberSchemaTestRangeCase() {
        numberSchema.required().range(5, 10);

        assertThat(
                numberSchema.isValid(5))
                .isTrue();

        assertThat(
                numberSchema.isValid(10))
                .isTrue();

        assertThat(
                numberSchema.isValid(20))
                .isFalse();

        assertThat(
                numberSchema.isValid(2))
                .isFalse();

        assertThat(
                numberSchema.isValid(-100))
                .isFalse();

        assertThat(
                numberSchema.isValid(null))
                .isFalse();

        assertThat(
                numberSchema.isValid("7"))
                .isFalse();
    }

    @Test
    public void stringSchemaTestComplexCase() {
        // without 'required'
        numberSchema.positive().range(5, 10);

        assertThat(
                numberSchema.isValid(7))
                .isTrue();

        assertThat(
                numberSchema.isValid(100))
                .isFalse();

        assertThat(
                numberSchema.isValid(null))
                .isTrue();

        assertThat(
                numberSchema.isValid("5"))
                .isFalse();

        // added 'required'
        numberSchema.required();

        assertThat(
                numberSchema.isValid(7))
                .isTrue();

        assertThat(
                numberSchema.isValid(100))
                .isFalse();

        assertThat(
                numberSchema.isValid(null))
                .isFalse();

        assertThat(
                numberSchema.isValid("5"))
                .isFalse();
    }
}
