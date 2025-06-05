/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.antialoureiroferrioindra.eventos;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Antía Loureiro Ferrío
 */
public class Organizador {
    //Propiedades.
    //Se ha añadido el id único para identificar al organizador de forma única.
    private final String id;
    private String nombre;
    //Se escribe "correo" en lugar de "correo-e" porque no se permiten guiones
    //medios y tampoco será necesario añadirle la "e".
    private String correo;
    //Se escribe "contrasenia" en lugar de "contraseña" por los problemas que
    //puede causar el caracter "ñ".
    private String contrasenia;
    public enum Tipo
    {
        EMPRESA, INSTITUCIÓN, PERSONA
    }
    //Para usar el enumerado, se ha creado un atributo de tipo Tipo.
    private Tipo tipo;
    //El "telefono" es una cadena de caracteres porque es posible indicar el
    //prefijo del mismo.
    private String telefono;
    //Esta lista no se puede modificar (setter) ni pasar al constructor, ya que
    //es un control interno de los eventos creados por un organizador.
    private ArrayList <Evento> listaEventosCreados = new ArrayList<>();
    
    
    //Constructores.
    //Recibe todos los datos del organizador.
    public Organizador(String id, String nombre, String correo, String contrasenia, Tipo tipo, String telefono)
    {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
        this.telefono = telefono;
    }

    //Recibe los datos del organizador, salvo el teléfono, ya que, al ser
    //opcional, puede no proporciarlo.
    public Organizador(String id, String nombre, String correo, String contrasenia, Tipo tipo)
    {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }
    
    
    //Getters y Setters de las propiedades.
    public String getId()
    {
        return id;
    }

    //El id no tendrá setter al ser un identificador único.

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getContrasenia()
    {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    public Tipo getTipo()
    {
        return tipo;
    }

    public void setTipo(Tipo tipo)
    {
        this.tipo = tipo;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
    
    public ArrayList<Evento> getListaEventosCreados()
    {
        return listaEventosCreados;
    }

    
    //Responsabilidades: crear y modificar eventos.
    //No se dispone de la responsabilidad de cancelar el evento, de forma que
    //se establecerá en el evento de forma directa.
    public Evento crearEvento(Categoria categoria, String idEvento, String nombre, String fecha, int duracion, Evento.Tipo tipo, Ubicacion ubicacion)
    {
        //Creación del evento con los datos pasados por parámetro asignándole el
        //organizador actual.
        Evento evento = new Evento(categoria, idEvento, nombre, fecha, duracion, tipo, ubicacion, this);
        //Añadir a la lista de eventos creados por un determinado organizador
        //el evento que se acaba de crear.
        listaEventosCreados.add(evento);
        //Se devuelve el evento creado.
        return evento;
    }

    //La categoría y el id del evento no se pueden modificar al formar parte de
    //su clave pimaria.
    //Tener en cuenta que un organizador solo puede modificar eventos que haya
    //creado previamente, por ello no se le pasa por parámetro al organizador.
    public boolean modificarEvento(Evento evento, String nuevoNombre, String nuevaFecha, int nuevaDuracion, Evento.Tipo nuevoTipo, Ubicacion nuevaUbicacion)
    {
        //Si el evento tiene contenido y lo ha creado el organizador que lo quiere modificar.
        if (evento != null && listaEventosCreados.contains(evento))
        {
            //Se pasan los nuevos valores de los campos.
            evento.setNombre(nuevoNombre);
            evento.setFecha(nuevaFecha);
            evento.setDuracion(nuevaDuracion);
            evento.setTipo(nuevoTipo);
            evento.setUbicacion(nuevaUbicacion);
            //Se devuelve verdadero, conforme el organizador ha modificado el
            //evento correctamente.
            return true;
        }
        //Se devuelve falso, conforme el organizador no ha podido modificar el
        //evento.
        return false;
    }
    

    //Método para imprimir correctamente a los usuarios.
    @Override
    public String toString()
    {
        return "Organizador" + "\n\t- Id: " + id + "\n\t- Nombre: " + nombre + "\n\t- Correo: " + correo + "\n\t- Contraseña: " + contrasenia + "\n\t- Tipo: " + tipo + "\n\t- Teléfono: " + telefono;
    }
    
    
    //Método para comprobar si varios usuarios son iguales.
    @Override
    public boolean equals(Object obj)
    {
        boolean igual = false;
        if (obj != null)
        {
            if (obj instanceof Organizador o)
            {
                if (this.getId().equals(o.getId()) && this.nombre.equals(o.getNombre()) && this.correo.equals(o.getCorreo()) && this.contrasenia.equals(o.getContrasenia()) && this.tipo.equals(o.getTipo()))
                {
                    /*Si el objeto tiene contenido, es un organizador y todos sus
                    atributos son iguales, entonces se comprobará su teléfono.*/
                    if (o.getTelefono() == null && this.telefono == null)
                    {
                        igual = true;
                    }
                    //Si alguno de ellos no es null.
                    else
                    {
                        //Comprobar si alguno es null.
                        if (o.getTelefono() == null || this.telefono == null)
                        {
                            //En ese caso, no serán iguales, ya que uno proporciona
                            //el teléfono y otro no.
                            igual = false;
                        }
                        //En caso de que ninguno de ellos sea null, se comprueba
                        //si son iguales los teléfonos proporcionados.
                        else if (this.telefono.equals(o.getTelefono()))
                        {
                            //En caso afirmativo, los organizadores son iguales.
                            igual = true;
                        }
                    }
                }
            }
        }
        return igual;
    }

    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.correo);
        hash = 97 * hash + Objects.hashCode(this.contrasenia);
        hash = 97 * hash + Objects.hashCode(this.tipo);
        hash = 97 * hash + Objects.hashCode(this.telefono);
        return hash;
    }
}
