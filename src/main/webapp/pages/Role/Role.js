Application.$controller("RolePageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };



    $scope.chips1Change = function($event, $isolateScope, newVal, oldVal) {
        //{
        //check for newVal > oldVal
        //Perform add operation on the difference entity
        //}

        //{
        //check for oldVal>newVal
        //Perform delte operation on the differnce entity
        //}
    };

}]);

Application.$controller("roleGridController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);