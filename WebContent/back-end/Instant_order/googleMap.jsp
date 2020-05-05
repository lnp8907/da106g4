<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>配送狀態</title>
    

</head>

<body>${ido_no}
       <div id="map"></div>
    <script>
    $(document).ready(function() {
    	initMap();
    	});
   		var marker = null;
   		var myLatlng = null;
    	var MyMap = null;
        var image = '../images/racer.png';
        var title = '外送員位置';
        function initMap() {
        	
        	myLatlng = { lat: 24.968498 , lng: 121.193672};

            MyMap = new google.maps.Map(document.getElementById('map'), {
                zoom: 18,
                center: myLatlng
            });
            marker = new google.maps.Marker({
                position: myLatlng,
                map: MyMap,
                title: title,
                icon: image
            });  
        	connect();
        }
        
        var webSocket =null;
    	function connect() {
    		// create a websocket
//     	var userName = document.getElementById("userName").value;
//     	var MyPoint = "/TogetherWS/" + userName;
//     	var host = window.location.host;
//     	var path = window.location.pathname;
//     	var webCtx = path.substring(0, path.indexOf('/', 1));
//     	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		var ido_no = '${ido_no}';
		console.log(ido_no);
    	var endPointURL = "ws://35.229.239.13:8081/DA106_G4_Foodporn_Git/DeliveryWS/" + ido_no;
    	
    		webSocket = new WebSocket(endPointURL);
    		webSocket.onopen = function(event) { 
    			
    		};
 
    		webSocket.onmessage = function(event) {
    			jsonObj = JSON.parse(event.data);
    			myLatlng = { lat: jsonObj.lat, lng: jsonObj.lng };

                MyMap = new google.maps.Map(document.getElementById('map'), {
                    zoom: 18,
                    center: myLatlng
                });
                marker = new google.maps.Marker({
                    position: myLatlng,
                    map: MyMap,
                    title: title,
                    icon: image
                });              
    		};

    	}

    	function disconnect() {
    		webSocket.close();
    		document.getElementById('sendMessage').disabled = true;
    		document.getElementById('connect').disabled = false;
    		document.getElementById('disconnect').disabled = true;
    	}
       
    </script>
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBGmwg1inJ4obsNT2Z04frK71MfuTRl2MU&callback=initMap">
       </script>
           <style>
        /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
     #map {
     height:400px;
        width: 400px;
       }

        /* Optional: Makes the sample page fill the window. */
        html,
        body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</body>

</html> 