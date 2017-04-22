Application.$controller("LicenseeListPageController", ["$scope", function($scope) {
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


    $scope.listBtnClick = function($event, $isolateScope) {
        $scope.Widgets.cardBtn.disabled = false;
        $isolateScope.disabled = true;
        $scope.Widgets.licenseeTable.show = true;
        $scope.Widgets.licenseeLiveList.show = false;
    };


    $scope.cardBtnClick = function($event, $isolateScope) {
        $scope.Widgets.listBtn.disabled = false;
        $isolateScope.disabled = true;
        $scope.Widgets.licenseeTable.show = false;
        $scope.Widgets.licenseeLiveList.show = true;
    };

}]);


Application.$controller("licenseeFilterController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("licenseeTableController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);