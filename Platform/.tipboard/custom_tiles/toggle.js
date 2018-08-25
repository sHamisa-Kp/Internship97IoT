

  function toggleOnByInput() {
    //$('#toggleID > div.tile-content > div').prop('checked', true).change();
    document.querySelector("#toggleID > div.tile-content > div").classList.remove("off");
    document.querySelector("#toggleID > div.tile-content > div").classList.remove("btn-default");
    document.querySelector("#toggleID > div.tile-content > div").classList.add("btn-primary");

    httpGetToRpi('.../pump/on');
  }
  function toggleOffByInput() {
    console.log('halle!');
    //$('#toggleID > div.tile-content > div').prop('checked', false).change();
    document.querySelector("#toggleID > div.tile-content > div").classList.add("off");
    document.querySelector("#toggleID > div.tile-content > div").classList.add("btn-default");
    document.querySelector("#toggleID > div.tile-content > div").classList.remove("btn-primary");

    httpGetToRpi('.../pump/off');
  }

  function httpGetToRpi(){}

  function httpGet(urlAddress) {
  var xmlHttp = new XMLHttpRequest();
  xmlHttp.open("GET", 'http://thingtalk.ir/channels/683/feed/last.json?key=5LKBQQ9VAATCJFNB');
  xmlHttp.send();
  xmlHttp.onreadystatechange = function() {
  	if(this.readyState == 4 && this.status == 200) {
  	  console.log(JSON.parse(xmlHttp.responseText));
  	  var pumpStatus = JSON.parse(xmlHttp.responseText).field1;
  	  console.log(JSON.parse(xmlHttp.responseText).field1);

      // var toggle = document.querySelector("#toggle-trigger");

        if(pumpStatus === '1'){
          console.log('This is 1');
          // toggle.bootstrapToggle('on');
          document.querySelector("#toggleID > div.tile-content > div").classList.remove("off");
          document.querySelector("#toggleID > div.tile-content > div").classList.remove("btn-default");
          document.querySelector("#toggleID > div.tile-content > div").classList.add("btn-primary");
        }
        else if(pumpStatus === '0') {
          console.log('This is 0');
          // toggle.bootstrapToggle('off')
          document.querySelector("#toggleID > div.tile-content > div").classList.add("off");
          document.querySelector("#toggleID > div.tile-content > div").classList.add("btn-default");
          document.querySelector("#toggleID > div.tile-content > div").classList.remove("btn-primary");
        }

    }   else{
          console.log("ERR: \n");
        }

  }
}

  function doIt() {
  /* Thing talk */
  httpGet('http://thingtalk.ir/channels/683/feed/last.json?key=5LKBQQ9VAATCJFNB');
  }
  setInterval(doIt, 5000);