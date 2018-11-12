const tChannel = {
	// Temperature
	'T': [{'id': '629', 'apiKey': 'G7KHR97UPN9OC5AC'},
	{'id': '720', 'apiKey': '6P4WUZHZZDR6U0TX'}]
};
const tErrorValue = {
	'T': {'min': 20, 'max': 30},
};

let tArray = new Array;
tArray = [[],[]];
let ttemp = new Array;
ttemp = [[],[]];
let tcount = 0;
let ttime = new Array();
let tcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function tHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (tcount < tChannel.T.length) {
            	for(let i = 0; i <= 19; i++) {
                	tArray[j].push(parseInt(text[i].field1));
            	};
            } tcount += 1;            

            if (tcount >= tChannel.T.length) {
            	let tArray = new Array;
            	tArray = [[],[]];
            	for(let i = 0; i <= 19; i++) {
            		tArray[j].push(parseInt(text[i].field1));
            	}
        	} 
            for(let j = 0; j < tChannel.T.length; j++) {
            	for(let i = 0; i <= 19; i++)
            		tArray[j].forEach(function(elem) {
            			if (elem < tErrorValue.T.min || elem > tErrorValue.T.max) {            				
            				tcolors[j] = 'red';
            			}
            		});
            }

        	if (tArray[j] != ttemp[j]) {
				setValueForTChart();
				ttemp[j] = tArray[j];
			}
			console.log(tArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function tUpdateThePage() {
    for(let j = 0; j < tChannel.T.length; j++) {
        tHttpGetAsync("http://thingtalk.ir/channels/" + tChannel.T[j].id + 
            "/feeds.json?key=" + tChannel.T[j].apiKey + "&results=20", function(){}, j);
    }
}

function tyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			ttime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(ttime);
    	}
    }
}

function setValueForTChart() {
	 var onechart = 
        {
            labels: ttime,
            datasets: []
        };

	for(let j = 0; j < tChannel.T.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:tArray[j],
			borderWidth: 1,
			fill:false,
			borderColor: tcolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	tUpdateTile(onechart);	
};

function tUpdateTile(onechart) {
	var ctx = document.getElementById('t');
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

setInterval(tUpdateThePage, 3000);
setInterval(tyAxisValues("http://thingtalk.ir/channels/720/feed.json?key=6P4WUZHZZDR6U0TX&results=20"), 3000);