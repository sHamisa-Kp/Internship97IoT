function doIt(){
        let chartdata2=new Array;
        chartdata2=[[],[],[],[],[],[],[],[]];
        let last2=new Array;
    // let j2;
    // let label=new Array;

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
                        // let chartdata2=new Array;
                        chartdata2[j].push(parseInt(text[i].field1))
                        // label.push(parseInt(text[i].entry_id))
                    };
                    // console.log(chartdata2);
                    // console.log(label    
                };
            };
         xmlHttp.open("GET", theUrl, true); // true for asynchronous 
         xmlHttp.send(null);
     };
     function httpGetAsyncLast2(theUrl){
        let xmlHttp=new XMLHttpRequest();
        xmlHttp.onreadystatechange =function(){
            if (xmlHttp.readyState===4 && xmlHttp.status===200){
                let text=JSON.parse(xmlHttp.responseText).field1;
                last2.push(parseInt(text));
                // console.log(text);
            };
        };
        xmlHttp.open("GET",theUrl,true);
        xmlHttp.send(null);
    };
    for(j=0;j<=7;j++) 
    {
        // console.log(SMV[j].id);
        // console.log(SMV[j].apiKey);

        httpGetAsync("http://thingtalk.ir/channels/"+SMV[j].id+"/feed.json?key="+SMV[j].apiKey+"&results=20",j);
        httpGetAsyncLast2("http://thingtalk.ir/channels/"+SMV[j].id+"/feeds/last2.json?key="+SMV[j].apiKey);
        // console.log(last2.length);
    };

    // console.log(chartdata2);
    setTimeout(function(){
        var onechart = 
        {
            labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14, 15, 16, 17, 18, 19, 20],
            datasets: []
        };
        var colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
        for(let i=0;i<=7;i++){
            let color=colors[i];
            if(last2[i]<20||last2[i]>90){
                color='red';
            };
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
    },2000);
};
setInterval(doIt,5000);



// setInterval(doIt, 5000);


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