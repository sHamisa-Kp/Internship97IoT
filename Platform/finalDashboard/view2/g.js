const gasChannel = {
	// Gas Sensor
	'G': [{'id': '747', 'apiKey': '65ZJD9TRET64FJ03'},
	{'id': '751', 'apiKey': '05VOPP6KT2NA1AZ5'}]
};
const gasErrorValue = {
    'G': {'min': 0, 'max': 150}
};

let gasArray = new Array;
gasArray = [[],[]];
let gastemp = new Array;
gastemp = [[],[]];
let gascount = 0;
let gastime = new Array();
let gasColors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function gasHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (gascount < gasChannel.G.length) {
            	for(let i = 0; i <= 19; i++) {
                	gasArray[j].push(parseInt(text[i].field1));
            	};
            } gascount += 1;            

            if (gascount >= gasChannel.G.length) {
            	let gasArray = new Array;
            	gasArray = [[],[]];
            	for(let i = 0; i <= 19; i++) {
            		gasArray[j].push(parseInt(text[i].field1));
            	}
        	} 
            for(let j = 0; j < gasChannel.G.length; j++) {
            	for(let i = 0; i <= 19; i++)
            		gasArray[j].forEach(function(elem) {
            			if (elem < gasErrorValue.G.min || elem > gasErrorValue.G.max) {            				
            				gasColors[j] = 'red';
            			}
            		});
            }

        	if (gasArray[j] != gastemp[j]) {
				setValueForGasChart();
				gastemp[j] = gasArray[j];
			}
			console.log(gasArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function gasUpdateThePage() {
    for(let j = 0; j < gasChannel.G.length; j++) {
        gasHttpGetAsync("http://thingtalk.ir/channels/" + gasChannel.G[j].id + 
            "/feeds.json?key=" + gasChannel.G[j].apiKey + "&results=20", function(){}, j);
    }
}

function gasyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			gastime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(gastime);
    	}
    }
}

function setValueForGasChart() {
	 var onechart = 
        {
            labels: gastime,
            datasets: []
        };

	for(let j = 0; j < gasChannel.G.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:gasArray[j],
			borderWidth: 1,
			fill:false,
			borderColor:gasColors[j]
		};
		onechart.datasets.push(Data);		
	} 
	gasUpdateTile(onechart);	
};

function gasUpdateTile(onechart) {
	var ctx = document.getElementById('g');
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
setInterval(gasUpdateThePage, 3000);
setInterval(gasyAxisValues("http://thingtalk.ir/channels/751/feed.json?key=05VOPP6KT2NA1AZ5&results=20"), 3000);