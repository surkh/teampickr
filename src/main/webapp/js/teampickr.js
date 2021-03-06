/** google global namespace for Google projects. */
var google = google || {};

/** devrel namespace for Google Developer Relations projects. */
google.devrel = google.devrel || {};

/** samples namespace for DevRel sample code. */
google.devrel.samples = google.devrel.samples || {};

/** hello namespace for this sample. */
google.devrel.samples.hello = google.devrel.samples.hello || {};

/**
 * Client ID of the application (from the APIs Console).
 * @type {string}
 */
google.devrel.samples.hello.CLIENT_ID =
    '180082767021-ivmhvviqv23ro2s0cqsrhgernvnhqcqa.apps.googleusercontent.com';

/**
 * Scopes used by the application.
 * @type {string}
 */
google.devrel.samples.hello.SCOPES =
    'https://www.googleapis.com/auth/userinfo.email';

/**
 * Whether or not the user is signed in.
 * @type {boolean}
 */
google.devrel.samples.hello.signedIn = false;

/**
 * Loads the application UI after the user has completed auth.
 */
google.devrel.samples.hello.userAuthed = function() {
    var request = gapi.client.oauth2.userinfo.get().execute(function(resp) {
        if (!resp.code) {
            google.devrel.samples.hello.signedIn = true;
            document.getElementById('signinButton').innerHTML = 'Sign out';
            document.getElementById('authedGreeting').disabled = false;
        }
    });
};

/**
 * Handles the auth flow, with the given value for immediate mode.
 * @param {boolean} mode Whether or not to use immediate mode.
 * @param {Function} callback Callback to call on completion.
 */
google.devrel.samples.hello.signin = function(mode, callback) {
    gapi.auth.authorize({client_id: google.devrel.samples.hello.CLIENT_ID,
            scope: google.devrel.samples.hello.SCOPES, immediate: mode},
        callback);
};

/**
 * Presents the user with the authorization popup.
 */
google.devrel.samples.hello.auth = function() {
    if (!google.devrel.samples.hello.signedIn) {
        google.devrel.samples.hello.signin(false,
            google.devrel.samples.hello.userAuthed);
    } else {
        google.devrel.samples.hello.signedIn = false;
        document.getElementById('signinButton').innerHTML = 'Sign in';
        document.getElementById('authedGreeting').disabled = true;
    }
};

/**
 * Prints a greeting to the greeting log.
 * param {Object} greeting Greeting to print.
 */
google.devrel.samples.hello.print = function(greeting) {
    var element = document.createElement('div');
    element.classList.add('row');
    element.innerHTML = greeting.message;
    document.getElementById('outputLog').appendChild(element);
};

/**
 * Gets a numbered greeting via the API.
 * @param {string} id ID of the greeting.
 */
google.devrel.samples.hello.getGreeting = function(id) {
    gapi.client.helloworld.greetings.getGreeting({'id': id}).execute(
        function(resp) {
            if (!resp.code) {
                google.devrel.samples.hello.print(resp);
            } else {
                window.alert(resp.message);
            }
        });
};

/**
 * Lists greetings via the API.
 */
google.devrel.samples.hello.listGreeting = function() {
    gapi.client.helloworld.teams.listGreeting().execute(
        function(resp) {
            if (!resp.code) {
                resp.items = resp.items || [];
                for (var i = 0; i < resp.items.length; i++) {
                    google.devrel.samples.hello.print(resp.items[i]);
                }
            }
        });
};

/**
 * Gets a greeting a specified number of times.
 * @param {string} greeting Greeting to repeat.
 * @param {string} count Number of times to repeat it.
 */
google.devrel.samples.hello.multiplyGreeting = function(
    greeting, times) {
    gapi.client.helloworld.greetings.multiply({
        'message': greeting,
        'times': times
    }).execute(function(resp) {
        if (!resp.code) {
            google.devrel.samples.hello.print(resp);
        }
    });
};

/**
 * Greets the current user via the API.
 */
google.devrel.samples.hello.authedGreeting = function(id) {
    gapi.client.helloworld.greetings.authed().execute(
        function(resp) {
            google.devrel.samples.hello.print(resp);
        });
};

/**
 * Enables the button callbacks in the UI.
 */
