const statusChannel = {
	// Pump Status (Vegetables, Flowers, TOP(Fogg))
   	'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'},
   	{'id': '744', 'apiKey': 'PD74MGJ9RFR4YMHK'},
   	{'id': '750', 'apiKey': 'FGGX36CLKXBW2BN4'}],

    // Tap Status (Water Tap)
    'TS': [{'id': '748', 'apiKey': 'A1079N4WNZESIRFC'}],

    // Light Bulb Status
    'LBS': [{'id': '745', 'apiKey': '2ZRJZIFPTQF79NOH'},
    {'id': '746', 'apiKey': '17J1AKN992YL3HUX'}]
};

function pumpStatusHttpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            console.log(num);
            if(num === 0) {
            	switch(i) {
            		case 0:
            		document.querySelector("#pumpStatusOfVegs").src = "img/drip-off.png";
                    break;
            		case 1:
            		document.querySelector("#pumpStatusOfFlwrs").src = "img/drip-off.png";
                    break;
            		case 2:
                    document.querySelector("#foggNozzleStatus").src = "img/fog-off.png";
            		// document.querySelector("#waterTapStatus").src = "img/watertap-off.png";
                    break;
            	}
            }
            if (num === 1) {
            	switch(i) {
            		case 0:
            		document.querySelector("#pumpStatusOfVegs").src = "img/drip-on.png";
                    break;
            		case 1:
					document.querySelector("#pumpStatusOfFlwrs").src = "img/drip-on.png";
                    break;
            		case 2:
					document.querySelector("#foggNozzleStatus").src = "img/fognozzle2-on.png";
                    break;
                }
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function tapStatusHttpGetAsync(theUrl, callback, i) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            console.log(num);
            if(num === 0) {
                    document.querySelector("#waterTapStatus").src = "img/watertap-off.png";
            }
            if (num === 1) {
                    document.querySelector("#waterTapStatus").src = "img/watertap-on-v2.png"; 
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function pumpStatusUpdateThePage() {
	for(let i = 0; i < statusChannel.PS.length; i++) {
		pumpStatusHttpGetAsync("http://thingtalk.ir/channels/" + statusChannel.PS[i].id + 
			"/feeds/last.json?key=" + statusChannel.PS[i].apiKey, function(){}, i);
	}
}

function tapStatusUpdateThePage() {
    for(let i = 0; i < statusChannel.TS.length; i++) {
        tapStatusHttpGetAsync("http://thingtalk.ir/channels/" + statusChannel.TS[i].id + 
            "/feeds/last.json?key=" + statusChannel.TS[i].apiKey, function(){}, i);
    }
}

setInterval(pumpStatusUpdateThePage, 3000);
setInterval(tapStatusUpdateThePage, 3000);