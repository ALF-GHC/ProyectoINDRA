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
public class Ubicacion {
    //Propiedades.
    //Se ha añadido el id único para identificar a la ubicación de forma única.
    private final String id;
    private String direccion;
    private String provincia;
    private String codigoPostal;
    
    
    //Constructor.
    public Ubicacion(String id, String direccion, String provincia, String codigoPostal)
    {
        this.id = id;
        this.direccion = direccion;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }
    
    
    //Getters y Setters de las propiedades.
    public String getId()
    {
        return id;
    }

    //El id no tendrá setter al ser un identificador único.

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getProvincia()
    {
        return provincia;
    }

    public void setProvincia(String provincia)
    {
        this.provincia = provincia;
    }

    public String getCodigoPostal()
    {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }
    
    
    //Método para imprimir correctamente las ubicaciones.
    @Override
    public String toString()
    {
        return "Ubicación" + "\n\t- Id: " + id + "\n\t- Dirección: " + direccion + "\n\t- Provincia: " + provincia + "\n\t- Código Postal: " + codigoPostal;
    }
    
    //Método para comprobar si varias ubicaciones son iguales.
    @Override
    public boolean equals(Object obj)
    {
        boolean igual = false;
        if (obj != null)
        {
            if (obj instanceof Ubicacion ubi)
            {
                if (this.getId() == ubi.getId() && this.direccion.equals(ubi.getDireccion()) && this.provincia.equals(ubi.getProvincia()) && this.codigoPostal.equals(ubi.getCodigoPostal()))
                {
                    /*Si el objeto tiene contenido, es una ubicación y todos sus
                    atributos son iguales, entonces las ubicaciones son iguales.*/
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
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.direccion);
        hash = 89 * hash + Objects.hashCode(this.provincia);
        hash = 89 * hash + Objects.hashCode(this.codigoPostal);
        return hash;
    }
}