google.devrel.samples.hello.enableButtons = function() {
    // document.getElementById('getGreeting').onclick = function() {
    //   google.devrel.samples.hello.getGreeting(
    //       document.getElementById('id').value);
    // }

    // document.getElementById('listGreeting').onclick = function() {
    //     google.devrel.samples.hello.listGreeting();
    // }

    // document.getElementById('multiplyGreetings').onclick = function() {
    //   google.devrel.samples.hello.multiplyGreeting(
    //       document.getElementById('greeting').value,
    //       document.getElementById('count').value);
    // }

    // document.getElementById('authedGreeting').onclick = function() {
    //   google.devrel.samples.hello.authedGreeting();
    // }

    // document.getElementById('signinButton').onclick = function() {
    //   google.devrel.samples.hello.auth();
    // }
};

/**
 * Initializes the application.
 * @param {string} apiRoot Root of the API's path.
 */
google.devrel.samples.hello.init = function(apiRoot) {
    // Loads the OAuth and helloworld APIs asynchronously, and triggers login
    // when they have completed.
    var apisToLoad;
    var callback = function() {
        if (--apisToLoad == 0) {
            google.devrel.samples.hello.enableButtons();
            google.devrel.samples.hello.signin(true,
                google.devrel.samples.hello.userAuthed);
        }
    }

    apisToLoad = 2; // must match number of calls to gapi.client.load()
    gapi.client.load('helloworld', 'v1', callback, apiRoot);
    gapi.client.load('oauth2', 'v2', callback);
};




var app = angular.module('myApp', []);



app.controller('myCtrl', ['$scope', 'filterFilter', 'gapiService', function ($scope, $window, gapiService) {

    var postInitiation = function() {
        $scope.load();
        $scope.loadMessage();
        

    }

    window.initGapi = function(url) {
        gapiService.initGapi(postInitiation,url);
    }

    $scope.teamWhite = [];
    $scope.teamRed = [];

    $scope.message = "Hello";

    $scope.autoAssign = function(){
        var isRed = true;
        angular.forEach($scope.players, function (player, key) {
            if (player.playing && !player.team) {
                player.team = isRed ? 'red' : 'white';
                isRed = !isRed;
            }
        })
    };

    $scope.assignTeam = function(player,team){
        player.team = team;
    };

    $scope.rankSwap = function (player1, player2) {
        if (player2) {
            var swap = player1.rank;
            player1.rank = player2.rank;
            player2.rank = swap;
        }
    };

    $scope.players = [
    ];

    

    var num = 6;

    $scope.items = [5,4,3,2,1];

    $scope.addNum = function (){
        $scope.items.unshift(num++);
    };

    $scope.remNum = function (){
        $scope.items.shift();
    }

    $scope.load = function () {
        $scope.message = "Loading...";
        $scope.$applyAsync();
        gapi.client.helloworld.teams.listGreeting().execute(
            function(resp) {
                if (!resp.code) {
                    $scope.players = [];
                    angular.forEach(resp.items, function (item, key) {
                        $scope.players.push(
                            {
                                id: item.id,
                                playing: true,
                                rank: key
                            }
                        )
                    });
                    $scope.message = "Loaded...";
                    $scope.$applyAsync();
                }
            });
    };
    
    $scope.loadMessage = function () {
        $scope.message = "Loading...";
        $scope.$applyAsync();
        gapi.client.helloworld.teams.myMessage().execute(
            function(resp) {
                if (!resp.code) {
                    $scope.message = "";
                    angular.forEach(resp.items, function (item, key) {
                        $scope.message += item;
                    });
                    $scope.$applyAsync();
                }
            });
    }
    
    // $scope.load();

    

}


]);

app.service('gapiService', function() {
    this.initGapi = function(postInitiation, apiRoot) {
        var apisToLoad;
        var callback = function() {
            if (--apisToLoad == 0) {
                google.devrel.samples.hello.enableButtons();
                google.devrel.samples.hello.signin(true,
                    google.devrel.samples.hello.userAuthed);
                postInitiation();
            }
        }

        apisToLoad = 2; // must match number of calls to gapi.client.load()
        gapi.client.load('helloworld', 'v1', callback, apiRoot);
        gapi.client.load('oauth2', 'v2', callback);
    }
});


var init = function() {
    window.initGapi('//' + window.location.host + '/_ah/api');
}