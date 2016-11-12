/*
 * Main application controller.
 */
'use strict';

var app = angular.module("creditCardApplicationApp", ["ngRoute", "ui.bootstrap"]);

// Configure application routing
app.config(function($routeProvider) {
            console.log("Bootstrapping AngularJS RouteProvider")
            $routeProvider
                .when('/', {
                    templateUrl: 'views/main.html',
                    controller: 'mainCtrl',
                    controllerAs: 'main'
                })
                .when('/teams', {
                    templateUrl: 'views/teams.html',
                    controller: 'teamsCtrl',
                    controllerAs: 'teams'
                })
                .when('/stages', {
                    templateUrl: 'views/stages.html',
                    controller: 'stagesCtrl',
                    controllerAs: 'stages'
                })
                .when('/task/results', {
                    templateUrl: 'views/results.html',
                    controller: 'resultsCtrl',
                    controllerAs: 'results'
                })
                //TODO: Project the settings resource with RBAC.
                .when('/task/settings', {
                    templateUrl: 'views/settings.html',
                    controller: 'settingsCtrl',
                    controllerAs: 'settings'
                })
                .when('/reviewSilverCard', {
                    templateUrl: 'views/reviewSilverCard.html',
                    // controller: 'reviewSilverCardCtrl',
                    // controllerAs: 'reviewSilverCard'
                })
                .when('/developGoldCard', {
                    templateUrl: 'views/developGoldCard.html',
                    // controller: 'developGoldCardCtrl',
                    // controllerAs: 'developGoldCard'
                })
                .when('/processInstances', {
                    templateUrl: 'views/processInstances.html',
                    controller: 'processInstancesCtrl',
                    controllerAs: 'processInstances'
                 })
                 .otherwise({
                    redirectTo: '/'
                 });
         })
        .config(function($httpProvider) {
            // Enable cross domain calls
            $httpProvider.defaults.useXDomain = true;
        })
        .service('sharedStateService', function() {
            var selectedCard;
            var currentProcessInstance;
            var currentTask;

            return {
                getSelectedCard: function() {
                    return selectedCard;
                },
                setSelectedCard: function(value) {
                    selectedCard = value;
                },
                getCurrentProcessInstance: function() {
                    return currentProcessInstance;
                },
                setCurrentProcessInstance: function(value) {
                    currentProcessInstance = value;
                },
                getCurrentTask: function () {
                    return currentTask;
                },
                setCurrentTask: function (id) {
                    currentTask = id;
                }
            };
        })
        .factory("util", function() {
            return {
                getKieServerUrl: function() {
                    return "http://" + ENV.kieserver_host + ":" + ENV.kieserver_port;
                },
                getCreditCardAppContainer: function() {
                    return ENV.kieserver_containerId;
                },
                getCreditCardProcess: function() {
                    return ENV.kieserver_processId;
                },
                getTaskView: function(taskName) {
                    var taskView
                    switch(taskName) {
                      case "Send Rejection Notification":
                        taskView = "rejectTask";
                        break;
                      case "Develop Wealth (Gold) Card Offer and Card":
                        taskView = "goldCardOfferTask";
                        break;
                      case "Review Retail (Silver) Card Offer and Card":
                        taskView = "silverCardOfferTask";
                        break;
                    }
                    console.log("Selected view: " + taskView);
                    return taskView;
                }
            }
        });
