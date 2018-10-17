//This is for humidity chart
var humidityChart = document.querySelector("#humidityChart"); 
console.log(humidityChart);
humidityChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "h");
	// const humidityChannel = {
	// 	'H': [{'id': '669', 'apiKey': '7TPW8OQOGN1EMURD'},
	// 	{'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}]
	// };

	// const humidityErrorValue = {
	// 	'H': {'min': 30, 'max': 55}
	// };

	// let humidityArray = [];
	// let humidityError = [];
	// for(let i = 0; i < humidityChannel.H.length; i++) {
	// 	humidityError.push(false);
	// }

	// function humidityHttpGetAsync(theUrl, callback, i) {
	// 	let xmlHttp = new XMLHttpRequest();
	// 	xmlHttp.onreadystatechange = function() { 
	// 		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	//             // callback(xmlHttp.responseText);
	//             var text = new Array;
	//             text = JSON.parse(xmlHttp.responseText).feeds;

	//             for (let j = 0; j <= 19; j++) {
	//             	var num = parseInt(text[j].field1);
	//             	humidityArray[j].push(num);
	//             }
	//             for (let j = 0; j <= 19; j++) {
	//             	var num = parseInt(text[j].field1);
	//             	if(num < humidityErrorValue.H.min || num  > humidityErrorValue.H.max) {
	//             		humidityError[i] = true;
	//             	} else {
	//             		humidityError[i] = false;
	//             	}	
	//             }

 //        	}
 //    	};
	//     xmlHttp.open("GET", theUrl, true); // true for asynchronous 
	//     xmlHttp.send(null);
	// }

	// function humidityUpdateThePage() {
	// 	for(let i = 0; i < humidityChannel.H.length; i++) {
	// 		humidityHttpGetAsync("http://thingtalk.ir/channels/" + humidityChannel.H[i].id + 
	// 			"/feed.json?key=" + humidityChannel.H[i].apiKey+"&results=20", function(){}, i);
	// 	}
	// }
	// function updateLable(){
	// 	httpGetAsyncLabel("http://thingtalk.ir/channels/721/feed.json?key=YV8JRH910ZJZQN1I&results=20");
	// }
	

	// function httpGetAsyncLabel(theUrl) {
	// 	let xmlHttp = new XMLHttpRequest();
	// 	xmlHttp.onreadystatechange = function() { 
	// 		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	// 			var text=new Array;
	// 			text = JSON.parse(xmlHttp.responseText).feeds;
	// 			for(let i=0;i<=19;i++){
	// 				label.push(text[i].created_at)
	// 				res.push(label[i].slice(11,19));
	// 			}; console.log(res);

	// 		};
	// 	};
	// 	xmlHttp.open("GET", theUrl, true);
	// 	xmlHttp.send(null);
	// };
	// setInterval(humidityUpdateThePage, 3000);
	// setInterval(humidityCalculateAverage, 3000);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	function doIt(){	
		let chartdata=new Array;
		chartdata=[[],[]];
		let last=new Array;
		let j;
		let min=10;
		let max=70;
		let a=chartdata.length;
		let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
		let H=[{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
		{'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}];


		function httpGetAsync(theUrl,j) {
			let xmlHttp = new XMLHttpRequest();
			xmlHttp.onreadystatechange = function() { 
				if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
					var text=new Array;
					text = JSON.parse(xmlHttp.responseText).feeds;

					for(let i=0;i<=19;i++){
						chartdata[j].push(parseInt(text[i].field1))
					};	
					let a=chartdata[j][19];
					if(a<min || a>max){
						colors[j]='red';
					};
				};
			};
			xmlHttp.open("GET", theUrl, true);
			
			xmlHttp.send(null);
		};

		function httpGetAsyncLabel(theUrl) {
			let xmlHttp = new XMLHttpRequest();
			xmlHttp.onreadystatechange = function() { 
				if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
					var text=new Array;
					text = JSON.parse(xmlHttp.responseText).feeds;
					for(let i=0;i<=19;i++){
						label.push(text[i].created_at)
						res.push(label[i].slice(11,19));
					}; console.log(res);

				};
			};
			xmlHttp.open("GET", theUrl, true);
			xmlHttp.send(null);
		};

		let label=new Array();
		let res=new Array();
		httpGetAsyncLabel("http://thingtalk.ir/channels/721/feed.json?key=YV8JRH910ZJZQN1I&results=20");

		for(j=0;j<a;j++) 
		{
			httpGetAsync("http://thingtalk.ir/channels/"+H[j].id+"/feed.json?key="+H[j].apiKey+"&results=20",j);
		};

		setTimeout(function(){
			var onechart = 
			{
				labels: res,
				datasets: []
			};
			for(let i=0;i<a;i++){
				var Data={
					label:'sensor'+(i+1),
					data:chartdata[i],
					borderWidth: 1,
					fill:false,
					borderColor:colors[i]
				};
				onechart.datasets.push(Data);

			};

			var ctx = document.getElementById("h");
			Chart.defaults.global.defaultFontSize=10;
			Chart.defaults.global.defaultFontColor='black';
			var myChart1 = new Chart(ctx, {
				type: 'line',
				data:onechart,
				options:{
					legend:{
						display:true,
						labels:{
							fontColor:'black'
						},
						fullWidth:true
					}
				}
			});
		},3000);
	};
	setInterval(doIt,3000);
});

