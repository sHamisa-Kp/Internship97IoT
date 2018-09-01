
var myData;

function jsonTest() {
	fetch('http://thingtalk.ir/channels/629/feed.json?key=G7KHR97UPN9OC5AC').then(response => {
	  return response.json();
	}).then(data => {
	  // Work with JSON data here
	  myData = data;
	  console.log(data);
	}).catch(err => {
	  // Do something for an error here
	});
}