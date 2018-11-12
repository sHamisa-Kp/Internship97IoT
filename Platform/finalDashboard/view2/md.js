const mdChannel = {
	// Motion Detector
	'MD': [{'id': '749', 'apiKey': 'V197BB4SL21A2IKG'},
	{'id': '752', 'apiKey': 'ZCLT56CFVCG7DU50'}]
};

let mdArray = new Array;
mdArray = [[],[]];
let mdtemp = new Array;
mdtemp = [[],[]];
let mdcount = 0;
let mdtime = new Array();
let mdcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function mdHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (mdcount < mdChannel.MD.length) {
            	for(let i = 0; i <= 19; i++) {
                	mdArray[j].push(parseInt(text[i].field1));
            	};
            } mdcount += 1;            

            if (mdcount >= mdChannel.MD.length) {
            	let mdArray = new Array;
            	mdArray = [[],[]];
            	for(let i = 0; i <= 19; i++) {
            		mdArray[j].push(parseInt(text[i].field1));
            	}
        	} 
            // for(let j = 0; j < mdChannel.MD.length; j++) {
            // 	for(let i = 0; i <= 19; i++)
            // 		mdArray[j].forEach(function(elem) {
            // 			if (elem < mdErrorValue.MD.min || elem > mdErrorValue.MD.max) {            				
            // 				mdcolors[j] = 'red';
            // 			}
            // 		});
            // }

        	if (mdArray[j] != mdtemp[j]) {
				setValueForMdChart();
				mdtemp[j] = mdArray[j];
			}
			console.log(mdArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function mdUpdateThePage() {
    for(let j = 0; j < mdChannel.MD.length; j++) {
        mdHttpGetAsync("http://thingtalk.ir/channels/" + mdChannel.MD[j].id + 
            "/feeds.json?key=" + mdChannel.MD[j].apiKey + "&results=20", function(){}, j);
    }
}

function mdyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			mdtime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(mdtime);
    	}
    }
}

function setValueForMdChart() {
	 var onechart = 
        {
            labels: mdtime,
            datasets: []
        };

	for(let j = 0; j < mdChannel.MD.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:mdArray[j],
			borderWidth: 1,
			fill:false,
			borderColor:mdcolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	mdUpdateTile(onechart);	
};

function mdUpdateTile(onechart) {
	var ctx = document.getElementById('md');
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

setInterval(mdUpdateThePage, 3000);
setInterval(mdyAxisValues("http://thingtalk.ir/channels/752/feed.json?key=ZCLT56CFVCG7DU50&results=20"), 3000);