//this is for temperature chart
var temperatureChart = document.querySelector("#temperatureChart"); 
temperatureChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "t");
	function doIt(){	
		let chartdata=new Array;
		chartdata=[[],[]];
		let j;
		let min=5;
		let max=40;
		let a=chartdata.length;
		let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];

		let T=[{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'},
				{'id': '720','apikey': '6P4WUZHZZDR6U0TX'}];/*this channel(720) has a problem */


		function httpGetAsync(theUrl,j) {
			   let xmlHttp = new XMLHttpRequest();
			 	xmlHttp.onreadystatechange = function() { 
			 	    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			 	        // callback(xmlHttp.responseText);
			 	        var text=new Array;
			 	        text = JSON.parse(xmlHttp.responseText).feeds;
			 	        // callback(i+1, parseInt(text));
			 	        
			 	        for(let i=0;i<=19;i++){
			 	            chartdata[j].push(parseInt(text[i].field1))
			 	        };	
			 	        let a=chartdata[j][19];
	                    if(a<min || a>max){
	                        colors[j]='red';
	                    };
			 	    };
			    };
			 xmlHttp.open("GET", theUrl, true);
			 xmlHttp.send(null);
		};
		function httpGetAsyncLabel(theUrl) {
	        let xmlHttp = new XMLHttpRequest();
	        xmlHttp.onreadystatechange = function() { 
	            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	                    // callback(xmlHttp.responseText);
	                    var text=new Array;
	                    text = JSON.parse(xmlHttp.responseText).feeds;
	                    for(let i=0;i<=19;i++){
	                        label.push(text[i].created_at)
	                        res.push(label[i].slice(11,19));
	                    }; console.log(res);

	                };
	            };
	         xmlHttp.open("GET", theUrl, true);
	         xmlHttp.send(null);
	     };
	     let label=new Array();
	     let res=new Array();
	     httpGetAsyncLabel("http://thingtalk.ir/channels/671/feed.json?key=XAKAVEUUJQ9GZGMT&results=20");//channel 671

		for(j=0;j<a;j++) 
		{
		    httpGetAsync("http://thingtalk.ir/channels/"+T[j].id+"/feed.json?key="+T[j].apiKey+"&results=20",j)
		};

		setTimeout(function(){
			var onechart = 
				{
					labels: res,
					datasets: []
			    };
			for(let i=0;i<a;i++){
				var Data={
					label:'sensor'+(i+1),
					data:chartdata[i],
					borderWidth: 1,
					fill:false,
					borderColor:colors[i]
				};
				onechart.datasets.push(Data);
				;

			};
		
			var ctx = document.getElementById("t");
			Chart.defaults.global.defaultFontSize=10;
			var myChart1 = new Chart(ctx, {
			  type: 'line',
			  data:onechart,
			  options:{
			  	legend:{
			  		display:true,
			  		labels:{
			  			fontColor:'black'
			  		},
			  		fullWidth:true
			  	}
			  }
			});
		},3000);
	};
setInterval(doIt,3000);
});

