const gasChannel = {

	  // Gas (balcony:1 , balcony:2)

	  'G': [{'id': '747', 'apiKey': '65ZJD9TRET64FJ03'},
   	       {'id': '751', 'apiKey': '05VOPP6KT2NA1AZ5'}]
};
const gasErrorValue = {
	'G': {'min': 9, 'max': 26}
};

function gasHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if ((num > 5 && num < 9) || (num > 26 && num < 50)){
	    		switch(i) {
	    			case 0:
					var btn50 = document.querySelector("#btn50");
					btn50.style.background ="yellow";
					break;
					case 1:
					var btn51 = document.querySelector("#btn51");
					btn51.style.background ="yellow";
					break;
			
				}
	    	}

	    	else if ((num >=0 && num <=5) || (num >=50 && num<=100)) {
	    			switch(i) {
	    			case 0:
					var btn50 = document.querySelector("#btn50");
					btn50.style.background ="red";
					break;
					case 1:
					var btn51 = document.querySelector("#btn51");
					btn51.style.background ="red";
					break;
				}	
	    	}

	    	else if (num >= 9 && num <= 26){
				switch(i) {
	    			case 0:
	    			var btn50 = document.querySelector("#btn50");
	    			btn50.style.background ="green";
					break;
	    			case 1:
					var btn51 = document.querySelector("#btn51");
					btn51.style.background ="green";
					break;
						    
				}
			}	    	        	
    	};

	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function doIt() {
  for (let i = 0 ; i < gasChannel.G.length ; i++) {
	gasHttpGet("http://thingtalk.ir/channels/" + gasChannel.G[i].id + "/feed/last.json?key=" + gasChannel.G[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  