package pl.put.poznan.info.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    Room room;
    @BeforeEach
    public void setUp() {
        room = new Room(1, "2", 3, 4, 5, 6, 777);
    }

    @Test
    public void test_getId() { assertEquals(room.getId(), 1); }

    @Test
    public void test_getName() { assertEquals(room.getName(), "2"); }

    @Test
    public void test_getArea() { assertEquals(room.getArea(), 3); }

    @Test
    public void test_getCube() { assertEquals(room.getCube(), 4); }

    @Test
    public void test_getLightning() { assertEquals(room.getLightning(), 6); }

    @Test
    public void test_getHeating() { assertEquals(room.getHeating(), 5); }

    @Test
    public void test_getRent() { assertEquals(room.getRent(), 777); }

}