//this is for soilmoisture of vegetables chart
var smvChart = document.querySelector("#smvChart"); 
smvChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "smv");

	function doIt(){
	    let chartdata2=new Array;
	    chartdata2=[[],[],[],[],[],[],[],[]];
	    let min=10;
	    let max=70;
	    // let colors=['#3d7000','#359100','#009148','#00ff00','#00c800','#01bf66','#156315','#3e6b3e']
	    // let colors=['#006400','#006400','#006400','#006400','#006400','#006400','#006400','#006400',]
	    let colors=['#00ff00','#006400','#3d7000','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
	    let SMV= [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'},
	    {'id': '700', 'apiKey': 'Q2JH4OBED4QQAA74'},
	    {'id': '701', 'apiKey': 'P6LY6LV7CPYYSJUP'},
	    {'id': '702', 'apiKey': 'PVWFQI4XLXLWL9DL'},
	    {'id': '703', 'apiKey': '6W8DYBXG0HDA141O'},
	    {'id': '704', 'apiKey': 'HZ9VM1PH1Q6LQSLZ'},
	    {'id': '705', 'apiKey': 'RUB06UUHPX0K4DDS'},
	    {'id': '706', 'apiKey': 'WQFB2JIGRVXDIAR4'}];

	    function httpGetAsync(theUrl,j) {
	        let xmlHttp = new XMLHttpRequest();
	        xmlHttp.onreadystatechange = function() { 
	            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	                    // callback(xmlHttp.responseText);
	                    var text=new Array;
	                    text = JSON.parse(xmlHttp.responseText).feeds;
	                    // callback(i+1, parseInt(text));
	                    
	                    for(let i=0;i<=19;i++){
	                        chartdata2[j].push(parseInt(text[i].field1))
	                    };
	                    let a=chartdata2[j][19];
	                    if(a<min || a>max){
	                        colors[j]='red';
	                    };
	                };
	            };
	         xmlHttp.open("GET", theUrl, true); // true for asynchronous 
	         xmlHttp.send(null);
	     };


	     function httpGetAsyncLabel(theUrl,j) {
	        let xmlHttp = new XMLHttpRequest();
	        xmlHttp.onreadystatechange = function() { 
	            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	                    // callback(xmlHttp.responseText);
	                    var text=new Array;
	                    text = JSON.parse(xmlHttp.responseText).feeds;
	                    for(let i=0;i<=19;i++){
	                        label.push(text[i].created_at)
	                        res.push(label[i].slice(11,19));
	                    }; console.log(res);

	                };
	            };
	         xmlHttp.open("GET", theUrl, true);
	         xmlHttp.send(null);
	     };
	     let label=new Array();
	     let res=new Array();
	     httpGetAsyncLabel("http://thingtalk.ir/channels/706/feed.json?key=WQFB2JIGRVXDIAR4&results=20");
	 
	  
	    for(j=0;j<=7;j++) 
	    {
	        httpGetAsync("http://thingtalk.ir/channels/"+SMV[j].id+"/feed.json?key="+SMV[j].apiKey+"&results=20",j);
	    };
    
	    setTimeout(function(){
	        var onechart = 
	        {
	            labels: res,
	            datasets: []
	        };
	       
	        for(let i=0;i<=7;i++){
	            var Data={
	                label:'sensor'+ (i+1),
	                data:chartdata2[i],
	                borderWidth: 1,
	                fill:false,
	                borderColor:colors[i]
	            };
	            onechart.datasets.push(Data);
	            // console.log(last2[i]);

	        };
	        // console.log(onechart);
	        // var color='#3e95cd',
	        var ctx = document.getElementById("smv");
	        Chart.defaults.global.defaultFontSize=10;
	        var myChart1 = new Chart(ctx, {
	            type: 'line',
	            data:onechart,
	            options:{
	                legend:{
	                    display:true,
	                    labels:{
	                        fontColor:'black'
	                    },
	                    fullWidth:false
	                }
	            }
	        });
	    },3000);
	};
setInterval(doIt,3000);
});

