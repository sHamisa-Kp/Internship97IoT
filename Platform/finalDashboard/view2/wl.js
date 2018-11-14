const wlChannel = {
	// Water Level
	'WL': [{'id': '742', 'apiKey': 'WGWJ660WN7V9394D'}]
};

const wlErrorValue = {
    'WL': {'min': 20, 'max': 99}
};

let wlArray = new Array;
let wltemp = new Array;
let wlcount = 0;
let wltime = new Array();
let wlcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function wlHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

        	for(let i = 0; i <= 19; i++) {
            	wlArray[i] = parseInt(text[i].field1);
        	};

        	for(let i = 0; i <= 19; i++)
        		wlArray.forEach(function(elem) {
        			if (elem < wlErrorValue.WL.min || elem > wlErrorValue.WL.max) {            				
        				wlcolors[j] = 'red';
        			}
        		});

        	if (wlArray != wltemp) {
				setValueForWlChart();
				wltemp = wlArray;
			}
			console.log(wlArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function wlUpdateThePage() {
    for(let j = 0; j < wlChannel.WL.length; j++) {
        wlHttpGetAsync("http://thingtalk.ir/channels/" + wlChannel.WL[j].id + 
            "/feeds.json?key=" + wlChannel.WL[j].apiKey + "&results=20", function(){}, j);
    }
}

function wlyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			wltime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(wltime);
    	}
    }
}

function setValueForWlChart() {
	 var onechart = 
        {
            labels: wltime,
            datasets: []
        };

	for(let j = 0; j < wlChannel.WL.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:wlArray,
			borderWidth: 1,
			fill:false,
			borderColor:wlcolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	wlUpdateTile(onechart);	
};

function wlUpdateTile(onechart) {
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

setInterval(wlUpdateThePage, 3000);
setInterval(wlyAxisValues("http://thingtalk.ir/channels/742/feed.json?key=WGWJ660WN7V9394D&results=20"), 3000);