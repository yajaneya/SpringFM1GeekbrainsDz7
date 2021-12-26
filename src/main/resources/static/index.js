angular.module('webapp', ['ngStorage']).controller('indexController', function($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app';

    $scope.page = 1;
    $scope.sum = 0;

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.showCurrentUserInfo = function () {
        $http.get('http://localhost:8189/app/api/v1/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS: ' + response.data.username);
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });
    };

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