//this is for soilmoisture of flower chart
var smfChart = document.querySelector("#smfChart");
smfChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "smf");

	function doIt(){	
		let chartdata=new Array;
		chartdata=[[],[],[],[],[],[],[],[],[],[]];
		let j;
		let min=10;
	    let max=70;
		var a=chartdata.length;
		let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];

		let SMF=[{'id': '710', 'apiKey': 'FYMFZRW8E2YLIKUK'},
				{'id': '711', 'apiKey': 'OXXNS5C338I0TCIR'},
				{'id': '712', 'apiKey': 'MZEZGGZL09ZB48L1'},
				{'id': '713', 'apiKey': '7R9CHM056LSY23ZO'},
				{'id': '714', 'apiKey': 'ZPX8TF59L251UFVE'},
				{'id': '715', 'apiKey': '3DMD11SS9G5B94I0'},
				{'id': '716', 'apiKey': 'QLH9NDZ20RQNK96S'},
				{'id': '717', 'apiKey': 'BVAC00J644INRNG7'},
				{'id': '718', 'apiKey': 'T42SU8NKLQ4KYF5E'},
				{'id': '719', 'apiKey': 'OTSO6GP0GO9XUAU3'}];


		function httpGetAsync(theUrl,j) {
			   let xmlHttp = new XMLHttpRequest();
			 	xmlHttp.onreadystatechange = function() { 
			 	    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			 	        var text=new Array;
			 	        text = JSON.parse(xmlHttp.responseText).feeds;		 	        
			 	        for(let i=0;i<=19;i++){
			 	            chartdata[j].push(parseInt(text[i].field1))		 	           
			 	        };
			 	        let a=chartdata[j][19];
	                    if(a<min || a>max){
	                        colors[j]='red';
	                    };
			 	        
			 	    };
			    };
			 xmlHttp.open("GET", theUrl, true); 
			 xmlHttp.send(null);
		};
		function httpGetAsyncLabel(theUrl) {
	        let xmlHttp = new XMLHttpRequest();
	        xmlHttp.onreadystatechange = function() { 
	            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	                    // callback(xmlHttp.responseText);
	                    var text=new Array;
	                    text = JSON.parse(xmlHttp.responseText).feeds;
	                    for(let i=0;i<=19;i++){
	                        label.push(text[i].created_at)
	                        res.push(label[i].slice(11,19));
	                    }; console.log(res);

	                };
	            };
	         xmlHttp.open("GET", theUrl, true);
	         xmlHttp.send(null);
	     };
	     let label=new Array();
	     let res=new Array();
	     httpGetAsyncLabel("http://thingtalk.ir/channels/719/feed.json?key=OTSO6GP0GO9XUAU3&results=20");

		for(j=0;j<a;j++) 
		{
		    httpGetAsync("http://thingtalk.ir/channels/"+SMF[j].id+"/feed.json?key="+SMF[j].apiKey+"&results=20",j);
		};
	
		setTimeout(function(){
			var onechart = 
				{
					labels: res,
					datasets: []
			    };
			
			for(let i=0;i<a;i++){
				var Data={
					label:'sensor'+(i+1),
					data:chartdata[i],
					borderWidth: 1,
					fill:false,
					borderColor:colors[i]
				};
				onechart.datasets.push(Data);
			};

			var ctx = document.getElementById("smf");
			Chart.defaults.global.defaultFontSize=10;
			var myChart1 = new Chart(ctx, {
			  type: 'line',
			  data:onechart,
			  options:{
			  	legend:{
			  		display:true,
			  		labels:{
			  			fontColor:'black'
			  		},
			  		fullWidth:true
			  	}
			  }
			});
		},3000);
	};
setInterval(doIt,3000);
});

//this is for WaterLevel chart
var wlChart = document.querySelector("#wlChart");
wlChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "wl");

	// water level
	function doIt(){	
		let chartdata=new Array;
		let min=1;
		let max=21;
		let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
	
		function httpGetAsync(theUrl,j) {
			let xmlHttp = new XMLHttpRequest();
			xmlHttp.onreadystatechange = function() { 
				if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
						var text=new Array;
						text = JSON.parse(xmlHttp.responseText).feeds;					
						for(let i=0;i<=19;i++){
							chartdata.push(parseInt(text[i].field1));
						};
						let a=chartdata[19];
						if(a<min || a>max){
							colors[0]='red';
						};
						
					};
				};
			 xmlHttp.open("GET", theUrl, true);
			 xmlHttp.send(null);
			};
		function httpGetAsyncLabel(theUrl) {
	       let xmlHttp = new XMLHttpRequest();
	       xmlHttp.onreadystatechange = function() { 
	           if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	                   // callback(xmlHttp.responseText);
	                   var text=new Array;
	                   text = JSON.parse(xmlHttp.responseText).feeds;
	                   for(let i=0;i<=19;i++){
	                       label.push(text[i].created_at)
	                       res.push(label[i].slice(11,19));
	                   }; console.log(res);
	            };
	        };
	        xmlHttp.open("GET", theUrl, true);
	        xmlHttp.send(null);
	    };
	     let label=new Array();
	     let res=new Array();
	     httpGetAsyncLabel("http://thingtalk.ir/channels/742/feed.json?key=WGWJ660WN7V9394D&results=20");




		httpGetAsync("http://thingtalk.ir/channels/742/feed.json?key=WGWJ660WN7V9394D&results=20",j);
		setTimeout(function(){
			var onechart = 
			{
				labels: res,
				datasets: []
			};
				var Data={
					label:'sensor',
					data:chartdata,
					borderWidth: 1,
					fill:false,
					borderColor:colors[0]
				};
				onechart.datasets.push(Data);
				var ctx = document.getElementById("wl");
				Chart.defaults.global.defaultFontSize=10;
				var myChart1 = new Chart(ctx, {
					type: 'line',
					data:onechart,
					options:{
						legend:{
							display:true,
							labels:{
								fontColor:'black'
							},
							fullWidth:true
						}
					}
				});
		},3000);
	};
