function doIt(){
    let chartdata2=new Array;
    chartdata2=[[],[],[],[],[],[],[],[]];
    let last;
    let color;
    let min=10;
    let max=70;
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
                    let a=chartdata2[j][19];
                    console.log(a);
                    if(a<min || a>max){
                        colors[j]='red';
                        console.log(colors[j]);
                    };
                };
            };
         xmlHttp.open("GET", theUrl, true); // true for asynchronous 
         xmlHttp.send(null);
     };
     function httpGetAsyncLast(theUrl,i){
        let xmlHttp=new XMLHttpRequest();
        xmlHttp.onreadystatechange =function(){
            if (xmlHttp.readyState===4 && xmlHttp.status===200){
                let text=JSON.parse(xmlHttp.responseText).field1;
                last=parseInt(text);
                // console.log(text);
                console.log(last);
            };
            if(last<min||last>max){
                colors[i]='red';
                console.log(colors[i]);
                console.log(chartdata2[i])
            };
            // 
        };
        xmlHttp.open("GET",theUrl,true);
        xmlHttp.send(null);
    };
// function httpGetAsyncLast(theUrl){
//     let xmlHttp=new XMLHttpRequest();
//     xmlHttp.onreadystatechange =function(){
//         if (xmlHttp.readyState===4 && xmlHttp.status===200){
//             let text=JSON.parse(xmlHttp.responseText).field1;
//             // last.push(parseInt(text));
//             // console.log(last[i]);
//             a=parseInt(text);
            
//         };
//         if(a>max){
//             i='red'

//         }else {
//             i='green'
//         }
        

//     };
//     xmlHttp.open("GET",theUrl,true);
//     xmlHttp.send(null);
// };




    let colors=['#00ff00','#006400','#00c800','#2faf2f','#47b247','#64af64','#80b780','#156315','#3e6b3e','#003d00','#264c26','#435b43','#003000','#053505','#112811','#364736','#06d168','#2fbc73','#034f28'];
    for(j=0;j<=7;j++) 
    {
        httpGetAsync("http://thingtalk.ir/channels/"+SMV[j].id+"/feed.json?key="+SMV[j].apiKey+"&results=20",j);
    };
    
    // for(let i=0;i<=7;i++)
    // {
    //     httpGetAsyncLast("http://thingtalk.ir/channels/"+SMV[i].id+"/feeds/last.json?key="+SMV[i].apiKey,i);
    // };
    setTimeout(function(){
        var onechart = 
        {
            labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14, 15, 16, 17, 18, 19, 20],
            datasets: []
        };
       
        for(let i=0;i<=7;i++){
            // color=colors[i];
            // httpGetAsyncLast("http://thingtalk.ir/channels/724/feeds/last.json?key=A7SC0GJKQAFJZWAO");
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
