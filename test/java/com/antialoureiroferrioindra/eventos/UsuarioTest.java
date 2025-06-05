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
public class UsuarioTest {

    private Usuario usuario;
    private Evento evento;
    private Categoria categoria;
    private Organizador organizador;
    private Ubicacion ubicacion;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("nick123", "Nombre Usuario", "usuario@correo.com", "pass123");

        categoria = new Categoria("c1", Categoria.Nombre.CONFERENCIA, "Descripción");
        organizador = new Organizador("o1", "Organizador", "org@correo.com", "pass", Organizador.Tipo.EMPRESA);
        ubicacion = new Ubicacion("u1", "Calle 1", "Madrid", "28001");

        evento = new Evento(categoria, "e1", "Evento Test", "12:00-01/01/2025", 60,
                Evento.Tipo.PRESENCIAL, ubicacion, organizador);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("nick123", usuario.getNick());
        assertEquals("Nombre Usuario", usuario.getNombre());
        assertEquals("usuario@correo.com", usuario.getCorreo());
        assertEquals("pass123", usuario.getContrasenia());
        assertTrue(usuario.getListaEventosInscrito().isEmpty());
    }

    @Test
    public void testSetters() {
        usuario.setNombre("Nuevo Nombre");
        usuario.setCorreo("nuevo@correo.com");
        usuario.setContrasenia("nuevaPass");

        assertEquals("Nuevo Nombre", usuario.getNombre());
        assertEquals("nuevo@correo.com", usuario.getCorreo());
        assertEquals("nuevaPass", usuario.getContrasenia());
    }

    @Test
    public void testInscribirEventoExitoso() {
        boolean result = usuario.inscribirEvento(evento);

        assertTrue(result);
        assertTrue(usuario.getListaEventosInscrito().contains(evento));
        assertTrue(evento.getListaUsuariosInscritos().contains(usuario));
    }

    @Test
    public void testInscribirEventoYaInscrito() {
        usuario.inscribirEvento(evento);
        boolean result = usuario.inscribirEvento(evento); // intentar inscribir otra vez

        assertFalse(result);
    }

    @Test
    public void testCancelarInscripcionExitosa() {
        usuario.inscribirEvento(evento);
        boolean result = usuario.cancelarInscripcion(evento);

        assertTrue(result);
        assertFalse(usuario.getListaEventosInscrito().contains(evento));
        assertFalse(evento.getListaUsuariosInscritos().contains(usuario));
    }

    @Test
    public void testCancelarInscripcionNoInscrito() {
        boolean result = usuario.cancelarInscripcion(evento);
        assertFalse(result);
    }

    @Test
    public void testToString() {
        String expected = "Usuario" +
                "\n\t- Nick: nick123" +
                "\n\t- Nombre: Nombre Usuario" +
                "\n\t- Correo: usuario@correo.com" +
                "\n\t- Contraseña: pass123";

        assertEquals(expected, usuario.toString());
    }

    @Test
    public void testEqualsYHashCode() {
        Usuario usuario2 = new Usuario("nick123", "Nombre Usuario", "usuario@correo.com", "pass123");
        assertTrue(usuario.equals(usuario2));
        assertEquals(usuario.hashCode(), usuario2.hashCode());

        Usuario distinto = new Usuario("nick999", "Otro", "otro@correo.com", "123");
        assertFalse(usuario.equals(distinto));
    }

    @Test
    public void testEqualsNullYDistintoTipo() {
        assertFalse(usuario.equals(null));
        assertFalse(usuario.equals("no es un usuario"));
    }
}