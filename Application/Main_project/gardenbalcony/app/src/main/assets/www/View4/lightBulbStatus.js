const lightBulbStatusChannel = {

	  // lightBulbStatus (balcony1 , balcony2)

	  'LBS': [{'id': '745', 'apiKey': '2ZRJZIFPTQF79NOH'},
   	        {'id': '746', 'apiKey': '17J1AKN992YL3HUX'}]
};

function lightBulbStatusHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if (num === 0){
	    		switch(i) {
	    			case 0:
					var btn48 = document.querySelector("#btn48");
					btn48.style.background ="red";
					break;
					case 1:
					var btn49 = document.querySelector("#btn49");
					btn49.style.background ="red";
					break;
				
				}	
			
	    	}
	    	else if (num === 1) {
			    switch(i) {
	                case 0:
	    		    var btn48 = document.querySelector("#btn48");
	    		    btn48.style.background ="green";
					break;
	    		    case 1:
	    		    var btn49 = document.querySelector("#btn49");
	    		    btn49.style.background ="green";
					break;
	    		   
				}
	    	}
		};
	}

xmlHttp.open("GET", theUrl, true); // true for asynchronous 
xmlHttp.send(null);
    
}


function doIt() {
  for (let i = 0 ; i < lightBulbStatusChannel.LBS.length ; i++) {
	lightBulbStatusHttpGet("http://thingtalk.ir/channels/" + lightBulbStatusChannel.LBS[i].id + "/feed/last.json?key=" + lightBulbStatusChannel.LBS[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  