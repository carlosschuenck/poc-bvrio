(function() {
    'use strict';
    angular
        .module('bvrioApp')
        .factory('Cliente', Cliente);

    Cliente.$inject = ['$resource', 'DateUtils'];

    function Cliente ($resource, DateUtils) {
        var resourceUrl =  'api/clientes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataNascimento = DateUtils.convertLocalDateFromServer(data.dataNascimento);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dataNascimento = DateUtils.conveRrtLocalDateToServer(copy.dataNascimento);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dataNascimento = DateUtils.convertLocalDateToServer(copy.dataNascimento);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
