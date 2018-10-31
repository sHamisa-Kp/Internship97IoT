const humidityChannel = {

	  // humidity (balcony:1 , balcony:2)

	  'H': [{'id': '669', 'apiKey': '7TPW8OQOGN1EMURD'},
    	    {'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}]
};
const humidityErrorValue = {
	'H': {'min': 20, 'max': 30}
};

function humidityHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if ((num > 10 && num < 20) || (num > 30 && num < 40)){
	    		switch(i) {
	    			case 0:
					var btn20 = document.querySelector("#btn20");
					btn20.style.background ="yellow";
					break;
					case 1:
					var btn21 = document.querySelector("#btn21");
					btn21.style.background ="yellow";
					break;
			
				}
	    	}

	    	else if ((num >=0 && num <=10) || (num >=40 && num<=100)) {
	    			switch(i) {
	    			case 0:
					var btn20 = document.querySelector("#btn20");
					btn20.style.background ="red";
					break;
					case 1:
					var btn21 = document.querySelector("#btn21");
					btn21.style.background ="red";
					break;
				}	
	    	}

	    	else if (num >= 20 && num <= 30){
				switch(i) {
	    			case 0:
	    			var btn20 = document.querySelector("#btn20");
	    			btn20.style.background ="green";
					break;
	    			case 1:
					var btn21 = document.querySelector("#btn21");
					btn21.style.background ="green";
					break;
						    
				}
			}	    	        	
    	};

	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function doIt() {
  for (let i = 0 ; i < humidityChannel.H.length ; i++) {
	humidityHttpGet("http://thingtalk.ir/channels/" + humidityChannel.H[i].id + "/feed/last.json?key=" + humidityChannel.H[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  