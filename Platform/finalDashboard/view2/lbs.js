const lbChannel = {
	// Light Bulb Status
	'LBS': [{'id': '745', 'apiKey': '2ZRJZIFPTQF79NOH'},
	{'id': '746', 'apiKey': '17J1AKN992YL3HUX'}]
};

let lbArray = new Array;
lbArray = [[],[]];
let lbtemp = new Array;
lbtemp = [[],[]];
let lbcount = 0;
let lbtime = new Array();
let lbcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function lbHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (lbcount < lbChannel.LBS.length) {
            	for(let i = 0; i <= 19; i++) {
                	lbArray[j].push(parseInt(text[i].field1));
            	};
            } lbcount += 1;            

            if (lbcount >= lbChannel.LBS.length) {
            	let lbArray = new Array;
            	lbArray = [[],[]];
            	for(let i = 0; i <= 19; i++) {
            		lbArray[j].push(parseInt(text[i].field1));
            	}
        	} 

        	if (lbArray[j] != lbtemp[j]) {
				setValueForLbChart();
				lbtemp[j] = lbArray[j];
			}
			console.log(lbArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function lbUpdateThePage() {
    for(let j = 0; j < lbChannel.LBS.length; j++) {
        lbHttpGetAsync("http://thingtalk.ir/channels/" + lbChannel.LBS[j].id + 
            "/feeds.json?key=" + lbChannel.LBS[j].apiKey + "&results=20", function(){}, j);
    }
}

function lbyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			lbtime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(lbtime);
    	}
    }
}

function setValueForLbChart() {
	 var onechart = 
        {
            labels: lbtime,
            datasets: []
        };

	for(let j = 0; j < lbChannel.LBS.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:lbArray[j],
			borderWidth: 1,
			fill:false,
			borderColor:lbcolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	lbUpdateTile(onechart);	
};

function lbUpdateTile(onechart) {
	var ctx = document.getElementById('lbs');
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

setInterval(lbUpdateThePage, 3000);
setInterval(lbyAxisValues("http://thingtalk.ir/channels/746/feed.json?key=17J1AKN992YL3HUX&results=20"), 3000);