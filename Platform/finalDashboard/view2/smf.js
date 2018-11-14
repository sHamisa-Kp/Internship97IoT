const smfChannel = {
	// SoilMoisture (Vegetables: 0-7, Flowers: 8-17)
	'SM': [{'id': '710', 'apiKey': 'FYMFZRW8E2YLIKUK'},
  {'id': '711', 'apiKey': 'OXXNS5C338I0TCIR'},
  {'id': '712', 'apiKey': 'MZEZGGZL09ZB48L1'},
  {'id': '713', 'apiKey': '7R9CHM056LSY23ZO'},
  {'id': '714', 'apiKey': 'ZPX8TF59L251UFVE'},
  {'id': '715', 'apiKey': '3DMD11SS9G5B94I0'},
  {'id': '716', 'apiKey': 'QLH9NDZ20RQNK96S'},
  {'id': '717', 'apiKey': 'BVAC00J644INRNG7'},
  {'id': '718', 'apiKey': 'T42SU8NKLQ4KYF5E'},
  {'id': '719', 'apiKey': 'OTSO6GP0GO9XUAU3'}]
};
const smfErrorValue = {
    'SM': {'min': 20, 'max': 30}
};

let vegSFArray = new Array;
vegSFArray = [[],[],[],[],[],[],[],[],[],[]];
let smftemp = new Array;
smftemp = [[],[],[],[],[],[],[],[],[],[]];
let smfcount = 0;
let smftime = new Array();
let smfcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function smfHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (smfcount < smfChannel.SM.length) {
            	for(let i = 0; i <= 19; i++) {
                	vegSFArray[j].push(parseInt(text[i].field1));
            	};
            } smfcount += 1;            

            if (smfcount >= smfChannel.SM.length) {
            	let vegSFArray = new Array;
            	vegSFArray = [[],[],[],[],[],[],[],[],[],[]];
            	for(let i = 0; i <= 19; i++) {
            		vegSFArray[j].push(parseInt(text[i].field1));
            	}
        	} 
            for(let j = 0; j < smfChannel.SM.length; j++) {
            	for(let i = 0; i <= 19; i++)
            		vegSFArray[j].forEach(function(elem) {
            			if (elem < smfErrorValue.SM.min || elem > smfErrorValue.SM.max) {            				
            				smfcolors[j] = 'red';
            			}
            		});
            }

        	if (vegSFArray[j] != smftemp[j]) {
				setValueForSmFChart();
				smftemp[j] = vegSFArray[j];
			}
			console.log(vegSFArray);
		}	       	
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function smfUpdateThePage() {
    for(let j = 0; j < smfChannel.SM.length; j++) {
        smfHttpGetAsync("http://thingtalk.ir/channels/" + smfChannel.SM[j].id + 
            "/feeds.json?key=" + smfChannel.SM[j].apiKey + "&results=20", function(){}, j);
    }
}

function smfyAxisValues(theUrl) {
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i = 0; i <= 19; i++) {
    			smftime[i] = (y[i].created_at.slice(11,19));
    		};
    		console.log(smftime);
    	}
    }
}

function setValueForSmFChart() {
	 var onechart = 
        {
            labels: smftime,
            datasets: []
        };

	for(let j = 0; j < smfChannel.SM.length; j++) {	
		var Data={
			label:'sensor'+ (j+1),
			data:vegSFArray[j],
			borderWidth: 1,
			fill:false,
			borderColor:smfcolors[j]
		};
		onechart.datasets.push(Data);		
	} 
	smfUpdateTile(onechart);	
};

function smfUpdateTile(onechart) {
	var ctx = document.getElementById('smf');
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

setInterval(smfUpdateThePage, 3000);
setInterval(smfyAxisValues("http://thingtalk.ir/channels/719/feed.json?key=OTSO6GP0GO9XUAU3&results=20"), 3000);