setInterval(doIt,3000);
});

//this is for photoresistor
var phrChart = document.querySelector("#phrChart");
phrChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "phr");

	function doIt(){	
		let chartdata=new Array;
		chartdata=[[],[]];
		let j;
		let min=10;
		let max=200;
		let a=chartdata.length;	
	    let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
		let PHR=[{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
			   {'id': '743', 'apiKey': 'ZH7OQMKALAPRZXQJ'}];
		function httpGetAsync(theUrl,j) {
			   let xmlHttp = new XMLHttpRequest();
			 	xmlHttp.onreadystatechange = function() { 
			 	    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			 	        var text=new Array;
			 	        text = JSON.parse(xmlHttp.responseText).feeds;		 	        
			 	        for(let i=0;i<=19;i++){
			 	            chartdata[j].push(parseInt(text[i].field1))
			 	        };
			 	        let a=chartdata[19];
	                    if(a<min || a>max){
	                        colors[j]='red';
	                    };		 	        											
			 	    };
			    };
			 xmlHttp.open("GET", theUrl, true);
			 xmlHttp.send(null);
		};
		

		function httpGetAsyncLabel(theUrl,j) {
	        let xmlHttp = new XMLHttpRequest();
	        xmlHttp.onreadystatechange = function() { 
	            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	                    // callback(xmlHttp.responseText);
	                    var text=new Array;
	                    text = JSON.parse(xmlHttp.responseText).feeds;
	                    for(let i=0;i<=19;i++){
	                        label.push(text[i].created_at)
	                        res.push(label[i].slice(11,19));
	                    }; console.log(res);

	                };
	            };
	         xmlHttp.open("GET", theUrl, true);
	         xmlHttp.send(null);
	     };
	     let label=new Array();
	     let res=new Array();
	     httpGetAsyncLabel("http://thingtalk.ir/channels/743/feed.json?key=ZH7OQMKALAPRZXQJ&results=20");


		for(j=0;j<a;j++) 
		{

		    httpGetAsync("http://thingtalk.ir/channels/"+PHR[j].id+"/feed.json?key="+PHR[j].apiKey+"&results=20",j);
		};
		setTimeout(function(){
			var onechart = 
				{
					labels: res,
					datasets: []
			    };
			for(let i=0;i<a;i++){
				var Data={
					label:'sensor'+(i+1),
					data:chartdata[i],
					fill:false,
					borderWidth: 1,
					borderColor:colors[i]
				};
				onechart.datasets.push(Data);
				;

			};
	
		
			var ctx = document.getElementById("phr");
			Chart.defaults.global.defaultFontSize=10;
			var myChart1 = new Chart(ctx, {
			  type: 'line',
			  data:onechart,
			  options:{
			  	legend:{
			  		display:true,
			  		labels:{
			  			fontColor:'black'
			  		},
			  		fullWidth:true
			  	}
			  }
			});
		},3000);
	};
setInterval(doIt,3000);

});

