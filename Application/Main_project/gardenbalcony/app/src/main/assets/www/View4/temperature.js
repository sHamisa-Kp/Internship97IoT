const temperatureChannel = {

	  // temperature (balcony:1 , balcony:2)

	  'T': [{'id': '629', 'apiKey': 'G7KHR97UPN9OC5AC'},
          {'id': '720', 'apiKey': '6P4WUZHZZDR6U0TX'}]
};
const temperatureErrorValue = {
	'T': {'min': 20, 'max': 30}
};

function temperatureHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if ((num > 10 && num < 20) || (num > 30 && num < 40)){
	    		switch(i) {
	    			case 0:
					var btn18 = document.querySelector("#btn18");
					btn18.style.background ="yellow";
					break;
					case 1:
					var btn19 = document.querySelector("#btn19");
					btn19.style.background ="yellow";
					break;
			
				}
	    	}

	    	else if ((num >=0 && num <=10) || (num >=40 && num<=100)) {
	    			switch(i) {
	    			case 0:
					var btn18 = document.querySelector("#btn18");
					btn18.style.background ="red";
					break;
					case 1:
					var btn19 = document.querySelector("#btn19");
					btn19.style.background ="red";
					break;
				}	
	    	}

	    	else if (num >= 20 && num <= 30){
				switch(i) {
	    			case 0:
	    			var btn18 = document.querySelector("#btn18");
	    			btn18.style.background ="green";
	    			break;
	    			case 1:
					var btn19 = document.querySelector("#btn19");
					btn19.style.background ="green";
					break;
						    
				}
			}	    	        	
    	};

	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function doIt() {
  for (let i = 0 ; i < temperatureChannel.T.length ; i++) {
	temperatureHttpGet("http://thingtalk.ir/channels/" + temperatureChannel.T[i].id + "/feed/last.json?key=" + temperatureChannel.T[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  