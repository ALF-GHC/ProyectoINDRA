/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.antialoureiroferrioindra.eventos;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Antía Loureiro Ferrío
 */
public class InscribirseTest {

    private Evento evento;
    private Usuario usuario;
    private LocalDate fecha;
    private Inscribirse inscripcion;

    @BeforeEach
    public void setUp() {
        Categoria categoria = new Categoria("c1", Categoria.Nombre.TALLER, "desc");
        Ubicacion ubicacion = new Ubicacion("u1", "Calle A", "Madrid", "28001");
        Organizador organizador = new Organizador("o1", "Org", "org@correo.com", "1234", Organizador.Tipo.PERSONA);

        evento = new Evento(categoria, "e1", "Evento Prueba", "10:00-10/06/2025", 120,
                Evento.Tipo.PRESENCIAL, ubicacion, organizador);
        usuario = new Usuario("nick1", "Usuario 1", "usu@correo.com", "1234");
        fecha = LocalDate.of(2025, 6, 5);

        inscripcion = new Inscribirse(evento, usuario, fecha);
    }

    @Test
    public void testConstructorYGetters() {
        assertEquals(evento, inscripcion.getEvento());
        assertEquals(usuario, inscripcion.getUsuario());
        assertEquals(fecha, inscripcion.getFecha());
    }

    @Test
    public void testSetFecha() {
        LocalDate nuevaFecha = LocalDate.of(2025, 7, 1);
        inscripcion.setFecha(nuevaFecha);
        assertEquals(nuevaFecha, inscripcion.getFecha());
    }

    @Test
    public void testToString() {
        String result = inscripcion.toString();
        assertTrue(result.contains("Inscripción"));
        assertTrue(result.contains("Evento"));
        assertTrue(result.contains("Usuario"));
        assertTrue(result.contains("Fecha de inscripción: 2025-06-05"));
    }

    @Test
    public void testEqualsTrueAndHashCode() {
        Inscribirse otra = new Inscribirse(evento, usuario, fecha);
        assertEquals(inscripcion, otra);
        assertEquals(inscripcion.hashCode(), otra.hashCode());
    }

    @Test
    public void testEqualsFalsePorEvento() {
        Evento diferente = new Evento(
                new Categoria("c2", Categoria.Nombre.ACTIVIDAD, "otra"), "e2",
                "Otro", "11:00-15/07/2025", 90,
                Evento.Tipo.ONLINE, null,
                new Organizador("o2", "Otro", "otro@correo.com", "pass", Organizador.Tipo.INSTITUCIÓN)
        );
        Inscribirse distinta = new Inscribirse(diferente, usuario, fecha);
        assertNotEquals(inscripcion, distinta);
    }

    @Test
    public void testEqualsFalsePorUsuario() {
        Usuario otroUsuario = new Usuario("nick2", "Otro", "otro@correo.com", "pass");
        Inscribirse distinta = new Inscribirse(evento, otroUsuario, fecha);
        assertNotEquals(inscripcion, distinta);
    }

    @Test
    public void testEqualsFalsePorFecha() {
        Inscribirse distinta = new Inscribirse(evento, usuario, LocalDate.of(2026, 1, 1));
        assertNotEquals(inscripcion, distinta);
    }

    @Test
    public void testEqualsNullYTipoDistinto() {
        assertNotEquals(null, inscripcion);
        assertNotEquals("no es una inscripción", inscripcion);
    }
}