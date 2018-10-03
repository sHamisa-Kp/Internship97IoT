function doIt(){	
	let chartdata=new Array;
	chartdata=[[],[]];
	let last=new Array;
	let j;
	// let label=new Array;

	let PHR=[{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
		   {'id': '743', 'apiKey': 'ZH7OQMKALAPRZXQJ'}];


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
				
			};
		};
		xmlHttp.open("GET",theUrl,true);
		xmlHttp.send(null);
	};
	var a=chartdata.length;

	for(j=0;j<a;j++) 
	{
	
		//(T[j].apiKey);
	    httpGetAsync("http://thingtalk.ir/channels/"+PHR[j].id+"/feed.json?key="+PHR[j].apiKey+"&results=20",j);
	    httpGetAsyncLast("http://thingtalk.ir/channels/"+PHR[j].id+"/feeds/last.json?key="+PHR[j].apiKey);
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
	},5000);
};
setInterval(doIt,6000);
