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
public class Evento {
    //Propiedades.
    //En este caso se pasa el objeto completo, en lugar del id de la categoría.
    private Categoria categoria;
    //Se ha añadido el id único para identificar al evento de forma única, este
    //está formado por dos campos, el id del evento y el id de la categoría.
    private final String idEvento;
    private String nombre;
    private String fecha;
    private int duracion;
    public enum Tipo
    {
        ONLINE, PRESENCIAL
    }
    //Para usar el enumerado, se ha creado un atributo de tipo Tipo.
    private Tipo tipo;
    //Objeto de la clase Ubicacion que contiene el lugar de celebración de un
    //evento de tipo presencial, en caso de ser online, el objeto será null.
    private Ubicacion ubicacion;
    //Creación de un atributo que permite saber en qué estado se encuentra dicho
    //evento, ya sea cancelado o activo.
    public enum Estado
    {
        CANCELADO, ACTIVO
    }
    //Para usar el enumerado, se ha creado un atributo estado de tipo Estado.
    private Estado estado;
    //Esta lista no se puede modificar (setter) ni pasar al constructor, ya que
    //es un control interno de los usuarios que están inscritos a un evento.
    private ArrayList <Usuario> listaUsuariosInscritos = new ArrayList<>();
    //Organizador que crea el evento.
    //Tal y como se ha indicado, un evento solo será creado por un organizador.
    private Organizador organizador;

    
    //Constructor.
    //La "ubicacion" se recibe siempre pero, en caso de que el tipo de evento
    //sea "online", su valor será null.
    public Evento(Categoria categoria, String idEvento, String nombre, String fecha, int duracion, Tipo tipo, Ubicacion ubicacion, Organizador organizador)
    {
        this.categoria = categoria;
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.tipo = tipo;
        if (tipo == Tipo.ONLINE)
        {
            this.ubicacion = null;
        }
        else
        {
            this.ubicacion = ubicacion;
        }
        //Por defecto, todos los eventos están activos hasta que, opcionalmente,
        //sean cancelados.
        estado = Estado.ACTIVO;
        this.organizador = organizador;
    }

    
    //Getters y Setters de las propiedades.
    public Categoria getCategoria()
    {
        return categoria;
    }

    //La categoría no tendrá setter al formar parte de la clave primaria.
    
