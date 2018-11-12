const wmChannel = {
	// WattMeter
	'WM': [{'id': '753', 'apiKey': 'OUAV3VIB076Y5UO0'}]
};

const wmErrorValue = {
    'WM': {'min': 10, 'max': 90}
};

let wmArray = new Array;
let wmtemp = new Array;
let wmcount = 0;
let wmtime = new Array();
let wmcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function wmHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

        	for(let i = 0; i <= 19; i++) {
            	wmArray[i] = parseInt(text[i].field1);
        	};

            for(let j = 0; j < wmChannel.WM.length; j++) {
            	for(let i = 0; i <= 19; i++)
            		wmArray[j].forEach(function(elem) {
            			if (elem < wmErrorValue.WL.min || elem > wmErrorValue.WM.max) {            				
            				wmcolors[j] = 'red';
            			}
            		});
            }

        	if (wmArray != wmtemp) {
				setValueForWmChart();
				wmtemp = wmArray;
			}
			console.log(wmArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function wmUpdateThePage() {
    for(let j = 0; j < wmChannel.WM.length; j++) {
        wmHttpGetAsync("http://thingtalk.ir/channels/" + wmChannel.WM[j].id + 
            "/feeds.json?key=" + wmChannel.WM[j].apiKey + "&results=20", function(){}, j);
    }
}

function wmyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			wmtime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(wmtime);
    	}
    }
}

function setValueForWmChart() {
	 var onechart = 
        {
            labels: wmtime,
            datasets: []
        };

	for(let j = 0; j < wmChannel.WM.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:wmArray,
			borderWidth: 1,
			fill:false,
			borderColor:wmcolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	wmUpdateTile(onechart);	
};

function wmUpdateTile(onechart) {
	var ctx = document.getElementById('wl');
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

setInterval(wmUpdateThePage, 3000);
setInterval(wmyAxisValues("http://thingtalk.ir/channels/753/feed.json?key=OUAV3VIB076Y5UO0&results=20"), 3000);