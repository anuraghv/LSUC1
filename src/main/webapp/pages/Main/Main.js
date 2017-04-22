Application.$controller("MainPageController", ["$scope", function($scope) {
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


    $scope.LicenseePracticegroupAudonSuccess = function(variable, data) {
        PersonHistory(data, "Status Change");
    };


    $scope.PersonaddressAudonSuccess = function(variable, data) {
        PersonHistory(data, "Address Change");
    };


    $scope.PersonAudonSuccess = function(variable, data) {
        $scope.Variables.PersonHistoryData.dataSet = []
        debugger;
        PersonHistory(data, "Name Change");
    };

    function PersonHistory(data, type) {
        _.forEach(data, function(obj) {
            var HistoryData = {
                "type": "",
                "username": "",
                "timeStamp": ""
            }
            debugger;
            HistoryData.type = type;
            HistoryData.username = obj.firstName + " " + obj.lastName;
            HistoryData.timeStamp = obj.revinfo.revtstmp;
            $scope.Variables.PersonHistoryData.dataSet.push(HistoryData);
        });



    }


}]);