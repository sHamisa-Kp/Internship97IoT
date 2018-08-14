const request = require('request');
tipBoardAPIKey = 'ffc3915e30f14b7694a23cadbc67ef71'

/* Request to Tipboard */
function postRequestToTipboard(formData) {
	request.post('http://localhost:7272/api/v0.1/' + tipBoardAPIKey + '/push', 
	{form: formData}, (err, res, body) => {
        if (!err && res.statusCode == 200) {
            console.log(body)
        }
        else {
        	console.log("BODY: " + body);
        	console.log("ERROR: " + err);
        }
	});
}


/* Request to Thingtalk */
function getRequestToThingTalk(channel, key) {
	request('http://thingtalk.ir/channels/' + channel + '/feed.json?key=' + key,
	{ json: true }, (err, res, body) => {
		if(!err && res.statusCode == 200) {
			var dataArray = new Array();
			body.feeds.forEach(function(elem) {
				dataArray.push([String(elem.entry_id), parseInt(elem.field1)])
			});
			data = {"subtitle": "Subtitle", "description": "Description", "series_list": [dataArray]};
			formData = {'tile': "line_chart", 'key': "IoTLineChart", 'data': JSON.stringify(data)};
			postRequestToTipboard(formData);
	    }
	    else {console.log("ERROR: \n" + err);}
	});	
}

function doIt() {
	/* Thing talk */
	getRequestToThingTalk('629', 'G7KHR97UPN9OC5AC');
}
/* Text */
// formData = {'tile': "text", 'key': "IoTTextId", 'data': JSON.stringify({"text": "SALAM SHAMISIA! :("})}
// postRequestToTipboard(formData)

/* Chart */
// data = {"subtitle": "Chart Subtitle", "description": "Chart Description", "series_list": [ [["1", -2], ["2", 11]] ]}
// formData = {'tile': "line_chart", 'key': "IoTLineChart", 'data': JSON.stringify(data)}



setInterval(doIt, 2000);