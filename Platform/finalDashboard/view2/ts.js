const tsChannel = {
	// Tap Status (Water Tap)
	'TS': [{'id': '748', 'apiKey': 'A1079N4WNZESIRFC'}]
};

let tsArray = new Array;
let tstemp = new Array;
let tscount = 0;
let tstime = new Array();
let tscolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function tsHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

        	for(let i = 0; i <= 19; i++) {
            	tsArray[i] = parseInt(text[i].field1);
        	};

        	if (tsArray != tstemp) {
				setValueForTSChart();
				tstemp = tsArray;
			}
			console.log(tsArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function tsUpdateThePage() {
    for(let j = 0; j < tsChannel.TS.length; j++) {
        tsHttpGetAsync("http://thingtalk.ir/channels/" + tsChannel.TS[j].id + 
            "/feeds.json?key=" + tsChannel.TS[j].apiKey + "&results=20", function(){}, j);
    }
}

function tsyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			tstime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(tstime);
    	}
    }
}

function setValueForTSChart() {
	 var onechart = 
        {
            labels: tstime,
            datasets: []
        };

	for(let j = 0; j < tsChannel.TS.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:tsArray,
			borderWidth: 1,
			fill:false,
			borderColor:tscolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	tsUpdateTile(onechart);	
};

function tsUpdateTile(onechart) {
	var ctx = document.getElementById('ts');
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

setInterval(tsUpdateThePage, 3000);
setInterval(tsyAxisValues("http://thingtalk.ir/channels/748/feed.json?key=A1079N4WNZESIRFC&results=20"), 3000);