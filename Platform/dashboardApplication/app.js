const request = require('request');

const tipBoardAPIKey = 'a500a57d8d1b4a5b9bb88c2d35108352';
const channel = {
    'T': [{'id': '629', 'apiKey': 'G7KHR97UPN9OC5AC'}],
    'H': [{'id': '669', 'apiKey': '7TPW8OQOGN1EMURD'}],
    'SM': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'}],
    'PS': [{'id': '671', 'apiKey': 'XAKAVEUUJQ9GZGMT'}],
    'PhR': [{'id': '672', 'apiKey': 'B1JQYWFKX2PCRBYF'}]
};

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

setInterval(updateCharts, 3000);