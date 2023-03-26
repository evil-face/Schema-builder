### Hexlet tests and linter status: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;Codeclimate status:   
[![Actions Status](https://github.com/evil-face/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/evil-face/java-project-78/actions)
[![project-check](https://github.com/evil-face/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/evil-face/java-project-78/actions/workflows/main.yml)
&nbsp; &nbsp; &nbsp; &nbsp;[![Maintainability](https://api.codeclimate.com/v1/badges/9c6ba5487b888c6e5e78/maintainability)](https://codeclimate.com/github/evil-face/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/9c6ba5487b888c6e5e78/test_coverage)](https://codeclimate.com/github/evil-face/java-project-78/test_coverage)

Data Validator
-----------
This is a small schema builder to do some basic value validation. You can set rules in a fluent-style way. Supported value types:

**String**
- `required()` — must be non-null and not empty
- `minLength(i)` — must be longer that this
- `contains(s)` — must contain this substring

*Use it like this*: 
```
Validator v = new Validator();
StringSchema schema = v.string().required().contains("Hel");
schema.isValid("Hello"); // true
schema.isValid(""); // false
```

**Integer**
- `required()` — must be any integer number
- `positive()` — must be greater than 0
- `range(i, j)` — must be inside this range

*Use it like this*: 
```
Validator v = new Validator();
NumberSchema schema = v.number().required().range(0, 100);
schema.isValid(50); // true
schema.isValid(-10); // false
```

**Map**
- `required()` — must be any Map instance
- `sizeof(i)` — must be this size

*Use it like this*: 
```
Validator v = new Validator();
MapSchema schema = v.map().required().sizeof(100);
schema.isValid(null); // false
schema.isValid(bigMapHere); // true
```

You can even create a Map with schemas to validate values of your maps:
```
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human = new HashMap<>();
human.put("name", "Michael");
human.put("age", 50);
schema.isValid(human); // true
```
