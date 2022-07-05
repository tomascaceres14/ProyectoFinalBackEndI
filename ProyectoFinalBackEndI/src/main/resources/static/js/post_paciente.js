window.addEventListener("load", function () {
  const inputNombre = document.getElementById("nombre");
  const inputApellido = document.getElementById("apellido");
  const inputDni = document.getElementById("dni");
  const inputDomicilioId = document.getElementById("id");
  const inputCalle = document.getElementById("calle");
  const inputNumero = document.getElementById("numero");
  const inputLocalidad = document.getElementById("localidad");
  const inputProvincia = document.getElementById("provincia");

  const formulario = document.querySelector("form");
  const responseDiv = document.getElementById("response");

  function cartelRespuesta(data) {
    let success =
      '<div class="alert alert-success alert-dismissible">' +
      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
      '<strong>paciente agregado</strong></div>';
    let failure =
      '<div class="alert alert-danger alert-dismissible">' +
      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
      '<strong> Error intente nuevamente</strong> </div>';
    responseDiv.style.display = "block";
    if (data != null) {
      responseDiv.innerHTML = success;
    } else responseDiv.innerHTML = failure;
  }

  function limpiarCampos() {
    inputDni.value = "";
    inputApellido.value = "";
    inputNombre.value = "";
    inputDomicilioId.value = "";
    inputCalle.value = "";
    inputNumero.value = "";
    inputLocalidad.value = "";
    inputProvincia.value = "";
  }

  formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    const data = {
      nombre: inputNombre.value,
      apellido: inputApellido.value,
      dni: inputDni.value,
      fechaIngreso: new Date().toISOString().slice(0, 10),
      domicilio: {
        id: inputDomicilioId.value,
        calle: inputCalle.value,
        numero: inputNumero.value,
        localidad: inputLocalidad.value,
        provincia: inputProvincia.value,
      }
    };

    console.log(data);

    const config = {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
      },
    };

    fetch(`/pacientes/crear`, config)
      .then((data) => data.json())
      .then((data) => {
        cartelRespuesta(data.id);
        console.log(data);
      });

    limpiarCampos();
  });
});
