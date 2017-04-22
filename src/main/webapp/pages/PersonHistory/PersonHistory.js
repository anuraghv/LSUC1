Application.$controller("PersonHistoryPageController", ["$scope", function($scope) {
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


    $scope.LSUCPersonAudDataonSuccess = function(variable, data) {
        $scope.PersonAudData = data.length;
        if (data.length == 0) {
            return;
        }
        diffData(data, "Name Change", "wi wi-person fa-2x");


    };



    function diffData(data, type, iconType) {
        var historyData = {
            "type": "edit",
            "entity": "status",
            "newPropertyValues": [],
            "oldPropertyValues": [],
            "changedby": "Gerald",
            "timestamp": "",
            "icon": ""
        };
        for (var i = 0; i < data.length - 1; i++) {
            var original = data[i],
                latest = data[i + 1];
            _.reduce(original, function(result, val, key) {
                var newobj = {
                    types: "",
                    value: ""
                }
                var oldObj = {
                    types: "",
                    value: ""
                }
                if (!_.isEqual(latest[key], val) && !_.includes(["rev", "revinfo", "revtstmp"], key)) {
                    historyData.type = type;
                    newobj.types = key || "NULL";
                    newobj.value = latest[key] || "NULL";
                    historyData.newPropertyValues.push(newobj);
                    oldObj.types = key || "NULL";
                    oldObj.value = val || "NULL";
                    historyData.oldPropertyValues.push(oldObj);
                    historyData.icon = iconType;
                    if (type == "Status Change") {
                        historyData.timestamp = latest.revtstmp;
                    } else {
                        historyData.timestamp = latest.revinfo.revtstmp;
                    }
                }

            });
            $scope.Variables.personHistoryData.dataSet.push(historyData);
        }
    }




    $scope.LSUCPersonaddresDataonSuccess = function(variable, data) {
        $scope.Personaddres = data.length;
        if (data.length == 0) {
            return;
        }

        diffData(data, "Address Change", "wi wi-location-on fa-2x");
    };


    $scope.LicenseeClassPracticegrouponSuccess = function(variable, data) {
        $scope.Classpracticegroup = data.length;
        if (data.length == 0) {
            return;
        }
        $scope.Variables.personHistoryData.dataSet = [];
        diffData(data.content, "Status Change", "wi wi-pencil fa-2x");
    };

}]);


Application.$controller("grid1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);