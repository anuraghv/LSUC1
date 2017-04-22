Application.$controller("LicensePageController", ["$scope", "$timeout", function($scope, $timeout) {
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



    $scope.addLanguageFormBeforeservicecall = function($event, $operation, $data) {
        $data['licenseepersonlanguagepurposes'] = [{
            languagePurposeFk: $data.languagepurpose,
            licenseeFk: $scope.Variables.LicenseeData.firstRecord.pk
        }]
    };




    $scope.licenseStatusFormBeforeservicecall1 = function($event, $operation, $data, item, currentItemWidgets) {
        if ($data.effectiveToDate == undefined) {
            $data.effectiveToDate = '9999-12-31';
        }
        $scope.Variables.LSUC_ApprovalData.setInput({
            "licenseeFk": $data.licenseeFk,
            "newIsPrimary": $data.isPrimary,
            "newEffectiveFromDate": $data.effectiveFromDate,
            "newEffectiveToDate": $data.effectiveToDate,
            "newClassPracticeGroupFk": $data.classPracticeGroupFk,
            "status": "Updated",
            "licenseeclasspracticegroupPk": $data.pk
        });
        $scope.Variables.LSUC_ApprovalData.insertRecord({}, function() {
            currentItemWidgets.licenseStatusForm.cancel();
        });
        return false;
    };


    $scope.licenseStatusDataonBeforeUpdate = function(variable, inputData) {
        if (inputData && !inputData.licenseeFk) {
            return false;
        }
    };


    $scope.addLicenseeStatusBtnClick = function($event, $isolateScope) {
        $scope.Widgets.addLicenseeStatusForm.new()
    };

    $scope.getAssociatedPracticeGroupsonBeforeUpdate = function(variable, inputData) {
        if ($scope.Widgets.addLicenseeStatusForm.formWidgets.dummyClass.datavalue == undefined) {
            return false;
        }

    };

}]);


Application.$controller("licenseStatusFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.licenseStUpBtnClick = function($event, $isolateScope) {
            $scope.edit();
        };

    }
]);

Application.$controller("licenseDetailsFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.licenseDetUpBtnClick = function($event, $isolateScope) {
            $scope.edit();
        };

    }
]);

Application.$controller("languagedatatableController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.addNewRowAction = function($event) {
            $scope.Widgets.addLanguageForm.new();
        };

    }
]);





Application.$controller("liveform4Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);


Application.$controller("insuranceForm Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("licenseDetailsForm Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid2 Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);



Application.$controller("licenseeinsurancepolicyDialogController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform5Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("addInsuranceFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("addPolicyFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("policyFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("licenseeinsuranceDialogController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.addInsuranceFormBeforeservicecall = function($event, $operation, $data) {
            $data.licenseeinsurancepolicies = [$scope.Widgets.policyForm.dataoutput];
        };

    }
]);


Application.$controller("addLanguageFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("addLicenseeStatusFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);