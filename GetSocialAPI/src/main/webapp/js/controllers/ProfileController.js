'use strict';

GetSocial.controller('ProfileController', function ProfileController($scope, UserService, $document) {

    $scope.user = {};
    var graph = {
        nodes: [],
        edges: []
    };

    $scope.init = function() {
        UserService.getUserProfile().then(function(response) {
            $scope.user = convertObjectToUser(response.data);
            graph.nodes[0] = $scope.user;
            graph.nodes[0].label = $scope.user.name;
            graph.nodes[0].id = 0;
            graph.nodes[0].fixed = true;

            var i = 1;
            UserService.getUserFriends().then(function(response) {
                _.each(response.data, function(friend) {
                    var myFriend = convertObjectToUser(friend);
                    graph.nodes[i] = myFriend;
                    graph.nodes[i].label = myFriend.name;
                    graph.nodes[i].id = i;
                    i++;
                    var myFriend = convertObjectToUser(friend);
                    graph.nodes[i] = myFriend;
                    graph.nodes[i].label = myFriend.name;
                    graph.nodes[i].id = i;
                    i++;
                    var myFriend = convertObjectToUser(friend);
                    graph.nodes[i] = myFriend;
                    graph.nodes[i].label = myFriend.name;
                    graph.nodes[i].id = i;
                    i++;
                    var myFriend = convertObjectToUser(friend);
                    graph.nodes[i] = myFriend;
                    graph.nodes[i].label = myFriend.name;
                    graph.nodes[i].id = i;
                    i++;
                });
                drawGraph();
            });
        });
    };

    $scope.init();

    function convertObjectToUser(data){
        var user = {
            name: '',
            image: ''
        };
        if(data.firstName !== undefined && data.lastName !== undefined) {
            user.name = data.firstName + ' ' + data.lastName;
        } else {
            user.name = data.name;
        }
        user.image = 'http://graph.facebook.com/'+data.id+'/picture?width=250';
        user.shape = 'circularImage';
        user.size=70;
        user.properties = [];
        return user;
    }

    function drawGraph(){
        var index;
        var j = 0;
        var options = {};
        options.interaction = {};
        options.interaction.dragView = false;
        for	(index = 1; index < graph.nodes.length; index++) {
            graph.edges[j] = {};
            graph.edges[j].id = j;
            graph.edges[j].from = '0';
            graph.edges[j].to = index;
            graph.edges[j].properties = [];
            graph.edges[j].type = 'FRIEND_WITH';
            graph.edges[j].length = 250;
            j++;
        }

        var container = document.getElementById('graph');
        var network = new vis.Network(container, graph, options);
    }
});