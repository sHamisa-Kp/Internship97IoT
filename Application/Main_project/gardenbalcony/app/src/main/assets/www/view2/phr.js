function doIt(){	
	let chartdata=new Array;
	chartdata=[[],[]];
	let j;
	let min=10;
	let max=200;
	let a=chartdata.length;	
    // let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
    let colors=['#9505f2','#f285b2','#f78340','#8785f2','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];
	
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
	},5000);
};
setInterval(doIt,6000);
