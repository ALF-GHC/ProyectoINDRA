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
public class Usuario {
    //Propiedades.
    //Se ha añadido el nick único para identificar al usuario de forma única.
    private final String nick;
    private String nombre;
    //Se escribe "correo" en lugar de "correo-e" porque no se permiten guiones
    //medios y tampoco será necesario añadirle la "e".
    private String correo;
    //Se escribe "contrasenia" en lugar de "contraseña" por los problemas que
    //puede causar el caracter "ñ".
    private String contrasenia;
    //Creación de la lista de eventos a los que está apuntado un usuario.
    //Esta lista no se puede modificar (setter) ni pasar al constructor, ya que
    //es un control interno de los eventos a los que se ha inscrito un usuario.
    private final ArrayList <Evento> listaEventosInscrito = new ArrayList<>();

    
    //Constructor.
    public Usuario(String nick, String nombre, String correo, String contrasenia)
    {
        this.nick = nick;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }
    
    //Getters y Setters de las propiedades.
    public String getNick()
    {
        return nick;
    }

    //El nick no tendrá setter al ser un identificador único.
   
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

    public ArrayList<Evento> getListaEventosInscrito()
    {
        return listaEventosInscrito;
    }
    
    
    //Responsabilidades: inscribirse y cancelar inscripción en eventos.
    public boolean inscribirEvento(Evento evento)
    {
        //Si el evento tiene contenido y el usuario no está inscrito en él actualmente.
        if (evento != null && !listaEventosInscrito.contains(evento))
        {
            //Se añade el evento a la lista de eventos del usuario.
            listaEventosInscrito.add(evento);
            //Llamada a registrarUsu pasándole el usuario actual para añadir al
            //usuario a la lista de usuarios que están inscritos en un evento.
            evento.registrarUsu(this);
            //Se devuelve verdadero, conforme el usuario se ha inscrito correctamente.
            return true;
        }
        //Se devuelve falso, conforme el usuario no se ha podido inscribir.
        return false;
    }

    public boolean cancelarInscripcion(Evento evento)
    {
        //Si el evento tiene contenido y el usuario está inscrito actualmente en él.
        if (evento != null && listaEventosInscrito.contains(evento))
        {
            //Se elimina el evento de la lista de eventos del usuario.
            listaEventosInscrito.remove(evento);
            //Llamada a quitarUsu pasándole el usuario actual para quitar al
            //usuario de la lista de usuarios que están inscritos en un evento.
            evento.quitarUsu(this);
            //Se devuelve verdadero, conforme el usuario ha cancelado la
            //inscripción correctamente.
            return true;
        }
        //Se devuelve falso, conforme el usuario no se ha cancelar la inscripción.
        return false;
    }
    
    
    //Método para imprimir correctamente a los usuarios.
    @Override
    public String toString()
    {
        return "Usuario" + "\n\t- Nick: " + nick + "\n\t- Nombre: " + nombre + "\n\t- Correo: " + correo + "\n\t- Contraseña: " + contrasenia;
    }
    
    
    //Método para comprobar si varios usuarios son iguales.
    @Override
    public boolean equals(Object obj)
    {
        boolean igual = false;
        if (obj != null)
        {
            if (obj instanceof Usuario usu)
            {
                if (this.getNick().equals(usu.getNick()) && this.nombre.equals(usu.getNombre()) && this.correo.equals(usu.getCorreo()) && this.contrasenia.equals(usu.getContrasenia()))
                {
                    /*Si el objeto tiene contenido, es un usuario y todos sus
                    atributos son iguales, entonces los usuarios son iguales.*/
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
        hash = 97 * hash + Objects.hashCode(this.nick);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.correo);
        hash = 97 * hash + Objects.hashCode(this.contrasenia);
        hash = 97 * hash + Objects.hashCode(this.listaEventosInscrito);
        return hash;
    }
}