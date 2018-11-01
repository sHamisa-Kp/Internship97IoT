const waterLevelChannel = {

	  // waterLevel

	  'WL': [{'id': '742', 'apiKey': 'WGWJ660WN7V9394D'}]
};
const waterLevelErrorValue = {
	'WL': {'min': 20, 'max': 80}
};

function waterLevelHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if ((num > 10 && num < 20) || (num > 80 && num < 99)){
	    		switch(i) {
	    			case 0:
					var btn42 = document.querySelector("#btn42");
					btn42.style.background ="yellow";
					break;
			
				}
	    	}

	    	else if ((num >=0 && num <=10) || (num >=99 && num<=100)) {
	    			switch(i) {
	    			case 0:
					var btn42 = document.querySelector("#btn42");
					btn42.style.background ="red";
					break;
					
				}	
	    	}

	    	else if (num >= 20 && num <= 80){
				switch(i) {
	    			case 0:
	    			var btn42 = document.querySelector("#btn42");
	    			btn42.style.background ="green";
					break;
						    
				}
			}	    	        	
    	};

	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function doIt() {
  for (let i = 0 ; i < waterLevelChannel.WL.length ; i++) {
	waterLevelHttpGet("http://thingtalk.ir/channels/" + waterLevelChannel.WL[i].id + "/feed/last.json?key=" + waterLevelChannel.WL[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  