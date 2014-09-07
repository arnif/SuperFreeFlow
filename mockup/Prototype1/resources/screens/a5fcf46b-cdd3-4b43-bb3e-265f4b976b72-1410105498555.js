jQuery("#simulation")
  .on("click", ".s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 .click", function(event, data) {
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
    } else if(jFirer.is("#s-Category_1")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Panel_9": {
                      "attributes": {
                        "background-color": "#8CD7F7",
                        "background-image": "none"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Panel_9 .verticalalign": {
                      "attributes": {
                        "vertical-align": "top"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Panel_9": {
                      "attributes-ie": {
                        "-pie-background": "#8CD7F7",
                        "-pie-poll": "false"
                      }
                    }
                  } ]
                },
                {
                  "action": "jimPause",
                  "parameter": {
                    "pause": 250
                  }
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Panel_9": {
                      "attributes": {
                        "background-color": "transparent",
                        "background-image": "none"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Panel_9 .verticalalign": {
                      "attributes": {
                        "vertical-align": "top"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Panel_9": {
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
    } else if(jFirer.is("#s-Label_26")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_26": {
                      "attributes": {
                        "background-color": "#0782AC",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_26": {
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
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_26": {
                      "attributes": {
                        "background-color": "transparent",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_26": {
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
                    "target": "screens/392f7263-9345-4b10-b363-dd466b21b1f8"
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
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_27": {
                      "attributes": {
                        "background-color": "#0782AC",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_27": {
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
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_27": {
                      "attributes": {
                        "background-color": "transparent",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_27": {
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
    } else if(jFirer.is("#s-Label_28")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_28": {
                      "attributes": {
                        "background-color": "#0782AC",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_28": {
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
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_28": {
                      "attributes": {
                        "background-color": "transparent",
                        "background-image": "none",
                        "font-family": "null,Arial"
                      }
                    }
                  },{
                    "#s-a5fcf46b-cdd3-4b43-bb3e-265f4b976b72 #s-Label_28": {
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
    }
  });