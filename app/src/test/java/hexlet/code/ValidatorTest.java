package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public final class ValidatorTest {
    private Validator v;
    private Map<String, Object> testMap;
    private final Map<String, String> emptyMap = new HashMap<>();
    private Map<String, BaseSchema> schemas;

    @BeforeEach
    public void setup() {
        v = new Validator();
        testMap = new HashMap<>();
        schemas = new HashMap<>();
    }

    public void fillSchemasMap() {
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schemas.put("skill", v.number().range(0, 100));
    }

    @Test
    public void stringSchemaTestBasicCase() {
        StringSchema stringSchema = v.string();

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
        StringSchema stringSchema = v.string().required();

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
    public void stringSchemaTestComplexCase() {
        StringSchema stringSchema = v.string();

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

        // added 'required'
        stringSchema.required();

        assertThat(
                stringSchema.isValid("PotatoNanoPotato"))
                .isTrue();

        assertThat(
                stringSchema.isValid("PotatoBigPotato"))
                .isFalse();

        assertThat(
                stringSchema.isValid("tiny"))
                .isFalse();

        assertThat(
                stringSchema.isValid(null))
                .isFalse();

        assertThat(
                stringSchema.isValid(0))
                .isFalse();
    }

    @Test
    public void numberSchemaTestBasicCase() {
        NumberSchema numberSchema = v.number();

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
        NumberSchema numberSchema = v.number().required();

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
    public void numberSchemaTestComplexCase() {
        NumberSchema numberSchema = v.number();

        // without 'required'
        numberSchema.positive().range(5, 10);

        assertThat(
                numberSchema.isValid(7))
                .isTrue();

        assertThat(
                numberSchema.isValid(-10))
                .isFalse();

        assertThat(
                numberSchema.isValid(100))
                .isFalse();

        assertThat(
                numberSchema.isValid(null))
                .isTrue();

        // added 'required'
        numberSchema.required();

        assertThat(
                numberSchema.isValid(7))
                .isTrue();

        assertThat(
                numberSchema.isValid(100))
                .isFalse();

        assertThat(
                numberSchema.isValid(-100))
                .isFalse();

        assertThat(
                numberSchema.isValid(null))
                .isFalse();

        assertThat(
                numberSchema.isValid("5"))
                .isFalse();
    }

    @Test
    public void mapSchemaTestBasicCase() {
        MapSchema mapSchema = v.map();

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
        MapSchema mapSchema = v.map().required();

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
        MapSchema mapSchema = v.map().required().sizeof(2);

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

    @Test
    public void shapeMapSchemaTestBasicCase() {
        MapSchema mapSchema = v.map();

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
        MapSchema mapSchema = v.map();

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
