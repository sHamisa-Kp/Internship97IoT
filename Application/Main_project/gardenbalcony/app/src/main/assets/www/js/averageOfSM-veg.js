const smVegChannel = {
    'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'}, {'id': '700', 'apiKey': 'BRAK9SFORHT6TH0H'}, 
            {'id': '701', 'apiKey': '7USY7M61EHNZFV12'}, {'id': '702', 'apiKey': 'SHCWDHP9FKVSDVFQ'},
            {'id': '703', 'apiKey': 'SG07TTIXLNFU07E2'}, {'id': '704', 'apiKey': 'FER392C3S936F0J5'},
            {'id': '705', 'apiKey': '1NQBG00SJAXGV6HR'}, {'id': '706', 'apiKey': 'EZQC7OTC8P0D1IRR'} ],
};

const smVegErrorValue = {
    'SM': {'min': 20, 'max': 30}
};

//const updateInterval = 3000; // ms

//Respectively Get last value of each SM from thingtalk and put them into soilmoistureArray and then update array of soilmoistureError according to this value.
function smVegHttpGetAsync(theUrl, callback, i) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            soilmoistureArray[i] = num;
            if(num < smVegErrorValue.SM.min || num  > smVegErrorValue.SM.max) {
                soilmoistureError[i] = true;
            } else {
                soilmoistureError[i] = false;
            }
            if (i === (smVegChannel.SM.length) - 1) {
                smVegCalculateAverage(soilmoistureArray);
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

//this func make the url of each SM respectively, and send them respectively as argument to httpGetAsync func instead of doIt function which send inordered.
function smVegUpdateThePage() {
    for(let i = 0; i < smVegChannel.SM.length; i++) {
        smVegHttpGetAsync("http://thingtalk.ir/channels/" + smVegChannel.SM[i].id + 
            "/feeds/last.json?key=" + smVegChannel.SM[i].apiKey, function(){}, i);
    }
}

function smVegCalculateAverage(soilmoistureArray) {
    let sum = 0;
    soilmoistureArray.forEach(function(elem) {
        sum += elem;
    });
    let average = sum / soilmoistureArray.length;
    console.log(average);
    console.log(soilmoistureArray.length);
    console.log(sum);
    smVegUpdateSoilmoistureTile(average);
}

function smVegUpdateSoilmoistureTile(average) {
    document.querySelector("#vegAveragevalue").textContent = "%" + String(average);

    var meterValue = document.querySelector("#myVegSmMeter");
    meterValue.value = average;

    if(average < 20) {
            var pic = document.querySelector(".pic");
            pic.id = "drySoil";
            
        }else if(20 <= average && average <= 30) {
            document.querySelector(".pic").id = "normalSoil";
        }
        else if(average > 30) {
            document.querySelector(".pic").id = "wetSoil";
        }

    soilmoistureVegErrorImage = document.getElementById('soilmoistureVegErrorImage');
    let thereIsAtLeastOneDanger = false;
    soilmoistureError.forEach(function(elem) {
        if(elem) {
            thereIsAtLeastOneDanger = true;
        }
    });
    if(thereIsAtLeastOneDanger) {
        soilmoistureVegErrorImage.style.visibility = 'visible';
    } else {
        soilmoistureVegErrorImage.style.visibility = 'hidden';
    }
}

let soilmoistureArray = [];
let soilmoistureError = [];
for(let i = 0; i < smVegChannel.SM.length; i++) {
    soilmoistureError.push(false);
}

setInterval(smVegUpdateThePage, updateInterval);

// let sum = 0;
// let num = 0;
// let avrge = 0;
// function getValueRequestToThingTalk(urlAddress) {
//     console.log("getValueRequestToThingTalk Called.");
//     var xmlHttp = new XMLHttpRequest();
//     xmlHttp.open("GET", urlAddress);
//     xmlHttp.send();
//     xmlHttp.onreadystatechange = function() {
//         if(this.readyState == 4 && this.status == 200) {
//             // console.log(JSON.parse(xmlHttp.responseText));
//             let soilValue = JSON.parse(xmlHttp.responseText).field1;
//             sum += parseInt(soilValue);
//             num++;
//             avrge = sum / num;
            
//         }else{console.log("ERR: \n");}
//     }
// }

// function doIt() {
//     num = 0;
//     sum = 0;
//     console.log(avrge);
//     var averageValue = document.querySelector("#vegAveragevalue");
//     averageValue.textContent = JSON.stringify(avrge);
//     averageValue.classList.add("font");
//     var meterValue = document.querySelector("#myVegSmMeter");
//     meterValue.value = avrge;
//     if(avrge < 20) {
//         var pic = document.querySelector(".pic");
//         pic.id = "drySoil";
        
//     }else if(20 <= avrge && avrge <= 30) {
//         document.querySelector(".pic").id = "normalSoil";
//     }
//     else if(avrge > 30) {
//         document.querySelector(".pic").id = "wetSoil";
//     }
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/670/feed/last.json?key=LUJ9D21E177HESAW');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/700/feed/last.json?key=BRAK9SFORHT6TH0H');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/701/feed/last.json?key=7USY7M61EHNZFV12');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/702/feed/last.json?key=SHCWDHP9FKVSDVFQ');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/703/feed/last.json?key=SG07TTIXLNFU07E2');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/704/feed/last.json?key=FER392C3S936F0J5');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/705/feed/last.json?key=1NQBG00SJAXGV6HR');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/706/feed/last.json?key=EZQC7OTC8P0D1IRR');
//  }