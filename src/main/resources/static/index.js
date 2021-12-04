angular.module('webapp', []).controller('indexController', function($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
        .then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

    $scope.min = 0;
    $scope.max = 10000;

    $scope.getMinMaxPrice = function (min, max) {
        $http({
            url: contextPath + '/products/between',
            method: 'GET',
            params: {
                min: $scope.min,
                max: $scope.max
            }
        }).then(function (response) {
           $scope.ProductsList = response.data;
        });
    };

    $scope.loadProducts();

});