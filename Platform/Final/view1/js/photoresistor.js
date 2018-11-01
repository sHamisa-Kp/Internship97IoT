const photoresistorChannel {
   	// Photo Resistor
   	'PhR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
   	{'id': '743', 'apiKey': 'ZH7OQMKALAPRZXQJ'}],
};
const photoresistorErrorValue {
	'PhR': {'min': 20, 'max': 99}
};

let photoresistorArray = [];
// let photoresistorError = [];
// for(let i = 0; i < photoresistorChannel.PhR.length; i++) {
//     photoresistorError.push(false);
// }

function photoresistorHttpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() { 
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            photoresistorArray[i] = num;
            // if(num < photoresistorErrorValue.PhR.min || num  > photoresistorErrorValue.PhR.max) {
            // 	photoresistorError[i] = true;
            // } else {
            // 	photoresistorError[i] = false;
            // }
            if (i === (photoresistorChannel.PhR.length) - 1) {
                photoresistorCalculateAverage(photoresistorArray);
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function photoresistorUpdateThePage() {
	for(let i = 0; i < photoresistorChannel.PhR.length; i++) {
		photoresistorHttpGetAsync("http://thingtalk.ir/channels/" + photoresistorChannel.PhR[i].id + 
			"/feeds/last.json?key=" + photoresistorChannel.PhR[i].apiKey, function(){}, i);
	}
}

function photoresistorCalculateAverage(waterLevelArray) {
    let sum = 0;
        photoresistorArray.forEach(function(elem) {
            sum += elem;
        });
        var average = sum / photoresistorArray.length;
        console.log(average);
        console.log(photoresistorArray.length);
        console.log(sum);

        updatePhotoresistorTile(average);
}

function updatePhotoresistorTile(average) {
    document.querySelector("#photoresistorAveragevalue").textContent = "%" + String(average);

    var meterValue = document.querySelector("#photoresistorMeter");
    meterValue.value = average;

//     photoresistorErrorImage = document.getElementById('photoresistorErrorImage');
//     let thereIsAtLeastOneDanger = false;
//     photoresistorError.forEach(function(elem) {
//         if(elem) {
//             thereIsAtLeastOneDanger = true;
//         }
//     });
//     if(thereIsAtLeastOneDanger) {
//         photoresistorErrorImage.style.visibility = 'visible';
//     } else {
//         photoresistorrErrorImage.style.visibility = 'hidden';
//     }
}

setInterval(photoresistorUpdateThePage, 3000);