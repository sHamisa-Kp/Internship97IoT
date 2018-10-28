const photoResistorChannel = {

	  // photoResistor (balcony:1 , balcony:2)

	  'PHR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
   	        {'id': '743', 'apiKey': 'ZH7OQMKALAPRZXQJ'}]
};
const photoResistorErrorValue = {
	'PHR': {'min': 20, 'max': 30}
};

function photoResistorHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if ((num > 10 && num < 20) || (num > 30 && num < 40)){
	    		switch(i) {
	    			case 0:
					var btn43 = document.querySelector("#btn43");
					btn43.style.background ="yellow";
					break;
					case 1:
					var btn44 = document.querySelector("#btn44");
					btn44.style.background ="yellow";
					break;
			
				}
	    	}

	    	else if ((num >=0 && num <=10) || (num >=40 && num<=100)) {
	    			switch(i) {
	    			case 0:
					var btn43 = document.querySelector("#btn43");
					btn43.style.background ="red";
					break;
					case 1:
					var btn44 = document.querySelector("#btn44");
					btn44.style.background ="red";
					break;
				}	
	    	}

	    	else if (num >= 20 && num <= 30){
				switch(i) {
	    			case 0:
	    			var btn43 = document.querySelector("#btn43");
	    			btn43.style.background ="green";
					break;
	    			case 1:
					var btn44 = document.querySelector("#btn44");
					btn44.style.background ="green";
					break;
						    
				}
			}	    	        	
    	};

	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function doIt() {
  for (let i = 0 ; i < photoResistorChannel.PHR.length ; i++) {
	photoResistorHttpGet("http://thingtalk.ir/channels/" + photoResistorChannel.PHR[i].id + "/feed/last.json?key=" + photoResistorChannel.PHR[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  