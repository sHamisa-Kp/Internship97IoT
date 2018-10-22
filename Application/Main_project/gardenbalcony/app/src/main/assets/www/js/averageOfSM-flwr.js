const smFlwrChannel = {
    'SM': [{'id': '710', 'apiKey': 'FYMFZRW8E2YLIKUK'},
    {'id': '711', 'apiKey': 'OXXNS5C338I0TCIR'},
    {'id': '712', 'apiKey': 'MZEZGGZL09ZB48L1'},
    {'id': '713', 'apiKey': '7R9CHM056LSY23ZO'},
    {'id': '714', 'apiKey': 'ZPX8TF59L251UFVE'},
    {'id': '715', 'apiKey': '3DMD11SS9G5B94I0'},
    {'id': '716', 'apiKey': 'QLH9NDZ20RQNK96S'},
    {'id': '717', 'apiKey': 'BVAC00J644INRNG7'},
    {'id': '718', 'apiKey': 'T42SU8NKLQ4KYF5E'},
    {'id': '719', 'apiKey': 'OTSO6GP0GO9XUAU3'}]
};

const smFlwrErrorValue = {
    'SM': {'min': 20, 'max': 30}
};

function smFlwrHttpGetAsync(theUrl, callback, i) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // callback(xmlHttp.responseText);
            let text = JSON.parse(xmlHttp.responseText).field1;
            let num = parseInt(text);
            flwrsoilmoistureArray[i] = num;
            if(num < smFlwrErrorValue.SM.min || num  > smFlwrErrorValue.SM.max) {
                flwrsoilmoistureError[i] = true;
            } else {
                flwrsoilmoistureError[i] = false;
            }
            if (i === (smFlwrChannel.SM.length) - 1) {
              smFlwrCalculateAverage(flwrsoilmoistureArray);  
            }
        }
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function smFlwrUpdateThePage() {
    for(let i = 0; i < smFlwrChannel.SM.length; i++) {
        smFlwrHttpGetAsync("http://thingtalk.ir/channels/" + smFlwrChannel.SM[i].id + 
            "/feeds/last.json?key=" + smFlwrChannel.SM[i].apiKey, function(){}, i);
    }
}

function smFlwrCalculateAverage(flwrsoilmoistureArray) {
    let sum = 0;
    flwrsoilmoistureArray.forEach(function(elem) {
        sum += elem;
    });
    let average = sum / flwrsoilmoistureArray.length;
    console.log(average);
    console.log(flwrsoilmoistureArray.length);
    console.log(sum);
    smFlwrUpdateSoilmoistureTile(average);
}

function smFlwrUpdateSoilmoistureTile(average) {
    document.querySelector("#flwrAveragevalue").textContent = "%" + String(average);

    var meterValue = document.querySelector("#myFlwrSmMeter");
    meterValue.value = average;

    var pic = document.querySelector("#SMofFlwrBackgroundImage");
    if(average < 20) {  //dry          
            pic.style.backgroundImage = 'url("img/dryFlwrSoil.jpg")';
            
        }else if(20 <= average && average <= 30) { //normal
            pic.style.backgroundImage = 'url("img/smOfFlwr3.jpg")';
        }
        else if(average > 30) { //wet
            pic.style.backgroundImage = 'url("img/wetFlwr2.jpg")';
        }

    soilmoistureFlwrErrorImage = document.getElementById('soilmoistureFlwrErrorImage');
    let thereIsAtLeastOneDanger = false;
    flwrsoilmoistureError.forEach(function(elem) {
        if(elem) {
            thereIsAtLeastOneDanger = true;
        }
    });
    if(thereIsAtLeastOneDanger) {
        soilmoistureFlwrErrorImage.style.visibility = 'visible';
    } else {
        soilmoistureFlwrErrorImage.style.visibility = 'hidden';
    }
}

let flwrsoilmoistureArray = [];
let flwrsoilmoistureError = [];
for(let i = 0; i < smFlwrChannel.SM.length; i++) {
    flwrsoilmoistureError.push(false);
}

setInterval(smFlwrUpdateThePage, updateInterval);