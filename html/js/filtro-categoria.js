document.addEventListener("DOMContentLoaded", function ()
{
    const filtro = document.getElementById("filtro-categoria");
    const filas = document.querySelectorAll("table tbody tr");

    filtro.addEventListener("change", function ()
    {
        const categoriaSeleccionada = this.value;

        filas.forEach(fila => 
        {
            const categoria = fila.cells[1].textContent.trim();

            if (categoriaSeleccionada === "" || categoria === categoriaSeleccionada)
            {
            fila.style.display = "";
            }
            else
            {
                fila.style.display = "none";
            }
        });
    });
});
