Application.$controller("ApprovalsPageController", ["$scope", function($scope) {
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


    $scope.approveBtnClick = function($event, $isolateScope, item, currentItemWidgets) {
        debugger
        if (item.status == "Created") {
            $scope.Variables.approveNewRequest.update();
        } else if (item.status == "Updated") {
            $scope.Variables.approvedEditRequest.update();
        }
    };


    $scope.approvedEditRequestonSuccess = function(variable, data) {
        if (data.result >= 1) {
            $scope.Variables.updateStatus.setInput("status", "Approved");
            $scope.Variables.updateStatus.setInput("pk", variable.dataBinding.approvalPk);
            $scope.Variables.updateStatus.update();
        }
    };


    $scope.approveNewRequestonSuccess = function(variable, data) {
        if (data.result >= 1) {
            $scope.Variables.updateStatus.setInput("status", "Approved");
            $scope.Variables.updateStatus.setInput("pk", variable.dataBinding.primaryKey);
            $scope.Variables.updateStatus.update();
        }
    };


    $scope.rejectBtnClick = function($event, $isolateScope, item, currentItemWidgets) {
        $scope.Variables.updateStatus.setInput("status", "Rejected");
        $scope.Variables.updateStatus.setInput("pk", item.pk);
        $scope.Variables.updateStatus.update();
    };
}]);