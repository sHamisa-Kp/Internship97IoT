	// WattMeter
// 	'WM': [{'id': '753', 'apiKey': 'OUAV3VIB076Y5UO0'}]


function doIt(){	
	let chartdata=new Array;
	let last=new Array;
	let j;


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
						chartdata.push(parseInt(text[i].field1));
						
						
					};
					// console.log(chartdata);
					
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
	// var a=chartdata.length;
	
	
	httpGetAsync("http://thingtalk.ir/channels/753/feed.json?key=OUAV3VIB076Y5UO0&results=20",j);
	// OUAV3VIB076Y5UO0
	// httpGetAsyncLast("http://thingtalk.ir/channels/"+FH.id+"/feeds/last.json?key="+FH.apiKey);
	
	setTimeout(function(){
		var onechart = 
		{
			labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14, 15, 16, 17, 18, 19, 20],
			datasets: []
		};
		var colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
		
		// let color=colors[i];
			// if(last[i]<20||last[i]>90){
			// 	color='red';
			// };
			// chartdata=[5,0,0,1,1,1,0,0,1,0,1,1,1,0,0,0,0,0,1,0];/*just for steppedLine*/
			var Data={
				label:'sensor',
				data:chartdata,
				borderWidth: 1,
				fill:false,
				steppedLine:true,
				borderColor:colors[1]
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
		},5000);
};
setInterval(doIt,6000);
