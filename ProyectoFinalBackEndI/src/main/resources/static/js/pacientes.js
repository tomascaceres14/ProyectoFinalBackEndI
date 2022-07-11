window.addEventListener("load", () => {
  let formulario = document.getElementById("add_new_paciente");
  let nombre = document.getElementById("nombre");
  let apellido = document.getElementById("apellido");
  let dni = document.getElementById("dni");
  let fechaIngreso = document.getElementById("fecha_ingreso");
  let idDomicilio = document.getElementById("id_domicilio");
  let calle = document.getElementById("calle");
  let numero = document.getElementById("numero");
  let localidad = document.getElementById("localidad");
  let provincia = document.getElementById("provincia");
  let pacientes = document.querySelector(".pacientes");

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

  consultarPacientes();

  /* Get pacientes*/

  function consultarPacientes() {
    const settings = {
      method: "GET",
    };

    fetch(`/pacientes`, settings)
      .then((respuesta) => respuesta.json())
      .then((data) => {
        console.log(data);
        renderizarPacientes(data);
      })
      .catch((error) => {
        console.log("Error escuchando la promesa.");
        console.log(error);
      });
  }

  //Crear pacientes

  formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    let paciente = {
      nombre: nombre.value,
      apellido: apellido.value,
      dni: dni.value,
      fechaIngreso: Date.now(),
      domicilio: {
        id: idDomicilio.value == "" ? null : idDomicilio.value,
        calle: calle.value,
        numero: numero.value,
        localidad: localidad.value,
        provincia: provincia.value,
      },
    };

    let urlApiUsuario = "/pacientes/crear";

    const options = {
      method: "POST",
      body: JSON.stringify(paciente),
      headers: {
        "Content-type": "application/json",
      },
    };

    fetch(urlApiUsuario, options)
      .then((response) => response.json())
      .then((json) => {
        // Aqui obtenemos la respuesta de la API.
        console.log("Paciente:", json);
        consultarPacientes();
      });
  });

  /* Función para renderizar pacientes */

  function renderizarPacientes(listado) {
    pacientes.innerHTML = "";

    listado.forEach((paciente) => {
      pacientes.innerHTML += `
            <li  class="paciente">
                <button  class="id-btn" id="oculto_${paciente.id}">${paciente.id}</button>
                <p>${paciente.nombre}</p>
                <p>${paciente.apellido}</p>
                <p>${paciente.dni}</p>
                <p>${paciente.fechaIngreso}</p>
                <p>${paciente.idDomicilio}</p>
                <button class="borrar" id="${paciente.id}">Borrar</button>
            </li>
            <div id="update_pacientes">
                <form class="update_paciente oculto" id="form_${paciente.id}">
                    <section class="formulario_pacientes">
                        <h3> Paciente</h3>
                        <div class="form-group">
                            <label class="control-label" for="nombre">Nombre:  </label>
                            <input type="text" class="form-control" id="nombre_update_${paciente.id}"
                                placeholder="Ingrese el nombre"
                                name="nombre"></input>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="apellido">Apellido:  </label>
                            <input type="text" class="form-control" id="apellido_update_${paciente.id}"                                    placeholder="Ingrese el apellido"
                                name="apellido"></input>
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="dni">Matricula:  </label>
                            <input type="text" class="form-control" id="dni_update_${paciente.id}"
                                placeholder="Ingrese el dni"
                                name="matricula"></input>
                        </div>

                    <section class="formulario_domicilio">
                        <h3>Domicilio</h3>
                        <div class="form-group">
                            <label class="control-label" for="id_domicilio_update">Domicilio id: </label>                                <input type="text" class="form-control" id="id_domicilio_update" placeholder="Ingrese el id del domicilio si ya existe"
                                name="id_domicilio_${paciente.id}" required></input>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="calle_update">Calle: </label>
                            <input type="text" class="form-control" id="calle_update" placeholder="Ingrese la calle"
                                name="calle_update_${paciente.id}" required></input>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="numero_update">Numero: </label>
                            <input type="text" class="form-control" id="numero_update" placeholder="Ingrese el numero"
                                name="numero_update_${paciente.id}" required></input>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="localidad_update">Localidad: </label>
                            <input type="text" class="form-control" id="localidad_update_${paciente.id}" placeholder="Ingrese la localidad"
                                name="localidad_update" required></input>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="provincia_update">Provincia: </label>
                            <input type="text" class="form-control" id="provincia_update_${paciente.id}" placeholder="Ingrese la provincia"
                                name="provincia_update" required></input>
                        </div>
                    </section>
                    <button type="submit" class="actualizar">
                        Actualizar
                    </button>
                <form>
            </div>
            `;
    });
    ocultarActualizacion();
    botonBorrarPaciente();
    actualizarPaciente();
  }

  /* formulario para update */

  function actualizarPaciente() {
    let idBtn = document.querySelectorAll(".id-btn");

    idBtn.forEach((boton) => {
      boton.addEventListener("click", (e) => {
        const stringId = e.target.id.split("_");
        const idUpdate = stringId[1];
        console.log(idUpdate);

        let formulario = document.getElementById("form_" + idUpdate);

        formulario.addEventListener("submit", (e) => {
          e.preventDefault();

        const nombreUpdate = document.getElementById("nombre_update_" + idUpdate);
        const apellidoUpdate = document.getElementById("apellido_update_" + idUpdate);
        const dniUpdate = document.getElementById("dni_update_" + idUpdate);
        const idDomicilioUpdate = document.getElementById("id_domicilio_update_" + idUpdate);
        const calleUpdate = document.getElementById("calle_update_" + idUpdate);
        const numeroUpdate = document.getElementById("numero_update_" + idUpdate);
        const localidadUpdate = document.getElementById("localidad_update_" + idUpdate);
        const provinciaUpdate = document.getElementById("provincia_update_" + idUpdate);

          let paciente = {
            nombre: nombreUpdate.value == "" ? null : nombreUpdate.value,
            apellido: apellidoUpdate.value == "" ? null : apellidoUpdate.value,
            dni: dniUpdate.value == "" ? null : dniUpdate.value,
            fechaIngreso: null,
            domicilio: {
                id: idDomicilioUpdate.value == "" ? null : idDomicilioUpdate.value,
                calle: calleUpdate.value == "" ? null : calleUpdate.value,
                numero: numeroUpdate.value == "" ? null : numeroUpdate.value,
                localidad: localidadUpdate.value == "" ? null : localidadUpdate.value,
                provincia: provinciaUpdate.value == "" ? null : provinciaUpdate.value,
            },
          };

          const configuraciones = {
            method: "PUT",
            body: JSON.stringify(paciente),
            headers: {
              "Content-type": "application/json",
            },
          };

          console.log(paciente);

          fetch(`/pacientes/actualizar/${idUpdate}`, configuraciones)
            .then((respuesta) => respuesta.json())
            .then((tarea) => {
              console.log(tarea);
              consultarPacientes();
            })
            .catch((error) => {
              console.log("Error escuchando la promesa.");
              console.log(error);
            });
        });
      });
    });
  }

  /* Eliminar paciente */

  function botonBorrarPaciente() {
    const btnsBorrar = document.querySelectorAll(".borrar");

    btnsBorrar.forEach((boton) => {
      boton.addEventListener("click", (e) => {
        const pacienteBorrar = e.target.id;
        console.log(pacienteBorrar);

        const configuracion = {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        };

        fetch("/pacientes/eliminar/" + pacienteBorrar, configuracion)
          .then((respuesta) => respuesta.json())
          .then((data) => {
            console.log(data);
            location.reload();
            consultarPacientes();
          })
          .catch((error) => console.log(error))
          .finally(() => {
            location.reload();
            consultarPacientes();
          });
      });
    });
  }
});