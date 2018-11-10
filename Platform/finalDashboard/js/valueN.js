/* Dashboard GUI - M.Naseri */

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
  'H': [{'id': '672', 'apiKey': '7TPW8OQOGN1EMURD'},
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

  // Pump Status (Vegetables, Flowers, TOP(Fogg))
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
	'T': {'min': 20, 'max': 30},
	'PhR': {'min': 0, 'max': 100},
	'FH': {'min': 0, 'max': 100},
	'G': {'min': 0, 'max': 150}
};

const updateInterval = 3000; // ms

function httpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			let text = JSON.parse(xmlHttp.responseText).field1;
			let num = parseInt(text);
			callback(i, num);
		}
	};
xmlHttp.open("GET", theUrl, true); // true for asynchronous 
xmlHttp.send(null);
}

function updateThePage() {
	for(let i = 0; i < channel.T.length; i++) {
		httpGetAsync("http://thingtalk.ir/channels/" + channel.T[i].id + 
			"/feeds/last.json?key=" + channel.T[i].apiKey, temperatureCallback, i);
	}

	for(let i = 0; i < channel.PhR.length; i++) {
		httpGetAsync("http://thingtalk.ir/channels/" + channel.PhR[i].id + 
			"/feeds/last.json?key=" + channel.PhR[i].apiKey, photoresistorCallback, i);
	}

	for(let i = 0; i < channel.LBS.length; i++) {
		httpGetAsync("http://thingtalk.ir/channels/" + channel.LBS[i].id + 
			"/feeds/last.json?key=" + channel.LBS[i].apiKey, lightBulbCallback, i);
	}

	for(let i = 0; i < channel.MD.length; i++) {
		httpGetAsync("http://thingtalk.ir/channels/" + channel.MD[i].id + 
			"/feeds/last.json?key=" + channel.MD[i].apiKey, motionDetectorCallback, i);
	}

	for(let i = 0; i < channel.FH.length; i++) {
		httpGetAsync("http://thingtalk.ir/channels/" + channel.FH[i].id + 
			"/feeds/last.json?key=" + channel.FH[i].apiKey, rainSensorCallback, i);
	}

	for(let i = 0; i < channel.G.length; i++) {
		httpGetAsync("http://thingtalk.ir/channels/" + channel.G[i].id + 
			"/feeds/last.json?key=" + channel.G[i].apiKey, gasCallback, i);
	}
}

function calculateAverage() {
	/* Temperature */
	let sum = 0;
	temperatureArray.forEach(function(elem) {
		sum += elem;
	});
	let average = sum / temperatureArray.length;
	updateTemperatureTile(average);

	/* Photoresistor */
	sum = 0;
	photoresistorArray.forEach(function(elem) {
		sum += elem;
	});
	average = sum / photoresistorArray.length;
	updatePhotoresistorTile(average);

	/* Motion Detector */
	updateMotionDetectorTile();

	/* Rain Sensor */
	sum = 0;
	rainSensorArray.forEach(function(elem) {
		sum += elem;
	});
	average = sum / rainSensorArray.length;
	updateRainSensorTile(average);

	/* Gas Sensor */
	sum = 0;
	gasArray.forEach(function(elem) {
		sum += elem;
	});
	average = sum / gasArray.length;
	updateGasTile(average);
}

