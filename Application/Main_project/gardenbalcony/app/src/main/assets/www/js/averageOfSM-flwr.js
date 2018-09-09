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

function smFlwrCalculateAverage() {
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

    // if(average < 20) {
    //         var pic = document.querySelector(".pic");
    //         pic.id = "drySoil";
            
    //     }else if(20 <= average && average <= 30) {
    //         document.querySelector(".pic").id = "normalSoil";
    //     }
    //     else if(average > 30) {
    //         document.querySelector(".pic").id = "wetSoil";
    //     }

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
setInterval(smFlwrCalculateAverage, updateInterval);

// function doIt() {
//     num = 0;
//     sum = 0;
//     console.log(avrge);
//     var averageValue = document.querySelector("#flwrAveragevalue");
//     averageValue.textContent = JSON.stringify(avrge);
//     averageValue.classList.add("font");
//     var meterValue = document.querySelector("#myFlwrSmMeter");
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
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/770/feed/last.json?key=98H1B5W1M798CW9X');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/711/feed/last.json?key=NGPRWTEBE7J2D7OH');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/712/feed/last.json?key=Q7KWNM5RV2L2NO9X');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/713/feed/last.json?key=0KU9RHTC8SNQ1PIO');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/714/feed/last.json?key=1PF7IY1S44DB62X1');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/715/feed/last.json?key=WDYWY5XBON1X0I95');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/716/feed/last.json?key=2FCB7CL5N85JF5T3');
//     getValueRequestToThingTalk('http://thingtalk.ir/channels/717/feed/last.json?key=HMO5RPP5ZQMQZF8Z');
//  }