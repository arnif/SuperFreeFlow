(function(window, undefined) {
  var dictionary = {
    "a5fcf46b-cdd3-4b43-bb3e-265f4b976b72": "timeTrial",
    "d12245cc-1680-458d-89dd-4f0d7fb22724": "Screen 1",
    "a7ae4cdf-26d1-4000-a661-98f2490845c2": "options",
    "d7c977bf-77b2-4f93-92ad-61142e1b5619": "about",
    "3005d723-aed9-49f6-85a6-ef0ddd51fe4b": "level1",
    "e4fa4809-0a30-443a-8c7f-01faa382f67d": "5x5",
    "f203da09-5398-47c0-b0e6-5c2c901edb4b": "Play",
    "392f7263-9345-4b10-b363-dd466b21b1f8": "level1_timetrials",
    "f39803f7-df02-4169-93eb-7547fb8c961a": "Template 1"
  };

  var uriRE = /^(\/#)?(screens|templates|masters)\/(.*)(\.html)?/;
  window.lookUpURL = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, url;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      url = folder + "/" + canvas;
    }
    return url;
  };

  window.lookUpName = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, canvasName;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      canvasName = dictionary[canvas];
    }
    return canvasName;
  };
})(window);