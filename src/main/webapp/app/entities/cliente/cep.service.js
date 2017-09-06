(function() {
    'use strict';
    angular
        .module('bvrioApp')
        .factory('Cep', Cep);

    Cep.$inject = ['$resource'];

    function Cep ($resource) {
        var resourceUrl =  'https://webmaniabr.com/api/1/cep/:cep/?app_key=l6xH5UVqx3QjIqpr7XdLhWWX2ZbSCDS3&app_secret=Nj4QGvhCTeXqvFMHAI38VsMululNs3aJL4RXesNk09SyrnxE';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: false}
        });
    }
})();
