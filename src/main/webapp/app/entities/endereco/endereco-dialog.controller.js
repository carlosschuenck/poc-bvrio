(function() {
    'use strict';

    angular
        .module('bvrioApp')
        .controller('EnderecoDialogController', EnderecoDialogController);

    EnderecoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Endereco', 'Cliente', 'Estado'];

    function EnderecoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Endereco, Cliente, Estado) {
        var vm = this;

        vm.endereco = entity;
        vm.clear = clear;
        vm.save = save;
        vm.clientes = Cliente.query();
        vm.estados = Estado.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.endereco.id !== null) {
                Endereco.update(vm.endereco, onSaveSuccess, onSaveError);
            } else {
                Endereco.save(vm.endereco, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('bvrioApp:enderecoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
