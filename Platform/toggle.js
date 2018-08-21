console.log("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")

// /*jslint browser: true, devel: true*/
// /*global WebSocket: false, Tipboard: false*/
// // console.log("OK!");
// // const request = require('request');
// // tipBoardAPIKey = 'd1f58e39f9bb4f3cb1511e3767acf1cb'

// // /* Request to Tipboard */
// // function postRequestToTipboard(formData) {
// // 	request.post('http://localhost:7272/api/v0.1/' + tipBoardAPIKey + '/push', 
// // 	{form: formData}, (err, res, body) => {
// //         if (!err && res.statusCode == 200) {
// //             console.log(body)

// //             if('#toggle-trigger' === '1'){
// // 				     function toggleOn() {
// // 					    $('#toggle-trigger').bootstrapToggle('on')
// // 					  			}  
// // 				                                   } 
// // 		      else if('#toggle-trigger' === '0') {
// // 					 function toggleOff() {
// // 					    $('#toggle-trigger').bootstrapToggle('off')}
// //                     }
// //         }
// //         else {
// //         	console.log("BODY: " + body);
// //         	console.log("ERROR: " + err);
// //         }
// // 	});
// // }
// // tipBoardAPIKey = 'd1f58e39f9bb4f3cb1511e3767acf1cb'

// // /* Request to Tipboard */
// // //function postRequestToTipboard(formData){}
// // function post(path, params, method) {
// //   method = method || "post";
// //   var form = document.createElement("form");
// //   form.setAttribute("method", method);
// //   form.setAttribute("action", path);
// //   for(var key in params) {
// //     if(params.hasOwnProperty(key)) {
// //       var hiddenField = document.createElement("input");
// //       hiddenField.setAttribute("type", "hidden");
// //       hiddenField.setAttribute("name", key);
// //       hiddenField.setAttribute("value", params[key]);

// //       form.appendChild(hiddenField);
// //     }
// //   }
// // document.body.appendChild(form);
// //     form.submit();
// //     if(form.pumpStatus === '1'){
// //       function toggleOn() {
// // 	   $('#toggle-trigger').bootstrapToggle('on')
// //       }  
// // 	} 
// //     else if(form.pumpStatus === '0') {
// // 	  function toggleOff() {
// // 	   $('#toggle-trigger').bootstrapToggle('off')
// // 	  }
// //     }

// // }                    

//     /* Request to Thingtalk */

// function httpGet(urlAddress) {
//   var xmlHttp = new XMLHttpRequest();
//   xmlHttp.open("GET", 'http://thingtalk.ir/channels/683/feed/last.json?key=5LKBQQ9VAATCJFNB');
//   xmlHttp.send();
// 	xmlHttp.onreadystatechange = function() {
//   	if(this.readyState == 4 && this.status == 200) {
//       console.log(JSON.parse(xmlHttp.responseText));
//       var pumpStatus = JSON.parse(xmlHttp.responseText).field1;
//       console.log(JSON.parse(xmlHttp.responseText).field1);

//       if(pumpStatus === '1') {
//         console.log('This is 1');
//         $('#toggle-trig').bootstrapToggle('on');
         
//       } 
//       else if(pumpStatus === '0') {
//         console.log('This is 0');
//         $('#toggle-trig').bootstrapToggle('off');
       
//       }

//   	} else {
          
//           console.log("ERR: \n");
//     }
//   }
// }

// function doIt() {
// 	/* Thing talk */
// 	httpGet('http://thingtalk.ir/channels/683/feed/last.json?key=5LKBQQ9VAATCJFNB');
// }

// // setInterval(doIt, 2000);
//  doIt();
//    // var textSelector = 'span.text-container';
//    // containers = $(tile).find(textSelector);
//    // if (containers.length != 1) {
//    //     console.log('tile ' + tile + 'does not include ONE: ' + textSelector);
//     //}
//     //var nodeWithText = containers[0];
//     //$(nodeWithText).html(data['text']);

//     //var textSelector = '#' + id + ' .text-container';
//     //if (meta.font_size) {
//       //  $(textSelector).css("font-size", meta.font_size);
//    // }
//     //if (meta.font_color) {
//        // $(textSelector).css(
//             //"color", Tipboard.DisplayUtils.replaceFromPalette(meta.font_color)
//       //  );

//     //}
//     //if (meta.font_weight) {
//         //$('.text-container').css("font-weight", meta.font_weight);
//     //}
//     //Tipboard.TileDisplayDecorator.runAllDecorators(tile);
// //}
// //Tipboard.Dashboard.updateFunctions['toggle-trigger'] = updateTileToggle;

