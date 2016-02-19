<%--
  Created by IntelliJ IDEA.
  User: sn6628
  Date: 2/18/2016
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>teampickr</title>
</head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<body>

<div ng-app="myApp" ng-controller="myCtrl">


    <div>
        <div ng-repeat="player in players">
            <input
                    type="checkbox"
                    name="playing[]"
                    value="{{ player.name }}"
                    ng-model="player.playing"
            >
            <span>
                {{ player.name }}
            </span>
        </div>
    </div>

    <div>
        {{ playing }}
    </div>

</div>


<script>
    var app = angular.module('myApp', []);
    app.controller('myCtrl', ['$scope', 'filterFilter', function ($scope) {


        $scope.playing = [];
        $scope.teamWhite = [];
        $scope.teamRed = [];

        // helper method to get selected fruits
        $scope.playing = function playing() {
            return filterFilter($scope.players, {playing: true});
        };

        // watch fruits for changes
        $scope.$watch('players|filter:{playing:true}', function (nv) {
            $scope.playing = nv.map(function (player) {
                return player;
            });
        }, true);

        $scope.checkAll = function () {
            if ($scope.selectedAll) {
                $scope.selectedAll = true;
            } else {
                $scope.selectedAll = false;
            }
            angular.forEach($scope.players, function (player) {
                player.selected = $scope.selectedAll;
            });

        };

        $scope.players = [
            {name: "Sibiao"},
            {name: "Gowri"},
            {name: "Surkhab"},
            {name: "Mike H."},
            {name: "Tanvir"},
            {name: "Peter"},
            {name: "Nick H"},
            {name: "Nik K"},
            {name: "Zareh"},
            {name: "Dom"},
            {name: "Abhishek"},
            {name: "Ed"},
            {name: "Raghu"},
            {name: "Darin"},
            {name: "Rudy"},
            {name: "Anthony"},
            {name: "Chun"},
            {name: "David S."},
            {name: "Marcos"},
            {name: "Al"},
            {name: "Michael S."},
        ];

    }

    ]);


    // curl --get --data-urlencode 'address=320 North Central Ave, Glendale, CA' 'https://maps.googleapis.com/maps/api/geocode/json?key='


</script>
</body>
</html>
