const humidityChannel = {
	'H': [{'id': '669', 'apiKey': '7TPW8OQOGN1EMURD'},
		{'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}]
};

const humidityErrorValue = {
	'H': {'min': 30, 'max': 55}
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
            if (i === (humidityChannel.H.length) - 1) {
            	humidityCalculateAverage(humidityArray);
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

function humidityCalculateAverage(humidityArray) {
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
	if(average >= 0 && average < 30) { //DryWeather
		backgroundImage.style.backgroundImage = 'url("img/lowHumidity.png")';
		
	} else if(average >= 30 && average <= 55) { //balancedHumidity-recommended
		backgroundImage.style.backgroundImage = 'url("img/normalWeather2.jpg")';

	} else if(average > 55 && average <= 100) { //highHumidity
		backgroundImage.style.backgroundImage = 'url("img/highHumidity3.jpg")';

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