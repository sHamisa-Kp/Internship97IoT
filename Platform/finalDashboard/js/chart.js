//This is for humidity chart
var humidityChart = document.querySelector("#humidityChart"); 
console.log(humidityChart);
humidityChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "h");

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
			
});

//this is for temperature chart
var temperatureChart = document.querySelector("#temperatureChart"); 
temperatureChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "t");
	
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
});

//this is for soilmoisture of vegetables chart
var smvChart = document.querySelector("#smvChart"); 
smvChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "smv");

	const smvChannel = {
    // SoilMoisture (Vegetables: 0-7, Flowers: 8-17)
    'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'},
    {'id': '700', 'apiKey': 'Q2JH4OBED4QQAA74'},
    {'id': '701', 'apiKey': 'P6LY6LV7CPYYSJUP'},
    {'id': '702', 'apiKey': 'PVWFQI4XLXLWL9DL'},
    {'id': '703', 'apiKey': '6W8DYBXG0HDA141O'},
    {'id': '704', 'apiKey': 'HZ9VM1PH1Q6LQSLZ'},
    {'id': '705', 'apiKey': 'RUB06UUHPX0K4DDS'},
    {'id': '706', 'apiKey': 'WQFB2JIGRVXDIAR4'}]
};
const smvErrorValue = {
    'SM': {'min': 20, 'max': 30}
};

let vegSMArray = new Array;
vegSMArray = [[],[],[],[],[],[],[],[]];
let temp = new Array;
temp = [[],[],[],[],[],[],[],[]];
let count = 0;
let time = new Array();
let colors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function smvHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (count < smvChannel.SM.length) {
                for(let i = 0; i <= 19; i++) {
                    vegSMArray[j].push(parseInt(text[i].field1));
                };
            } count += 1;            

            if (count >= smvChannel.SM.length) {
                let vegSMArray = new Array;
                vegSMArray = [[],[],[],[],[],[],[],[]];
                for(let i = 0; i <= 19; i++) {
                    vegSMArray[j].push(parseInt(text[i].field1));
                }
            } 
            for(let j = 0; j < smvChannel.SM.length; j++) {
             for(let i = 0; i <= 19; i++)
                 vegSMArray[j].forEach(function(elem) {
                     if (elem < smvErrorValue.SM.min || elem > smvErrorValue.SM.max) {                           
                         colors[j] = 'red';
                     }
                 });
            }

            if (vegSMArray[j] != temp[j]) {
                setValueForChart();
                temp[j] = vegSMArray[j];
            }
            console.log(vegSMArray);
        }           
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function smvUpdateThePage() {
    for(let j = 0; j < smvChannel.SM.length; j++) {
        smvHttpGetAsync("http://thingtalk.ir/channels/" + smvChannel.SM[j].id + 
            "/feeds.json?key=" + smvChannel.SM[j].apiKey + "&results=20", function(){}, j);
    }
}

function yAxisValues(theUrl) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var y = new Array();
            y = JSON.parse(xmlHttp.responseText).feeds;
            for(let i = 0; i <= 19; i++) {
                time[i] = (y[i].created_at.slice(11,19));
            };
            console.log(time);
        }
    }
}

function setValueForChart() {
     var onechart = 
        {
            labels: time,
            datasets: []
        };

    for(let j = 0; j < smvChannel.SM.length; j++) { 
        var Data={
            label:'sensor'+ (j+1),
            data:vegSMArray[j],
            borderWidth: 1,
            fill:false,
            borderColor:colors[j]
        };
        onechart.datasets.push(Data);       
    } 
    smvUpdateTile(onechart);    
};

