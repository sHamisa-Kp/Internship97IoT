const waterLevelChannel = {
	'WL': [{'id': '742', 'apiKey': 'WGWJ660WN7V9394D'}]
};

const waterLevelErrorValue = {
	'WL': {'min': 20, 'max': 99}
};

function waterLevelHttpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            waterLevelArray[i] = num;
            if(num < waterLevelErrorValue.WL.min || num  > waterLevelErrorValue.WL.max) {
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

function waterLevelCalculateAverage() {
    let sum = 0;
    humidityArray.forEach(function(elem) {
        sum += elem;
    });
    let average = sum / humidityArray.length;
    console.log(average);
    console.log(humidityArray.length);
    console.log(sum);

    updateWaterLevelTile(average);
}

function updateWaterLevelTile(average) {

    // var guageValue = document.querySelector("#fillgauge2");
    // guageValue.values = average;

    waterLevelErrorImage = document.getElementById('waterLevelErrorImage');
    let thereIsAtLeastOneDanger = false;
    waterLevelError.forEach(function(elem) {
        if(elem) {
            thereIsAtLeastOneDanger = true;
        }
    });
    if(thereIsAtLeastOneDanger) {
        waterLevelErrorImage.style.visibility = 'visible';
    } else {
        waterLevelErrorImage.style.visibility = 'hidden';
    }
}

let waterLevelArray = [];
let waterLevelError = [];
for(let i = 0; i < waterLevelChannel.WL.length; i++) {
	humidityError.push(false);
}

setInterval(waterLevelUpdateThePage, 3000);
setInterval(waterLevelCalculateAverage, 3000);