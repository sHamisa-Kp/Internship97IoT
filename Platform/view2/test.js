let last=new Array();
function httpGetAsyncLast(theUrl){
	let xmlHttp=new XMLHttpRequest();
	xmlHttp.onreadystatechange =function(){
		if (xmlHttp.readyState===4 && xmlHttp.status===200){
			let text=JSON.parse(xmlHttp.responseText).field1;
			last.push(parseInt(text));
			
		};
	};
	xmlHttp.open("GET",theUrl,true);
	xmlHttp.send(null);
};
for (var i=0;i<18;i++){
	httpGetAsyncLast("http://thingtalk.ir/channels/724/feeds/last.json?key=A7SC0GJKQAFJZWAO");	
};
console.log(last);

