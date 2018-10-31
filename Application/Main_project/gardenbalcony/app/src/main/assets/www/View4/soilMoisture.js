const soilMoistureChannel = {

	  // SoilMoisture (Vegetables: 0-7, Flowers: 8-17)

	  'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'},
	  {'id': '700', 'apiKey': 'Q2JH4OBED4QQAA74'},
	  {'id': '701', 'apiKey': 'P6LY6LV7CPYYSJUP'},
	  {'id': '702', 'apiKey': 'PVWFQI4XLXLWL9DL'},
	  {'id': '703', 'apiKey': '6W8DYBXG0HDA141O'},
	  {'id': '704', 'apiKey': 'HZ9VM1PH1Q6LQSLZ'},
	  {'id': '705', 'apiKey': 'RUB06UUHPX0K4DDS'},
	  {'id': '706', 'apiKey': 'WQFB2JIGRVXDIAR4'},
	  {'id': '710', 'apiKey': 'FYMFZRW8E2YLIKUK'},
	  {'id': '711', 'apiKey': 'OXXNS5C338I0TCIR'},
	  {'id': '712', 'apiKey': 'MZEZGGZL09ZB48L1'},
	  {'id': '713', 'apiKey': '7R9CHM056LSY23ZO'},
	  {'id': '714', 'apiKey': 'ZPX8TF59L251UFVE'},
	  {'id': '715', 'apiKey': '3DMD11SS9G5B94I0'},
	  {'id': '716', 'apiKey': 'QLH9NDZ20RQNK96S'},
	  {'id': '717', 'apiKey': 'BVAC00J644INRNG7'},
	  {'id': '718', 'apiKey': 'T42SU8NKLQ4KYF5E'},
	  {'id': '719', 'apiKey': 'OTSO6GP0GO9XUAU3'}]
};
const soilmoistureErrorValue = {
	'SM': {'min': 20, 'max': 30}
};

