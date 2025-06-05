/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.antialoureiroferrioindra.eventos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Antía Loureiro Ferrío
 */
public class UbicacionTest {

    private Ubicacion ubicacion;

    @BeforeEach
    void setUp() {
        ubicacion = new Ubicacion("ubi1", "Calle Falsa 123", "Madrid", "28001");
    }

    @Test
    void testConstructorYGetters() {
        assertEquals("ubi1", ubicacion.getId());
        assertEquals("Calle Falsa 123", ubicacion.getDireccion());
        assertEquals("Madrid", ubicacion.getProvincia());
        assertEquals("28001", ubicacion.getCodigoPostal());
    }

    @Test
    void testSetters() {
        ubicacion.setDireccion("Nueva Calle 456");
        ubicacion.setProvincia("Barcelona");
        ubicacion.setCodigoPostal("08001");

        assertEquals("Nueva Calle 456", ubicacion.getDireccion());
        assertEquals("Barcelona", ubicacion.getProvincia());
        assertEquals("08001", ubicacion.getCodigoPostal());
    }

    @Test
    void testToString() {
        String str = ubicacion.toString();
        assertTrue(str.contains("Ubicación"));
        assertTrue(str.contains("ubi1"));
        assertTrue(str.contains("Calle Falsa 123"));
        assertTrue(str.contains("Madrid"));
        assertTrue(str.contains("28001"));
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(ubicacion, ubicacion);
    }

    @Test
    void testEquals_EqualObjects() {
        Ubicacion otra = new Ubicacion("ubi1", "Calle Falsa 123", "Madrid", "28001");
        assertEquals(ubicacion, otra);
    }

    @Test
    void testEquals_DifferentObjects() {
        Ubicacion diferenteId = new Ubicacion("ubi2", "Calle Falsa 123", "Madrid", "28001");
        Ubicacion diferenteDireccion = new Ubicacion("ubi1", "Otra Calle 456", "Madrid", "28001");
        Ubicacion diferenteProvincia = new Ubicacion("ubi1", "Calle Falsa 123", "Barcelona", "28001");
        Ubicacion diferenteCodigo = new Ubicacion("ubi1", "Calle Falsa 123", "Madrid", "08001");

        assertNotEquals(ubicacion, diferenteId);
        assertNotEquals(ubicacion, diferenteDireccion);
        assertNotEquals(ubicacion, diferenteProvincia);
        assertNotEquals(ubicacion, diferenteCodigo);
    }

    @Test
    void testEquals_NullAndOtherTypes() {
        assertNotEquals(ubicacion, null);
        assertNotEquals(ubicacion, "una cadena");
    }

    @Test
    void testHashCode_Consistent() {
        Ubicacion otra = new Ubicacion("ubi1", "Calle Falsa 123", "Madrid", "28001");
        assertEquals(ubicacion.hashCode(), otra.hashCode());
    }
}