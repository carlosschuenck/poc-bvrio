(function() {
    'use strict';

    angular
        .module('bvrioApp')
        .controller('EnderecoController', EnderecoController);

    EnderecoController.$inject = ['Endereco'];

    function EnderecoController(Endereco) {

        var vm = this;

        vm.enderecos = [];

        loadAll();

        function loadAll() {
            Endereco.query(function(result) {
                vm.enderecos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
