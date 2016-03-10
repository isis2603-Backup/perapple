(function (ng) {
    var mod = ng.module("moduloCiudad", ["ui.bootstrap"]);

    mod.constant("ciudadContext", "http://localhost:8080/misVacaciones.web/api/ciudades");

})(window.angular);
