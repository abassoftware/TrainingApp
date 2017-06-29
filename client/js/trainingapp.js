/*
 * Copyright (c) 2016 abas Software AG (http://www.abas.de).
 */

angular.module("trainingApp", ["aba", "aba-chart", "aba-log", "aba-translation", "aba-table", "aba-customer", "aba-pagination"]);
angular.module("trainingApp").controller("trainingAppCtrl", ["$scope", "$log", "$http", "$location", "abaContext", "abaAlerts", "abaOpenGuiInterface", "customerInterface",
    function ($scope, $log, $http, $location, abaContext, abaAlerts, abaOpenGuiInterface, customerInterface) {
        var link = 'script/public/abas.training.TrainingApp/de.abas.training.TrainingApp.ws.table';
        $scope.data = {
            options: {},
            data: []
        };
        $scope.customer = customerInterface.getCustomer();
        $scope.$watch("customer.details.id", function (nv) {
            if (angular.isDefined(nv)) {
                $http.get(abaContext.getScriptLink() + 'de.abas.training.TrainingApp.ws.table?contact='+nv)
                    .then(function (response) {
                        $scope.data = response.data;
                    })
            }
        });
}]);
