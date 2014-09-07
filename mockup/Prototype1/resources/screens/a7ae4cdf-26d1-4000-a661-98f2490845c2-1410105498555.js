jQuery("#simulation")
  .on("click", ".s-a7ae4cdf-26d1-4000-a661-98f2490845c2 .click", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Image_2")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/d12245cc-1680-458d-89dd-4f0d7fb22724"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Button_2")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-a7ae4cdf-26d1-4000-a661-98f2490845c2 #s-Button_2": {
                      "attributes": {
                        "background-color": "#2C94BB",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-a7ae4cdf-26d1-4000-a661-98f2490845c2 #s-Button_2": {
                      "attributes-ie": {
                        "-pie-background": "#2C94BB",
                        "-pie-poll": "false"
                      }
                    }
                  } ]
                },
                {
                  "action": "jimPause",
                  "parameter": {
                    "pause": 200
                  }
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-a7ae4cdf-26d1-4000-a661-98f2490845c2 #s-Button_2": {
                      "attributes": {
                        "background-color": "#474747",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-a7ae4cdf-26d1-4000-a661-98f2490845c2 #s-Button_2": {
                      "attributes-ie": {
                        "-pie-background": "#474747",
                        "-pie-poll": "false"
                      }
                    }
                  } ]
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    }
  })
  .on("click", ".s-a7ae4cdf-26d1-4000-a661-98f2490845c2 .toggle", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Image_44")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-Image_44",
                      "value": "./images/94f132e6-aeac-4105-8184-40be1c4f5cd2.png"
                    }
                  }
                ]
              }
            ]
          }
        ];
        jEvent.launchCases(cases);
      }
    } else if(jFirer.is("#s-Image_45")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-Image_45",
                      "value": "./images/94f132e6-aeac-4105-8184-40be1c4f5cd2.png"
                    }
                  }
                ]
              }
            ]
          }
        ];
        jEvent.launchCases(cases);
      }
    }
  });