"use strict";
const request = require('request');

const tipBoardAPIKey = 'a500a57d8d1b4a5b9bb88c2d35108352';
const channel = {
	 'SM0': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'}],
	 'SM1': [{'id': '700', 'apiKey': 'BRAK9SFORHT6TH0H'}],
	 'SM2': [{'id': '701', 'apiKey': '7USY7M61EHNZFV12'}],
	 'SM3': [{'id': '702', 'apiKey': 'SHCWDHP9FKVSDVFQ'}],
	 'SM4': [{'id': '703', 'apiKey': 'SG07TTIXLNFU07E2'}],
	 'SM5': [{'id': '704', 'apiKey': 'FER392C3S936F0J5'}],
	 'SM6': [{'id': '705', 'apiKey': '1NQBG00SJAXGV6HR'}],
	 'SM7': [{'id': '706', 'apiKey': 'EZQC7OTC8P0D1IRR'}],
};

const updateInterval = 3000; // ms

function postRequestToTipboard(formData) {
	request.post('http://localhost:7272/api/v0.1/' + tipBoardAPIKey + '/push',
	{form: formData}, (err, res, body) => {
	    console.log(formData);
        if (!err && res.statusCode === 200) {
            console.log(body);
        }
        else {
        	console.log("Tipboard connection: " + err);
        }
	});
}

function getValueRequestToThingTalk(channel, key, key2, title, description) {
    request('http://thingtalk.ir/channels/' + channel + '/feeds/last.json?key=' + key,
    { json: true }, (err, res, body) => {
        if(!err && res.statusCode === 200) {
            let value = 0;
            let sum;
            let num;
            let avrge;
            value = parseInt(body.field1);
            console.log(value);
	 	 	sum += value;
            console.log(sum);
            num++;
     	 	avrge = sum / num;
     	 	console.log(avrge);
            let data = {"title": title, "description": description, "just-value": avrge};
            let formData = {'tile': "just_value", 'key': key2 , 'data': JSON.stringify(data)};
            //console.log(formData);
            postRequestToTipboard(formData);
        }
        else {console.log("ERROR: \n" + err);}
    });
}

function updateJustValues() {
    getValueRequestToThingTalk(channel.SM0[0].id, channel.SM0[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
    getValueRequestToThingTalk(channel.SM1[0].id, channel.SM1[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
    getValueRequestToThingTalk(channel.SM2[0].id, channel.SM2[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
    getValueRequestToThingTalk(channel.SM3[0].id, channel.SM3[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
    getValueRequestToThingTalk(channel.SM4[0].id, channel.SM4[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
    getValueRequestToThingTalk(channel.SM5[0].id, channel.SM5[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
    getValueRequestToThingTalk(channel.SM6[0].id, channel.SM6[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
    getValueRequestToThingTalk(channel.SM7[0].id, channel.SM7[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
}
setInterval(updateJustValues, updateInterval);