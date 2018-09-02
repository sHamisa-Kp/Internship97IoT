const channel = {
    'T': [{'id': '629', 'apiKey': 'G7KHR97UPN9OC5AC'}],
    'H': [{'id': '669', 'apiKey': '7TPW8OQOGN1EMURD'}],
    'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'},
     	   {'id': '700', 'apiKey': 'Q2JH4OBED4QQAA74'},
     	   {'id': '701', 'apiKey': 'P6LY6LV7CPYYSJUP'},
     	   {'id': '702', 'apiKey': 'PVWFQI4XLXLWL9DL'},
     	   {'id': '703', 'apiKey': '6W8DYBXG0HDA141O'},
     	   {'id': '704', 'apiKey': 'HZ9VM1PH1Q6LQSLZ'},
     	   {'id': '705', 'apiKey': 'RUB06UUHPX0K4DDS'},
     	   {'id': '706', 'apiKey': 'WQFB2JIGRVXDIAR4'}],
    'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'}],
    'PhR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'}]
};
const updateInterval = 3000; // ms

function updateVegetableTile(i, value) {
	document.getElementById('valueText' + i).innerText = value;

    let vegetableStatus = document.getElementById('vegetableStatus' + i);
	if(value < 33) {
		vegetableStatus.setAttribute("src", "./soilMoisture_files/img/vegetableYellow.png");
	}	else if(value < 67) {
		vegetableStatus.setAttribute("src", "./soilMoisture_files/img/vegetableGreenYellow.png");
	}	else if(value <= 100) {
		vegetableStatus.setAttribute("src", "./soilMoisture_files/img/vegetableGreen.png");
	}

    let dangerStatus = document.getElementById('dangerStatus' + i);
	if(value >= 50 && value <= 85) {
		dangerStatus.style.display = "none";
	}	else {
		dangerStatus.setAttribute("src", "./soilMoisture_files/img/error.png");
		
	}
}

function updatePumpStatus(i, pS) {
    let pumpStatus = document.getElementById('pumpStatus' + i);
	if(pS === 0) {
		pumpStatus.setAttribute("src", "./soilMoisture_files/img/pumpOff.png");
	}	else if(pS === 1) {
		pumpStatus.setAttribute("src", "./soilMoisture_files/img/pumpOn.png");
	}
}

function httpGetAsync(theUrl, callback, i) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            callback(i+1, parseInt(text));
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function updateThePage() {
	for(let i = 0; i < channel.SM.length; i++) {
		httpGetAsync("http://thingtalk.ir/channels/" + channel.SM[i].id + 
			"/feeds/last.json?key=" + channel.SM[i].apiKey, updateVegetableTile, i);

		httpGetAsync("http://thingtalk.ir/channels/" + channel.PS[0].id + 
			"/feeds/last.json?key=" + channel.PS[0].apiKey, updatePumpStatus, i);

	}
}

function createTile(i) {
	let HTMLText = `
	<div class="col col_1_of_4">
		<div class="tile highlighter-tile" id="soilMoistureOfVegetable`+i+`" style="width: 138px; height: 115px; top: 10px; left: 10px;">
			<div class="tile-header">
				<h3>Soil Moisture Of Vegetable `+i+`</h3>
			</div>

			<div class="tile-content">
				<div class="imgHolder">
					<img class="vegetableStatus" id="vegetableStatus`+i+`" src="./soilMoisture_files/img/vegetableYellow.png">
				</div>

				<div class="centerDiv">
					<span class="pumpImgHolder">
						<img class="pumpStatus" id="pumpStatus`+i+`" src="./soilMoisture_files/img/pumpOff.png">
					</span>

					<span class="textHolder">
						<p class="valueText" id="valueText`+i+`">100</p>
					</span>

					<span class="dangerHolder">
						<img class="dangerStatus" id="dangerStatus`+i+`" src="./soilMoisture_files/img/error.png">
					</span>
				</div>
			</div>
		</div>
	</div>
	`;
	return HTMLText;
}

function createTilesInHTML() {
	let row = document.querySelector('body > div:nth-child(1)');
	let HTMLText = '';
	for(let i = 1; i<= 4; i++) {
		createTile(i);
		HTMLText += createTile(i);
	}	
	row.innerHTML = HTMLText;

	row = document.querySelector('body > div:nth-child(2)');
	HTMLText = '';
	for(let i = 5; i<= 8; i++) {
		HTMLText += createTile(i);
	}	
	row.innerHTML = HTMLText;
}

createTilesInHTML();
setInterval(updateThePage, updateInterval);
