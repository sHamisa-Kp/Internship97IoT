"use strict";
const request = require('request');

const tipBoardAPIKey = 'a500a57d8d1b4a5b9bb88c2d35108352';
const channel = {

    'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'},{'id': '700', 'apiKey': 'Q2JH4OBED4QQAA74'},
    {'id': '701', 'apiKey': 'P6LY6LV7CPYYSJUP'},{'id': '702', 'apiKey': 'PVWFQI4XLXLWL9DL'},{'id': '703', 'apiKey': '6W8DYBXG0HDA141O'},
    {'id': '704', 'apiKey': 'HZ9VM1PH1Q6LQSLZ'},{'id': '705', 'apiKey': 'RUB06UUHPX0K4DDS'},{'id': '706', 'apiKey': 'WQFB2JIGRVXDIAR4'}]


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
    const maxNum = 20;
	request('http://thingtalk.ir/channels/' + channel + '/feed.json?key=' + key + '&results=' + maxNum,
	{ json: true }, (err, res, body) => {
            if (!err && res.statusCode === 200) {
                let dataArray = [];
                // const length = Math.min(maxNum, body.feeds.length);
                // let tempArray = body.feeds.slice(body.feeds.length - length, body.feeds.length);
                body.feeds.forEach(function(elem) {
                    dataArray.push([String(elem.entry_id), parseInt(elem.field1)]);
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
    let data = {"subtitle": "Soil Moisture 0", "description": "", "series_list": []};
    let formData = {'tile': "line_chart", 'key': "SoilMoistureChart0", 'data': ''};
    getChartRequestToThingTalk(channel.SM[0].id, channel.SM[0].apiKey, data, formData);

    data = {"subtitle": "Soil Moisture 1", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "SoilMoistureChart1", 'data': ''};
    getChartRequestToThingTalk(channel.SM[1].id, channel.SM[1].apiKey, data, formData);

    data = {"subtitle": "Soil Moisture 2", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "SoilMoistureChart2", 'data': ''};
    getChartRequestToThingTalk(channel.SM[2].id, channel.SM[2].apiKey, data, formData);

    data = {"subtitle": "Soil Moisture 3", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "SoilMoistureChart3", 'data': ''};
    getChartRequestToThingTalk(channel.SM[3].id, channel.SM[3].apiKey, data, formData);

    data = {"subtitle": "Soil Moisture 4", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "SoilMoistureChart4", 'data': ''};
    getChartRequestToThingTalk(channel.SM[4].id, channel.SM[4].apiKey, data, formData);

    data = {"subtitle": "Soil Moisture 5", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "SoilMoistureChart5", 'data': ''};
    getChartRequestToThingTalk(channel.SM[5].id, channel.SM[5].apiKey, data, formData);
    
    data = {"subtitle": "Soil Moisture 6", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "SoilMoistureChart6", 'data': ''};
    getChartRequestToThingTalk(channel.SM[6].id, channel.SM[6].apiKey, data, formData);
    
    data = {"subtitle": "Soil Moisture 7", "description": "", "series_list": []};
    formData = {'tile': "line_chart", 'key': "SoilMoistureChart7", 'data': ''};
    getChartRequestToThingTalk(channel.SM[7].id, channel.SM[7].apiKey, data, formData);
    
}

function updateJustValues() {
     getValueRequestToThingTalk(channel.SM[0].id, channel.SM[0].apiKey, 'SoilMoistureChart0', 'Soil Moisture Chart 0', "");
     getValueRequestToThingTalk(channel.SM[1].id, channel.SM[1].apiKey, 'SoilMoistureChart1', 'Soil Moisture Chart 1', "");
     getValueRequestToThingTalk(channel.SM[2].id, channel.SM[2].apiKey, 'SoilMoistureChart2', 'Soil Moisture Chart 2', "");
     getValueRequestToThingTalk(channel.SM[3].id, channel.SM[3].apiKey, 'SoilMoistureChart3', 'Soil Moisture Chart 3', "");
     getValueRequestToThingTalk(channel.SM[4].id, channel.SM[4].apiKey, 'SoilMoistureChart4', 'Soil Moisture Chart 4', "");
     getValueRequestToThingTalk(channel.SM[5].id, channel.SM[5].apiKey, 'SoilMoistureChart5', 'Soil Moisture Chart 5', "");
     getValueRequestToThingTalk(channel.SM[6].id, channel.SM[6].apiKey, 'SoilMoistureChart6', 'Soil Moisture Chart 6', "");
     getValueRequestToThingTalk(channel.SM[7].id, channel.SM[7].apiKey, 'SoilMoistureChart7', 'Soil Moisture Chart 7', "");
}


setInterval(updateCharts, updateInterval);
setInterval(updateJustValues, updateInterval);
