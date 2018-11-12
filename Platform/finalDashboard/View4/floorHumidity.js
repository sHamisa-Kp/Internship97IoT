const floorHumidityChannel = {

	  // floorHumidity (balcony:1 , balcony:2)

	  'FH': [{'id': '722', 'apiKey': '3ZIOUDCBO1X4W0B7'}, 
   	       {'id': '723', 'apiKey': 'IG7Z0OW1NR2LVGSW'}]
};
const floorHumidityErrorValue = {
	'FH': {'min': 20, 'max': 30}
};

function floorHumidityHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if ((num > 10 && num < 20) || (num > 30 && num < 40)){
	    		switch(i) {
	    			case 0:
					var btn22 = document.querySelector("#btn22");
					btn22.style.background ="yellow";
					break;
					case 1:
					var btn23 = document.querySelector("#btn23");
					btn23.style.background ="yellow";
					break;
			
				}
	    	}

	    	else if ((num >=0 && num <=10) || (num >=40 && num<=100)) {
	    			switch(i) {
	    			case 0:
					var btn22 = document.querySelector("#btn22");
					btn22.style.background ="red";
					break;
					case 1:
					var btn23 = document.querySelector("#btn23");
					btn23.style.background ="red";
					break;
				}	
	    	}

	    	else if (num >= 20 && num <= 30){
				switch(i) {
	    			case 0:
	    			var btn22 = document.querySelector("#btn22");
	    			btn22.style.background ="green";
					break;
	    			case 1:
					var btn23 = document.querySelector("#btn23");
					btn23.style.background ="green";
					break;
						    
				}
			}	    	        	
    	};

	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function doIt() {
  for (let i = 0 ; i < floorHumidityChannel.FH.length ; i++) {
	floorHumidityHttpGet("http://thingtalk.ir/channels/" + floorHumidityChannel.FH[i].id + "/feed/last.json?key=" + floorHumidityChannel.FH[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  