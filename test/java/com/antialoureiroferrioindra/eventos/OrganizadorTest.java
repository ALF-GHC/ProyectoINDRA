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
public class OrganizadorTest {

    @Test
    public void testConstructorYGetters() {
        Organizador org = new Organizador("org1", "Mi Empresa", "correo@empresa.com", "pass123", Organizador.Tipo.EMPRESA, "000000000");
        assertEquals("org1", org.getId());
        assertEquals("Mi Empresa", org.getNombre());
        assertEquals("correo@empresa.com", org.getCorreo());
        assertEquals("pass123", org.getContrasenia());
        assertEquals(Organizador.Tipo.EMPRESA, org.getTipo());
        assertEquals("000000000", org.getTelefono());
        
        // Verificamos que la lista de eventos creados esté vacía inicialmente
        assertNotNull(org.getListaEventosCreados());
        assertTrue(org.getListaEventosCreados().isEmpty());
    }

    @Test
    public void testConstructorSinTelefono() {
        Organizador org = new Organizador("org2", "Otra Empresa", "correo@otra.com", "pass456", Organizador.Tipo.INSTITUCIÓN);
        assertEquals("org2", org.getId());
        assertEquals("Otra Empresa", org.getNombre());
        assertEquals("correo@otra.com", org.getCorreo());
        assertEquals("pass456", org.getContrasenia());
        assertEquals(Organizador.Tipo.INSTITUCIÓN, org.getTipo());
        assertNull(org.getTelefono());
    }

    @Test
    public void testSetters() {
        Organizador org = new Organizador("org3", "Nombre", "correo@correo.com", "pass", Organizador.Tipo.PERSONA);
        org.setNombre("Nuevo Nombre");
        org.setCorreo("nuevo@correo.com");
        org.setContrasenia("nuevaPass");
        org.setTipo(Organizador.Tipo.EMPRESA);
        org.setTelefono("123456789");
        
        assertEquals("Nuevo Nombre", org.getNombre());
        assertEquals("nuevo@correo.com", org.getCorreo());
        assertEquals("nuevaPass", org.getContrasenia());
        assertEquals(Organizador.Tipo.EMPRESA, org.getTipo());
        assertEquals("123456789", org.getTelefono());
    }

    @Test
    public void testCrearYModificarEvento() {
        // Crear organizador
        Organizador org = new Organizador("orgTest", "Organizador Test", "test@correo.com", "clave", Organizador.Tipo.EMPRESA);

        // Crear categoría y ubicación
        Categoria categoria = new Categoria("cat1", Categoria.Nombre.CONFERENCIA, "Descripción");
        Ubicacion ubicacion = new Ubicacion("ub1", "Calle 1", "Provincia", "12345");

        // Crear evento
        Evento evento = org.crearEvento(categoria, "ev1", "Evento Original", "2025-12-01", 90, Evento.Tipo.PRESENCIAL, ubicacion);

        // Verificar que el evento está en la lista de eventos creados
        assertTrue(org.getListaEventosCreados().contains(evento));
        assertEquals(1, org.getListaEventosCreados().size());

        // Modificar el evento (válido porque lo creó el organizador)
        Ubicacion nuevaUbicacion = new Ubicacion("ub2", "Nueva Calle", "Provincia", "67890");
        boolean modificado = org.modificarEvento(evento, "Evento Modificado", "2025-12-15", 120, Evento.Tipo.ONLINE, nuevaUbicacion);
        assertTrue(modificado);

        // Verificar cambios
        assertEquals("Evento Modificado", evento.getNombre());
        assertEquals("2025-12-15", evento.getFecha());
        assertEquals(120, evento.getDuracion());
        assertEquals(Evento.Tipo.ONLINE, evento.getTipo());
        assertNull(evento.getUbicacion()); // porque es online

        // Intentar modificar un evento que no pertenece al organizador
        Organizador otroOrg = new Organizador("otro", "Otro", "otro@correo.com", "pass", Organizador.Tipo.PERSONA);
        Evento otroEvento = new Evento(categoria, "ev2", "Ajeno", "2025-11-01", 60, Evento.Tipo.PRESENCIAL, ubicacion, otroOrg);
        boolean noModificado = org.modificarEvento(otroEvento, "Cambio Ilegal", "2025-10-01", 60, Evento.Tipo.ONLINE, null);
        assertFalse(noModificado);
    }

    
    @Test
    public void testEqualsYHashCode() {
        Organizador org1 = new Organizador("org1", "Empresa", "correo@emp.com", "pass", Organizador.Tipo.EMPRESA, "1234");
        Organizador org2 = new Organizador("org1", "Empresa", "correo@emp.com", "pass", Organizador.Tipo.EMPRESA, "1234");
        Organizador org3 = new Organizador("org1", "Empresa", "correo@emp.com", "pass", Organizador.Tipo.EMPRESA, null);
        Organizador org4 = new Organizador("org1", "Empresa", "correo@emp.com", "pass", Organizador.Tipo.EMPRESA, null);

        // Mismo contenido con telefono igual
        assertEquals(org1, org2);
        assertEquals(org1.hashCode(), org2.hashCode());

        // Ambos sin telefono (null)
        assertEquals(org3, org4);
        assertEquals(org3.hashCode(), org4.hashCode());

        // Uno con telefono y otro sin telefono
        assertNotEquals(org1, org3);
        assertNotEquals(org1.hashCode(), org3.hashCode());

        // Cambiando tipo
        Organizador org5 = new Organizador("org1", "Empresa", "correo@emp.com", "pass", Organizador.Tipo.PERSONA, "1234");
        assertNotEquals(org1, org5);
    }

    @Test
    public void testToString() {
        Organizador org = new Organizador("orgX", "NombreX", "correoX@empresa.com", "passX", Organizador.Tipo.PERSONA, "999999999");
        String esperado = "Organizador" + 
                         "\n\t- Id: orgX" +
                         "\n\t- Nombre: NombreX" +
                         "\n\t- Correo: correoX@empresa.com" +
                         "\n\t- Contraseña: passX" +
                         "\n\t- Tipo: PERSONA" +
                         "\n\t- Teléfono: 999999999";
        assertEquals(esperado, org.toString());
    }
}