const phFlwrChannel = {
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

const phFlwrErrorValue = {
	'PH': {'min': 6, 'max': 7} 
}

function phFlwrHttpGetAsync(theUrl, callback, i) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let text = JSON.parse(xmlHttp.response).field1;
			let num = parseInt(text);
			phFlwrArray[i] = num;
			if (num < phFlwrErrorValue.PH.min || num > phFlwrErrorValue.PH.max) {
				phFlwrError[i] = true;
			} else {
				phFlwrError[i] = false;
			}
            if (i === (phFlwrChannel.PH.length) - 1) {
              phFlwrCalculateAverage(phFlwrArray);  
            }
		}
	};
	xmlHttp.open("GET", theUrl, true); //True for asynchronous
	xmlHttp.send(null);
}

function phFlwrUpdateThePage() {
	for (let i = 0; i < phFlwrChannel.PH.length; i++) {
		phFlwrHttpGetAsync("http://thingtalk.ir/channels/" + phFlwrChannel.PH[i].id + "/feeds/last.json?key=" + phFlwrChannel.PH[i].apiKey, function(){}, i);
	}
}

function phFlwrCalculateAverage(phFlwrArray) {
	let sum = 0;
	phFlwrArray.forEach(function(elem){
		sum += elem;
	});
	let average = sum / phFlwrArray.length;
	console.log(average);
	console.log(phFlwrArray.length);
	console.log(sum);

	updatePhFlwrTile(average);
}

function updatePhFlwrTile(average) {
	document.querySelector("#phFlwrAveragevalue").textContent = String(average);

	var meterVlaue = document.querySelector("#phFlwrMeter");
	meterVlaue.value = average;

	var PhFlwrErrorImage = document.querySelector("#PhFlwrErrorImage");
	let thereIsAtLeastOneDanger = false;

	phFlwrError.forEach(function(elem) {
		if(elem) {
			thereIsAtLeastOneDanger = true;
		} 
	});
	if (thereIsAtLeastOneDanger) {
		PhFlwrErrorImage.style.visibility = 'visible';
	} else {
		PhFlwrErrorImage.style.visibility = 'hidden';
	}
}

let phFlwrArray = [];
let phFlwrError = [];
for(let i = 8; i < phFlwrChannel.PH.length; i++) {
	phFlwrError.push(false);
}

{
  "type":"gauge",
  "scale-r":{
    "aperture":200,
    "values":"0:100:20",
    "center":{
      "size":12,
      "background-color":"#66CCFF #FFCCFF",
      "border-color":"none"
    },
    "ring":{
      "size":10,
      "rules":[
        {
          "rule":"%v >= 0 && %v <= 20",
          "background-color":"red"
        },
        {
          "rule":"%v >= 20 && %v <= 40",
          "background-color":"orange"
        },
        {
          "rule":"%v >= 40 && %v <= 60",
          "background-color":"yellow"
        },
        {
          "rule":"%v >= 60 && %v <= 80",
          "background-color":"green"
        },
        {
          "rule":"%v >= 80 && %v <=100",
          "background-color":"blue"
        }
      ]
    },
    "guide":{
      "background-color":"#66CCFF #FFCCFF",
      "alpha":0.2
    },
    "tick":{
      "line-color":"#66CCFF",
      "line-style":"solid",
      "line-width":3,
      "size":15,
      "placement":"inner"
    },
    "minor-ticks":4,
    "minor-tick":{
      "line-color":"#FFFFFF",
      "line-style":"solid",
      "line-width":2,
      "size":10,
      "placement":"inner"
    }
  },
  "plot":{
    "csize":"5%",
    "size":"80%",
    "background-color":"#000000",
    "value-box":{ //Value Boxes
      "placement":"center",  //Specify placement at "center", "tip", or "edge".
      "text":"%v",
      "font-color":"blue"
    }
  },
  "series":[
    {"values":[87]}
  ]
}

setInterval(phFlwrUpdateThePage, 3000);