function updateTemperatureTile(average) {
	document.getElementById('temperatureText').textContent = String(average) + "°C";

	temperatureGauge.update({ value: average });

	let backgroundImage = document.getElementById('temperatureBackGroundImage');
	if(average < 0) {
		backgroundImage.style.backgroundImage = 'url("img/veryCold.jpg")';

	} else if(average >= 0 && average < 20) {
		backgroundImage.style.backgroundImage = 'url("img/coldTemp.jpg")';

	} else if(average >= 20 && average < 30) {
		backgroundImage.style.backgroundImage = 'url("img/normTemp2.jpg")';

	} else if(average >= 30) {
		backgroundImage.style.backgroundImage = 'url("img/hotWeather2.jpg")';

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

function updatePhotoresistorTile(average) {
	document.getElementById('photoresistorText').textContent = "%" + average.toPrecision(3);

	let backgroundImage = document.getElementById('photoresistorBackGroundImage');
	if(average < 33) {
		backgroundImage.style.backgroundImage = 'url("img/darkPhR.jpg")';
		document.getElementById('photoresistorText').style.color = 'white';
	} else if(average >= 33 && average < 67) {
		backgroundImage.style.backgroundImage = 'url("img/normalPhR.jpg")';
		document.getElementById('photoresistorText').style.color = 'black';
	} else if(average >= 67 && average <= 100) {
		backgroundImage.style.backgroundImage = 'url("img/lightPhR.jpg")';
		document.getElementById('photoresistorText').style.color = 'black';
	}

	photoresistorErrorImage = document.getElementById('photoresistorErrorImage');
	let thereIsAtLeastOneDanger = false;
	photoresistorError.forEach(function(elem) {
		if(elem) {
			thereIsAtLeastOneDanger = true;
		}
	});
	if(thereIsAtLeastOneDanger) {
		photoresistorErrorImage.style.visibility = 'visible';
	} else {
		photoresistorErrorImage.style.visibility = 'hidden';
	}

	/* LBS */ //lightBulb of left balc(class room)
	let lightbulb1 = document.getElementById('lightbulb1');
	if(lightBulbArray[0] === 1) {
		lightbulb1.src = "img/lightbulb-on.png";
	} else if (lightBulbArray[0] === 0) {
		lightbulb1.src = "img/lightbulb-off.png";
	}
	//lightBulb of right balc(dr's room)
	let lightbulb2 = document.getElementById('lightbulb2');
	if(lightBulbArray[1] === 1) {
		lightbulb2.src = "img/lightbulb-on.png";
	} else if (lightBulbArray[1] === 0) {
		lightbulb2.src = "img/lightbulb-off.png";
	}
}

function updateMotionDetectorTile() {
	let motionDetectionStatus1 = document.getElementById('motionDetectionStatus1');
	if(motionDetectorArray[0] === 1) {
		motionDetectionStatus1.src = "img/mdIcon-on.png";
	} else if (motionDetectorArray[0] === 0) {
		motionDetectionStatus1.src = "img/mdIcon-off.png";
	}

	let motionDetectionStatus2 = document.getElementById('motionDetectionStatus2');
	if(motionDetectorArray[1] === 1) {
		motionDetectionStatus2.src = "img/mdIcon-on.png";
	} else if (motionDetectorArray[1] === 0) {
		motionDetectionStatus2.src = "img/mdIcon-off.png";
	}
}

function updateRainSensorTile(average) {
	document.getElementById('rainSensorText').textContent = average.toPrecision(3) + "%";

	let backgroundImage = document.getElementById('rainSensorBackgroundImage');
	let rainStatus = document.getElementById('rainStatus');
	if(average < 75) {
		backgroundImage.style.backgroundImage = 'url("img/dryWood.jpg")';
		rainStatus.src = "img/dryFloor.png";
	} else if(average >= 75) {
		backgroundImage.style.backgroundImage = 'url("img/wetWood.jpg")';
		rainStatus.src = "img/wetFloor.png";
	} 

	rainSensorErrorImage = document.getElementById('rainSensorErrorImage');
	let thereIsAtLeastOneDanger = false;
	rainSensorError.forEach(function(elem) {
		if(elem) {
			thereIsAtLeastOneDanger = true;
		}
	});
	if(thereIsAtLeastOneDanger) {
		rainSensorErrorImage.style.visibility = 'visible';
	} else {
		rainSensorErrorImage.style.visibility = 'hidden';
	}
}

function updateGasTile(average) {
	document.getElementById('gasText').textContent = average + "ppm";

	let backgroundImage = document.getElementById('gasBackgroundImage');
	let gasStatus = document.getElementById('gasStatus');
	if(average < 150) {
		backgroundImage.style.backgroundImage = 'url("")';
	} else if(average >= 150) {
		backgroundImage.style.backgroundImage = 'url("img/Smoke.jpg")';
	} 

	gasErrorImage = document.getElementById('gasErrorImage');
	let thereIsAtLeastOneDanger = false;
	gasError.forEach(function(elem) {
		if(elem) {
			thereIsAtLeastOneDanger = true;
		}
	});
	if(thereIsAtLeastOneDanger) {
		gasErrorImage.style.visibility = 'visible';
	} else {
		gasErrorImage.style.visibility = 'hidden';
	}
}

function temperatureCallback(i, num) {
	temperatureArray[i] = num;
	if(num < errorValue.T.min || num  > errorValue.T.max) {
		temperatureError[i] = true;
	} else {
		temperatureError[i] = false;
	}
}

function photoresistorCallback(i, num) {
	num *= 100/1023; //Mapping [0, 1023] -> [0, 100]
	num = 100 - num;
	photoresistorArray[i] = num;
	/* Consider the time and the obstacle here */
	if(num < errorValue.PhR.min || num  > errorValue.PhR.max) {
		photoresistorError[i] = true;
	} else {
		photoresistorError[i] = false;
	}
}

function lightBulbCallback(i, num) {
	lightBulbArray[i] = num;
}

function motionDetectorCallback(i, num) {
	motionDetectorArray[i] = num;
}

function wattmeterCallback(i, num) {
	wattmeterPoint.update(num);
}

function rainSensorCallback(i, num) {
	num *= 100/1023;
	num = 100 - num;
	rainSensorArray[i] = num;
	if(num < errorValue.FH.min || num  > errorValue.FH.max) {
		rainSensorError[i] = true;
	} else {
		rainSensorError[i] = false;
	}
}

function gasCallback(i, num) {
	gasArray[i] = num;
	if(num < errorValue.G.min || num  > errorValue.G.max) {
		gasError[i] = true;
	} else {
		gasError[i] = false;
	}
}

let temperatureArray = [];
let temperatureError = [];
let photoresistorArray = [];
let photoresistorError = [];
let lightBulbArray = [];
let motionDetectorArray = [];
let rainSensorArray = [];
let rainSensorError = [];
let gasArray = [];
let gasError = [];
var wattmeterPoint; // wattmeterGauge

temperatureGauge = new LinearGauge({
	renderTo: 'temperatureGauge',
	width: 70,
	height: 158,

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

Highcharts.chart('container', {

	chart: {
		type: 'gauge',
		plotBackgroundColor: null,
		plotBackgroundImage: null,
		plotBorderWidth: 0,
		plotShadow: false
	},

	title: {
		text: null
	},

	pane: {
		startAngle: -150,
		endAngle: 150,
		background: [{
			backgroundColor: {
				linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
				stops: [
				[0, '#FFF'],
				[1, '#333']
				]
			},
			borderWidth: 0,
			outerRadius: '109%'
		}, {
			backgroundColor: {
				linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
				stops: [
				[0, '#333'],
				[1, '#FFF']
				]
			},
			borderWidth: 1,
			outerRadius: '107%'
		}, {
// default background
}, 
{
	backgroundColor: '#DDD',
	borderWidth: 0,
	outerRadius: '105%',
	innerRadius: '103%'
}]
},

// the value axis
yAxis: {
	min: 0,
	max: 800,

	minorTickInterval: 'auto',
	minorTickWidth: 1,
	minorTickLength: 10,
	minorTickPosition: 'inside',
	minorTickColor: '#666',

	tickPixelInterval: 30,
	tickWidth: 2,
	tickPosition: 'inside',
	tickLength: 10,
	tickColor: '#666',
	labels: {
		step: 2,
		rotation: 'auto'
	},
	title: {
		text: 'Watt'
	},
	plotBands: [{
		from: 0,
		to: 100,
color: '#55BF3B' // green
}, {
	from: 100,
	to: 200,
color: '#DDDF0D' // yellow
}, {
	from: 200,
	to: 300,
color: '#DF5353' // red
}, {
	from: 300,
	to: 400,
color: 'blue;' // red            
}, {
	from: 400,
	to: 500,
color: '#DF5353' // red            
}, {
	from: 500,
	to: 600,
color: '#DDDF0D' // yellow
}, {
	from: 600,
	to: 700,
color: '#55BF3B' // green
}, {
	from: 700,
	to: 800,
color: 'pink;' // green
}]
},

series: [{
	name: 'watt meter',
	data: [0],
	tooltip: {
		valueSuffix: 'kW'
	}
}]

},
// Add some life
function (chart) {
	if (!chart.renderer.forExport) {
		setInterval(function () {
			wattmeterPoint = chart.series[0].points[0];
			
			httpGetAsync("http://thingtalk.ir/channels/" + channel.WM[0].id + 
			"/feeds/last.json?key=" + channel.WM[0].apiKey, wattmeterCallback, 0);
			
		}, updateInterval);
	}
});

for(let i = 0; i < channel.T.length; i++) {temperatureError.push(false);}
for(let i = 0; i < channel.PhR.length; i++) {photoresistorError.push(false);}
for(let i = 0; i < channel.FH.length; i++) {rainSensorError.push(false);}
for(let i = 0; i < channel.G.length; i++) {gasError.push(false);}

updateThePage();
calculateAverage();
setInterval(updateThePage, updateInterval);
setInterval(calculateAverage, updateInterval);