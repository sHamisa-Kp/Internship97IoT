const waterLevelChannel = {
'	WL': [{'id': '742', 'apiKey': 'WGWJ660WN7V9394D'}]
};

const waterLevelErrorValue = {
	'WL': {'min': 20, 'max': 30}
};

function waterLevelHttpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            waterLeveArray[i] = num;
            if(num < waterLeveErrorValue.H.min || num  > waterLeveErrorValue.H.max) {
            	waterLevelError[i] = true;
            } else {
            	waterLevelError[i] = false;
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function waterLevelUpdateThePage() {
	for(let i = 0; i < waterLevelChannel.WL.length; i++) {
		waterLevelHttpGetAsync("http://thingtalk.ir/channels/" + waterLevelChannel.WL[i].id + 
			"/feeds/last.json?key=" + waterLevelChannel.WL[i].apiKey, function(){}, i);
	}
}

let waterLevelArray = [];
let waterLevelError = [];
for(let i = 0; i < waterLevelChannel.WL.length; i++) {
	humidityError.push(false);
}

setInterval(waterLevelUpdateThePage, 3000);
setInterval(waterLevelCalculateAverage, 3000);