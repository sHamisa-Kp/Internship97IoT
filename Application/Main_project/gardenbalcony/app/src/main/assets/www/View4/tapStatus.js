const tapStatusChannel = {

	  // Tap Status (Water Tap)

	  'TS': [{'id': '748', 'apiKey': 'A1079N4WNZESIRFC'}]
};

function tapStatusHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if (num === 0){
	    		switch(i) {
	    			case 0:
					var btn52 = document.querySelector("#btn52");
					btn52.style.background ="red";
				    break;
				}	
			
	    	}
	    	else if (num === 1) {
			    switch(i) {
	                case 0:
	    		    var btn52 = document.querySelector("#btn52");
	    		    btn52.style.background ="green";
					break;
	    		    
				}
	    	}
		};
	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}
function doIt() {
  for (let i = 0 ; i < tapStatusChannel.TS.length ; i++) {
	tapStatusHttpGet("http://thingtalk.ir/channels/" + tapStatusChannel.TS[i].id + "/feed/last.json?key=" + tapStatusChannel.TS[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  