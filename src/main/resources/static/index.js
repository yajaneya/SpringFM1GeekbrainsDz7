angular.module('webapp', []).controller('indexController', function($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.min = 0;
    $scope.max = 10000;
    $scope.page = 1;

    $scope.getMinMaxPrice = function (min, max, page) {
        $http({
            url: contextPath + '/products/between/page',
            method: 'GET',
            params: {
                min: $scope.min,
                max: $scope.max,
                page: $scope.page
            }
        }).then(function (response) {
           $scope.ProductsList = response.data;
        });
    };

    $scope.getMinMaxPrice($scope.min, $scope.max, $scope.page);

});