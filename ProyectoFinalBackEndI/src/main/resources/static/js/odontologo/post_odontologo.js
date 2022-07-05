window.addEventListener("load", function () {

  const inputNombre = document.getElementById("nombre");
  const inputApellido = document.getElementById("apellido");
  const inputMatricula = document.getElementById("matricula");
  const formulario = document.querySelector("form");
  const responseDiv = document.getElementById("response");

  function cartelRespuesta(data) {
    let success =
      '<div class="alert alert-success alert-dismissible">' +
      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
      '<strong>odontologo agregado</strong></div>';
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
    inputMatricula.value = "";
    inputApellido.value = "";
    inputNombre.value = "";
  }

  formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    const data = {
      nombre: inputNombre.value,
      apellido: inputApellido.value,
      matricula: inputMatricula.value,
    };

    const config = {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
      },
    };

    fetch(`/odontologos/crear`, config)
      .then((data) => data.json())
      .then((data) => {
        cartelRespuesta(data.id);
        console.log(data);
      });

    limpiarCampos();
  });
});
