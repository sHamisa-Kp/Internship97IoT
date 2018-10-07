function doIt(){    
    let chartdata=new Array;
    chartdata=[[],[],[],[],[],[],[],[]];
    let j;
    let min=5;
    let max=7;
    let a=chartdata.length;
    let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
    let PHV=[{'id': '724', 'apiKey': 'A7SC0GJKQAFJZWAO'},
            {'id': '725', 'apiKey': 'THRJGRI2XUH1YZIO'},
            {'id': '726', 'apiKey': 'E8QFCI9OYGTOZIMT'},
            {'id': '727', 'apiKey': 'ATGVGAYRFPS4QCVO'},
            {'id': '728', 'apiKey': 'Z4AXFJXQK1ZN33BQ'},
            {'id': '729', 'apiKey': '61SF7VT52KJZJCPW'},
            {'id': '730', 'apiKey': 'NF3K4HLNFZ0BE2XO'},
            {'id': '731', 'apiKey': 'VAK0BLGBNXUDZMQB'}];

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
     httpGetAsyncLabel("http://thingtalk.ir/channels/731/feed.json?key=VAK0BLGBNXUDZMQB&results=20");
    
    for(j=0;j<a;j++) 
    {
        httpGetAsync("http://thingtalk.ir/channels/"+PHV[j].id+"/feed.json?key="+PHV[j].apiKey+"&results=20",j);
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
        var ctx = document.getElementById("phv");
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
