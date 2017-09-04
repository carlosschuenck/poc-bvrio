'use strict';

describe('Controller Tests', function() {

    describe('Endereco Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockEndereco, MockCliente, MockEstado;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockEndereco = jasmine.createSpy('MockEndereco');
            MockCliente = jasmine.createSpy('MockCliente');
            MockEstado = jasmine.createSpy('MockEstado');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Endereco': MockEndereco,
                'Cliente': MockCliente,
                'Estado': MockEstado
            };
            createController = function() {
                $injector.get('$controller')("EnderecoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'bvrioApp:enderecoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
