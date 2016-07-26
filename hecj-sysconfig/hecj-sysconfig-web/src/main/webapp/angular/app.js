var routeApp = angular.module('routeApp',['ngRoute']);

routeApp.controller('RouteListCtl',function($scope) {
});
routeApp.controller('RouteDetailCtl',function($scope, $routeParams) {
    $scope.id = $routeParams.id;
});


routeApp.config(['$routeProvider',function ($routeProvider) {
    $routeProvider
        .when('/list', {
            templateUrl: 'route/list.html',
            controller: 'RouteListCtl'
        })
        .when('/list/:id', {
            templateUrl: 'route/detail.html',
            controller: 'RouteDetailCtl'
        })
        .otherwise({
            redirectTo: '/list'
        });
}]);

