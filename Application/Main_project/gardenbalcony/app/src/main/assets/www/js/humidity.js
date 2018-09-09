const humidityChannel = {
	'H': [{'id': '669', 'apiKey': '7TPW8OQOGN1EMURD'},
		{'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}]
};

const humidityErrorValue = {
	'H': {'min': 20, 'max': 30}
};

function humidityHttpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            humidityArray[i] = num;
            if(num < humidityErrorValue.H.min || num  > humidityErrorValue.H.max) {
            	humidityError[i] = true;
            } else {
            	humidityError[i] = false;
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function humidityUpdateThePage() {
	for(let i = 0; i < humidityChannel.H.length; i++) {
		humidityHttpGetAsync("http://thingtalk.ir/channels/" + humidityChannel.H[i].id + 
			"/feeds/last.json?key=" + humidityChannel.H[i].apiKey, function(){}, i);
	}
}

function humidityCalculateAverage() {
	let sum = 0;
	humidityArray.forEach(function(elem) {
		sum += elem;
	});
	let average = sum / humidityArray.length;
	console.log(average);
	console.log(humidityArray.length);
	console.log(sum);

	updateHumidityTile(average);
}

function updateHumidityTile(average) {
	document.getElementById('humidityAveragevalue').textContent = "%" + String(average);

    var meterValue = document.querySelector("#myHumidityMeter");
    meterValue.value = average;

	let backgroundImage = document.getElementById('humidityBackGroundImage');
	if(average < 0) {
		backgroundImage.style.backgroundImage = 'url("img/veryColdTemperature.jpg")';
		// console.log("average < 0");
	} else if(average >= 0 && average < 20) {
		backgroundImage.style.backgroundImage = 'url("img/coldTemperature.jpg")';
		// console.log('average > 0 && average < 20');
	} else if(average >= 20 && average < 30) {
		backgroundImage.style.backgroundImage = 'url("img/balancedTemperature.jpg")';
		// console.log('average > 20 && average < 30');
	} else if(average >= 30) {
		backgroundImage.style.backgroundImage = 'url("img/hotTemperature.jpg")';
		// console.log('average > 30');
	}

	humidityErrorImage = document.getElementById('humidityErrorImage');
	let thereIsAtLeastOneDanger = false;
	humidityError.forEach(function(elem) {
		if(elem) {
			thereIsAtLeastOneDanger = true;
		}
	});
	if(thereIsAtLeastOneDanger) {
		humidityErrorImage.style.visibility = 'visible';
	} else {
		humidityErrorImage.style.visibility = 'hidden';
	}
}

let humidityArray = [];
let humidityError = [];
for(let i = 0; i < humidityChannel.H.length; i++) {
	humidityError.push(false);
}

setInterval(humidityUpdateThePage, 3000);
setInterval(humidityCalculateAverage, 3000);