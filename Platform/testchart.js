console.log("hey there!!")
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
let vegSMError = [];
for(let j = 0; j < smvChannel.SM.length; j++) {
    vegSMError.push(false);
}
let time = new Array();
let date = new Array;
let colors=['#9505f2','#f285b2','#f78340','#8785f2','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function smvHttpGetAsync(theUrl, callback, j) {
	console.log("First func is working");
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        	console.log("everything is ok!");
            // callback(xmlHttp.responseText);
            var text=new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;
            for(let i=0;i<=19;i++){
                vegSMArray[j].push(parseInt(text[i].field1));
                // date[j]=(text[i].created_at.slice(0,9) + '' + text[i].created_at.slice(11,19));
            };
            date = yAxisValues("http://thingtalk.ir/channels/706/feed.json?key=WQFB2JIGRVXDIAR4&results=20");
            console.log(date);
            var onechart = 
            {
            	labels: date,
            	datasets: []
            };
            for(let j=0;j<=7;j++){
            	var Data={
            		label:'sensor'+ (j+1),
            		data:vegSMArray[j],
            		borderWidth: 1,
            		fill:false,
            		borderColor:colors[j]
            	};
        	}
        	onechart.datasets.push(Data);
            smvUpdateTile(onechart);
        }
        else {console.log("xml req have trouble :(");}
    };
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
	console.log("yAxisValues calling correctly!!");
	let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
    	if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
    		var y = new Array();
    		y = JSON.parse(xmlHttp.responseText).feeds;
    		for(let i=0;i<=19;i++){
    			time.push(y[i].created_at.slice(0,9) + '' + y[i].created_at.slice(11,19));
    			// label.push(text[i].created_at)
    			// res.push(label[i].slice(11,19));
    		};
    		console.log(time);
    		return time;

    	}
    }
    // return time;	
}

function smvUpdateTile(onechart) {
	var ctx = document.getElementById('myChart');
	var chart = new Chart(ctx, {
	    // The type of chart we want to create
	    type: 'line',

	    // The data for our dataset
	    data: onechart,
	    // {
	    // 	labels: date,
	    // 	datasets: [{
	    // 		label: "My First dataset",
	    // 		backgroundColor: 'rgba(255, 99, 132,0)',
	    // 		borderColor: 'rgb(255, 0, 0)',
	    // 		data: vegSMArray,
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

setInterval(smvUpdateThePage,3000);