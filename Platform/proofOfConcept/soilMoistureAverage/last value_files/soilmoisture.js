// const tipBoardAPIKey = 'a500a57d8d1b4a5b9bb88c2d35108352';
// const channel = {
// 	 'SM0': [{'id': '670', 'apiKey': 'LUJ9D21E177HESAW'}],
// 	 'SM1': [{'id': '700', 'apiKey': 'BRAK9SFORHT6TH0H'}],
// 	 'SM2': [{'id': '701', 'apiKey': '7USY7M61EHNZFV12'}],
// 	 'SM3': [{'id': '702', 'apiKey': 'SHCWDHP9FKVSDVFQ'}],
// 	 'SM4': [{'id': '703', 'apiKey': 'SG07TTIXLNFU07E2'}],
// 	 'SM5': [{'id': '704', 'apiKey': 'FER392C3S936F0J5'}],
// 	 'SM6': [{'id': '705', 'apiKey': '1NQBG00SJAXGV6HR'}],
// 	 'SM7': [{'id': '706', 'apiKey': 'EZQC7OTC8P0D1IRR'}],
// };
// var opts = {
//   angle: "0.14"; // The span of the gauge arc
//   lineWidth: "0.2"; // The line thickness
//   radiusScale: "1"; // Relative radius
// }
// var style = document.querySelector("meter");
// var pointer: {
//     length: "0.6"; // // Relative to gauge radius
//     strokeWidth: "0.035"; // The thickness
//     color: "#000000"; // Fill color
// } 


let sum = 0;
let num = 0;
let avrge = 0;
function getValueRequestToThingTalk(urlAddress) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", urlAddress);
    xmlHttp.send();
    xmlHttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            let soilValue = 0;
            console.log(JSON.parse(xmlHttp.responseText));
            soilValue = JSON.parse(xmlHttp.responseText).field1;
            console.log(soilValue);
            sum += parseInt(soilValue);
            console.log(sum);
            num++;
            avrge = sum / num;
            console.log(avrge);
            // let data = {"title": title, "description": description, "just-value": avrge};
            // let formData = {'tile': "just_value", 'key': key2 , 'data': JSON.stringify(data)};
            var averageValue = document.querySelector("#averagevalue");
            averageValue.textContent = JSON.stringify(avrge);
            averageValue.classList.add("font");
            var meterValue = document.querySelector("#myMeter");
            meterValue.value = avrge;
            if(avrge < 20){
                var pic = document.querySelector(".pic");
                pic.id = "drySoil";
                console.log("here i am");
            }else if (20 <= avrge <=30) {
                document.querySelector(".pic").id = "normalSoil";
            }else{
                document.querySelector(".pic").id = "wetSoil";
            }
            console.log("mina");
        }else{console.log("ERR: \n");}
    }
}

function doIt() {
    getValueRequestToThingTalk('http://thingtalk.ir/channels/670/feed/last.json?key=LUJ9D21E177HESAW');
    getValueRequestToThingTalk('http://thingtalk.ir/channels/700/feed/last.json?key=BRAK9SFORHT6TH0H');
    getValueRequestToThingTalk('http://thingtalk.ir/channels/701/feed/last.json?key=7USY7M61EHNZFV12');
    getValueRequestToThingTalk('http://thingtalk.ir/channels/702/feed/last.json?key=SHCWDHP9FKVSDVFQ');
    getValueRequestToThingTalk('http://thingtalk.ir/channels/703/feed/last.json?key=SG07TTIXLNFU07E2');
    getValueRequestToThingTalk('http://thingtalk.ir/channels/704/feed/last.json?key=FER392C3S936F0J5');
    getValueRequestToThingTalk('http://thingtalk.ir/channels/705/feed/last.json?key=1NQBG00SJAXGV6HR');
    getValueRequestToThingTalk('http://thingtalk.ir/channels/706/feed/last.json?key=EZQC7OTC8P0D1IRR');
 }
  setInterval(doIt, 3000);