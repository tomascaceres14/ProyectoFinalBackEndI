window.addEventListener("load", () => {
  let formulario = document.getElementById("add_new_odontologo");
  let nombre = document.getElementById("nombre");
  let apellido = document.getElementById("apellido");
  let matricula = document.getElementById("matricula");
  let odontologos = document.querySelector(".odontologo");

  /* ocultar formulario*/
  function ocultarActualizacion() {
    let idBtns = document.querySelectorAll(".id-btn");

    idBtns.forEach((boton) => {
      boton.addEventListener("click", (e) => {
        e.preventDefault();

        const stringId = e.target.id.split("_");
        const idModificar = stringId[1];
        console.log(idModificar);

        const oculto = document.getElementById("form_" + idModificar);
        console.log(oculto);

        oculto.classList.toggle("oculto");
      });
    });
  }

  consultarOdontologos();

  /* Get odontologos*/

  function consultarOdontologos() {
    const settings = {
      method: "GET",
    };

    fetch(`/odontologos`, settings)
      .then((respuesta) => respuesta.json())
      .then((data) => {
        console.log(data);
        renderizarOdontologos(data);
      })
      .catch((error) => {
        console.log("Error escuchando la promesa.");
        console.log(error);
      });
  }

  //Crear odontologos

  formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    let odontologo = {
      nombre: nombre.value,
      apellido: apellido.value,
      matricula: matricula.value,
    };

    let urlApiUsuario = "/odontologos/crear";

    const options = {
      method: "POST",
      body: JSON.stringify(odontologo),
      headers: {
        "Content-type": "application/json",
      },
    };

    fetch(urlApiUsuario, options)
      .then((response) => response.json())
      .then((json) => {
        // Aqui obtenemos la respuesta de la API.
        console.log("Usuario:", json);
        nombre.value = "";
        apellido.value = "";
        matricula.value = "";
        consultarOdontologos();
      });
  });

  /* Función para renderizar odontologos */

  function renderizarOdontologos(listado) {
    odontologos.innerHTML = "";

    listado.forEach((odontologo) => {
      odontologos.innerHTML += `
          <li  class="odontologos">
              <button  class="id-btn" id="oculto_${odontologo.id}">${odontologo.id}</button>
              <p>${odontologo.nombre}</p>
              <p>${odontologo.apellido}</p>
              <p>${odontologo.matricula}</p>
              <button class="borrar" id="${odontologo.id}">Borrar</button>
          </li>
          <div id="update_odontologo">
                      <form class="update oculto" id="form_${odontologo.id}">
                      <div class="form-group">
                          <label class="control-label" for="nombre">Nombre:  </label>
                          <input type="text" class="form-control" id="nombre_${odontologo.id}"
                                 placeholder="Ingrese el nombre"
                                 name="nombre"></input>
                      </div>
                      <div class="form-group">
                          <label class="control-label" for="apellido">Apellido:  </label>
                          <input type="text" class="form-control" id="apellido_${odontologo.id}"
                                 placeholder="Ingrese el apellido"
                                 name="apellido"></input>
                      </div>

                      <div class="form-group">
                          <label class="control-label" for="matricula">Matricula:  </label>
                          <input type="text" class="form-control" id="matricula_${odontologo.id}"
                                 placeholder="Ingrese la matricula"
                                 name="matricula"></input>
                      </div>

                      <button type="submit" class="actualizar">
                      Actualizar
                      </button>
                      <form>
                      </div>
          `;
    });
    ocultarActualizacion();
    botonBorrarodontologo();
    actualizarOdontologo();
  }

  /* formulario para update */

  function actualizarOdontologo() {
    let idBtn = document.querySelectorAll(".id-btn");

    idBtn.forEach((boton) => {
      boton.addEventListener("click", (e) => {
        const stringId = e.target.id.split("_");
        const idUpdate = stringId[1];
        console.log(idUpdate);

        let formulario = document.getElementById("form_" + idUpdate);

        formulario.addEventListener("submit", (e) => {
          e.preventDefault();

          const nombreUpdate = document.getElementById("nombre_" + idUpdate);
          const apellidoUpdate = document.getElementById(
            "apellido_" + idUpdate
          );
          const matriculaUpdate = document.getElementById(
            "matricula_" + idUpdate
          );

          let odontologo = {
            nombre: nombreUpdate.value == "" ? null : nombreUpdate.value,
            apellido: apellidoUpdate.value == "" ? null : apellidoUpdate.value,
            matricula: matriculaUpdate.value,
          };

          const configuraciones = {
            method: "PUT",
            body: JSON.stringify(odontologo),
            headers: {
              "Content-type": "application/json",
            },
          };

          console.log(odontologo);

          fetch(`/odontologos/actualizar/${idUpdate}`, configuraciones)
            .then((respuesta) => respuesta.json())
            .then((tarea) => {
              console.log(tarea);
              consultarOdontologos();
            })
            .catch((error) => {
              console.log("Error escuchando la promesa.");
              console.log(error);
            });
        });
      });
    });
  }

  /* Eliminar odontologo */

  function botonBorrarodontologo() {
    const btnsBorrar = document.querySelectorAll(".borrar");

    btnsBorrar.forEach((boton) => {
      boton.addEventListener("click", (e) => {
        const odontologoBorrar = e.target.id;
        console.log(odontologoBorrar);

        const configuracion = {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        };

        fetch("/odontologos/eliminar/" + odontologoBorrar, configuracion)
          .then((respuesta) => respuesta.json())
          .then((data) => {
            console.log(data);
            location.reload();
            consultarOdontologos();
          })
          .catch((error) => console.log(error))
          .finally(() => {
            location.reload();
            consultarOdontologos();
          });
      });
    });
  }
});
