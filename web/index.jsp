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
<body
        style="background-color: lightgreen; font-family: Corbel; font-size: large"
>

<div ng-app="myApp" ng-controller="myCtrl">


    <div style="float:left">
        <div ng-repeat="player in players">
            <input
                    type="checkbox"
                    ng-model="player.playing"
            >
            <span>
                {{ player.id }}
            </span>
        </div>
    </div>

    <table style="font-size: xx-large">
        <tr>
            <td style="background-color: red; width: 300px">
                <div  >
                    Red
                </div>
            </td>
            <td style="width: 50px;" ></td>
            <td style="background-color: lightgray; width: 300px">
                <div  >
                    Playing
                </div>
            </td>
            <td style="width: 50px;" ></td>
            <td style="background-color: white; width: 300px">
                <div >
                    White
                </div>
            </td>
        </tr>
        <tr>
            <td >
                <div  >
                </div>
            </td>
            <td  ></td>
            <td >
                <table  >
                    <tr ng-repeat="player in players | filter: { playing: true } ">
                        <td style="color: red">&LessLess; </td>
                        <td> {{ player.id }}</td>
                        <td style="color: white">&GreaterGreater;</td>
                    </tr>
                </table>
            </td>
            <td  ></td>
            <td >
                <div >
                </div>
            </td>
        </tr>
    </table>


</div>


<script>
    var app = angular.module('myApp', []);
    app.controller('myCtrl', ['$scope', 'filterFilter', function ($scope) {


        $scope.teamWhite = [];
        $scope.teamRed = [];

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
            {id: "Sibiao"},
            {id: "Gowri"},
            {id: "Surkhab"},
            {id: "MikeH"},
            {id: "Tanvir"},
            {id: "Peter"},
            {id: "NickH"},
            {id: "NikK"},
            {id: "Zareh"},
            {id: "Dom"},
            {id: "Abhishek"},
            {id: "Ed"},
            {id: "Raghu"},
            {id: "Darin"},
            {id: "Rudy"},
            {id: "Anthony"},
            {id: "Chun"},
            {id: "DavidS"},
            {id: "Marcos"},
            {id: "Al"},
            {id: "MichaelS"},
        ];

    }

    ]);


    // curl --get --data-urlencode 'address=320 North Central Ave, Glendale, CA' 'https://maps.googleapis.com/maps/api/geocode/json?key='


</script>
</body>
</html>
