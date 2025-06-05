/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.antialoureiroferrioindra.eventos;

import java.util.Objects;

/**
 *
 * @author Antía Loureiro Ferrío
 */
public class Categoria {
    //Propiedades.
    //Se ha añadido el id único para identificar a la categoría de forma única.
    private final String id;
    public enum Nombre
    {
        TALLER, CONFERENCIA, ACTIVIDAD
    };
    //Para usar el enumerado, se ha creado un atributo de nombre de tipo Nombre.
    private Nombre nombre;
    //También se ha añadido una descripción sobre la
    private String descripcion;
    
    
    //Constructor.
    public Categoria(String id, Nombre nombre, String descripcion)
    {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    
    //Getters y Setters de las propiedades.
    public String getId()
    {
        return id;
    }

    //El id no tendrá setter al ser un identificador único.

    public Nombre getNombre()
    {
        return nombre;
    }

    public void setNombre(Nombre nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
   
    
    //Método para imprimir correctamente las categorías.
    @Override
    public String toString()
    {
        return "Categoría" + "\n\t- Id: " + id + "\n\t- Nombre: " + nombre + "\n\t- Descripción: " + descripcion;
    }
    
    
    //Método para comprobar si varias categorías son iguales.
    @Override
    public boolean equals(Object obj)
    {
        boolean igual = false;
        if (obj != null)
        {
            if (obj instanceof Categoria cat)
            {
                if (this.getId().equals(cat.getId()) && this.nombre.equals(cat.getNombre()) && this.descripcion.equals(cat.getDescripcion()))
                {
                    /*Si el objeto tiene contenido, es una categoría y todos sus
                    atributos son iguales, entonces las categorías son iguales.*/
                    igual = true;
                }
            }
        }
        return igual;
    }

    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }
}
