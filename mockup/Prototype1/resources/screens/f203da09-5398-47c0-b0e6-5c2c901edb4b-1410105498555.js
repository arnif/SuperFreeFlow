jQuery("#simulation")
  .on("click", ".s-f203da09-5398-47c0-b0e6-5c2c901edb4b .click", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Label_26")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-f203da09-5398-47c0-b0e6-5c2c901edb4b #s-Label_26": {
                      "attributes": {
                        "background-color": "#0782AC",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-f203da09-5398-47c0-b0e6-5c2c901edb4b #s-Label_26": {
                      "attributes-ie": {
                        "-pie-background": "#0782AC",
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
                    "#s-f203da09-5398-47c0-b0e6-5c2c901edb4b #s-Label_26": {
                      "attributes": {
                        "background-color": "transparent",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-f203da09-5398-47c0-b0e6-5c2c901edb4b #s-Label_26": {
                      "attributes-ie": {
                        "-pie-background": "transparent",
                        "-pie-poll": "false"
                      }
                    }
                  } ]
                }
              ]
            }
          ]
        },
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/e4fa4809-0a30-443a-8c7f-01faa382f67d"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Label_27")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-f203da09-5398-47c0-b0e6-5c2c901edb4b #s-Label_27": {
                      "attributes": {
                        "background-color": "#0782AC",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-f203da09-5398-47c0-b0e6-5c2c901edb4b #s-Label_27": {
                      "attributes-ie": {
                        "-pie-background": "#0782AC",
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
                    "#s-f203da09-5398-47c0-b0e6-5c2c901edb4b #s-Label_27": {
                      "attributes": {
                        "background-color": "transparent",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-f203da09-5398-47c0-b0e6-5c2c901edb4b #s-Label_27": {
                      "attributes-ie": {
                        "-pie-background": "transparent",
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
    } else if(jFirer.is("#s-Image_2")) {
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
    }
  });