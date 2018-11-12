const psChannel = {
	// Pump Status (Vegetables, Flowers, TOP(Fogg))
	'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'},
	{'id': '744', 'apiKey': 'PD74MGJ9RFR4YMHK'},
	{'id': '750', 'apiKey': 'FGGX36CLKXBW2BN4'}]
};

let psArray = new Array;
psArray = [[],[],[]];
let pstemp = new Array;
pstemp = [[],[],[]];
let pscount = 0;
let pstime = new Array();
let pscolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function psHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (pscount < psChannel.PS.length) {
                for(let i = 0; i <= 19; i++) {
                    psArray[j].push(parseInt(text[i].field1));
                };
            } pscount += 1;            

            if (pscount >= psChannel.PS.length) {
                let psArray = new Array;
                psArray = [[],[],[]];
                for(let i = 0; i <= 19; i++) {
                    psArray[j].push(parseInt(text[i].field1));
                }
            } 
            // for(let j = 0; j <= 7; j++) {
            //  for(let i = 0; i <= 19; i++)
            //      psArray[j].forEach(function(elem) {
            //          if (elem < psErrorValue.PS.min || elem > psErrorValue.PS.max) {                           
            //              pscolors[j] = 'red';
            //          }
            //      });
            // }

            if (psArray[j] != pstemp[j]) {
                setValueForPsChart();
                pstemp[j] = psArray[j];
            }
            console.log(psArray);
        }           
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function psUpdateThePage() {
    for(let j = 0; j < psChannel.PS.length; j++) {
        psHttpGetAsync("http://thingtalk.ir/channels/" + psChannel.PS[j].id + 
            "/feeds.json?key=" + psChannel.PS[j].apiKey + "&results=20", function(){}, j);
    }
}

function psyAxisValues(theUrl) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var y = new Array();
            y = JSON.parse(xmlHttp.responseText).feeds;
            for(let i = 0; i <= 19; i++) {
                pstime[i] = (y[i].created_at.slice(11,19));
            };
            console.log(pstime);
        }
    }
}

function setValueForPsChart() {
     var onechart = 
        {
            labels: pstime,
            datasets: []
        };

    for(let j = 0; j < psChannel.PS.length; j++) { 
        var Data={
            label:'sensor'+ (j+1),
            data:psArray[j],
            borderWidth: 1,
            fill:false,
            borderColor:pscolors[j]
        };
        onechart.datasets.push(Data);       
    } 
    psUpdateTile(onechart);    
};

function psUpdateTile(onechart) {
    var ctx = document.getElementById('ps');
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

setInterval(psUpdateThePage, 3000);
setInterval(psyAxisValues("http://thingtalk.ir/channels/750/feed.json?key=FGGX36CLKXBW2BN4&results=20"), 3000);