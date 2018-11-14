const fhChannel = {
  // Floor Humidity
  'FH': [{'id': '722', 'apiKey': '3ZIOUDCBO1X4W0B7'}, 
  {'id': '723', 'apiKey': 'IG7Z0OW1NR2LVGSW'}]

  }
const fhErrorValue = {
    'FH': {'min': 0, 'max': 50}
};

let fhArray = new Array;
fhArray = [[],[]];
let fhtemp = new Array;
fhtemp = [[],[]];
let fhcount = 0;
let fhtime = new Array();
let fhcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function fhHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (fhcount < fhChannel.FH.length) {
            	for(let i = 0; i <= 19; i++) {
                	fhArray[j].push(parseInt(text[i].field1));
            	};
            }             

            if (fhcount >= fhChannel.FH.length) {
            	let fhArray = new Array;
            	fhArray = [[],[],[],[],[],[],[],[]];
            	for(let i = 0; i <= 19; i++) {
            		fhArray[j].push(parseInt(text[i].field1));
            	}
        	} fhcount += 1;

            for(let j = 0; j < fhChannel.FH.length; j++) {
            	for(let i = 0; i <= 19; i++)
            		fhArray[j].forEach(function(elem) {
            			if (elem < fhErrorValue.FH.min || elem > fhErrorValue.FH.max) {            				
            				fhcolors[j] = 'red';
            			}
            		});
            }

        	if (fhArray[j] != fhtemp[j]) {
				setValueForFhChart();
				fhtemp[j] = fhArray[j];
			}
			console.log(fhArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function fhUpdateThePage() {
    for(let j = 0; j < fhChannel.FH.length; j++) {
        fhHttpGetAsync("http://thingtalk.ir/channels/" + fhChannel.FH[j].id + 
            "/feeds.json?key=" + fhChannel.FH[j].apiKey + "&results=20", function(){}, j);
    }
}

function fhyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			fhtime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(fhtime);
    	}
    }
}

function setValueForFhChart() {
	 var onechart = 
        {
            labels: fhtime,
            datasets: []
        };

	for(let j = 0; j < fhChannel.FH.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:fhArray[j],
			borderWidth: 1,
			fill:false,
			borderColor: fhcolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	UpdateFhTile(onechart);	
};

function UpdateFhTile(onechart) {
	var ctx = document.getElementById('fh');
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

setInterval(fhUpdateThePage, 3000);
setInterval(fhyAxisValues("http://thingtalk.ir/channels/723/feed.json?key=IG7Z0OW1NR2LVGSW&results=20"), 3000);
