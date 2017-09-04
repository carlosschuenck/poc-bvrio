(function() {
    'use strict';

    angular
        .module('bvrioApp')
        .controller('EstadoDetailController', EstadoDetailController);

    EstadoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Estado', 'Endereco'];

    function EstadoDetailController($scope, $rootScope, $stateParams, previousState, entity, Estado, Endereco) {
        var vm = this;

        vm.estado = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('bvrioApp:estadoUpdate', function(event, result) {
            vm.estado = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
