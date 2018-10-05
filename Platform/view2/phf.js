function doIt(){    
    let chartdata=new Array;
    chartdata=[[],[],[],[],[],[],[],[],[],[]];
    let j;
    let min=5;
    let max=6;
    let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
    let a=chartdata.length;

    let PHF=[{'id': '732', 'apiKey': 'LG2G6VYVGRROA0O2'},
         {'id': '733', 'apiKey': 'BQYNJJL4G8A5RCHJ'},
         {'id': '734', 'apiKey': 'RZAEIWTMDVBOCNZS'},
         {'id': '735', 'apiKey': '4NWVLGMJHDE8WDAJ'},
         {'id': '736', 'apiKey': 'OW4B80VUEXJ38AG7'},
         {'id': '737', 'apiKey': 'I7MAFZ8E11BHLD4T'},
         {'id': '738', 'apiKey': '5WPKZ6PW9ZYCXC1T'},
         {'id': '739', 'apiKey': 'TD2B6CE14BODBAU4'},
         {'id': '740', 'apiKey': 'AGOE4BOV5662UOQF'},
         {'id': '741', 'apiKey': '29LLHTWCLAZONUOS'}];

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
     function httpGetAsyncLabel(theUrl,j) {
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
     httpGetAsyncLabel("http://thingtalk.ir/channels/741/feed.json?key=29LLHTWCLAZONUOS&results=20");
    
    for(j=0;j<a;j++) 
    {
        httpGetAsync("http://thingtalk.ir/channels/"+PHF[j].id+"/feed.json?key="+PHF[j].apiKey+"&results=20",j);
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
        var ctx = document.getElementById("phf");
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
