(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);


        mod.controller("controlador", function($scope, sharedProperties){

            $scope.usuario= "Perapple";

            $scope.contrasena = "Perapple";
            $scope.correo = "perapple@gmail.com";
            $scope.itinerario = "";
            $scope.itinerarios = [];

            $scope.agregar = function(itinerario){
            $scope.itinerarios.push(itinerario);
             $scope.itinerario = "";
        };

            $scope.objectValue = sharedProperties.getObject();
            $scope.setString = function(newValue) {
                $scope.objectValue.data = newValue;
                sharedProperties.setString(newValue);
               };

        });

         mod.controller("ctrl", function($scope, sharedProperties){
        $scope.nueva_ciudad = "";
        $scope.ciudades = ["Bogotá","Bucaramanga", "Cali" ];
        $scope.nuevo_evento="";
        $scope.eventos=[ "Festival Estereo Picnic", "Festival Internacional de Teatro" , "Fiesta Andres" ];
        $scope.sitios = ["Andrés Carne de Res", "Museo del Oro", "Monserrate"];
        $scope.stringValue = sharedProperties.getString();
        $scope.objectValue = sharedProperties.getObject().data;

        $scope.agregar_ciudad = function(){

          $scope.ciudades.push(prompt("¿Cuál es la nueva ciudad que desea agregar...?(por ahora luego se cambia a pop-Up decente)"));
          $scope.nueva_ciudad = "";
        };
        $scope.agregar_evento= function(){
            $scope.eventos.push(prompt("¿Cuál es el nuevo evento/sitio que desea agregar...?(por ahora luego se cambia a pop-Up decente)"));
            $scope.nuevo_evento="";
        };

        $scope.agregar_sitio= function(){
            $scope.sitios.push(prompt("¿Cuál es el nuevo evento/sitio que desea agregar...?(por ahora luego se cambia a pop-Up decente)"));
           };


        $scope.detalles = function(ciudad){
          window.alert("Actualizacion de eventos y sitios de interes segun ciudad...");
        };

        $scope.detalles_evento = function(evento){
          window.alert("Detalles del evento con pop-Up...");
        };

                $scope.calificar_evento = function(evento){
          window.alert("Calificación del evento con pop-Up...");
        };

                $scope.detalles_sitio = function(sitio){
          window.alert("Detalles del sitio con pop-Up...");
        };

                $scope.calificar_sitio = function(sitio){
          window.alert("Calificación del sitio con pop-Up...");
        };


  });

  mod.service('sharedProperties', function() {
    var stringValue = 'Itinerario';
    var objectValue = {
        data: 'test object value'
    };

    return {
        getString: function() {
            return stringValue;
        },
        setString: function(value) {
            stringValue = value;
        },
        getObject: function() {
            return objectValue;
        }
    };
});

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/home");
            $stateProvider
                    .state('itinerario', {
                        url: '/itinerario',
                        templateUrl: "./modules/itinerario/itinerario.tpl.html"
                    })
                    .state('home', {
                        url: '/home',
                        templateUrl: "./modules/home/home.tpl.html"
                    })
                    .state('ciudad', {
                        url: '/ciudad',
                        templateUrl: "./modules/ciudad/ciudad.tpl.html"
                    });
        }]);

})(window.angular);



/**
 * @preserve
 * Project: Bootstrap Hover Dropdown
 * Author: Cameron Spear
 * Version: v2.2.1
 * Contributors: Mattia Larentis
 * Dependencies: Bootstrap's Dropdown plugin, jQuery
 * Description: A simple plugin to enable Bootstrap dropdowns to active on hover and provide a nice user experience.
 * License: MIT
 * Homepage: http://cameronspear.com/blog/bootstrap-dropdown-on-hover-plugin/
 */
;(function ($, window, undefined) {
    // outside the scope of the jQuery plugin to
    // keep track of all dropdowns
    var $allDropdowns = $();

    // if instantlyCloseOthers is true, then it will instantly
    // shut other nav items when a new one is hovered over
    $.fn.dropdownHover = function (options) {
        // don't do anything if touch is supported
        // (plugin causes some issues on mobile)
        if('ontouchstart' in document) return this; // don't want to affect chaining

        // the element we really care about
        // is the dropdown-toggle's parent
        $allDropdowns = $allDropdowns.add(this.parent());

        return this.each(function () {
            var $this = $(this),
                $parent = $this.parent(),
                defaults = {
                    delay: 500,
                    hoverDelay: 0,
                    instantlyCloseOthers: true
                },
                data = {
                    delay: $(this).data('delay'),
                    hoverDelay: $(this).data('hover-delay'),
                    instantlyCloseOthers: $(this).data('close-others')
                },
                showEvent   = 'show.bs.dropdown',
                hideEvent   = 'hide.bs.dropdown',
                // shownEvent  = 'shown.bs.dropdown',
                // hiddenEvent = 'hidden.bs.dropdown',
                settings = $.extend(true, {}, defaults, options, data),
                timeout, timeoutHover;

            $parent.hover(function (event) {
                // so a neighbor can't open the dropdown
                if(!$parent.hasClass('open') && !$this.is(event.target)) {
                    // stop this event, stop executing any code
                    // in this callback but continue to propagate
                    return true;
                }

                openDropdown(event);
            }, function () {
                // clear timer for hover event
                window.clearTimeout(timeoutHover)
                timeout = window.setTimeout(function () {
                    $this.attr('aria-expanded', 'false');
                    $parent.removeClass('open');
                    $this.trigger(hideEvent);
                }, settings.delay);
            });

            // this helps with button groups!
            $this.hover(function (event) {
                // this helps prevent a double event from firing.
                // see https://github.com/CWSpear/bootstrap-hover-dropdown/issues/55
                if(!$parent.hasClass('open') && !$parent.is(event.target)) {
                    // stop this event, stop executing any code
                    // in this callback but continue to propagate
                    return true;
                }

                openDropdown(event);
            });

            // handle submenus
            $parent.find('.dropdown-submenu').each(function (){
                var $this = $(this);
                var subTimeout;
                $this.hover(function () {
                    window.clearTimeout(subTimeout);
                    $this.children('.dropdown-menu').show();
                    // always close submenu siblings instantly
                    $this.siblings().children('.dropdown-menu').hide();
                }, function () {
                    var $submenu = $this.children('.dropdown-menu');
                    subTimeout = window.setTimeout(function () {
                        $submenu.hide();
                    }, settings.delay);
                });
            });

            function openDropdown(event) {
                if($this.parents(".navbar").find(".navbar-toggle").is(":visible")) {
                    // If we're inside a navbar, don't do anything when the
                    // navbar is collapsed, as it makes the navbar pretty unusable.
                    return;
                }

                // clear dropdown timeout here so it doesnt close before it should
                window.clearTimeout(timeout);
                // restart hover timer
                window.clearTimeout(timeoutHover);
                
                // delay for hover event.  
                timeoutHover = window.setTimeout(function () {
                    $allDropdowns.find(':focus').blur();

                    if(settings.instantlyCloseOthers === true)
                        $allDropdowns.removeClass('open');
                    
                    // clear timer for hover event
                    window.clearTimeout(timeoutHover);
                    $this.attr('aria-expanded', 'true');
                    $parent.addClass('open');
                    $this.trigger(showEvent);
                }, settings.hoverDelay);
            }
        });
    };

    $(document).ready(function () {
        // apply dropdownHover to all elements with the data-hover="dropdown" attribute
        $('[data-hover="dropdown"]').dropdownHover();
    });
})(jQuery, window);