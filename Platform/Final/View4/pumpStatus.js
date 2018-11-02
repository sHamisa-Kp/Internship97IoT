const pumpStatusChannel = {

	  // Pump Status (Vegetables, Flowers, TOP)

	  'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'},
   	 	     {'id': '744', 'apiKey': 'PD74MGJ9RFR4YMHK'},
   	 	     {'id': '750', 'apiKey': 'FGGX36CLKXBW2BN4'}]
};

function pumpStatusHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if (num === 0){
	    		switch(i) {
		    			case 0:
						var btn45 = document.querySelector("#btn45");
						btn45.style.background ="red";
					    break;
						case 1:
						var btn46 = document.querySelector("#btn46");
						btn46.style.background ="red";
					    break;
						case 2:
						var btn47 = document.querySelector("#btn47");
						btn47.style.background ="red";
					    break;
				}	
			
	    	}
	    	else if (num === 1) {
			    switch(i) {
	                case 0:
	    		    var btn45 = document.querySelector("#btn45");
	    		    btn45.style.background ="green";
					break;
	    		    case 1:
	    		    var btn46 = document.querySelector("#btn46");
	    		    btn46.style.background ="green";
					break;
	    		    case 2:
	    		    var btn47 = document.querySelector("#btn47");
	    		    btn47.style.background ="green";
					break;
				}
	    	}
		};
	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}
function doIt() {
  for (let i = 0 ; i < pumpStatusChannel.PS.length ; i++) {
	pumpStatusHttpGet("http://thingtalk.ir/channels/" + pumpStatusChannel.PS[i].id + "/feed/last.json?key=" + pumpStatusChannel.PS[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  