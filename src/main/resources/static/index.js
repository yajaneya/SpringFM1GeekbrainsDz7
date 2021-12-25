angular.module('webapp', []).controller('indexController', function($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.page = 1;
    $scope.sum = 0;

    $scope.loadProducts = function () {

        $http({
            url: contextPath + '/api/v1/products/',
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

    $scope.loadCart = function () {

        $http({
            url: contextPath + '/api/v1/cart/',
            method: 'GET',
        }).then(function (response) {
           $scope.Cart = response.data;
        });
    };

    $scope.toCart = function (prodId) {
       $http.post(contextPath + '/api/v1/cart/', prodId)
            .then(function (response) {
                $scope.getSum();
                $scope.loadCart();
            });
    };

    $scope.minusQuantity = function(prodId) {
       $http.delete(contextPath + '/api/v1/cart/' + prodId)
            .then(function (response) {
                $scope.getSum();
                $scope.loadCart();
            });
    }

    $scope.plusQuantity = function(prodId) {
       $http.put(contextPath + '/api/v1/cart/', prodId)
            .then(function (response) {
                $scope.getSum();
                $scope.loadCart();
            });
    }

    $scope.getSum = function() {
        $http({
            url: contextPath + '/api/v1/cart/sum',
            method: 'GET',
        }).then(function (response) {
            $scope.sum = response.data;
        });
    }

    $scope.loadProducts();
    $scope.loadCart();

});