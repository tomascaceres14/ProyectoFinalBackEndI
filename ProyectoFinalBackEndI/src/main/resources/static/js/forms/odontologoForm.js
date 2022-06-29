window.addEventListener('load', function () {

    const container = document.getElementById("method");
    const postBtn = document.getElementById("postBtn");
    const getBtn = document.getElementById("getBtn");

    postBtn.addEventListener("click", postForm);
    getBtn.addEventListener("click", getForm);
    updateBtn.addEventListener("click", updateForm);
    deleteBtn.addEventListener("click", deleteForm);

    function postForm() {
        container.innerHTML = `
        <div class="row">
                    <div class="col-sm-7" style="background-color:#e6fffa; padding:10px; border-radius:3px">
                        <h3>Crear</h3>
                        <form id="post_odontologo">
                            <div class="form-group">
                                <label class="control-label" for="nombre">Nombre:</label>
                                <input type="text" class="form-control" id="nombre"
                                       placeholder="Ingrese el nombre"
                                       name="nombre" required></input>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="apellido">Apellido:</label>
                                <input type="text" class="form-control" id="apellido"
                                       placeholder="Ingrese el apellido"
                                       name="apellido" required></input>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="matricula">Matricula:</label>
                                <input type="text" class="form-control" id="matricula"
                                       placeholder="Ingrese la matricula"
                                       name="matricula" required>
                                </input>
                            </div>

                            <button type="submit" class="btn btn-danger"
                                    id="btn-add-new-odontologo">Guardar</button>
                        </form>
                        <div id="response" style="display:none; margin-top:10px">
                        </div>
                    </div>
                </div>
        `;}

    function getForm() {
            container.innerHTML = `
            <div class="row">
                        <div class="col-sm-7" style="background-color:#e6fffa; padding:10px; border-radius:3px">
                            <h3>Buscar</h3>
                            <form id="get_odontologo">
                                <div class="form-group">
                                    <label class="control-label" for="nombre">id:</label>
                                    <input type="text" class="form-control" id="id"
                                           placeholder="Ingrese el id"
                                           name="id" required>
                                    </input>
                                </div>
                                <button type="submit" class="btn btn-danger"
                                        id="btn-add-new-odontologo">Guardar</button>
                            </form>
                            <div id="response" style="display:none; margin-top:10px">
                            </div>
                        </div>
                    </div>
            `;}

    function updateForm() {
                container.innerHTML = `
                <div class="row">
                            <div class="col-sm-7" style="background-color:#e6fffa; padding:10px; border-radius:3px">
                                <h3>Actualizar</h3>
                                <form id="update_odontologo">
                                    <div class="form-group">
                                        <label class="control-label" for="nombre">id:</label>
                                        <input type="text" class="form-control" id="id"
                                               placeholder="Ingrese el id"
                                               name="id">
                                        </input>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="nombre">Nombre:</label>
                                        <input type="text" class="form-control" id="nombre"
                                               placeholder="Ingrese el nombre"
                                               name="nombre">
                                        </input>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="apellido">Apellido:</label>
                                        <input type="text" class="form-control" id="apellido"
                                               placeholder="Ingrese el apellido"
                                               name="apellido">
                                        </input>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label" for="matricula">Matricula:</label>
                                        <input type="text" class="form-control" id="matricula"
                                                placeholder="Ingrese la matricula"
                                                name="matricula">
                                        </input>
                                    </div>


                                    <button type="submit" class="btn btn-danger"
                                            id="btn-add-new-odontologo">Guardar</button>
                                </form>
                                <div id="response" style="display:none; margin-top:10px">
                                            </div>
                                </div>
                            </div>
                </div>
                `;}

    function deleteForm() {
                container.innerHTML = `
                <div class="row">
                            <div class="col-sm-7" style="background-color:#e6fffa; padding:10px; border-radius:3px">
                                <h3>Eliminar</h3>
                                <form id="delete_odontologo">
                                    <div class="form-group">
                                        <label class="control-label" for="nombre">id:</label>
                                        <input type="text" class="form-control" id="id"
                                               placeholder="Ingrese el id"
                                               name="id" required>
                                        </input>
                                    </div>
                                    <button type="submit" class="btn btn-danger"
                                            id="btn-add-new-odontologo">Guardar</button>
                                </form>
                                <div id="response" style="display:none; margin-top:10px">
                                </div>
                            </div>
                        </div>
                `;}
    });
