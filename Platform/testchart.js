console.log("hey there!!")
const smvChannel = {
	// SoilMoisture (Vegetables: 0-7, Flowers: 8-17)
	'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'},
	{'id': '700', 'apiKey': 'Q2JH4OBED4QQAA74'},
	{'id': '701', 'apiKey': 'P6LY6LV7CPYYSJUP'},
	{'id': '702', 'apiKey': 'PVWFQI4XLXLWL9DL'},
	{'id': '703', 'apiKey': '6W8DYBXG0HDA141O'},
	{'id': '704', 'apiKey': 'HZ9VM1PH1Q6LQSLZ'},
	{'id': '705', 'apiKey': 'RUB06UUHPX0K4DDS'},
	{'id': '706', 'apiKey': 'WQFB2JIGRVXDIAR4'}]
};
const smvErrorValue = {
    'SM': {'min': 20, 'max': 30}
};

let vegSMArray = new Array;
vegSMArray = [[],[],[],[],[],[],[],[]];
let temp = new Array;
temp = [[],[],[],[],[],[],[],[]];
let vegSMError = [];
for(let j = 0; j < smvChannel.SM.length; j++) {
    vegSMError.push(false);
}
let count = 0;
let time = new Array();
let colors = ['#9505f2','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function smvHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;
            if (count <= 7) {
	            for(let i = 0; i <= 19; i++) {
	                vegSMArray[j].push(parseInt(text[i].field1));
	            };
	            count += 1;
            }
            if (count > 7) {
            	let vegSMArray = new Array;
            	vegSMArray = [[],[],[],[],[],[],[],[]];
            	for(let i = 0; i <= 19; i++) {
            		vegSMArray[j].push(parseInt(text[i].field1));
            	}
            	count += 1;
            }
            // for(let j = 0; j <= 7; j++) {
            // 	for(let i = 0; i <= 19; i++)
            // 		vegSMArray[j].forEach(function(elem) {
            // 			if (elem < smvErrorValue.SM.min || elem > smvErrorValue.SM.max) {            				
            // 				colors[j] = 'red';
            // 			}
            // 		});
            // }
            console.log(vegSMArray);
            // newValueRecognizer(vegSMArray);
			setValueForChart();	       	
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function smvUpdateThePage() {
    for(let j = 0; j < smvChannel.SM.length; j++) {
        smvHttpGetAsync("http://thingtalk.ir/channels/" + smvChannel.SM[j].id + 
            "/feeds.json?key=" + smvChannel.SM[j].apiKey + "&results=20", function(){}, j);
    }
}

function yAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			time[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(time);
    	}
    }
}

// function newValueRecognizer(vegSMArray) {
// 	for (var j = 0; j <= 7; j++) {
// 		for (var i = 0; i <= 19; i++)
// 			vegSMArray.forEach(function(elem){
// 				if (elem != temp[j][i]) {
// 					setValueForChart();
// 				}
// 			});		
// 	}
// 	temp = vegSMArray;
// }

function setValueForChart() {
	 var onechart = 
        {
            labels: time,
            datasets: []
        };

	for(let j = 0; j <= 7; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:vegSMArray[j],
			borderWidth: 1,
			fill:false,
			borderColor:colors[j]
		};
		onechart.datasets.push(Data);		
	} 
	smvUpdateTile(onechart);	
};

function smvUpdateTile(onechart) {
	var ctx = document.getElementById('myChart');
	var chart = new Chart(ctx, {
	    // The type of chart we want to create
	    type: 'line',

	    // The data for our dataset
	    data: onechart,
	    // {
	    // 	labels: time,
	    // 	datasets: [{
	    // 		label: myLabel,
	    // 		data: vegSMArray,
	    // 		borderColor: myBorderColor,
	    // 		borderWidth: 1,
	    // 		fill: false,
	    // 		// backgroundColor: 'rgba(255, 99, 132,0)',
	    // 		// borderColor: 'rgb(255, 0, 0)',
	    		
	    // 	}]
	    // },
	    // Configuration options go here
	    options: {
	    	legend:{
	    		display:true,
	    		labels:{
	    			fontColor:'black'
	    		},
	    		fullWidth:false
	    	}
	    }
	});
}

setInterval(smvUpdateThePage, 3000);
setInterval(yAxisValues("http://thingtalk.ir/channels/706/feed.json?key=WQFB2JIGRVXDIAR4&results=20"), 3000);
// setInterval(newValueRecognizer(vegSMArray), 3000);