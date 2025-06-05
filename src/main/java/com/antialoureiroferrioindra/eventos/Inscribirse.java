/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.antialoureiroferrioindra.eventos;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Antía Loureiro Ferrío
 */
public class Inscribirse {
    //Propiedades.
    //Se han añadido la clave primaria de inscribirse, la cual está formada por
    //el id de evento y el nick del usuario.
    private final Evento evento;
    private final Usuario usuario;
    private LocalDate fecha;
    
    
    //Constructor.
    public Inscribirse(Evento evento, Usuario usuario, LocalDate fecha)
    {
        this.evento = evento;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    
    //Getters y Setters de las propiedades.
    public Evento getEvento()
    {
        return evento;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }
    
    //El evento y el usuario no se podrán modificar, al formar la clave primaria.

    public LocalDate getFecha()
    {
        return fecha;
    }

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }
    
        
    //Método para imprimir correctamente la información de las inscripciones.
    @Override
    public String toString()
    {
        return "Inscripción" + "\n\t- " + evento + "\n\t- " + usuario + "\n\t- Fecha de inscripción: " + fecha;
    }
    
    
    //Método para comprobar si varias inscripciones son iguales.
    @Override
    public boolean equals(Object obj)
    {
        boolean igual = false;
        if (obj != null)
        {
            if (obj instanceof Inscribirse ins)
            {
                if (this.evento.equals(ins.getEvento()) && this.usuario.equals(ins.getUsuario()) && this.fecha.equals(ins.getFecha()))
                {
                    /*Si el objeto tiene contenido, es una inscripción y todos sus
                    atributos son iguales, entonces las inscripciones son iguales.*/
                    igual = true;
                }
            }
        }
        return igual;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.evento);
        hash = 53 * hash + Objects.hashCode(this.usuario);
        hash = 53 * hash + Objects.hashCode(this.fecha);
        return hash;
    }
}
