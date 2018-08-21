const request = require('request');
tipBoardAPIKey = 'd1f58e39f9bb4f3cb1511e3767acf1cb'

/* Request to Tipboard */
function postRequestToTipboard(formData) {
	request.post('http://localhost:1500/api/v0.1/' + tipBoardAPIKey + '/push', 
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
	request('http://thingtalk.ir/channels/' + channel + '/feed/last.json?key=' + key,
	{ json: true }, (err, res, body) => {
		if(!err && res.statusCode == 200) {
			// console.log(body.field1);
			var dataArray = body.field1
			// body.feeds.forEach(function(elem) {
			//	dataArray.push([String(elem.entry_id), parseInt(elem.field1)])
			// });
			 data = { "title" : "Air Humidity" , "subtitle": "in this moment", "big_value": dataArray + "%" };
			 // console.log(data);
			 formData = {'tile': "simple_percentage", 'key': "simple_percentage.html", 'data': JSON.stringify(data) };
			 console.log(formData);
			 postRequestToTipboard(formData);
	    }

	    else {console.log("ERROR: \n" + err);}
	});	
}

function doIt() {
	/* Thing talk */
	getRequestToThingTalk('678', 'K2FPY7NECL9XGVOO');
}
/* Text */
// formData = {'tile': "text", 'key': "IoTTextId", 'data': JSON.stringify({"text": "SALAM SHAMISIA! :("})}
// postRequestToTipboard(formData)

/* Chart */
// data = {"subtitle": "Chart Subtitle", "description": "Chart Description", "series_list": [ [["1", -2], ["2", 11]] ]}
// formData = {'tile': "line_chart", 'key': "IoTLineChart", 'data': JSON.stringify(data)}



setInterval(doIt, 2000);