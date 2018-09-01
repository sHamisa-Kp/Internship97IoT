var request = new XMLHttpRequest();
request.open('GET', 'http://thingtalk.ir/channels/629/feed.json?key=G7KHR97UPN9OC5AC', true);

var data = null;

request.onload = function () {
  data = JSON.parse(this.response);
  if (request.status >= 200 && request.status < 400) {
    console.log("OK!");
  } else {
    const errorMessage = document.createElement('marquee');
    errorMessage.textContent = `Gah, it's not working!`;
    app.appendChild(errorMessage);
  }
}

request.send();

console.log(data);