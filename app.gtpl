layout "$layout",
        true, //layout inherits model
        title: 'Developer Training Application',
        bodyContents: contents {
            div(id: "ng-app", 'ng-app': 'trainingApp', 'ng-controller': 'trainingAppCtrl', class: 'row  ng-cloak', 'ng-init': "appConfig = ${appConfig}") {
                    includeUnescaped( '../public/apps/abas/training/TrainingApp/client/partials/main.html')
            }
        }