    public String getIdEvento()
    {
        return idEvento;
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

    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public int getDuracion()
    {
        return duracion;
    }

    public void setDuracion(int duracion)
    {
        this.duracion = duracion;
    }

    public Tipo getTipo()
    {
        return tipo;
    }

    public void setTipo(Tipo tipo)
    {
        this.tipo = tipo;
        if (tipo == Tipo.ONLINE)
        {
            this.ubicacion = null;
        }
    }

    public Ubicacion getUbicacion()
    {
        return ubicacion;
    }

    //La "ubicacion" no se podrá cambiar para aquellos eventos que sean de tipo
    //"online", ya que esta siempre será "null".
    public void setUbicacion(Ubicacion ubicacion)
    {
        if (tipo == Tipo.ONLINE)
        {
            this.ubicacion = null;
        }
        else
        {
            this.ubicacion = ubicacion;
        }
    }

    public ArrayList<Usuario> getListaUsuariosInscritos()
    {
    return listaUsuariosInscritos;
    }

    public Organizador getOrganizador()
    {
        return organizador;
    }

    public void setOrganizador(Organizador organizador)
    {
        this.organizador = organizador;
    }
    
    public Estado getEstado()
    {
        return estado;
    }

    //Responsabilidades: cancelar evento, registrar y quitar usuarios.
    //Setter del estado del evento a cancelado.
    public void cancelarEvento()
    {
        //Cambio del estado del evento una vez es cancelado.
        estado = Estado.CANCELADO;
        //Cancelación de las inscripciones de todos los usuarios apuntados al
        //evento cancelado.
        for (int i = listaUsuariosInscritos.size() - 1; i >= 0; i--)
        {
            listaUsuariosInscritos.get(i).cancelarInscripcion(this);
        }
    }
    
    //Se ha añadido un método para que los eventos que hayan sido cancelados se
    //puedan reactivar.
    public void reactivarEvento()
    {
        //Cambio del estado del evento una vez es reactivado.
        estado = Estado.ACTIVO;
    }
    
    //Tanto registrarUsu como quitarUsu están diseñados para llamarlos desde la
    //clase Usuario, por lo que no se deben utilizar directamente desde el Evento,
    //ya que las listas de usuarios y eventos no estarían sincronizadas.
    public void registrarUsu(Usuario usuario)
    {
        //Si en la lista de usuarios apuntados a dicho evento no está el usuario
        //a inscribir.
        if (!listaUsuariosInscritos.contains(usuario))
        {
            //Añadir a la lista de usuarios inscritos en un evento el usuario
            //que se acaba de inscribir.
            listaUsuariosInscritos.add(usuario);
        }
    }
    
    public void quitarUsu(Usuario usuario)
    {
        //Si en la lista de usuarios apuntados a dicho evento está el usuario
        //a quitar.
        if (listaUsuariosInscritos.contains(usuario))
        {
            //Quitar de la lista de usuarios inscritos en un evento el usuario
            //que se acaba de cancelar la inscripción.
            listaUsuariosInscritos.remove(usuario);
        }
    }
    
    
    //Método para imprimir correctamente los eventos.
    @Override
    public String toString()
    {
        return "Evento" + "\n\t- Categoría: " + categoria + "\n\t- Id_Evento: " + idEvento + "\n\t- Nombre: " + nombre + "\n\t- Fecha: " + fecha + "\n\t- Duración: " + duracion + "\n\t- Tipo: " + tipo + "\n\t- Ubicacion: " + ubicacion + "\n\t- Estado: " + estado + "\n\t- Organizador: " + organizador;
    }
    
    
    //Método para comprobar si varios eventos son iguales.
    @Override
    public boolean equals(Object obj)
    {
        boolean igual = false;
        if (obj != null)
        {
            if (obj instanceof Evento ev)
            {
                if (this.getCategoria().equals(ev.getCategoria()) && this.idEvento.equals(ev.getIdEvento()) && this.nombre.equals(ev.getNombre()) && this.fecha.equals(ev.getFecha()) && this.duracion == ev.getDuracion() && this.tipo.equals(ev.getTipo()) && this.estado.equals(ev.getEstado()) && this.organizador.equals(ev.getOrganizador()))
                {
                    /*Si el objeto tiene contenido, es un evento y todos sus
                    atributos son iguales, entonces se comprobará su ubicación.*/
                    if (ev.getUbicacion()== null && this.ubicacion == null)
                    {
                        igual = true;
                    }
                    //Hay que tener en cuenta que el tipo de evento controla si
                    //la ubicación es null o no, por lo que no habrá que comprobar
                    //(si tienen el mismo tipo) que uno sea null y otro no lo es.
                    else
                    {
                        if (this.ubicacion.equals(ev.getUbicacion()))
                        {
                            //En caso afirmativo, los eventos son iguales.
                            igual = true;
                        }
                    }
                }
            }
        }
        return igual;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.categoria);
        hash = 13 * hash + Objects.hashCode(this.idEvento);
        hash = 13 * hash + Objects.hashCode(this.nombre);
        hash = 13 * hash + Objects.hashCode(this.fecha);
        hash = 13 * hash + this.duracion;
        hash = 13 * hash + Objects.hashCode(this.tipo);
        hash = 13 * hash + Objects.hashCode(this.ubicacion);
        hash = 13 * hash + Objects.hashCode(this.estado);
        hash = 13 * hash + Objects.hashCode(this.organizador);
        return hash;
    }
}