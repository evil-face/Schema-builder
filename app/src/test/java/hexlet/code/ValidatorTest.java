package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
    @Test
    public void stringSchemaTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(
                schema.isValid(""))
                .isTrue();

        schema = v.string();
        assertThat(
                schema.isValid(null))
                .isTrue();

        schema = v.string();
        assertThat(
                schema.required().isValid("Yooo"))
                .isTrue();

        schema = v.string();
        assertThat(
                schema.required().isValid(""))
                .isFalse();

        schema = v.string();
        assertThat(
                schema.minLength(3).isValid("Yooo"))
                .isTrue();

        schema = v.string();
        assertThat(
                schema.contains("abo").isValid("Bibaboba"))
                .isTrue();

        schema = v.string();
        assertThat(
                schema.required().contains("nano").minLength(5).isValid("Potatonanopotato"))
                .isTrue();

        schema = v.string();
        assertThat(
                schema.required().contains("rwqwe").minLength(5).isValid("Potatonanopotato"))
                .isFalse();
    }
}
