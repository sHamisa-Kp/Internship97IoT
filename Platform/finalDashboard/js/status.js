const statusChannel = {
	// Pump Status (Vegetables, Flowers, Fogg)
   	'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'},
   	{'id': '744', 'apiKey': 'PD74MGJ9RFR4YMHK'},
   	{'id': '750', 'apiKey': 'FGGX36CLKXBW2BN4'}],

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
                    document.querySelector("#foggNozzleStatus").src = "img/fognozzle2-off-v2.png";
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
					document.querySelector("#foggNozzleStatus").src = "img/fognozzle2-off-v2.png";
                    break;
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function LBStatusHttpGetAsync(theUrl, callback, i) {
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
                    document.querySelector("#lightbulbStatus1").src = "img/X.png"; //lightbulbStatus1 is for Dr's balconey
                    break;
                    case 1:
                    document.querySelector("#lightbulbStatus2").src = "img/X.png"; //lightbulbStatus2 is for conferenceroom's balconey
                    break;
                }
            }
            if (num === 1) {
                switch(i) {
                    case 0:
                    document.querySelector("#lightbulbStatus1").src = "img/X.png"; //lightbulbStatus1 is for Dr's balconey
                    break;
                    case 1:
                    document.querySelector("#lightbulbStatus2").src = "img/X.png"; //lightbulbStatus2 is for conferenceroom's balconey
                    break;                              
                }

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

function LBStatusUpdateThePage() {
    for(let i = 0; i < statusChannel.LBS.length; i++) {
        LBStatusHttpGetAsync("http://thingtalk.ir/channels/" + statusChannel.LBS[i].id + 
            "/feeds/last.json?key=" + statusChannel.LBS[i].apiKey, function(){}, i);
    }
}

setInterval(pumpStatusUpdateThePage, 3000);
setInterval(LBStatusUpdateThePage, 3000);