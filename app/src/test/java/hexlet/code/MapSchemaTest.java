package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public final class MapSchemaTest {
    private MapSchema mapSchema;
    private Map<String, String> testMap;
    private final Map<String, String> emptyMap = new HashMap<>();

    @BeforeEach
    public void setup() {
        Validator v = new Validator();
        mapSchema = v.map();
        testMap = new HashMap<>();
    }

    @Test
    public void mapSchemaTestBasicCase() {
        assertThat(
                mapSchema.isValid(null))
                .isTrue();

        assertThat(
                mapSchema.isValid(emptyMap))
                .isTrue();

        testMap.put("one", "keyOne");
        assertThat(
                mapSchema.isValid(testMap))
                .isTrue();

        assertThat(
                mapSchema.isValid(-100))
                .isTrue();

        assertThat(
                mapSchema.isValid("100"))
                .isTrue();
    }

    @Test
    public void mapSchemaTestRequiredCase() {
        mapSchema.required();

        assertThat(
                mapSchema.isValid(null))
                .isFalse();

        assertThat(
                mapSchema.isValid(emptyMap))
                .isTrue();

        testMap.put("one", "keyOne");
        assertThat(
                mapSchema.isValid(testMap))
                .isTrue();

        assertThat(
                mapSchema.isValid(-100))
                .isFalse();

        assertThat(
                mapSchema.isValid("100"))
                .isFalse();
    }

    @Test
    public void mapSchemaTestSizeOfCase() {
        mapSchema.required().sizeOf(2);

        assertThat(
                mapSchema.isValid(null))
                .isFalse();

        assertThat(
                mapSchema.isValid(emptyMap))
                .isFalse();

        testMap.put("one", "keyOne");
        assertThat(
                mapSchema.isValid(testMap))
                .isFalse();

        testMap.put("two", "keyTwo");
        assertThat(
                mapSchema.isValid(testMap))
                .isTrue();

        testMap.put("three", "keyThree");
        assertThat(
                mapSchema.isValid(testMap))
                .isFalse();

        assertThat(
                mapSchema.isValid(-100))
                .isFalse();

        assertThat(
                mapSchema.isValid("100"))
                .isFalse();
    }
}
