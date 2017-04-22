Application.$controller("alertsPageController", ["$scope", function ($scope) {
    "use strict";

    $scope.items = ["one", 'two', 'three'];

    /* perform any action on the variables within this block(on-page-load) */
    $scope.onPageVariablesReady = function () {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         */
    };

    /* perform any action on widgets within this block */
    $scope.onPageReady = function () {
        /*
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };
}]);

Application.$controller("liveform1Controller", ["$scope",
    function ($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);