function smvUpdateTile(onechart) {
    var ctx = document.getElementById('smv');
    var chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'line',

        // The data for our dataset
        data: onechart,
        // {
        //  labels: time,
        //  datasets: [{
        //      label: myLabel,
        //      data: vegSMArray,
        //      borderColor: myBorderColor,
        //      borderWidth: 1,
        //      fill: false,
        //      // backgroundColor: 'rgba(255, 99, 132,0)',
        //      // borderColor: 'rgb(255, 0, 0)',
                
        //  }]
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

setInterval(smvUpdateThePage, 3000);
setInterval(yAxisValues("http://thingtalk.ir/channels/706/feed.json?key=WQFB2JIGRVXDIAR4&results=20"), 3000);
});

//this is for soilmoisture of flower chart
var smfChart = document.querySelector("#smfChart");
smfChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "smf");

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
});

//this is for WaterLevel chart
var wlChart = document.querySelector("#wlChart");
wlChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "wl");

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
});

//this is for photoresistor
var phrChart = document.querySelector("#phrChart");
phrChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "phr");

	const phrChannel = {
    // Photo Resistor
    'PhR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
    {'id': '743', 'apiKey': 'ZH7OQMKALAPRZXQJ'}]
	};
	const phrErrorValue = {
	    'PhR': {'min': 0, 'max': 100}
	};

	let phrArray = new Array;
	phrArray = [[],[]];
	let phrtemp = new Array;
	phrtemp = [[],[]];
	let phrcount = 0;
	let phrtime = new Array();
	let phrcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

	function phrHttpGetAsync(theUrl, callback, j) {
	    let xmlHttp = new XMLHttpRequest();
	    xmlHttp.onreadystatechange = function() { 
	        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            var text = new Array;
	            text = JSON.parse(xmlHttp.responseText).feeds;

	            if (phrcount < phrChannel.PhR.length) {
	                for(let i = 0; i <= 19; i++) {
	                    phrArray[j].push(parseInt(text[i].field1));
	                };
	            } phrcount += 1;            

	            if (phrcount >= phrChannel.PhR.length) {
	                let phrArray = new Array;
	                phrArray = [[],[]];
	                for(let i = 0; i <= 19; i++) {
	                    phrArray[j].push(parseInt(text[i].field1));
	                }
	            } 
	            for(let j = 0; j < phrChannel.PhR.length; j++) {
	             for(let i = 0; i <= 19; i++)
	                 phrArray[j].forEach(function(elem) {
	                     if (elem < phrErrorValue.PhR.min || elem > phrErrorValue.PhR.max) {                           
	                         phrcolors[j] = 'red';
	                     }
	                 });
	            }

	            if (phrArray[j] != phrtemp[j]) {
	                setValueForPHrChart();
	                phrtemp[j] = phrArray[j];
	            }
	            console.log(phrArray);
	        }           
	    }      
	    xmlHttp.open("GET", theUrl, true); // true for asynchronous
	    xmlHttp.send(null);
	}

	function phrUpdateThePage() {
	    for(let j = 0; j < phrChannel.PhR.length; j++) {
	        phrHttpGetAsync("http://thingtalk.ir/channels/" + phrChannel.PhR[j].id + 
	            "/feeds.json?key=" + phrChannel.PhR[j].apiKey + "&results=20", function(){}, j);
	    }
	}

	function phryAxisValues(theUrl) {
	    let xmlHttp = new XMLHttpRequest();
	    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
	    xmlHttp.send(null);
	    xmlHttp.onreadystatechange = function() {
	        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            var y = new Array();
	            y = JSON.parse(xmlHttp.responseText).feeds;
	            for(let i = 0; i <= 19; i++) {
	                phrtime[i] = (y[i].created_at.slice(11,19));
	            };
	            console.log(phrtime);
	        }
	    }
	}

	function setValueForPHrChart() {
	     var onechart = 
	        {
	            labels: phrtime,
	            datasets: []
	        };

	    for(let j = 0; j < phrChannel.PhR.length; j++) { 
	        var Data={
	            label:'sensor'+ (j+1),
	            data:phrArray[j],
	            borderWidth: 1,
	            fill:false,
	            borderColor:phrcolors[j]
	        };
	        onechart.datasets.push(Data);       
	    } 
	    phrUpdateTile(onechart);    
	};

	function phrUpdateTile(onechart) {
	    var ctx = document.getElementById('phr');
	    var chart = new Chart(ctx, {
	        // The type of chart we want to create
	        type: 'line',

	        // The data for our dataset
	        data: onechart,
	        // {
	        //  labels: time,
	        //  datasets: [{
	        //      label: myLabel,
	        //      data: vegSMArray,
	        //      borderColor: myBorderColor,
	        //      borderWidth: 1,
	        //      fill: false,
	        //      // backgroundColor: 'rgba(255, 99, 132,0)',
	        //      // borderColor: 'rgb(255, 0, 0)',
	                
	        //  }]
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

	setInterval(phrUpdateThePage, 3000);
	setInterval(phryAxisValues("http://thingtalk.ir/channels/743/feed.json?key=ZH7OQMKALAPRZXQJ&results=20"), 3000);

});

//this is for motion detector
var mdChart = document.querySelector("#mdChart");
mdChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "md");

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
});

