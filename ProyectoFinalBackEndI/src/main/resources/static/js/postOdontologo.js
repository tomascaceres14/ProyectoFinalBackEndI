window.addEventListener('load', function () {

    const inputNombre = document.getElementById('nombre');
    const inputApellido = document.getElementById('apellido');
    const inputMatricula = document.getElementById('matricula');
    const formulario = document.querySelector('form');


    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const data = {
            nombre: inputNombre.value,
            apellido: inputApellido.value,
            matricula: inputMatricula.value
        }

        const config = {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
                }
        }

        fetch(`/odontologos/crear`, config)
            .then(respuesta => respuesta.json())
            .then(data => console.log(data));

    })
    })
