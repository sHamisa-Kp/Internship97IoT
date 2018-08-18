const request = require('request');
tipBoardAPIKey = 'a500a57d8d1b4a5b9bb88c2d35108352';

/* Request to Tipboard */
function postRequestToTipboard(formData) {
	request.post('http://localhost:7272/api/v0.1/' + tipBoardAPIKey + '/push',
	{form: formData}, (err, res, body) => {
	    console.log(body);
        if (!err && res.statusCode === 200) {
            console.log(body);
        }
        else {
        	console.log("Tipboard connection: " + err);
        }
	});
}


/* Request to Thingtalk */
function getRequestToThingTalk(channel, key) {
	request('http://thingtalk.ir/channels/' + channel + '/feed.json?key=' + key,
	{ json: true }, (err, res, body) => {
            let data;
            let formData;
            let dataArray;
            if (!err && res.statusCode === 200) {
                dataArray = [];
                body.feeds.forEach(function (elem) {
                    dataArray.push([String(elem.entry_id), parseInt(elem.field1)])
                });
                data = {"subtitle": "Subtitle", "description": "Description", "series_list": [dataArray]};
                formData = {'tile': "line_chart", 'key': "IoTLineChart", 'data': JSON.stringify(data)};
                myDATA00 = formData;

                postRequestToTipboard(formData);
            }
            else {
                console.log("Thingtalk connection: " + err);
            }
	});
}

function doIt() {
	/* Thing talk */
	getRequestToThingTalk('629', 'G7KHR97UPN9OC5AC');
}
/* Text */
// formData = {'tile': "text", 'key': "IoTTextId", 'data': JSON.stringify({"text": "HEY THERE! :("})}
// postRequestToTipboard(formData)

/* Chart */
// data = {"subtitle": "Chart Subtitle", "description": "Chart Description", "series_list": [ [["1", -2], ["2", 11], ["3", 15], ["4", 30]] ]};
// formData = {'tile': "line_chart", 'key': "IoTLineChart", 'data': JSON.stringify(data)};
// postRequestToTipboard(formData);

setInterval(doIt, 2000);
// getRequestToThingTalk('629', 'G7KHR97UPN9OC5AC');