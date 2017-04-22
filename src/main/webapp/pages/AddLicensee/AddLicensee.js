Application.$controller("AddLicenseePageController", ["$scope", function($scope) {
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
        $scope.Widgets.licenseDate.mindate = moment().subtract(40, 'year').format('YYYY-MM-DD');
    };


    $scope.personLiveFormBeforeservicecall = function($event, $operation, $data) {

        if ($scope.Widgets.licenseeclasprctcegrpLiveForm.dataoutput.effectiveToDate == undefined) {
            $scope.Widgets.licenseeclasprctcegrpLiveForm.dataoutput.effectiveToDate = "2999-12-31";
        }
        $data.addresses = [$scope.Widgets.addresses.dataoutput];
        $data.licensees = [$scope.Widgets.licensees.dataoutput];
        $data.personroles = [$scope.Widgets.personroles.dataoutput];
        //$data.licensees[0].licenseeclasspracticegroups = [$scope.Widgets.licenseeclasprctcegrpLiveForm.dataoutput];
    };


    $scope.personLiveFormSuccess = function($event, $operation, $data) {
        $scope.Variables.LSUC_ApprovalData.setInput({
            "licenseeFk": $data.licensees[0].pk,
            "newIsPrimary": "Y",
            "newEffectiveFromDate": $scope.Widgets.licenseeclasprctcegrpLiveForm.dataoutput.effectiveFromDate,
            "newEffectiveToDate": $scope.Widgets.licenseeclasprctcegrpLiveForm.dataoutput.effectiveToDate,
            "newClassPracticeGroupFk": $scope.Widgets.licenseeclasprctcegrpLiveForm.dataoutput.classpraticegroup.pk,
            "status": "Created"
        });
        $scope.Variables.LSUC_ApprovalData.insertRecord();
    };


    $scope.getAssociatedPracticeGroupsonBeforeUpdate = function(variable, inputData) {
        if ($scope.Widgets.licenseeclasprctcegrpLiveForm.formWidgets.dummyClass.datavalue == undefined) {
            return false;
        }

    };


    $scope.LSUCInsertPersonDataonSuccess = function(variable, data) {
        $scope.Variables.NewPerson.dataSet.dataValue = data.pk;
        $scope.Variables.sendEmail.setInput({
            "licenseeNumber": data.licensees[0].licenseeNumber,
            "licenseeName": data.firstName + " " + data.lastName,
            "personPk": data.pk
        });
        $scope.Variables.sendEmail.update();
    };


}]);


Application.$controller("personLiveFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("addressesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("licenseesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("licenseeclasprctcegrpLiveFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("personrolesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);