package pl.put.poznan.info.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.put.poznan.info.exceptions.JsonInputException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BuildingTest {
    Level levelMock1 = Mockito.mock(Level.class);
    Level levelMock2 = Mockito.mock(Level.class);
    Building building;

    @BeforeEach
    public void setUp() {
        building = new Building(1);
        building.addLevel(levelMock1);
        building.addLevel(levelMock2);
    }

    @Test
    public void test_getArea() {
        when(levelMock1.getArea()).thenReturn(20.0F);
        when(levelMock2.getArea()).thenReturn(2.0F);
        assertEquals(building.getArea(), 22);
    }

    @Test
    public void test_getCube() {
        when(levelMock1.getCube()).thenReturn(20.0F);
        when(levelMock2.getCube()).thenReturn(2.0F);
        assertEquals(building.getCube(), 22);
    }

    @Test
    public void test_getLightning() {
        when(levelMock1.getLightning()).thenReturn(20.0F);
        when(levelMock2.getLightning()).thenReturn(2.0F);
        assertEquals(building.getLightning(), 11);
    }

    @Test
    public void test_getHeating() {
        when(levelMock1.getHeating()).thenReturn(20.0F);
        when(levelMock2.getHeating()).thenReturn(2.0F);
        assertEquals(building.getHeating(), 11);
    }

    @Test
    public void test_getRent() {
        when(levelMock1.getRent()).thenReturn(100.0F);
        when(levelMock2.getRent()).thenReturn(200.0F);
        assertEquals(building.getRent(), 300);
    }

    @Test
    public void test_getAreaById() throws JsonInputException {
        when(levelMock1.getId()).thenReturn(1);
        when(levelMock2.getId()).thenReturn(2);
        when(levelMock1.getArea()).thenReturn(11.0F);
        when(levelMock2.getArea()).thenReturn(22.0F);
        assertEquals(building.getAreaById(2), 22);
    }

    @Test
    public void test_getCubeById() throws JsonInputException {
        when(levelMock1.getId()).thenReturn(1);
        when(levelMock2.getId()).thenReturn(2);
        when(levelMock1.getCube()).thenReturn(11.0F);
        when(levelMock2.getCube()).thenReturn(22.0F);
        assertEquals(building.getCubeById(2), 22);
    }

    @Test
    public void test_getLightById() throws JsonInputException {
        when(levelMock1.getId()).thenReturn(1);
        when(levelMock2.getId()).thenReturn(2);
        when(levelMock1.getLightning()).thenReturn(11.0F);
        when(levelMock2.getLightning()).thenReturn(22.0F);
        assertEquals(building.getLightById(2), 22);
    }

    @Test
    public void test_getHeatingById() throws JsonInputException {
        when(levelMock1.getId()).thenReturn(1);
        when(levelMock2.getId()).thenReturn(2);
        when(levelMock1.getHeating()).thenReturn(11.0F);
        when(levelMock2.getHeating()).thenReturn(22.0F);
        assertEquals(building.getHeatingById(2), 22);
    }

    @Test
    public void test_getRentById() throws JsonInputException {
        when(levelMock1.getId()).thenReturn(1);
        when(levelMock2.getId()).thenReturn(2);
        when(levelMock1.getRent()).thenReturn(11.0F);
        when(levelMock2.getRent()).thenReturn(22.0F);
        assertEquals(building.getRentById(2), 22);
    }
}