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
    },5000);
};
setInterval(doIt,6000);


// let SMF=[{'id': '710', 'apiKey': 'FYMFZRW8E2YLIKUK'},
//      {'id': '711', 'apiKey': 'OXXNS5C338I0TCIR'},
//      {'id': '712', 'apiKey': 'MZEZGGZL09ZB48L1'},
//      {'id': '713', 'apiKey': '7R9CHM056LSY23ZO'},
//      {'id': '714', 'apiKey': 'ZPX8TF59L251UFVE'},
//      {'id': '715', 'apiKey': '3DMD11SS9G5B94I0'},
//      {'id': '716', 'apiKey': 'QLH9NDZ20RQNK96S'},
//      {'id': '717', 'apiKey': 'BVAC00J644INRNG7'},
//      {'id': '718', 'apiKey': 'T42SU8NKLQ4KYF5E'},
//      {'id': '719', 'apiKey': 'OTSO6GP0GO9XUAU3'}];
