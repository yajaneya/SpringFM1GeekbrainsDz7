angular.module('webapp', []).controller('indexController', function($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.a = '12';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
        .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.loadProducts();

});