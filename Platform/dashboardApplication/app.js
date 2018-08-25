"use strict";
const request = require('request');

const tipBoardAPIKey = 'a500a57d8d1b4a5b9bb88c2d35108352';
const channel = {
    'T': [{'id': '629', 'apiKey': 'G7KHR97UPN9OC5AC'}],
    'H': [{'id': '669', 'apiKey': '7TPW8OQOGN1EMURD'}],
    'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'}],
    'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'}],
    'PhR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'}]
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

function getChartRequestToThingTalk(channel, key, data, formData) {
	request('http://thingtalk.ir/channels/' + channel + '/feed.json?key=' + key,
	{ json: true }, (err, res, body) => {
            if (!err && res.statusCode === 200) {
                let dataArray = [];
                body.feeds.forEach(function(elem) {
                    dataArray.push([String(elem.entry_id), parseInt(elem.field1)])
                });

                data.series_list = [dataArray];
                formData.data = JSON.stringify(data);
                postRequestToTipboard(formData);
            }
            else {
                console.log("Thingtalk connection: " + err);
            }
	});
}

/*request for just value*/
function getValueRequestToThingTalk(channel, key, key2, title, description) {
    request('http://thingtalk.ir/channels/' + channel + '/feeds/last.json?key=' + key,
    { json: true }, (err, res, body) => {
        if(!err && res.statusCode === 200) {
            let value = 0;
            value = parseInt(body.field1);
            console.log(value);
            let data = {"title": title, "description": description, "just-value": value};
            let formData = {'tile': "just_value", 'key': key2 , 'data': JSON.stringify(data)};
            //console.log(formData);
            postRequestToTipboard(formData);
        }
        else {console.log("ERROR: \n" + err);}
    });
}   

function updateCharts() {
    let data = {"subtitle": "Soil Moisture", "description": "", "series_list": []};
    let formData = {'tile': "line_chart", 'key': "soilMoistureChart", 'data': ''};
    getChartRequestToThingTalk(channel.SM[0].id, channel.SM[0].apiKey, data, formData);

    data = {"subtitle": "Pump Status", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "pumpStatusChart", 'data': ''};
    getChartRequestToThingTalk(channel.PS[0].id, channel.PS[0].apiKey, data, formData);

    data = {"subtitle": "Humidity", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "humidityChart", 'data': ''};
    getChartRequestToThingTalk(channel.H[0].id, channel.H[0].apiKey, data, formData);

    data = {"subtitle": "Temperature", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "temperatureChart", 'data': ''};
    getChartRequestToThingTalk(channel.T[0].id, channel.T[0].apiKey, data, formData);

    data = {"subtitle": "Photo Resistor", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "photoResistorChart", 'data': ''};
    getChartRequestToThingTalk(channel.PhR[0].id, channel.PhR[0].apiKey, data, formData);
    
}

function updateJustValues() {
    getValueRequestToThingTalk(channel.T[0].id, channel.T[0].apiKey, 'temperature', 'Temperature', "");
    getValueRequestToThingTalk(channel.SM[0].id, channel.SM[0].apiKey, 'soilMoisture', 'SoilMoisture', "");
    getValueRequestToThingTalk(channel.PhR[0].id, channel.PhR[0].apiKey, 'photoResistor', 'PhotoResistor', "");
    getValueRequestToThingTalk(channel.H[0].id, channel.H[0].apiKey, 'humidity', 'Humidity', "");
    getValueRequestToThingTalk(channel.PS[0].id, channel.PS[0].apiKey, 'pumpStatus', 'PumpStatus', "");
}


setInterval(updateCharts, updateInterval);
setInterval(updateJustValues, updateInterval);