//this is for watt meter
var wmChart = document.querySelector("#wmChart");
wmChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "wm");

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
});

//this is for floor humidity (rain sensor)
var fhChart = document.querySelector("#fhChart");
fhChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "fh1");

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
				borderColor:fhcolors[j]
			};
			onechart.datasets.push(Data);		
		} 
		UpdateFhTile(onechart);	
	};

	function UpdateFhTile(onechart) {
		var ctx = document.getElementById('fh1');
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
});

//this is for Gas
var gasChart = document.querySelector("#gasChart");
gasChart.addEventListener("click",function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "g");

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
});

//this is for ph of vegetables
var phvChart = document.querySelector("#phvChart");
phvChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "phv");

	const phvChannel = {
	// pH (Vegetables: 0-7, Flowers: 8-17)
	  'PH': [{'id': '724', 'apiKey': 'A7SC0GJKQAFJZWAO'},
	  {'id': '725', 'apiKey': 'THRJGRI2XUH1YZIO'},
	  {'id': '726', 'apiKey': 'E8QFCI9OYGTOZIMT'},
	  {'id': '727', 'apiKey': 'ATGVGAYRFPS4QCVO'},
	  {'id': '728', 'apiKey': 'Z4AXFJXQK1ZN33BQ'},
	  {'id': '729', 'apiKey': '61SF7VT52KJZJCPW'},
	  {'id': '730', 'apiKey': 'NF3K4HLNFZ0BE2XO'},
	  {'id': '731', 'apiKey': 'VAK0BLGBNXUDZMQB'}]
	};
	const phvErrorValue = {
	    'PH': {'min': 6, 'max': 7}
	};

	let phvArray = new Array;
	phvArray = [[],[],[],[],[],[],[],[]];
	let phvtemp = new Array;
	phvtemp = [[],[],[],[],[],[],[],[]];
	let phvcount = 0;
	let phvtime = new Array();
	let phvcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

	function phvHttpGetAsync(theUrl, callback, j) {
	    let xmlHttp = new XMLHttpRequest();
	    xmlHttp.onreadystatechange = function() { 
	        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            var text = new Array;
	            text = JSON.parse(xmlHttp.responseText).feeds;

	            if (phvcount < phvChannel.PH.length) {
	                for(let i = 0; i <= 19; i++) {
	                    phvArray[j].push(parseInt(text[i].field1));
	                };
	            } phvcount += 1;            

	            if (phvcount >= phvChannel.PH.length) {
	                let phvArray = new Array;
	                phvArray = [[],[],[],[],[],[],[],[]];
	                for(let i = 0; i <= 19; i++) {
	                    phvArray[j].push(parseInt(text[i].field1));
	                }
	            } 
	            for(let j = 0; j < phvChannel.PH.length; j++) {
	             for(let i = 0; i <= 19; i++)
	                 phvArray[j].forEach(function(elem) {
	                     if (elem < phvErrorValue.PH.min || elem > phvErrorValue.PH.max) {                           
	                         phvcolors[j] = 'red';
	                     }
	                 });
	            }

	            if (phvArray[j] != phvtemp[j]) {
	                setValueForPHvChart();
	                phvtemp[j] = phvArray[j];
	            }
	            console.log(phvArray);
	        }           
	    }      
	    xmlHttp.open("GET", theUrl, true); // true for asynchronous
	    xmlHttp.send(null);
	}

	function phvUpdateThePage() {
	    for(let j = 0; j < phvChannel.PH.length; j++) {
	        phvHttpGetAsync("http://thingtalk.ir/channels/" + phvChannel.PH[j].id + 
	            "/feeds.json?key=" + phvChannel.PH[j].apiKey + "&results=20", function(){}, j);
	    }
	}

	function phvyAxisValues(theUrl) {
	    let xmlHttp = new XMLHttpRequest();
	    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
	    xmlHttp.send(null);
	    xmlHttp.onreadystatechange = function() {
	        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            var y = new Array();
	            y = JSON.parse(xmlHttp.responseText).feeds;
	            for(let i = 0; i <= 19; i++) {
	                phvtime[i] = (y[i].created_at.slice(11,19));
	            };
	            console.log(phvtime);
	        }
	    }
	}

	function setValueForPHvChart() {
	     var onechart = 
	        {
	            labels: phvtime,
	            datasets: []
	        };

	    for(let j = 0; j < phvChannel.PH.length; j++) { 
	        var Data={
	            label:'sensor'+ (j+1),
	            data:phvArray[j],
	            borderWidth: 1,
	            fill:false,
	            borderColor:phvcolors[j]
	        };
	        onechart.datasets.push(Data);       
	    } 
	    phvUpdateTile(onechart);
	};

	function phvUpdateTile(onechart) {
	    var ctx = document.getElementById('phv');
	    var chart = new Chart(ctx, {
	        // The type of chart we want to create
	        type: 'line',

	        // The data for our dataset
	        data: onechart,
	        // {
	        //  labels: time,
	        //  datasets: [{
	        //      label: myLabel,
	        //      data: vegSMArray,
	        //      borderColor: myBorderColor,
	        //      borderWidth: 1,
	        //      fill: false,
	        //      // backgroundColor: 'rgba(255, 99, 132,0)',
	        //      // borderColor: 'rgb(255, 0, 0)',
	                
	        //  }]
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

	setInterval(phvUpdateThePage, 3000);
	setInterval(phvyAxisValues("http://thingtalk.ir/channels/731/feed.json?key=VAK0BLGBNXUDZMQB&results=20"), 3000);
});

//this is ph of flowers
var phfChart = document.querySelector("#phfChart");
phfChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "phf");

	const phfChannel = {
	    // pH (Vegetables: 0-7, Flowers: 8-17)
	    'PH': [{'id': '732', 'apiKey': 'LG2G6VYVGRROA0O2'},
	    {'id': '733', 'apiKey': 'BQYNJJL4G8A5RCHJ'},
	    {'id': '734', 'apiKey': 'RZAEIWTMDVBOCNZS'},
	    {'id': '735', 'apiKey': '4NWVLGMJHDE8WDAJ'},
	    {'id': '736', 'apiKey': 'OW4B80VUEXJ38AG7'},
	    {'id': '737', 'apiKey': 'I7MAFZ8E11BHLD4T'},
	    {'id': '738', 'apiKey': '5WPKZ6PW9ZYCXC1T'},
	    {'id': '739', 'apiKey': 'TD2B6CE14BODBAU4'},
	    {'id': '740', 'apiKey': 'AGOE4BOV5662UOQF'},
	    {'id': '741', 'apiKey': '29LLHTWCLAZONUOS'}]
	};
	const phfErrorValue = {
	    'PH': {'min': 6, 'max': 7}
	};

	let phfArray = new Array;
	phfArray = [[],[],[],[],[],[],[],[],[],[]];
	let phftemp = new Array;
	phftemp = [[],[],[],[],[],[],[],[],[],[]];
	let phfcount = 0;
	let phftime = new Array();
	let phfcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

	function phfHttpGetAsync(theUrl, callback, j) {
	    let xmlHttp = new XMLHttpRequest();
	    xmlHttp.onreadystatechange = function() { 
	        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            var text = new Array;
	            text = JSON.parse(xmlHttp.responseText).feeds;

	            if (phfcount < phfChannel.PH.length) {
	                for(let i = 0; i <= 19; i++) {
	                    phfArray[j].push(parseInt(text[i].field1));
	                };
	            } phfcount += 1;            

	            if (phfcount >= phfChannel.PH.length) {
	                let phfArray = new Array;
	                phfArray = [[],[],[],[],[],[],[],[],[],[]];
	                for(let i = 0; i <= 19; i++) {
	                    phfArray[j].push(parseInt(text[i].field1));
	                }
	            } phfcount += 1;  

	            for(let j = 0; j < phfChannel.PH.length; j++) {
	             for(let i = 0; i <= 19; i++)
	                 phfArray[j].forEach(function(elem) {
	                     if (elem < phfErrorValue.PH.min || elem > phfErrorValue.PH.max) {                           
	                         phfcolors[j] = 'red';
	                     }
	                 });
	            }

	            if (phfArray[j] != phftemp[j]) {
	                setValueForPHfChart();
	                phftemp[j] = phfArray[j];
	            }
	            console.log(phfArray);
	        }           
	    }      
	    xmlHttp.open("GET", theUrl, true); // true for asynchronous
	    xmlHttp.send(null);
	}

	function phfUpdateThePage() {
	    for(let j = 0; j < phfChannel.PH.length; j++) {
	        phfHttpGetAsync("http://thingtalk.ir/channels/" + phfChannel.PH[j].id + 
	            "/feeds.json?key=" + phfChannel.PH[j].apiKey + "&results=20", function(){}, j);
	    }
	}

	function phfyAxisValues(theUrl) {
	    let xmlHttp = new XMLHttpRequest();
	    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
	    xmlHttp.send(null);
	    xmlHttp.onreadystatechange = function() {
	        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	            var y = new Array();
	            y = JSON.parse(xmlHttp.responseText).feeds;
	            for(let i = 0; i <= 19; i++) {
	                phftime[i] = (y[i].created_at.slice(11,19));
	            };
	            console.log(phftime);
	        }
	    }
	}

	function setValueForPHfChart() {
	     var onechart = 
	        {
	            labels: phftime,
	            datasets: []
	        };

	    for(let j = 0; j < phfChannel.PH.length; j++) { 
	        var Data={
	            label:'sensor'+ (j+1),
	            data:phfArray[j],
	            borderWidth: 1,
	            fill:false,
	            borderColor:phfcolors[j]
	        };
	        onechart.datasets.push(Data);       
	    } 
	    phfUpdateTile(onechart);    
	};

	function phfUpdateTile(onechart) {
	    var ctx = document.getElementById('phf');
	    var chart = new Chart(ctx, {
	        // The type of chart we want to create
	        type: 'line',

	        // The data for our dataset
	        data: onechart,
	        // {
	        //  labels: time,
	        //  datasets: [{
	        //      label: myLabel,
	        //      data: vegSMArray,
	        //      borderColor: myBorderColor,
	        //      borderWidth: 1,
	        //      fill: false,
	        //      // backgroundColor: 'rgba(255, 99, 132,0)',
	        //      // borderColor: 'rgb(255, 0, 0)',
	                
	        //  }]
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

	setInterval(phfUpdateThePage, 3000);
	setInterval(phfyAxisValues("http://thingtalk.ir/channels/741/feed.json?key=29LLHTWCLAZONUOS&results=20"), 3000);
});