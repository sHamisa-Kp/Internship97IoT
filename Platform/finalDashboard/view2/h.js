const hChannel = {
	// Humidity
	'H': [{'id': '672', 'apiKey': '7TPW8OQOGN1EMURD'},
	{'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}]
};
const hErrorValue = {
    'H': {'min': 20, 'max': 30}
};

let hArray = new Array;
hArray = [[],[]];
let htemp = new Array;
htemp = [[],[]];
let hcount = 0;
let htime = new Array();
let hcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function hHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (hcount < hChannel.H.length) {
            	for(let i = 0; i <= 19; i++) {
                	hArray[j].push(parseInt(text[i].field1));
            	};
            } hcount += 1;            

            if (hcount >= hChannel.H.length) {
            	let hArray = new Array;
            	hArray = [[],[]];
            	for(let i = 0; i <= 19; i++) {
            		hArray[j].push(parseInt(text[i].field1));
            	}
        	} 
            for(let j = 0; j < hChannel.H.length; j++) {
            	for(let i = 0; i <= 19; i++)
            		hArray[j].forEach(function(elem) {
            			if (elem < hErrorValue.H.min || elem > hErrorValue.H.max) {            				
            				hcolors[j] = 'red';
            			}
            		});
            }

        	if (hArray[j] != htemp[j]) {
				setValueForHChart();
				htemp[j] = hArray[j];
			}
			console.log(hArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function hUpdateThePage() {
    for(let j = 0; j < hChannel.H.length; j++) {
        hHttpGetAsync("http://thingtalk.ir/channels/" + hChannel.H[j].id + 
            "/feeds.json?key=" + hChannel.H[j].apiKey + "&results=20", function(){}, j);
    }
}

function hyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			htime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(htime);
    	}
    }
}

function setValueForHChart() {
	 var onechart = 
        {
            labels: htime,
            datasets: []
        };

	for(let j = 0; j < hChannel.H.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:hArray[j],
			borderWidth: 1,
			fill:false,
			borderColor:hcolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	hUpdateTile(onechart);
};

function hUpdateTile(onechart) {
	var ctx = document.getElementById('h');
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

setInterval(hUpdateThePage, 3000);
setInterval(hyAxisValues("http://thingtalk.ir/channels/721/feed.json?key=YV8JRH910ZJZQN1I&results=20"), 3000);