const phvChannel = {
// pH (Vegetables: 0-7, Flowers: 8-17)
  'PH': [{'id': '724', 'apiKey': 'A7SC0GJKQAFJZWAO'},
  {'id': '725', 'apiKey': 'THRJGRI2XUH1YZIO'},
  {'id': '726', 'apiKey': 'E8QFCI9OYGTOZIMT'},
  {'id': '727', 'apiKey': 'ATGVGAYRFPS4QCVO'},
  {'id': '728', 'apiKey': 'Z4AXFJXQK1ZN33BQ'},
  {'id': '729', 'apiKey': '61SF7VT52KJZJCPW'},
  {'id': '730', 'apiKey': 'NF3K4HLNFZ0BE2XO'},
  {'id': '731', 'apiKey': 'VAK0BLGBNXUDZMQB'}]
};
const phvErrorValue = {
    'PH': {'min': 6, 'max': 7}
};

let phvArray = new Array;
phvArray = [[],[],[],[],[],[],[],[]];
let phvtemp = new Array;
phvtemp = [[],[],[],[],[],[],[],[]];
let phvcount = 0;
let phvtime = new Array();
let phvcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function phvHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (phvcount < phvChannel.PH.length) {
                for(let i = 0; i <= 19; i++) {
                    phvArray[j].push(parseInt(text[i].field1));
                };
            } phvcount += 1;            

            if (phvcount >= phvChannel.PH.length) {
                let phvArray = new Array;
                phvArray = [[],[],[],[],[],[],[],[]];
                for(let i = 0; i <= 19; i++) {
                    phvArray[j].push(parseInt(text[i].field1));
                }
            } 
            for(let j = 0; j < phvChannel.PH.length; j++) {
             for(let i = 0; i <= 19; i++)
                 phvArray[j].forEach(function(elem) {
                     if (elem < phvErrorValue.PH.min || elem > phvErrorValue.PH.max) {                           
                         phvcolors[j] = 'red';
                     }
                 });
            }

            if (phvArray[j] != phvtemp[j]) {
                setValueForPHvChart();
                phvtemp[j] = phvArray[j];
            }
            console.log(phvArray);
        }           
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function phvUpdateThePage() {
    for(let j = 0; j < phvChannel.PH.length; j++) {
        phvHttpGetAsync("http://thingtalk.ir/channels/" + phvChannel.PH[j].id + 
            "/feeds.json?key=" + phvChannel.PH[j].apiKey + "&results=20", function(){}, j);
    }
}

function phvyAxisValues(theUrl) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var y = new Array();
            y = JSON.parse(xmlHttp.responseText).feeds;
            for(let i = 0; i <= 19; i++) {
                phvtime[i] = (y[i].created_at.slice(11,19));
            };
            console.log(phvtime);
        }
    }
}

function setValueForPHvChart() {
     var onechart = 
        {
            labels: phvtime,
            datasets: []
        };

    for(let j = 0; j < phvChannel.PH.length; j++) { 
        var Data={
            label:'sensor'+ (j+1),
            data:phvArray[j],
            borderWidth: 1,
            fill:false,
            borderColor:phvcolors[j]
        };
        onechart.datasets.push(Data);       
    } 
    phvUpdateTile(onechart);
};

function phvUpdateTile(onechart) {
    var ctx = document.getElementById('phv');
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

setInterval(phvUpdateThePage, 3000);
setInterval(phvyAxisValues("http://thingtalk.ir/channels/731/feed.json?key=VAK0BLGBNXUDZMQB&results=20"), 3000);