function soilmoistureHttpGet(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
          
            if((num >=0 && num <=10) || (num >=40 && num<=100)) {
            	switch(i) {
	    			case 0:
					var btn0 = document.querySelector("#btn0");	
					btn0.style.background ="red";
					break;
					case 1:
					var btn1 = document.querySelector("#btn1");
					btn1.style.background ="red";
					break;
					case 2:
					var btn2 = document.querySelector("#btn2");
					btn2.style.background ="red";
					break;
					case 3:
					var btn3 = document.querySelector("#btn3");
					btn3.style.background ="red";
					break;
					case 4:
					var btn4 = document.querySelector("#btn4");
					btn4.style.background ="red";
					break;
					case 5:
					var btn5 = document.querySelector("#btn5");
					btn5.style.background ="red";
					break;
					case 6:
					var btn6 = document.querySelector("#btn6");
					btn6.style.background ="red";
					break;
					case 7:
					var btn7 = document.querySelector("#btn7");
					btn7.style.background ="red";
					break;
					case 8:
					var btn8 = document.querySelector("#btn8");
					btn8.style.background ="red";
					break;
					case 9:
					var btn9 = document.querySelector("#btn9");
					btn9.style.background ="red";
					break;
					case 10:
					var btn10 = document.querySelector("#btn10");
					btn10.style.background ="red";
					break;
					case 11:
					var btn11 = document.querySelector("#btn11");
					btn11.style.background ="red";
					break;
					case 12:
					var btn12 = document.querySelector("#btn12");
					btn12.style.background ="red";
					break;
					case 13:
					var btn13 = document.querySelector("#btn13");
					btn13.style.background ="red";
					break;
					case 14:
					var btn14 = document.querySelector("#btn14");
					btn14.style.background ="red";
					break;
					case 15:
					var btn15 = document.querySelector("#btn15");
					btn15.style.background ="red";
					break;
					case 16:
					var btn16 = document.querySelector("#btn16");
					btn16.style.background ="red";
					break;
					case 17:
					var btn17 = document.querySelector("#btn17");
					btn17.style.background ="red";
					break;
        		}
        	}			
        	else if ((num > 10 && num < 20) || (num > 30 && num < 40)) {
        		switch(i) {
        			case 0:
        			var btn0 = document.querySelector("#btn0");
        			btn0.style.background ="yellow";
					break;
        			case 1:
					var btn1 = document.querySelector("#btn1");
					btn1.style.background ="yellow";
					break;
					case 2:
					var btn2 = document.querySelector("#btn2");
					btn2.style.background ="yellow";
					break;
					case 3:
					var btn3 = document.querySelector("#btn3");
					btn3.style.background ="yellow";
					break;
					case 4:
					var btn4 = document.querySelector("#btn4");
					btn4.style.background ="yellow";
					break;
					case 5:
					var btn5 = document.querySelector("#btn5");
					btn5.style.background ="yellow";
					break;
					case 6:
					var btn6 = document.querySelector("#btn6");
					btn6.style.background ="yellow";
					break;
					case 7:
					var btn7 = document.querySelector("#btn7");
					btn7.style.background ="yellow";
					break;
					case 8:
					var btn8 = document.querySelector("#btn8");
					btn8.style.background ="yellow";
					break;
					case 9:
					var btn9 = document.querySelector("#btn9");
					btn9.style.background ="yellow";
					break;
					case 10:
					var btn10 = document.querySelector("#btn10");
					btn10.style.background ="yellow";
					break;
					case 11:
					var btn11 = document.querySelector("#btn11");
					btn11.style.background ="yellow";
					break;
					case 12:
					var btn12 = document.querySelector("#btn12");
					btn12.style.background ="yellow";
					break;
					case 13:
					var btn13 = document.querySelector("#btn13");
					btn13.style.background ="yellow";
					break;
					case 14:
					var btn14 = document.querySelector("#btn14");
					btn14.style.background ="yellow";
					break;
					case 15:
					var btn15 = document.querySelector("#btn15");
					btn15.style.background ="yellow";
					break;
					case 16:
					var btn16 = document.querySelector("#btn16");
					btn16.style.background ="yellow";
					break;
					case 17:
					var btn17 = document.querySelector("#btn17");
					btn17.style.background ="yellow";
					break;	    
        		}
        	}
        	else if (num >= 20 && num <= 30){
        		switch(i) {
        			case 0:
        			var btn0 = document.querySelector("#btn0");
        			btn0.style.background ="green";
					break;
        			case 1:
					var btn1 = document.querySelector("#btn1");
					btn1.style.background ="green";
					break;
					case 2:
					var btn2 = document.querySelector("#btn2");
					btn2.style.background ="green";
					break;
					case 3:
					var btn3 = document.querySelector("#btn3");
					btn3.style.background ="green";
					break;
					case 4:
					var btn4 = document.querySelector("#btn4");
					btn4.style.background ="green";
					break;
					case 5:
					var btn5 = document.querySelector("#btn5");
					btn5.style.background ="green";
					break;
					case 6:
					var btn6 = document.querySelector("#btn6");
					btn6.style.background ="green";
					break;
					case 7:
					var btn7 = document.querySelector("#btn7");
					btn7.style.background ="green";
					break;
					case 8:
					var btn8 = document.querySelector("#btn8");
					btn8.style.background ="green";
					break;
					case 9:
					var btn9 = document.querySelector("#btn9");
					btn9.style.background ="green";
					break;
					case 10:
					var btn10 = document.querySelector("#btn10");
					btn10.style.background ="green";
					break;
					case 11:
					var btn11 = document.querySelector("#btn11");
					btn11.style.background ="green";
					break;
					case 12:
					var btn12 = document.querySelector("#btn12");
					btn12.style.background ="green";
					break;
					case 13:
					var btn13 = document.querySelector("#btn13");
					btn13.style.background ="green";
					break;
					case 14:
					var btn14 = document.querySelector("#btn14");
					btn14.style.background ="green";
					break;
					case 15:
					var btn15 = document.querySelector("#btn15");
					btn15.style.background ="green";
					break;
					case 16:
					var btn16 = document.querySelector("#btn16");
					btn16.style.background ="green";
					break;
					case 17:
					var btn17 = document.querySelector("#btn17");
					btn17.style.background ="green";
					break;    
        		}
        	}			
            	
        	
    	};
	}
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function doIt() {
  for (let i = 0 ; i < soilMoistureChannel.SM.length ; i++) {
	soilmoistureHttpGet("http://thingtalk.ir/channels/" + soilMoistureChannel.SM[i].id + "/feed/last.json?key=" + soilMoistureChannel.SM[i].apiKey, function(){}, i);
	}
}
setInterval(doIt, 3000);
  