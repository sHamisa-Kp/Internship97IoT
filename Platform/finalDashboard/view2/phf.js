const phfChannel = {
    // pH (Vegetables: 0-7, Flowers: 8-17)
    'PH': [{'id': '732', 'apiKey': 'LG2G6VYVGRROA0O2'},
    {'id': '733', 'apiKey': 'BQYNJJL4G8A5RCHJ'},
    {'id': '734', 'apiKey': 'RZAEIWTMDVBOCNZS'},
    {'id': '735', 'apiKey': '4NWVLGMJHDE8WDAJ'},
    {'id': '736', 'apiKey': 'OW4B80VUEXJ38AG7'},
    {'id': '737', 'apiKey': 'I7MAFZ8E11BHLD4T'},
    {'id': '738', 'apiKey': '5WPKZ6PW9ZYCXC1T'},
    {'id': '739', 'apiKey': 'TD2B6CE14BODBAU4'},
    {'id': '740', 'apiKey': 'AGOE4BOV5662UOQF'},
    {'id': '741', 'apiKey': '29LLHTWCLAZONUOS'}]
};
const phfErrorValue = {
    'PH': {'min': 6, 'max': 7}
};

let phfArray = new Array;
phfArray = [[],[],[],[],[],[],[],[],[],[]];
let phftemp = new Array;
phftemp = [[],[],[],[],[],[],[],[],[],[]];
let phfcount = 0;
let phftime = new Array();
let phfcolors = ['#ba2898','#f285b2','#f78340','#efef07','#85adf2','#85f2ea','#9cf285','#f00585','#f2b685','#f29585','#bcbbba'];

function phfHttpGetAsync(theUrl, callback, j) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = new Array;
            text = JSON.parse(xmlHttp.responseText).feeds;

            if (phfcount < phfChannel.PH.length) {
                for(let i = 0; i <= 19; i++) {
                    phfArray[j].push(parseInt(text[i].field1));
                };
            } phfcount += 1;            

            if (phfcount >= phfChannel.PH.length) {
                let phfArray = new Array;
                phfArray = [[],[],[],[],[],[],[],[],[],[]];
                for(let i = 0; i <= 19; i++) {
                    phfArray[j].push(parseInt(text[i].field1));
                }
            } phfcount += 1;  

            for(let j = 0; j < phfChannel.PH.length; j++) {
             for(let i = 0; i <= 19; i++)
                 phfArray[j].forEach(function(elem) {
                     if (elem < phfErrorValue.PH.min || elem > phfErrorValue.PH.max) {                           
                         phfcolors[j] = 'red';
                     }
                 });
            }

            if (phfArray[j] != phftemp[j]) {
                setValueForPHfChart();
                phftemp[j] = phfArray[j];
            }
            console.log(phfArray);
        }           
    }      
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function phfUpdateThePage() {
    for(let j = 0; j < phfChannel.PH.length; j++) {
        phfHttpGetAsync("http://thingtalk.ir/channels/" + phfChannel.PH[j].id + 
            "/feeds.json?key=" + phfChannel.PH[j].apiKey + "&results=20", function(){}, j);
    }
}

function phfyAxisValues(theUrl) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var y = new Array();
            y = JSON.parse(xmlHttp.responseText).feeds;
            for(let i = 0; i <= 19; i++) {
                phftime[i] = (y[i].created_at.slice(11,19));
            };
            console.log(phftime);
        }
    }
}

function setValueForPHfChart() {
     var onechart = 
        {
            labels: phftime,
            datasets: []
        };

    for(let j = 0; j < phfChannel.PH.length; j++) { 
        var Data={
            label:'sensor'+ (j+1),
            data:phfArray[j],
            borderWidth: 1,
            fill:false,
            borderColor:phfcolors[j]
        };
        onechart.datasets.push(Data);       
    } 
    phfUpdateTile(onechart);    
};

function phfUpdateTile(onechart) {
    var ctx = document.getElementById('phf');
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

setInterval(phfUpdateThePage, 3000);
setInterval(phfyAxisValues("http://thingtalk.ir/channels/741/feed.json?key=29LLHTWCLAZONUOS&results=20"), 3000);