(function() {
    'use strict';

    angular
        .module('bvrioApp')
        .controller('EnderecoDetailController', EnderecoDetailController);

    EnderecoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Endereco', 'Cliente', 'Estado'];

    function EnderecoDetailController($scope, $rootScope, $stateParams, previousState, entity, Endereco, Cliente, Estado) {
        var vm = this;

        vm.endereco = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('bvrioApp:enderecoUpdate', function(event, result) {
            vm.endereco = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
