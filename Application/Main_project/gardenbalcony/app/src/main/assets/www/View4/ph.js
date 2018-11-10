const phChannel = {

	  // ph (Vegetables: 0-7, Flowers: 8-17)

	  'PH': [{'id': '724', 'apiKey': 'A7SC0GJKQAFJZWAO'},
     		   {'id': '725', 'apiKey': 'THRJGRI2XUH1YZIO'},
     		   {'id': '726', 'apiKey': 'E8QFCI9OYGTOZIMT'},
     		   {'id': '727', 'apiKey': 'ATGVGAYRFPS4QCVO'},
     		   {'id': '728', 'apiKey': 'Z4AXFJXQK1ZN33BQ'},
     		   {'id': '729', 'apiKey': '61SF7VT52KJZJCPW'},
     		   {'id': '730', 'apiKey': 'NF3K4HLNFZ0BE2XO'},
     		   {'id': '731', 'apiKey': 'VAK0BLGBNXUDZMQB'},
     		   {'id': '732', 'apiKey': 'LG2G6VYVGRROA0O2'},
     		   {'id': '733', 'apiKey': 'BQYNJJL4G8A5RCHJ'},
     		   {'id': '734', 'apiKey': 'RZAEIWTMDVBOCNZS'},
     		   {'id': '735', 'apiKey': '4NWVLGMJHDE8WDAJ'},
     		   {'id': '736', 'apiKey': 'OW4B80VUEXJ38AG7'},
     		   {'id': '737', 'apiKey': 'I7MAFZ8E11BHLD4T'},
     		   {'id': '738', 'apiKey': '5WPKZ6PW9ZYCXC1T'},
     		   {'id': '739', 'apiKey': 'TD2B6CE14BODBAU4'},
     		   {'id': '740', 'apiKey': 'AGOE4BOV5662UOQF'},
     		   {'id': '741', 'apiKey': '29LLHTWCLAZONUOS'}]
};
const phErrorValue = {
	'PH': {'min': 6, 'max': 8}
};

function phHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
          
            if((num >=0 && num <=3) || (num >=11 && num<=14)) {
            	switch(i) {
	    			case 0:
					var btn24 = document.querySelector("#btn24");	
					btn24.style.background ="red";
					break;
					case 1:
					var btn25 = document.querySelector("#btn25");
					btn25.style.background ="red";
					break;
					case 2:
					var btn26 = document.querySelector("#btn26");
					btn26.style.background ="red";
					break;
					case 3:
					var btn27 = document.querySelector("#btn27");
					btn27.style.background ="red";
					break;
					case 4:
					var btn28 = document.querySelector("#btn28");
					btn28.style.background ="red";
					break;
					case 5:
					var btn29 = document.querySelector("#btn29");
					btn29.style.background ="red";
					break;
					case 6:
					var btn30 = document.querySelector("#btn30");
					btn30.style.background ="red";
					break;
					case 7:
					var btn31 = document.querySelector("#btn31");
					btn31.style.background ="red";
					break;
					case 8:
					var btn32 = document.querySelector("#btn32");
					btn32.style.background ="red";
					break;
					case 9:
					var btn33 = document.querySelector("#btn33");
					btn33.style.background ="red";
					break;
					case 10:
					var btn34 = document.querySelector("#btn34");
					btn34.style.background ="red";
					break;
					case 11:
					var btn35 = document.querySelector("#btn35");
					btn35.style.background ="red";
					break;
					case 12:
					var btn36 = document.querySelector("#btn36");
					btn36.style.background ="red";
					break;
					case 13:
					var btn37 = document.querySelector("#btn37");
					btn37.style.background ="red";
					break;
					case 14:
					var btn38 = document.querySelector("#btn38");
					btn38.style.background ="red";
					break;
					case 15:
					var btn39 = document.querySelector("#btn39");
					btn39.style.background ="red";
					break;
					case 16:
					var btn40 = document.querySelector("#btn40");
					btn40.style.background ="red";
					break;
					case 17:
					var btn41 = document.querySelector("#btn41");
					btn41.style.background ="red";
					break;
        		}
        	}			
        	else if ((num > 3 && num < 6) || (num > 8 && num < 11)) {
        		switch(i) {
        			case 0:
					var btn24 = document.querySelector("#btn24");	
					btn24.style.background ="yellow";
					break;
					case 1:
					var btn25 = document.querySelector("#btn25");
					btn25.style.background ="yellow";
					break;
					case 2:
					var btn26 = document.querySelector("#btn26");
					btn26.style.background ="yellow";
					break;
					case 3:
					var btn27 = document.querySelector("#btn27");
					btn27.style.background ="yellow";
					break;
					case 4:
					var btn28 = document.querySelector("#btn28");
					btn28.style.background ="yellow";
					break;
					case 5:
					var btn29 = document.querySelector("#btn29");
					btn29.style.background ="yellow";
					break;
					case 6:
					var btn30 = document.querySelector("#btn30");
					btn30.style.background ="yellow";
					break;
					case 7:
					var btn31 = document.querySelector("#btn31");
					btn31.style.background ="yellow";
					break;
					case 8:
					var btn32 = document.querySelector("#btn32");
					btn32.style.background ="yellow";
					break;
					case 9:
					var btn33 = document.querySelector("#btn33");
					btn33.style.background ="yellow";
					break;
					case 10:
					var btn34 = document.querySelector("#btn34");
					btn34.style.background ="yellow";
					break;
					case 11:
					var btn35 = document.querySelector("#btn35");
					btn35.style.background ="yellow";
					break;
					case 12:
					var btn36 = document.querySelector("#btn36");
					btn36.style.background ="yellow";
					break;
					case 13:
					var btn37 = document.querySelector("#btn37");
					btn37.style.background ="yellow";
					break;
					case 14:
					var btn38 = document.querySelector("#btn38");
					btn38.style.background ="yellow";
					break;
					case 15:
					var btn39 = document.querySelector("#btn39");
					btn39.style.background ="yellow";
					break;
					case 16:
					var btn40 = document.querySelector("#btn40");
					btn40.style.background ="yellow";
					break;
					case 17:
					var btn41 = document.querySelector("#btn41");
					btn41.style.background ="yellow";
					break;
        		}
        	}
        	else if (num >= 6 && num <= 8){
        		switch(i) {
        			case 0:
					var btn24 = document.querySelector("#btn24");	
					btn24.style.background ="green";
					break;
					case 1:
					var btn25 = document.querySelector("#btn25");
					btn25.style.background ="green";
					break;
					case 2:
					var btn26 = document.querySelector("#btn26");
					btn26.style.background ="green";
					break;
					case 3:
					var btn27 = document.querySelector("#btn27");
					btn27.style.background ="green";
					break;
					case 4:
					var btn28 = document.querySelector("#btn28");
					btn28.style.background ="green";
					break;
					case 5:
					var btn29 = document.querySelector("#btn29");
					btn29.style.background ="green";
					break;
					case 6:
					var btn30 = document.querySelector("#btn30");
					btn30.style.background ="green";
					break;
					case 7:
					var btn31 = document.querySelector("#btn31");
					btn31.style.background ="green";
					break;
					case 8:
					var btn32 = document.querySelector("#btn32");
					btn32.style.background ="green";
					break;
					case 9:
					var btn33 = document.querySelector("#btn33");
					btn33.style.background ="green";
					break;
					case 10:
					var btn34 = document.querySelector("#btn34");
					btn34.style.background ="green";
					break;
					case 11:
					var btn35 = document.querySelector("#btn35");
					btn35.style.background ="green";
					break;
					case 12:
					var btn36 = document.querySelector("#btn36");
					btn36.style.background ="green";
					break;
					case 13:
					var btn37 = document.querySelector("#btn37");
					btn37.style.background ="green";
					break;
					case 14:
					var btn38 = document.querySelector("#btn38");
					btn38.style.background ="green";
					break;
					case 15:
					var btn39 = document.querySelector("#btn39");
					btn39.style.background ="green";
					break;
					case 16:
					var btn40 = document.querySelector("#btn40");
					btn40.style.background ="green";
					break;
					case 17:
					var btn41 = document.querySelector("#btn41");
					btn41.style.background ="green";
					break; 
        		}
        	}			
            	
        	
    	};
	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function doIt() {
  for (let i = 0 ; i < phChannel.PH.length ; i++) {
	phHttpGet("http://thingtalk.ir/channels/" + phChannel.PH[i].id + "/feed/last.json?key=" + phChannel.PH[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  