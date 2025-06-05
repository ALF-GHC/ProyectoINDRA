/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.antialoureiroferrioindra.eventos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Antía Loureiro Ferrío
 */
public class CategoriaTest {

    @Test
    public void testConstructorYGetters() {
        Categoria cat = new Categoria("1", Categoria.Nombre.TALLER, "Descripción prueba");
        assertEquals("1", cat.getId());
        assertEquals(Categoria.Nombre.TALLER, cat.getNombre());
        assertEquals("Descripción prueba", cat.getDescripcion());
    }

    @Test
    public void testSetters() {
        Categoria cat = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        cat.setNombre(Categoria.Nombre.CONFERENCIA);
        cat.setDescripcion("Nueva descripción");

        assertEquals(Categoria.Nombre.CONFERENCIA, cat.getNombre());
        assertEquals("Nueva descripción", cat.getDescripcion());
    }

    @Test
    public void testToString() {
        Categoria cat = new Categoria("1", Categoria.Nombre.ACTIVIDAD, "Desc");
        String output = cat.toString();
        assertTrue(output.contains("Categoría"));
        assertTrue(output.contains("Id: 1"));
        assertTrue(output.contains("Nombre: ACTIVIDAD"));
        assertTrue(output.contains("Descripción: Desc"));
    }

    @Test
    public void testEqualsIguales() {
        Categoria c1 = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        Categoria c2 = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        assertEquals(c1, c2);
    }

    @Test
    public void testEqualsDistintoId() {
        Categoria c1 = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        Categoria c2 = new Categoria("2", Categoria.Nombre.TALLER, "Desc");
        assertNotEquals(c1, c2);
    }

    @Test
    public void testEqualsDistintoNombre() {
        Categoria c1 = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        Categoria c2 = new Categoria("1", Categoria.Nombre.CONFERENCIA, "Desc");
        assertNotEquals(c1, c2);
    }

    @Test
    public void testEqualsDistintaDescripcion() {
        Categoria c1 = new Categoria("1", Categoria.Nombre.TALLER, "Desc1");
        Categoria c2 = new Categoria("1", Categoria.Nombre.TALLER, "Desc2");
        assertNotEquals(c1, c2);
    }

    @Test
    public void testEqualsConNull() {
        Categoria c1 = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        assertNotEquals(c1, null);
    }

    @Test
    public void testEqualsConOtroTipo() {
        Categoria c1 = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        assertNotEquals(c1, "no es categoría");
    }

    @Test
    public void testHashCode() {
        Categoria c1 = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        Categoria c2 = new Categoria("1", Categoria.Nombre.TALLER, "Desc");
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void testEnumNombre() {
        for (Categoria.Nombre nombre : Categoria.Nombre.values()) {
            assertNotNull(nombre);
        }
    }
}