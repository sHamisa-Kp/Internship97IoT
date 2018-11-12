const phrChannel = {
    // Photo Resistor
    'PhR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'},
    {'id': '743', 'apiKey': 'ZH7OQMKALAPRZXQJ'}]
};
const phrErrorValue = {
    'PhR': {'min': 0, 'max': 100}
};

let phrArray = new Array;
phrArray = [[],[]];
let phrtemp = new Array;
phrtemp = [[],[]];
let phrcount = 0;
let phrtime = new Array();
let phrcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function phrHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (phrcount < phrChannel.PhR.length) {
                for(let i = 0; i <= 19; i++) {
                    phrArray[j].push(parseInt(text[i].field1));
                };
            } phrcount += 1;            

            if (phrcount >= phrChannel.PhR.length) {
                let phrArray = new Array;
                phrArray = [[],[]];
                for(let i = 0; i <= 19; i++) {
                    phrArray[j].push(parseInt(text[i].field1));
                }
            } 
            for(let j = 0; j < phrChannel.PhR.length; j++) {
             for(let i = 0; i <= 19; i++)
                 phrArray[j].forEach(function(elem) {
                     if (elem < phrErrorValue.PhR.min || elem > phrErrorValue.PhR.max) {                           
                         phrcolors[j] = 'red';
                     }
                 });
            }

            if (phrArray[j] != phrtemp[j]) {
                setValueForPHrChart();
                phrtemp[j] = phrArray[j];
            }
            console.log(phrArray);
        }           
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function phrUpdateThePage() {
    for(let j = 0; j < phrChannel.PhR.length; j++) {
        phrHttpGetAsync("http://thingtalk.ir/channels/" + phrChannel.PhR[j].id + 
            "/feeds.json?key=" + phrChannel.PhR[j].apiKey + "&results=20", function(){}, j);
    }
}

function phryAxisValues(theUrl) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var y = new Array();
            y = JSON.parse(xmlHttp.responseText).feeds;
            for(let i = 0; i <= 19; i++) {
                phrtime[i] = (y[i].created_at.slice(11,19));
            };
            console.log(phrtime);
        }
    }
}

function setValueForPHrChart() {
     var onechart = 
        {
            labels: phrtime,
            datasets: []
        };

    for(let j = 0; j < phrChannel.PhR.length; j++) { 
        var Data={
            label:'sensor'+ (j+1),
            data:phrArray[j],
            borderWidth: 1,
            fill:false,
            borderColor:phrcolors[j]
        };
        onechart.datasets.push(Data);       
    } 
    phrUpdateTile(onechart);    
};

function phrUpdateTile(onechart) {
    var ctx = document.getElementById('phr');
    var chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'line',

        // The data for our dataset
        data: onechart,
        // {
        //  labels: time,
        //  datasets: [{
        //      label: myLabel,
        //      data: vegSMArray,
        //      borderColor: myBorderColor,
        //      borderWidth: 1,
        //      fill: false,
        //      // backgroundColor: 'rgba(255, 99, 132,0)',
        //      // borderColor: 'rgb(255, 0, 0)',
                
        //  }]
        // },
        // Configuration options go here
        options: {
            legend:{
                display:true,
                labels:{
                    fontColor:'black'
                },
                fullWidth:false
            }
        }
    });
}

setInterval(phrUpdateThePage, 3000);
setInterval(phryAxisValues("http://thingtalk.ir/channels/743/feed.json?key=ZH7OQMKALAPRZXQJ&results=20"), 3000);