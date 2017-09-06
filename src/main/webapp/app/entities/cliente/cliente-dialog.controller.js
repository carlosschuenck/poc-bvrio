(function() {
    'use strict';

    angular
        .module('bvrioApp')
        .controller('ClienteDialogController', ClienteDialogController);

    ClienteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Cliente', 'Endereco', 'Estado','Cep'];

    function ClienteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Cliente, Endereco, Estado, Cep) {
        var vm = this;

        vm.cliente = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.$onInit = onInit;
        vm.enderecos = [];
        var dataAtual = new Date();
        var dataMaxima = new Date(dataAtual.getFullYear()-16, dataAtual.getMonth(), dataAtual.getDate());
        vm.dateOptions = {
            maxDate: dataMaxima,
            initDate: dataMaxima
        }

        $q.all([vm.cliente.$promise, vm.enderecos.$promise]).then(function() {
            if (!vm.cliente.enderecoId) {
                return $q.reject();
            }
            return Endereco.get({id : vm.cliente.enderecoId}).$promise;
        }).then(function(endereco) {
            vm.enderecos.push(endereco);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function onInit (){
            Estado.query(function(result) {
                vm.estados = result;
                vm.searchQuery = null;
            });
        }

        vm.buscarPorCep = buscarPorCep;
        function buscarPorCep(){
            if(vm.cliente.endereco.cep !== undefined && vm.cliente.endereco.cep !== null && vm.cliente.endereco.cep.length > 7){
                Cep.query(
                    {
                        cep: vm.cliente.endereco.cep
                    },
                    function (response) {
                        if(response.endereco !== undefined && response.endereco !== null && response.endereco !== ""){
                            vm.cliente.endereco.logradouro = response.endereco;
                        }

                        if(response.cidade!== undefined && response.cidade !== null && response.cidade !== ""){
                            vm.cliente.endereco.cidade = response.cidade;
                        }

                        if(response.uf !== undefined && response.uf !== null && response.uf !== ""){
                            vm.estados.forEach(function (estado) {
                                if(estado.uf == response.uf){
                                    vm.cliente.endereco.estado = estado;
                                }
                            });
                        }
                    },
                    function (erro) {
                        console.log("CEP ERRO",erro);
                    }
                );
            }
        }

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }


        function save () {
            vm.isSaving = true;
            if (vm.cliente.id !== null) {
                Cliente.update(vm.cliente, onSaveSuccess, onSaveError);
            } else {
                Cliente.save(vm.cliente, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('bvrioApp:clienteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError (erro) {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataNascimento = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
