# Descripción
Este proyecto ha sido creado por Antía Loureiro Ferrío como parte de las Prácticas de Primer Curso del Ciclo Superior de Desarrollo de Aplicaciones Web con la empresa Minsait - Indra.

El objetivo final era crear un portal de Eventos Sostenibles con las instrucciones dadas, pasando por todas las fases necesarias; instalación y configuración S.O., instalación y configuración de un servidor web, creación del modelo EER, bases de datos, programación de objetos, diseño web, repositorios...

Las ramas contienen documentación detallada, ficheros de creación y código fuente.

# Lenguajes de marcas
A continuación se muestran algunas de las secciones resultantes del diseño de la página web.

- Página Inicial:

![Antía Loureiro Ferrío - Portal de Eventos Sostenibles](https://github.com/user-attachments/assets/dd5988c3-0159-4f6b-80ee-3ab83c89c62e)

- Listado de Eventos filtrado por categoría "taller":

![Antía Loureiro Ferrío - Filtro Categoría Taller](https://github.com/user-attachments/assets/641105f0-bab8-470d-8af7-50af322fc2d3)

- Detalle del Evento:
  
![Antía Loureiro Ferrío - Detalle Evento](https://github.com/user-attachments/assets/516a1c46-e7f0-4f87-9c08-ad31d176ff0b)

- Inicio de Sesión:

![Antía Loureiro Ferrío - Inicio Sesión](https://github.com/user-attachments/assets/03e5c4da-3053-4d67-a730-925f0d5d8580)

El código, estilos e imágenes de la página web se encuentran en /html.

# Bases de datos
Atendiendo a los requisitos indicados en el proyecto, se ha realizado el siguiente Modelo Entidad Relación:

![Antía Loureiro Ferrío - Modelo EER](https://github.com/user-attachments/assets/66e2b786-588e-4775-9ffb-25270f3162b1)

Los pasos detallados se pueden consultar en /doc.

# Programación
Atendiendo a los requisitos indicados en el proyecto, se han creado los objetos, con sus propiedades, atributos, constructores, métodos y responsabilidades, así como los test necesarios para comprobar el correcto funcionamiento del programa, aplicando una cobertura del 100%:

![Antía Loureiro Ferrío - 100 Cobertura Test](https://github.com/user-attachments/assets/f1861f9e-8b2c-4acb-bb41-ccc02a9184a8)

Los objetos y los test en archivos .java se encuentran alojados en /src.
Se ha realizado este apartado con la herramienta NetBeans, ya que es la utilizada en el ciclo.


# Sistemas Informáticos
Atendiendo a los requisitos indicados en el proyecto, se ha creado una máquina virtual, en la cual se ha instalado y configurado Windows 10 Pro como sistema operatico.
También se ha instalado y configurado XAMPP, este paquete contiene un servidor web (Apache), que se utiliza para la visualización de la página web:

Los pasos detallados se pueden consultar en /doc.

# Entornos de Desarrollo
Se ha utilizado un repositorio personal en GitHub para el desarrollo del proyecto.
Dicho repositorio se ha hecho público para que facilitar su consulta y acceso por parte de la empresa y el centro.

Este es el grafo de ramas del repositorio:

![Antía Loureiro Ferrío - Grafo ramas](https://github.com/user-attachments/assets/a6a4092c-bb94-4220-a024-dc809d1d4630)

# Estructura del Proyecto
<pre>
📁 /doc
   📄 Antía Loureiro Ferrío - Bases de Datos.pdf (EER, tablas entidad y scripts SQL)
   📄 Antía Loureiro Ferrío - Sistemas Informáticos.pdf (Instalación y configuración Win 10 Pro y XAMPP)
   📄 pom.txt (Archivo que contiene la configuración utilizada pra compilar el proyecto)
  
📁 /sql
   📄 Script-antia_loureiro_ferrio_eventos.sql (Script SQL con creación de tablas y diseño físico de la BD)
  
📁 /html
   📄 evento1.html (Detalles del evento 1)
   📄 evento2.html (Detalles del evento 2)
   📄 evento3.html (Detalles del evento 3)
   📄 evento4.html (Detalles del evento 4)
   📄 evento5.html (Detalles del evento 5)
   📄 evento6.html (Detalles del evento 6)
   📄 evento7.html (Detalles del evento 7)
   📄 evento8.html (Detalles del evento 8)
   📄 evento9.html (Detalles del evento 9)
   📄 evento10.html (Detalles del evento 10)
   📄 eventos.html (Listado de eventos)
   📄 index.html (Página principal con carrusel)
   📄 inicio-sesion.html (Página de inicio de sesión)
   📄 registro.html (Página de registro del usuario)
  
   📁 /css → Hojas de estilo CSS
      📄 styles.css (Estilos aplicados a toda la página web)
  
   📁 /js → Archivos JavaScript
      📄 carrusel-eventos.js (Carrusel de eventos destacados)
      📄 filtro-categoria.js (Filtro de eventos por categoría)
  
   📁 /images → Imágenes utilizadas en la página web
      📄 cambio_climatico.jpg
      📄 compostaje_casero.jpg
      📄 documentales_ambientales.png
      📄 ecoarte.jpg
      📄 economia_circular.jpeg
      📄 energia_sociedad.jpg
      📄 energias_renovables.jpg
      📄 huertos_urbanos.jpg
      📄 logo.png
      📄 movilidad_sostenible.jpg
      📄 reciclaje_creativo.jpg
  
📁 /src
   📁 /main
       📁 /java/com/antialoureiroferrioindra/eventos → Código fuente en Java
  
   📁 /test
       📁 /java/com/antialoureiroferrioindra/eventos → Pruebas en Java
</pre>

# ProyectoINDRA
Proyecto Eventos Sostenibles Indra - ALF-GHC

El contenido de este repositorio no se puede reproducir ni distribuir. ALF-GHC conserva todos los derechos sobre sus creaciones.

Tal y como GitHub explica: "No tienes la obligación de elegir una licencia. Sin embargo, sin una licencia, se aplican las leyes de derecho de autor predeterminadas, lo que implica que conservas todos los derechos de tu código fuente, y nadie puede reproducir, distribuir o crear trabajos a partir de tu trabajo."

Puede verificarse la veracidad de este contenido en el siguiente enlace: https://docs.github.com/es/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/licensing-a-repository
