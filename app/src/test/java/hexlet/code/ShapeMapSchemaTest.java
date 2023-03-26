package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public final class ShapeMapSchemaTest {
    private Validator v;
    private MapSchema mapSchema;
    private Map<String, Object> testMap;
    private final Map<String, Object> emptyMap = new HashMap<>();
    private Map<String, BaseSchema> schemas;

    @BeforeEach
    public void setup() {
        v = new Validator();
        mapSchema = v.map();
        testMap = new HashMap<>();
        schemas = new HashMap<>();
    }

    public void fillSchemasMap() {
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schemas.put("skill", v.number().range(0, 100));
    }

    @Test
    public void shapeMapSchemaTestNullSchemasCase() {
        testMap.put("name", "Michael");
        testMap.put("age", 20);
        testMap.put("skill", null);
        mapSchema.shape(null);

        assertThat(
                mapSchema.isValid(null))
                .isTrue();

        assertThat(
                mapSchema.isValid(testMap))
                .isTrue();
    }

    @Test
    public void shapeMapSchemaTestEmptySchemasCase() {
        testMap.put("name", "Michael");
        testMap.put("age", 20);
        testMap.put("skill", null);
        mapSchema.shape(new HashMap<>());

        assertThat(
                mapSchema.isValid(null))
                .isTrue();

        assertThat(
                mapSchema.isValid(emptyMap))
                .isTrue();

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
    public void shapeMapSchemaTestBasicCase() {
        testMap.put("name", "Michael");
        testMap.put("age", 20);
        testMap.put("skill", null);
        fillSchemasMap();
        mapSchema.shape(schemas);

        assertThat(
                mapSchema.isValid(null))
                .isTrue();

        assertThat(
                mapSchema.isValid(testMap))
                .isTrue();
    }

    @Test
    public void shapeMapSchemaTestWrongCase() {
        testMap.put("name", "");
        testMap.put("age", 20);
        testMap.put("skill", 50);
        fillSchemasMap();
        mapSchema.shape(schemas);

        assertThat(
                mapSchema.isValid(testMap))
                .isFalse();

        testMap.clear();
        testMap.put("name", "Michael");
        testMap.put("age", -20);
        testMap.put("skill", 50);

        assertThat(
                mapSchema.isValid(testMap))
                .isFalse();

        testMap.clear();
        testMap.put("name", "Michael");
        testMap.put("age", 20);
        testMap.put("skill", 150);

        assertThat(
                mapSchema.isValid(testMap))
                .isFalse();
    }
}
