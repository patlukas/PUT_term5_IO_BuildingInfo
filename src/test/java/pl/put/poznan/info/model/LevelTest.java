package pl.put.poznan.info.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    Level level;

    @BeforeEach
    public void setUp() {
        level = new Level(1, "Level");
        level.addRoom(new Room(2, "2", 2, 2, 2, 2, 200));
        level.addRoom(new Room(3, "3", 3, 3, 3, 3, 300));
        level.addRoom(new Room(4, "4", 4, 4, 4, 4, 400));
        level.addRoom(new Room(5, "5", 5, 5, 5, 5, 500));
        level.addRoom(new Room(6, "6", 6, 6, 6, 6, 600));
    }

    @Test
    public void test_getId() { assertEquals(level.getId(), 1); }

    @Test
    public void test_getName() { assertEquals(level.getName(), "Level"); }

    @Test
    public void test_getArea() { assertEquals(level.getArea(), 20); }

    @Test
    public void test_getCube() { assertEquals(level.getCube(), 20); }

    @Test
    public void test_getLightning() { assertEquals(level.getLightning(), 1); }

    @Test
    public void test_getHeating() { assertEquals(level.getHeating(), 1); }

    @Test
    public void test_getRent() { assertEquals(level.getRent(), 2000); }

    @Test
    public void test_getArea3() {
        level.addRoom(new Room(7, "", 70, 70, 70, 70, 70));
        assertEquals(level.getArea(), 90);
    }

    @Test
    public void test_getCube2() {
        level.addRoom(new Room(7, "", 70, 70, 70, 70, 70));
        assertEquals(level.getCube(), 90);
    }

    @Test
    public void test_getLightning2() {
        level.addRoom(new Room(7, "", 1, 70, 70, 70, 70));
        assertEquals(level.getLightning(), 4.2857, 0.0001);
    }

    @Test
    public void test_getHeating2() {
        level.addRoom(new Room(7, "", 1, 2, 70, 70, 70));
        assertEquals(level.getHeating(), 4.0909, 0.0001);
    }

    @Test
    public void test_getRent2() {
        level.addRoom(new Room(7, "", 70, 70, 70, 70, 70));
        assertEquals(level.getRent(), 2070);
    }

    @Test
    public void test_getArea2() {
        level.addRoom(new Room(7, "", 100, 70, 70, 70, 70));
        assertEquals(level.getArea(), 120);
    }

}