const motionDetectorChannel = {

	  // motionDetector (balcony1 , balcony2)

	  'MD': [{'id': '749', 'apiKey': 'V197BB4SL21A2IKG'},
   	       {'id': '752', 'apiKey': 'ZCLT56CFVCG7DU50'}]
};

function motionDetectorHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            // callback(xmlHttp.responseText);
	            let text = JSON.parse(xmlHttp.responseText).field1;
	            let num = parseInt(text);
	          
	    	if (num === 0){
	    		switch(i) {
	    			case 0:
					var btn53 = document.querySelector("#btn53");
					btn53.style.background ="red";
					break;
					case 1:
					var btn54 = document.querySelector("#btn54");
					btn54.style.background ="red";
					break;
				
				}	
			
	    	}
	    	else if (num === 1) {
			    switch(i) {
	                case 0:
	    		    var btn53 = document.querySelector("#btn53");
	    		    btn53.style.background ="green";
					break;
	    		    case 1:
	    		    var btn54 = document.querySelector("#btn54");
	    		    btn54.style.background ="green";
					break;
	    		   
				}
	    	}
		};
	}

xmlHttp.open("GET", theUrl, true); // true for asynchronous 
xmlHttp.send(null);
    
}


function doIt() {
  for (let i = 0 ; i < motionDetectorChannel.MD.length ; i++) {
	motionDetectorHttpGet("http://thingtalk.ir/channels/" + motionDetectorChannel.MD[i].id + "/feed/last.json?key=" + motionDetectorChannel.MD[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  