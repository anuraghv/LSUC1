Application.$controller("LicenseePageController", ["$scope", "$timeout", function($scope, $timeout) {
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


    $scope.liveform1Beforeservicecall = function($event, $operation, $data) {
        if ($scope.Widgets.licenseeclasspracticegroupsForm.dataoutput.effectiveToDate == undefined) {
            $scope.Widgets.licenseeclasspracticegroupsForm.dataoutput.effectiveToDate = "2999-12-31";
        }
        $data['licenseeclasspracticegroups'] = [$scope.Widgets.licenseeclasspracticegroupsForm.dataoutput];
    };

    $scope.getAssociatedPracticeGroupsonBeforeUpdate = function(variable, inputData) {
        if ($scope.Widgets.licenseeclasspracticegroupsForm.formWidgets.dummyClass.datavalue == undefined) {
            return false;
        }

    };


    $scope.addLicenseBtnClick = function($event, $isolateScope) {
        $scope.Widgets.addLicensee.new();
        $timeout(function() {
            $scope.Widgets.licenseeNumber.focus()
        });

    };

}]);


Application.$controller("licenseeFilterController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("addressGridController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("licenseeLiveFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("personInfoLiveFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("addressLiveFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("phoneGridController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("phoneLiveformController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("emailGridController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("emailLiveFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("socialGridController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("socialLiveFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);





Application.$controller("roleGridController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("roleLiveFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("addLicenseeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("licenseeclasspracticegroupsFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);