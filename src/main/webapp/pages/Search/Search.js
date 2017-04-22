Application.$controller("SearchPageController", ["$scope", function($scope) {
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

    function doSearch() {
        var q = $scope.Widgets.searchtext.datavalue;

        function qs(fields) {
            var obj = {};
            _.forEach(fields, function(field) {
                obj[field] = {
                    value: q
                }
            });
            return obj;
        }

        $scope.Variables.LSUCPersonData.listRecords({
            'logicalOp': 'OR',
            filterFields: qs(['firstName', 'lastName', 'middleNames', 'commonlyReferredToName', 'mailingName'])
        });
        $scope.Variables.LSUCBusinessData.listRecords({
            'logicalOp': 'OR',
            filterFields: qs(['businessNumber', 'businessName', 'alsoKnownAs'])
        });
        $scope.Variables.LSUCLicenseeData.listRecords({
            'logicalOp': 'OR',
            filterFields: qs(['licenseeNumber', "person.firstName", "person.lastName", 'person.commonlyReferredToName'])
        });
    }

    $scope.searchtextBlur = function($event, $isolateScope) {
        doSearch();
    };

    $scope.searchButtonClick = function($event, $isolateScope) {
        doSearch();
    };

}]);