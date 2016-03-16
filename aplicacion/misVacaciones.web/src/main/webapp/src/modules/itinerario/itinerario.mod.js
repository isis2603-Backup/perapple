(function (ng) {
    var mod = ng.module("itinerarioModule", ["ui.bootstrap"]);

    mod.constant("itinerarioContext", "http://localhost:8080/misVacaciones.web/api/itinerarios");

})(window.angular);
