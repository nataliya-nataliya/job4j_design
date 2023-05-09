package ru.job4j.template;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Disabled
class TemplateGeneratorTest {
    @Test
    public void whenKeysInTemplateMatchKeysInMap() {
        Generator generator = new TemplateGenerator();
        String template = "This vegetable is called a ${nameVegetable}";
        Map<String, String> map = new HashMap<>();
        map.put("nameVegetable", "tomato");
        String expected = "This vegetable is called a tomato";
        String actual = generator.produce(template, map);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void whenTemplateHasKeysButMapDoesNotHaveTheseKeys() {
        Generator generator = new TemplateGenerator();
        String template = "This vegetable is called a ${nameVegetable}, it has a ${color} color.";
        Map<String, String> map = new HashMap<>();
        map.put("nameVegetable", "tomato");
        Assertions.assertThrows(IllegalArgumentException.class, () -> generator.produce(template, map));
    }

    @Test
    public void whenMapHasKeysButTemplateDoesNotHaveTheseKeys() {
        Generator generator = new TemplateGenerator();
        String template = "This vegetable is called a ${nameVegetable}";
        Map<String, String> map = new HashMap<>();
        map.put("nameVegetable", "tomato");
        map.put("color", "red");
        Assertions.assertThrows(IllegalArgumentException.class, () -> generator.produce(template, map));
    }
}
