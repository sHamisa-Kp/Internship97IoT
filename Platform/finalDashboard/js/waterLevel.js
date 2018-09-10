const waterLevelChannel = {
	'WL': [{'id': '742', 'apiKey': 'WGWJ660WN7V9394D'}]
};
console.log(waterLevelChannel.WL.length);
const waterLevelErrorValue = {
	'WL': {'min': 20, 'max': 99}
};

var result = 0;
let waterLevelArray = [];
let waterLevelError = [];
for(let i = 0; i < waterLevelChannel.WL.length; i++) {
    waterLevelError.push(false);
}

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
            if (i === (waterLevelChannel.WL.length) - 1) {
                waterLevelCalculateAverage(waterLevelArray);
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

function waterLevelCalculateAverage(waterLevelArray) {
    let sum = 0;
    // if (waterLevelArray.length > 0) {
        waterLevelArray.forEach(function(elem) {
            sum += elem;
        });
        var average = sum / waterLevelArray.length;
        console.log(average);
        console.log(waterLevelArray.length);
        console.log(sum);

        updateWaterLevelTile(average);
    // else {
    //     console.log("average is not ready yet!")
    // }
 
// }
}
function updateWaterLevelTile(average) {
    
        result = average;
        var config1 = liquidFillGaugeDefaultSettings();
        config1.circleColor = "#081caf";
        config1.textColor = "#9f03ad";
        config1.waveTextColor = "#490177";
        config1.waveColor = "#5ebcf2";
        config1.circleThickness = 0.09;
        config1.textVertPosition = 0.2;
        config1.waveAnimateTime = 1000;
        var gauge2= loadLiquidFillGauge("fillgauge2", result, config1);
        
        function NewValue(){
            if(Math.random() > .5){
                return Math.round(Math.random()*100);
            } else {
                return (Math.random()*100).toFixed(1);
            }
        }

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


setInterval(waterLevelUpdateThePage, 3000);
// setInterval(waterLevelCalculateAverage, 3000);

