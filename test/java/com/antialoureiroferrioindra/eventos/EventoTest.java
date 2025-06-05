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
public class EventoTest {

    private Categoria categoria;
    private Organizador organizador;
    private Ubicacion ubicacion;
    private Usuario usuario1, usuario2;

    private Evento eventoPresencial;
    private Evento eventoOnline;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria("1", Categoria.Nombre.CONFERENCIA, "Eventos de tipo conferencia");

        organizador = new Organizador("org1", "Org Uno", "org@correo.com", "1234", Organizador.Tipo.PERSONA, "123456789");
        ubicacion = new Ubicacion("ub1", "Calle Falsa 123", "ProvinciaX", "28080");
        usuario1 = new Usuario("user1", "Usuario Uno", "user1@mail.com", "pass1");
        usuario2 = new Usuario("user2", "Usuario Dos", "user2@mail.com", "pass2");

        eventoPresencial = new Evento(categoria, "ev1", "Evento Presencial", "2025-12-01", 120, Evento.Tipo.PRESENCIAL, ubicacion, organizador);
        eventoOnline = new Evento(categoria, "ev2", "Evento Online", "2025-11-01", 90, Evento.Tipo.ONLINE, ubicacion, organizador);
    }

    @Test
    public void testConstructorYGetters() {
        assertEquals(categoria, eventoPresencial.getCategoria());
        assertEquals("ev1", eventoPresencial.getIdEvento());
        assertEquals("Evento Presencial", eventoPresencial.getNombre());
        assertEquals("2025-12-01", eventoPresencial.getFecha());
        assertEquals(120, eventoPresencial.getDuracion());
        assertEquals(Evento.Tipo.PRESENCIAL, eventoPresencial.getTipo());
        assertEquals(ubicacion, eventoPresencial.getUbicacion());
        assertEquals(organizador, eventoPresencial.getOrganizador());
        assertEquals(Evento.Estado.ACTIVO, eventoPresencial.getEstado());

        assertEquals(categoria, eventoOnline.getCategoria());
        assertEquals("ev2", eventoOnline.getIdEvento());
        assertEquals("Evento Online", eventoOnline.getNombre());
        assertEquals("2025-11-01", eventoOnline.getFecha());
        assertEquals(90, eventoOnline.getDuracion());
        assertEquals(Evento.Tipo.ONLINE, eventoOnline.getTipo());
        assertNull(eventoOnline.getUbicacion());
        assertEquals(organizador, eventoOnline.getOrganizador());
        assertEquals(Evento.Estado.ACTIVO, eventoOnline.getEstado());
    }

    @Test
    public void testSetters() {
        eventoPresencial.setNombre("Nuevo Nombre");
        assertEquals("Nuevo Nombre", eventoPresencial.getNombre());

        eventoPresencial.setFecha("2025-12-15");
        assertEquals("2025-12-15", eventoPresencial.getFecha());

        eventoPresencial.setDuracion(150);
        assertEquals(150, eventoPresencial.getDuracion());

        eventoOnline.setTipo(Evento.Tipo.PRESENCIAL);
        eventoOnline.setUbicacion(ubicacion);
        assertEquals(Evento.Tipo.PRESENCIAL, eventoOnline.getTipo());
        assertEquals(ubicacion, eventoOnline.getUbicacion());

        eventoPresencial.setTipo(Evento.Tipo.ONLINE);
        assertEquals(Evento.Tipo.ONLINE, eventoPresencial.getTipo());
        assertNull(eventoPresencial.getUbicacion());
    }

    @Test
    public void testSetUbicacion() {
        Ubicacion nuevaUbicacion = new Ubicacion("ub2", "Nueva Calle 45", "ProvinciaY", "28090");

        // Evento presencial: puede cambiar ubicación
        eventoPresencial.setUbicacion(nuevaUbicacion);
        assertEquals(nuevaUbicacion, eventoPresencial.getUbicacion());

        // Evento online: ubicación siempre null aunque intente cambiarse
        eventoOnline.setUbicacion(nuevaUbicacion);
        assertNull(eventoOnline.getUbicacion());

        // Cambiar tipo online a presencial y luego set ubicacion
        eventoOnline.setTipo(Evento.Tipo.PRESENCIAL);
        eventoOnline.setUbicacion(nuevaUbicacion);
        assertEquals(nuevaUbicacion, eventoOnline.getUbicacion());
    }

    @Test
    public void testSetOrganizador() {
        Organizador nuevoOrganizador = new Organizador("org2", "Org Dos", "org2@correo.com", "4321", Organizador.Tipo.EMPRESA, "987654321");
        eventoPresencial.setOrganizador(nuevoOrganizador);
        assertEquals(nuevoOrganizador, eventoPresencial.getOrganizador());
    }

     @Test
    public void testEquals_objNull() {
        assertFalse(eventoPresencial.equals(null));
    }

    @Test
    public void testEquals_objOtroTipo() {
        assertFalse(eventoPresencial.equals("no es un evento"));
    }

    @Test
    public void testEquals_diferenteNombre() {
        Evento otro = new Evento(categoria, "ev1", "Otro Nombre", "2025-12-01", 120, Evento.Tipo.PRESENCIAL, ubicacion, organizador);
        assertFalse(eventoPresencial.equals(otro));
    }

    @Test
    public void testEquals_ubicacionesAmbasNull() {
        Evento online1 = new Evento(categoria, "ev2", "Evento Online", "2025-11-01", 90, Evento.Tipo.ONLINE, null, organizador);
        Evento online2 = new Evento(categoria, "ev2", "Evento Online", "2025-11-01", 90, Evento.Tipo.ONLINE, ubicacion, organizador);
        assertTrue(online1.equals(online2));
    }

    @Test
    public void testEquals_unaUbicacionNull() {
        Evento presencial = new Evento(categoria, "ev2", "Evento Online", "2025-11-01", 90, Evento.Tipo.PRESENCIAL, ubicacion, organizador);
        Evento online = new Evento(categoria, "ev2", "Evento Online", "2025-11-01", 90, Evento.Tipo.ONLINE, ubicacion, organizador);
        assertFalse(presencial.equals(online));
    }

    @Test
    public void testEquals_ubicacionesDistintas() {
        Ubicacion otraUbicacion = new Ubicacion("ub2", "Otra calle", "OtraProvincia", "00000");
        Evento diferenteUbicacion = new Evento(categoria, "ev1", "Evento Presencial", "2025-12-01", 120, Evento.Tipo.PRESENCIAL, otraUbicacion, organizador);
        assertFalse(eventoPresencial.equals(diferenteUbicacion));
    }

    @Test
    public void testEquals_todoIgual() {
        Evento copia = new Evento(categoria, "ev1", "Evento Presencial", "2025-12-01", 120, Evento.Tipo.PRESENCIAL, ubicacion, organizador);
        assertTrue(eventoPresencial.equals(copia));
    }
    
    @Test
    public void testRegistrarYQuitarUsuario() {
        eventoPresencial.registrarUsu(usuario1);
        assertTrue(eventoPresencial.getListaUsuariosInscritos().contains(usuario1));
        eventoPresencial.registrarUsu(usuario1);
        assertEquals(1, eventoPresencial.getListaUsuariosInscritos().size());

        eventoPresencial.registrarUsu(usuario2);
        assertTrue(eventoPresencial.getListaUsuariosInscritos().contains(usuario2));
        assertEquals(2, eventoPresencial.getListaUsuariosInscritos().size());

        eventoPresencial.quitarUsu(usuario1);
        assertFalse(eventoPresencial.getListaUsuariosInscritos().contains(usuario1));
        assertEquals(1, eventoPresencial.getListaUsuariosInscritos().size());
    }

    @Test
    public void testCancelarYReactivarEvento() {
        // Inscripción desde Usuario para mantener listas sincronizadas
        usuario1.inscribirEvento(eventoPresencial);
        usuario2.inscribirEvento(eventoPresencial);

        assertTrue(eventoPresencial.getListaUsuariosInscritos().contains(usuario1));
        assertTrue(eventoPresencial.getListaUsuariosInscritos().contains(usuario2));

        eventoPresencial.cancelarEvento();
        assertEquals(Evento.Estado.CANCELADO, eventoPresencial.getEstado());

        // Verificar que los usuarios ya no están inscritos (listas sincronizadas)
        assertFalse(eventoPresencial.getListaUsuariosInscritos().contains(usuario1));
        assertFalse(eventoPresencial.getListaUsuariosInscritos().contains(usuario2));
        assertEquals(0, eventoPresencial.getListaUsuariosInscritos().size());

        eventoPresencial.reactivarEvento();
        assertEquals(Evento.Estado.ACTIVO, eventoPresencial.getEstado());
    }


    @Test
    public void testToString() {
        String toString = eventoPresencial.toString();
        assertTrue(toString.contains("Evento"));
        assertTrue(toString.contains("Categoría"));
        assertTrue(toString.contains("Id_Evento: ev1"));
        assertTrue(toString.contains("Nombre: Evento Presencial"));
        assertTrue(toString.contains("Tipo: PRESENCIAL"));
        assertTrue(toString.contains("Estado: ACTIVO"));
    }
}