const phVegChannel = {
		'PH': [{'id': '724', 'apiKey': 'A7SC0GJKQAFJZWAO'},
   	{'id': '725', 'apiKey': 'THRJGRI2XUH1YZIO'},
   	{'id': '726', 'apiKey': 'E8QFCI9OYGTOZIMT'},
   	{'id': '727', 'apiKey': 'ATGVGAYRFPS4QCVO'},
   	{'id': '728', 'apiKey': 'Z4AXFJXQK1ZN33BQ'},
   	{'id': '729', 'apiKey': '61SF7VT52KJZJCPW'},
   	{'id': '730', 'apiKey': 'NF3K4HLNFZ0BE2XO'},
   	{'id': '731', 'apiKey': 'VAK0BLGBNXUDZMQB'}]
};

const phVegErrorValue = {
	'PH' : {'min' : 6 , 'max' : 7 }
}

function phVegHttpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			let text = JSON.parse(xmlHttp.responseText).field1;
			let num = parseInt(text);
			phVegArray[i] = num;
			if(num < phVegErrorValue.PH.min || num > phVegErrorValue.PH.max){
				phVegError[i] = true;
			} else {
				phVegError[i] = false;
			}
            if (i === (phVegChannel.PH.length) - 1) {
              phVegCalculateAverage(phVegArray);  
            }
		}
	};
	xmlHttp.open("GET", theUrl, true); //True for asynchronous
	xmlHttp.send(null);
}

function phVegUpdateThePage(){
	for(let i = 0; i < phVegChannel.PH.length; i++){
		phVegHttpGetAsync("http://thingtalk.ir/channels/" +phVegChannel.PH[i].id + "/feeds/last.json?key=" + phVegChannel.PH[i].apiKey, function(){}, i);
	}
}

function phVegCalculateAverage(phVegArray){
	let sum = 0;
	phVegArray.forEach(function(elem){
		sum += elem;
	});
	let average = sum / phVegArray.length;
	console.log(average);
	console.log(phVegArray.length);
	console.log(sum);

	updatePhVegTile(average);
}

function updatePhVegTile(average) {
	document.querySelector("#phVegAveragevalue").textContent = String(average);

	var meterValue = document.querySelector("#phVegMeter");
	meterValue.value = average;

	// let backgroundImage = document.getElementById('humidityBackGroundImage');
	// if(average < 0) {
	// 	backgroundImage.style.backgroundImage = 'url("img/veryColdTemperature.jpg")';
	// 	// console.log("average < 0");
	// } else if(average >= 0 && average < 20) {
	// 	backgroundImage.style.backgroundImage = 'url("img/coldTemperature.jpg")';
	// 	// console.log('average > 0 && average < 20');
	// } else if(average >= 20 && average < 30) {
	// 	backgroundImage.style.backgroundImage = 'url("img/balancedTemperature.jpg")';
	// 	// console.log('average > 20 && average < 30');
	// } else if(average >= 30) {
	// 	backgroundImage.style.backgroundImage = 'url("img/hotTemperature.jpg")';
	// 	// console.log('average > 30');
	// }

	var PhVegErrorImage = document.querySelector("#PhVegErrorImage");
	let thereIsAtLeastOneDanger = false;
	phVegError.forEach(function(elem) {
		if (elem) {
			thereIsAtLeastOneDanger = true;
		}
	});
	if (thereIsAtLeastOneDanger) {
		PhVegErrorImage.style.visibility = 'visible';
	} else {
		PhVegErrorImage.style.visibility = 'hidden';
	}
}

let phVegArray = [];
let phVegError = [];
for(let i = 0; i < phVegChannel.PH.length; i++){
	phVegError.push(false);
}

var gaugeOptions = {

    chart: {
        type: 'solidgauge'
    },
    title: null,

    pane: {
        center: ['50%', '85%'],
        size: '140%',
        startAngle: -90,
        endAngle: 90,
        background: {
            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
            innerRadius: '60%',
            outerRadius: '100%',
            shape: 'arc'
        }
    },

    tooltip: {
        enabled: false
    },

    // the value axis
    yAxis: {
        stops: [
            [0.1, '#55BF3B'], // green
            [0.5, '#DDDF0D'], // yellow
            [0.9, '#DF5353'] // red
        ],
        lineWidth: 0,
        minorTickInterval: null,
        tickAmount: 2,
        title: {
            y: -70
        },
        labels: {
            y: 16
        }
    },

    plotOptions: {
        solidgauge: {
            dataLabels: {
                y: 5,
                borderWidth: 0,
                useHTML: true
            }
        }
    }
};

// The speed gauge
var chartSpeed = Highcharts.chart('container-speed', Highcharts.merge(gaugeOptions, {
	yAxis: {
		min: 0,
		max: 14,
		title: {
			text: 'PH'
		}
	},

	credits: {
		enabled: false
	},

	series: [{
		name: 'Speed',
		data: [0],
		dataLabels: {
			format: '<div style="text-align:center"><span style="font-size:25px;color:' +
			((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
			'<span style="font-size:12px;color:silver"></span></div>'
		},
		tooltip: {
			valueSuffix: 'PH'
		}
	}]

}));


setInterval(phVegUpdateThePage, 3000);
setInterval(phVegCalculateAverage, 3000);