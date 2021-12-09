angular.module('webapp', []).controller('indexController', function($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.page = 1;

    $scope.getMinMaxPrice = function () {

        $http({
            url: contextPath + '/products/between/page',
            method: 'GET',
            params: {
                min_price: $scope.min_price,
                max_price: $scope.max_price,
                page: $scope.page
            }
        }).then(function (response) {
           $scope.page = response.data.pageable.pageNumber + 1;
           $scope.ProductsList = response.data.content;
        });
    };

    $scope.getMinMaxPrice();

});