//this is for motion detector
var mdChart = document.querySelector("#mdChart");
mdChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "md");

	function doIt(){	
		let chartdata=new Array;
		chartdata=[[],[]];
		let j;
		let status=1;
		let a=chartdata.length;
		let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
		let MD=[{'id': '749', 'apiKey': 'V197BB4SL21A2IKG'},
				{'id': '752', 'apiKey': 'ZCLT56CFVCG7DU50'}];


		function httpGetAsync(theUrl,j) {
			   let xmlHttp = new XMLHttpRequest();
			 	xmlHttp.onreadystatechange = function() { 
			 	    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			 	        var text=new Array;
			 	        text = JSON.parse(xmlHttp.responseText).feeds;		 	        
			 	        for(let i=0;i<=19;i++){
			 	            chartdata[j].push(parseInt(text[i].field1))
			 	        };
			 	        let a=chartdata[j][19];	
			 	        if(a==status){
			 	        	colors[j]='red';
			 	        }
			 	    };
			    };
			 xmlHttp.open("GET", theUrl, true); 
			 xmlHttp.send(null);
		};
		function httpGetAsyncLabel(theUrl) {
	        let xmlHttp = new XMLHttpRequest();
	        xmlHttp.onreadystatechange = function() { 
	            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	                    var text=new Array;
	                    text = JSON.parse(xmlHttp.responseText).feeds;
	                    for(let i=0;i<=19;i++){
	                        label.push(text[i].created_at)
	                        res.push(label[i].slice(11,19));
	                    }; console.log(res);

	                };
	            };
	         xmlHttp.open("GET", theUrl, true);
	         xmlHttp.send(null);
	     };
	     let label=new Array();
	     let res=new Array();
	     httpGetAsyncLabel("http://thingtalk.ir/channels/752/feed.json?key=ZCLT56CFVCG7DU50&results=20");

		for(j=0;j<a;j++) 
		{
		    httpGetAsync("http://thingtalk.ir/channels/"+MD[j].id+"/feed.json?key="+MD[j].apiKey+"&results=20",j);
		};

		setTimeout(function(){
			var onechart = 
				{
					labels: res,
					datasets: []
			    };
			for(let i=0;i<a;i++){
				var Data={
					label:'sensor'+(i+1),
					data:chartdata[i],
					borderWidth: 1,
					fill:false,
					borderColor:colors[i]
				};
				onechart.datasets.push(Data);
				;

			};
	
		
			var ctx = document.getElementById("md");
			Chart.defaults.global.defaultFontSize=10;
			var myChart1 = new Chart(ctx, {
			  type: 'line',
			  data:onechart,
			  options:{
			  	legend:{
			  		display:true,
			  		labels:{
			  			fontColor:'black'
			  		},
			  		fullWidth:true
			  	}
			  }
			});
		},3000);
	};
// doIt();
setInterval(doIt,3000);
});

//this is for watt meter
var wmChart = document.querySelector("#wmChart");
wmChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "wm");

	// WattMeter
	function doIt(){	
		let chartdata=new Array;
		let min=10;
		let max=70;
		let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
		function httpGetAsync(theUrl) {
			let xmlHttp = new XMLHttpRequest();
			xmlHttp.onreadystatechange = function() { 
				if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
						// callback(xmlHttp.responseText);
						var text=new Array;
						text = JSON.parse(xmlHttp.responseText).feeds;
						// callback(i+1, parseInt(text));
						
						for(let i=0;i<=19;i++){
							chartdata.push(parseInt(text[i].field1));						
						};
						let a=chartdata[19];
						if(a<min || a>max){
							colors[0]='red';
						}
						
					};
				};
			 xmlHttp.open("GET", theUrl, true); 
			 xmlHttp.send(null);
		};
		function httpGetAsyncLabel(theUrl) {
	        let xmlHttp = new XMLHttpRequest();
	        xmlHttp.onreadystatechange = function() { 
	            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
	                    // callback(xmlHttp.responseText);
	                    var text=new Array;
	                    text = JSON.parse(xmlHttp.responseText).feeds;
	                    for(let i=0;i<=19;i++){
	                        label.push(text[i].created_at)
	                        res.push(label[i].slice(11,19));
	                    };

	                };
	            };
	         xmlHttp.open("GET", theUrl, true);
	         xmlHttp.send(null);
	     };
	     let label=new Array();
	     let res=new Array();
	     httpGetAsyncLabel("http://thingtalk.ir/channels/753/feed.json?key=OUAV3VIB076Y5UO0&results=20");


		httpGetAsync("http://thingtalk.ir/channels/753/feed.json?key=OUAV3VIB076Y5UO0&results=20");
		setTimeout(function(){
			var onechart = 
			{
				labels: res,
				datasets: []
			};
				var Data={
					label:'sensor',
					data:chartdata,
					borderWidth: 1,
					fill:false,
					borderColor:colors[0]
				};
				onechart.datasets.push(Data);

				// console.log(onechart.datasets);
				var ctx = document.getElementById("wm");
				Chart.defaults.global.defaultFontSize=10;
				var myChart1 = new Chart(ctx, {
					type: 'line',
					data:onechart,
					options:{
						legend:{
							display:true,
							labels:{
								fontColor:'black'
							},
							fullWidth:true
						}
					}
				});
			},3000);
	};
setInterval(doIt,3000);
});

//this is for floor humidity (rain sensor)
var fhChart = document.querySelector("#fhChart");
fhChart.addEventListener("click", function(){
	var canvas = document.querySelector(".chartPlace");
	canvas.getAttribute("id");
	canvas.setAttribute("id", "fh");	
});