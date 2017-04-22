Application.$controller("LanguagePageController", ["$scope", function($scope) {
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


    $scope.LSUCPersonlanguagecommunicationchannelDataonBeforeUpdate = function(variable, inputData) {
        if (inputData) {
            inputData['personlanguage.personFk'] = {
                'value': $scope.pageParams.id
            };
        }
    };


    $scope.liveform2Beforeservicecall = function($event, $operation, $data) {
        $data.personlanguagecommunicationchannels = [{
            communicationChannelFk: $data.Communicationchannel,
            proficiencyLevelFk: $data.Proficiencylevel
        }];
    };


    $scope.liveform3Beforeservicecall = function($event, $operation, $data) {
        $data[personlanguage] = {
            languageFk: 41,
            personFk: 1
        }
    };

}]);





Application.$controller("grid2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customButtonAction = function($event) {
            $scope.Widgets.liveform2.new();
        };

    }
]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);