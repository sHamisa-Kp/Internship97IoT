function doIt(){	
	let chartdata=new Array;
	chartdata=[[],[]];
	let last=new Array;
	let j;
	// let label=new Array;

	let H=[{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
			{'id': '721', 'apiKey': 'YV8JRH910ZJZQN1I'}];


	function httpGetAsync(theUrl,j) {
		   let xmlHttp = new XMLHttpRequest();
		 	xmlHttp.onreadystatechange = function() { 
		 	    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
		 	        // callback(xmlHttp.responseText);
		 	        var text=new Array;
		 	        text = JSON.parse(xmlHttp.responseText).feeds;
		 	        // callback(i+1, parseInt(text));
		 	        
		 	        for(let i=0;i<=19;i++){
		 	            // let chartdata=new Array;
		 	            chartdata[j].push(parseInt(text[i].field1))
		 	            // label.push(parseInt(text[i].entry_id))
		 	        };
		 	        // console.log(chartdata);
		 	        // console.log(label	
		 	    };
		    };
		 xmlHttp.open("GET", theUrl, true); // true for asynchronous 
		 xmlHttp.send(null);
	};
	function httpGetAsyncLast(theUrl){
		let xmlHttp=new XMLHttpRequest();
		xmlHttp.onreadystatechange =function(){
			if (xmlHttp.readyState===4 && xmlHttp.status===200){
				let text=JSON.parse(xmlHttp.responseText).field1;
				last.push(parseInt(text));
				// console.log(text);
			};
		};
		xmlHttp.open("GET",theUrl,true);
		xmlHttp.send(null);
	};
	var a=chartdata.length;
	
	for(j=0;j<a;j++) 
	{
		

	    httpGetAsync("http://thingtalk.ir/channels/"+H[j].id+"/feed.json?key="+H[j].apiKey+"&results=20",j);
	    httpGetAsyncLast("http://thingtalk.ir/channels/"+H[j].id+"/feeds/last.json?key="+H[j].apiKey);
	};
	setTimeout(function(){
		var onechart = 
			{
				labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14, 15, 16, 17, 18, 19, 20],
				datasets: []
		    };
		var colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
		for(let i=0;i<a;i++){
			let color=colors[i];
			if(last[i]<20||last[i]>90){
				color='red';
			};
			var Data={
				label:'sensor'+(i+1),
				data:chartdata[i],
				borderWidth: 1,
				fill:false,
				borderColor:colors[i]
			};
			onechart.datasets.push(Data);
			// console.log(last[i]);

		};
		console.log(onechart.datasets);
		// var color='#3e95cd',
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
	},2000);
};
setInterval(doIt,5000);