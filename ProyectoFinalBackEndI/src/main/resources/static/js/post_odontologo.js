window.addEventListener("load", ()=>{

  let formulario = document.getElementById("add_new_odontologo");
  let nombre = document.getElementById("nombre")
  let apellido = document.getElementById("apellido")
  let matricula = document.getElementById("matricula")
  let odontologos =document.querySelector(".odontologo")
  let idBtns =document.querySelectorAll(".id-btn")



  /* ocultar formulario*/
  function ocultarActualizacion(){
      idBtns.forEach(boton => {
                boton.addEventListener("click", (e) =>{
                  e.preventDefault();
                  console.log("entra aquí");

                  const oculto = document.getElementById("oculto")
                  oculto.classList.toggle("oculto")

                })
              })
  }


  consultarOdontologos()

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

  formulario.addEventListener("submit", function(event){
      event.preventDefault();
  let odontologo = {
      nombre: nombre.value,
      apellido: apellido.value,
      matricula: matricula.value,
  }

  let urlApiUsuario = "/odontologos/crear"

       const options = {
          method: "POST",
          body: JSON.stringify(odontologo),
          headers: {
          "Content-type": "application/json",
          }
      }

      fetch(urlApiUsuario, options )
          .then((response) => response.json())
          .then((json) => {
              // Aqui obtenemos la respuesta de la API.
              console.log("Usuario:", json);
              consultarOdontologos()
          })
  })


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
          <div class="oculto" id="update_odontologo">
                      <form class="update" id="form_${odontologo.id}">
                      <div class="form-group">
                          <label class="control-label" for="nombre">Nombre:  </label>
                          <input type="text" class="form-control" id="nombre_update""
                                 placeholder="Ingrese el nombre"
                                 name="nombre"></input>
                      </div>
                      <div class="form-group">
                          <label class="control-label" for="apellido">Apellido:  </label>
                          <input type="text" class="form-control" id="apellido_update""
                                 placeholder="Ingrese el apellido"
                                 name="apellido"></input>
                      </div>

                      <div class="form-group">
                          <label class="control-label" for="matricula">Matricula:  </label>
                          <input type="text" class="form-control" id="matricula_update""
                                 placeholder="Ingrese la matricula"
                                 name="matricula"></input>
                      </div>


                      <button type="submit" class="actualizar"
                              >Actualizar
                      </button>
                      <form>
                      </div>
          `;
        })
        ocultarActualizacion();
        botonBorrarodontologo();
        actualizarOdontologo();
      }

        /* formulario para update*/

        function actualizarOdontologo(){


        let formularioUpdate = document.querySelectorAll(".update")

          formularioUpdate.forEach(formulario =>{

          formulario.addEventListener("submit", (e)=>{
                        e.preventDefault();
                        const stringId = e.target.id.split("_")
                        const idModificar = stringId[1]
                        console.log(idModificar)

                        const nombreUpdate = document.getElementById("nombre_update")
                        const apellidoUpdate = document.getElementById("apellido_update")
                        const matriculaUpdate = document.getElementById("matricula_update")

                        let odontologo = {
                          nombre: nombreUpdate.value,
                          apellido: apellidoUpdate.value,
                          matricula: matriculaUpdate.value,
                      }

                      const configuraciones = {
                        method: "PUT",
                        body: JSON.stringify(odontologo),
                        headers: {
                          'Content-type': 'application/json'
                        }
                      }

                      fetch(`/odontologos/actualizar/${idModificar}`, configuraciones)
                          .then((respuesta) => respuesta.json())
                          .then((tarea) => {
                            console.log(tarea);
                            consultarOdontologos();
                          })
                          .catch((error) => {
                            console.log("Error escuchando la promesa.");
                            console.log(error);
                          });

          })
          })

        }


  /* Eliminar odontologo */

  function botonBorrarodontologo() {
  const btnsBorrar = document.querySelectorAll(".borrar")

  btnsBorrar.forEach( boton =>{
    boton.addEventListener("click", (e)=>{
      const odontologoBorrar = e.target.id
      console.log(odontologoBorrar);

      const configuracion = {
        method: "DELETE",
        path: odontologoBorrar,
        headers: {
          "Content-Type": "application/json",
        }
      }

      fetch(`/odontologos/eliminar/${odontologoBorrar}`, configuracion)
      .then((respuesta) => respuesta.json())
      .then(data =>{
        console.log(data);
        consultarOdontologos()
      })
      .catch(error => console.log(error))
    })
  })
}


})