const channel = {
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
	{'id': '719', 'apiKey': 'OTSO6GP0GO9XUAU3'}],

	// Temperature
	'T': [{'id': '629', 'apiKey': 'G7KHR97UPN9OC5AC'},
	{'id': '720', 'apiKey': '6P4WUZHZZDR6U0TX'}],

    // Humidity
    'H': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
    {'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}],

	// Floor Humidity
	'FH': [{'id': '722', 'apiKey': '3ZIOUDCBO1X4W0B7'}, 
	{'id': '723', 'apiKey': 'IG7Z0OW1NR2LVGSW'}],

   	// pH (Vegetables: 0-7, Flowers: 8-17)
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
   	{'id': '741', 'apiKey': '29LLHTWCLAZONUOS'}],

    // Water Level
    'WL': [{'id': '742', 'apiKey': 'WGWJ660WN7V9394D'}],

   	// Photo Resistor
   	'PhR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
   	{'id': '743', 'apiKey': 'ZH7OQMKALAPRZXQJ'}],

   	// Pump Status (Vegetables, Flowers, TOP)
   	'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'},
   	{'id': '744', 'apiKey': 'PD74MGJ9RFR4YMHK'},
   	{'id': '750', 'apiKey': 'FGGX36CLKXBW2BN4'}],

   	// Light Bulb Status
   	'LBS': [{'id': '745', 'apiKey': '2ZRJZIFPTQF79NOH'},
   	{'id': '746', 'apiKey': '17J1AKN992YL3HUX'}],

   	// Gas Sensor
   	'G': [{'id': '747', 'apiKey': '65ZJD9TRET64FJ03'},
   	{'id': '751', 'apiKey': '05VOPP6KT2NA1AZ5'}],

   	// Tap Status (Water Tap)
   	'TS': [{'id': '748', 'apiKey': 'A1079N4WNZESIRFC'}],

   	// Motion Detector
   	'MD': [{'id': '749', 'apiKey': 'V197BB4SL21A2IKG'},
   	{'id': '752', 'apiKey': 'ZCLT56CFVCG7DU50'}],

   	// WattMeter
   	'WM': [{'id': '753', 'apiKey': 'OUAV3VIB076Y5UO0'}]
   };

const errorValue = {
	'T': {'min': 20, 'max': 30}
};

const updateInterval = 3000; // ms

function httpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            temperatureArray[i] = num;
            if(num < errorValue.T.min || num  > errorValue.T.max) {
            	temperatureError[i] = true;
            } else {
            	temperatureError[i] = false;
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function updateThePage() {
	for(let i = 0; i < channel.T.length; i++) {
		httpGetAsync("http://thingtalk.ir/channels/" + channel.T[i].id + 
			"/feeds/last.json?key=" + channel.T[i].apiKey, function(){}, i);
	}
}

function calculateAverage() {
	let sum = 0;
	temperatureArray.forEach(function(elem) {
		sum += elem;
	});
	let average = sum / temperatureArray.length;

	updateTemperatureTile(average);
}

function updateTemperatureTile(average) {
	document.getElementById('temperatureText').textContent = String(average) + "°C";

	temperatureGauge.update({ value: average });

	let backgroundImage = document.getElementById('temperatureBackGroundImage');
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

	temperatureErrorImage = document.getElementById('temperatureErrorImage');
	let thereIsAtLeastOneDanger = false;
	temperatureError.forEach(function(elem) {
		if(elem) {
			thereIsAtLeastOneDanger = true;
		}
	});
	if(thereIsAtLeastOneDanger) {
		temperatureErrorImage.style.visibility = 'visible';
	} else {
		temperatureErrorImage.style.visibility = 'hidden';
	}
}

let temperatureArray = [];
let temperatureError = [];
for(let i = 0; i < channel.T.length; i++) {
	temperatureError.push(false);
}


temperatureGauge = new LinearGauge({
	renderTo: 'temperatureGauge',
	width: 70,
	height: 210,

	borderRadius: 5,
	borders: 0,
	barStrokeWidth: 1,

	minValue: -20,
	maxValue: 50,
	minorTicks: 5,
	majorTicks: [-20, -10, 0, 10, 20, 30, 40, 50],

	colorBarProgress: "red",
	value: 25,
	units: "°C",
	colorValueBoxShadow: false
}).draw();

setInterval(updateThePage, updateInterval);
setInterval(calculateAverage, updateInterval);
