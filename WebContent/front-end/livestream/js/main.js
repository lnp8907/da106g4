                var config = {
                    openSocket: function(config) {
// var SIGNALING_SERVER = 'https://socketio-over-nodejs2.herokuapp.com:443/';
// var SIGNALING_SERVER = 'https://54.148.206.111:9001/';
							var SIGNALING_SERVER = 'https://35.229.239.13:9001/';
                     config.channel = config.channel || location.href.replace(/\/|:|#|%|\.|\[|\]/g, '');
                        var sender = Math.round(Math.random() * 999999999) + 999999999;
// var sender = 123456789;
						console.log(config.channel);
                        io.connect(SIGNALING_SERVER).emit('new-channel', {
                            channel: config.channel,
                            sender: sender
                        });

                        var socket = io.connect(SIGNALING_SERVER + config.channel);
                        socket.channel = config.channel;
                        socket.on('connect', function () {
                            if (config.callback) config.callback(socket);
                        });

                        socket.send = function (message) {
                            socket.emit('message', {
                                sender: sender,
                                data: message
                            });
                        };

                        socket.on('message', config.onmessage);
                    },
                    onRemoteStream: function(htmlElement) {
                        videosContainer.appendChild(htmlElement);
                        rotateInCircle(htmlElement);
                    },
                    onRoomFound: function(room) {
                        document.getElementById("subTitle").innerHTML = "<strong>您正準備觀看 " + room.roomName + " 的直播</strong>";
                        var alreadyExist = document.querySelector('button[data-broadcaster="' + room.broadcaster + '"]');
                        if (alreadyExist) return;

                        if (typeof roomsList === 'undefined') roomsList = document.body;

                        var tr = document.createElement('tr');
                        tr.innerHTML = '<td><strong>' + room.roomName + '</strong> is broadcasting his media!</td>' +
                            '<td><button class="join">&nbsp;Join&nbsp;</button></td>';
                        roomsList.appendChild(tr);
 
                        var joinRoomButton = tr.querySelector('.join');
                        joinRoomButton.setAttribute('data-broadcaster', room.broadcaster);
                        joinRoomButton.setAttribute('data-roomToken', room.broadcaster);
                        joinRoomButton.onclick = function() {
                        	document.getElementById("CustomCardheader").className = "CustomCardheader text-white btn-success";
                        	document.getElementById("webSocket-submit").className = "g2";
                        	document.getElementById("subTitle").innerHTML = "<strong2>您正在觀看 " + room.roomName + " 的直播</strong2>";
                        	document.getElementById('broadcast-name').value = room.roomName;
                            videosContainer.className = "videosContainer";                   	
                        	document.getElementById("slider").src = "images/tenor.gif";
                        	document.getElementById("WebRTC-count").innerHTML = "";
                            this.disabled = true;
// this.style.display = 'none';
                            
                            var broadcaster = this.getAttribute('data-broadcaster');
                            var roomToken = this.getAttribute('data-roomToken');
                            broadcastUI.joinRoom({
                                roomToken: roomToken,
                                joinUser: broadcaster
                            });
                            hideUnnecessaryStuff2();
                        };
                    },
                    onNewParticipant: function(numberOfViewers) {
                        document.title = 'Viewers: ' + numberOfViewers;
                        document.getElementById("WebRTC-count").innerHTML  = "WebRTC 累計觀看人數"+" "+numberOfViewers;
                        document.getElementById("lsViewNum").value  = numberOfViewers;
                    },
                    onReady: function() {
                        console.log('now you can open or join rooms');
                    }
                };

                function setupNewBroadcastButtonClickHandler() {
                	document.getElementById("subTitle").innerHTML = "<strong>${hostID}: 您正在直播</strong>"
                    $('#record').show();
  	                $('#download').show();
                    DetectRTC.load(function() {
                        captureUserMedia(function() {
                            var shared = 'video';
                            if (window.option == 'Only Audio') {
                                shared = 'audio';
                            }
                            if (window.option == 'Screen') {
                                shared = 'screen';
                            }

                            broadcastUI.createRoom({
                                roomName: (document.getElementById('broadcast-name') || { }).value || 'Anonymous',
                                isAudio: shared === 'audio'
                            });
                        });
                        hideUnnecessaryStuff();
                    });
                }

                function captureUserMedia(callback) {
                    var constraints = null;
                    window.option = broadcastingOption ? broadcastingOption.value : '';
                    if (option === 'Only Audio') {
                        constraints = {
                            audio: true,
                            video: false
                        };

                        if(DetectRTC.hasMicrophone !== true) {
                            alert('DetectRTC library is unable to find microphone; maybe you denied microphone access once and it is still denied or maybe microphone device is not attached to your system or another app is using same microphone.');
                        }
                    }
                    if (option === 'Screen') {
                        var video_constraints = {
                            mandatory: {
                                chromeMediaSource: 'screen'
                            },
                            optional: []
                        };
                        constraints = {
                            audio: false,
                            video: video_constraints
                        };

                        if(DetectRTC.isScreenCapturingSupported !== true) {
                           alert('DetectRTC library is unable to find screen capturing support. You MUST run chrome with command line flag "chrome --enable-usermedia-screen-capturing"');
                        }
                    }

                    if (option != 'Only Audio' && option != 'Screen' && DetectRTC.hasWebcam !== true) {
                        alert('DetectRTC library is unable to find webcam; maybe you denied webcam access once and it is still denied or maybe webcam device is not attached to your system or another app is using same webcam.');
                    }

                    var htmlElement = document.createElement(option === 'Only Audio' ? 'audio' : 'video');

                    htmlElement.muted = true;
                    htmlElement.volume = 0;

                    try {
                        htmlElement.setAttributeNode(document.createAttribute('autoplay'));
                        htmlElement.setAttributeNode(document.createAttribute('playsinline'));
                        htmlElement.setAttributeNode(document.createAttribute('controls'));
                    } catch (e) {
                        htmlElement.setAttribute('autoplay', true);
                        htmlElement.setAttribute('playsinline', true);
                        htmlElement.setAttribute('controls', true);
                    }
var mediaElement = getHTMLMediaElement(htmlElement, {
buttons: ['record-video']
});
                    var mediaConfig = {
                        video: htmlElement,
                        onsuccess: function(stream) {
                            config.attachStream = stream;
                            
                            videosContainer.appendChild(mediaElement);
                            rotateInCircle(htmlElement);
                            
                            callback && callback();
                        },
                        onerror: function() {
                            if (option === 'Only Audio') alert('unable to get access to your microphone');
                            else if (option === 'Screen') {
                                if (location.protocol === 'http:') alert('Please test this WebRTC experiment on HTTPS.');
                                else alert('Screen capturing is either denied or not supported. Are you enabled flag: "Enable screen capture support in getUserMedia"?');
                            } else alert('unable to get access to your webcam');
                        }
                    };
                    if (constraints) mediaConfig.constraints = constraints;
                    getUserMedia(mediaConfig);
                }

                var broadcastUI = broadcast(config);

                /* UI specific */
                var videosContainer = document.getElementById('videos-container') || document.body;
                var setupNewBroadcast = document.getElementById('setup-new-broadcast');
                var roomsList = document.getElementById('rooms-list');

                var broadcastingOption = document.getElementById('broadcasting-option');

                if (setupNewBroadcast) setupNewBroadcast.onclick = setupNewBroadcastButtonClickHandler;

                function hideUnnecessaryStuff() {
                	connect();

                    var visibleElements = document.getElementsByClassName('visible'),
                        length = visibleElements.length;
                    for (var i = 0; i < length; i++) {
                        visibleElements[i].style.display = 'inline';
                    }
                }
                
                function hideUnnecessaryStuff2() {
                	connect();
                }

                function rotateInCircle(video) {
                    video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(0deg)';
                    setTimeout(function() {
                        video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(360deg)';
                    }, 1000);
  	                document.querySelector('button#record').disabled = false;
                	document.getElementById("session1").className = "experiment2";
                 }
                (function() {
                    var uniqueToken = document.getElementById('unique-token');
                    if (uniqueToken)
                        if (location.hash.length > 2) uniqueToken.parentNode.parentNode.parentNode.innerHTML = '<div class="share"><h2>&nbsp;<i class="fa fa-hand-o-right fa-2x"></i><a href="' + location.href + '" target="_blank"><b>由此分享此直播間的鏈接 </b></a></h2></div>';
                        else uniqueToken.innerHTML = uniqueToken.parentNode.parentNode.href = '#' + (Math.random() * new Date().getTime()).toString(36).toUpperCase().replace( /\./g , '-');
                })();
                 

// =============================================以下為錄製、下載、與上傳=============================================
// -->

        'use strict';
        const mediaSource = new MediaSource();
        mediaSource.addEventListener('sourceopen', handleSourceOpen, false);
        let mediaRecorder;
        let recordedBlobs;  // 錄製成功的Blob
        let sourceBuffer;

        const errorMsgElement = document.querySelector('span#errorMsg'); 
        const recordedVideo = document.querySelector('video#recorded');
        const recordButton = document.querySelector('button#record');
        recordButton.addEventListener('click', () => {
          if (recordButton.textContent === '開始錄影') {
adjustControls();
volumeControl.style.opacity = 1;
recordVideo.className = 'control record-video';
            startRecording();
          } else {
recordVideo.className = recordVideo.className.replace('record-video', 'stop-recording-video selected');
            stopRecording();
            recordButton.textContent = '開始錄影';
volumeControl.style.opacity = 1;
            playButton.disabled = false;
            // downloadButton.disabled = false;
          }
        });
 
        const playButton = document.querySelector('button#play');
        playButton.addEventListener('click', () => {
          const superBuffer = new Blob(recordedBlobs, {type: 'video/webm'});
          recordedVideo.src = null;
          recordedVideo.srcObject = null;
          recordedVideo.src = window.URL.createObjectURL(superBuffer);
          recordedVideo.controls = true;
          recordedVideo.play();
        });

        const downloadButton = document.querySelector('button#download');
        downloadButton.addEventListener('click', () => {
              document.querySelector('button#record').disabled = false;
              document.querySelector('button#download').disabled = true;
              const blob = new Blob(recordedBlobs, {type: 'video/webm'});	 
        	  var xhr = new XMLHttpRequest();
         	  xhr.open('POST', '<%=request.getContextPath()%>/Update_StreamServlet', true);
        	  xhr.onload = function(e) { console.log("loaded"); };
        	  xhr.onreadystatechange = function(){
        	      console.log("state: " + xhr.readyState);
        	  };
        	  // Listen to the upload progress.
        	  xhr.upload.onprogress = function(e) { console.log("uploading..."); };
        	  xhr.setRequestHeader("Content-Type", "video/webm");
        	  xhr.send(blob);
        	  swal(
            		  '你已儲存影片！',
            		  '可以去直播管理 listAllStream.jsp 確認',
            		  'success'
            	  )
volumeControl.style.opacity = 0;        
        });

        function handleSourceOpen(event) {
          console.log('MediaSource opened');
          sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"');
          console.log('Source buffer: ', sourceBuffer);
        }

        function handleDataAvailable(event) {
          if (event.data && event.data.size > 0) {
            recordedBlobs.push(event.data);
          }
        }

        function startRecording() {
          recordedBlobs = [];
          let options = {mimeType: 'video/webm;codecs=vp9'};
          if (!MediaRecorder.isTypeSupported(options.mimeType)) {
            console.error(`${options.mimeType} is not Supported`);
            errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
            options = {mimeType: 'video/webm;codecs=vp8'};
            if (!MediaRecorder.isTypeSupported(options.mimeType)) {
              console.error(`${options.mimeType} is not Supported`);
              errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
              options = {mimeType: 'video/webm'};
              if (!MediaRecorder.isTypeSupported(options.mimeType)) {
                console.error(`${options.mimeType} is not Supported`);
                errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
                options = {mimeType: ''};
              }
            }
          }

          try {
            mediaRecorder = new MediaRecorder(window.stream, options);
          } catch (e) {
            console.error('Exception while creating MediaRecorder:', e);
            errorMsgElement.innerHTML = `Exception while creating MediaRecorder: ${JSON.stringify(e)}`;
            return;
          }

          console.log('Created MediaRecorder', mediaRecorder, 'with options', options);
          recordButton.textContent = '結束錄影';
          playButton.disabled = true;
          downloadButton.disabled = true;
          mediaRecorder.onstop = (event) => {
            console.log('Recorder stopped: ', event);
          };
          mediaRecorder.ondataavailable = handleDataAvailable;
          mediaRecorder.start(10); // collect 10ms of data
          console.log('MediaRecorder started', mediaRecorder);
        }
        function stopRecording() {
          mediaRecorder.stop();
          console.log('Recorded Blobs: ', recordedBlobs);

        	 $.ajax({
        		 type: "POST",
        		 url: "<%=request.getContextPath()%>/InsertOrDelete_StreamServlet",
        		 data: creatQueryString($(this).val(), ""),
        		 dataType: "json",
        		 success: function (data){
        			aelrt("成功送資料庫囉");
        	     },
                 error: function(){
                	    swal(
                		  '您已完成錄影',
                		  '記得要按儲存影片',
                		  'success'
                		);
                	    downloadButton.disabled = false;
volumeControl.style.opacity = 1;
recordVideo.className = recordVideo.className.replace('stop-recording-video selected', 'stop-recording-video2 selected');
                 }
             })
             
             function creatQueryString(paramGrade, paramClass){
                    document.querySelector('button#record').disabled = true;       		 	
        		    var hostID=$("#hostID").val();
        		 	var lsViewNum=$("#lsViewNum").val();
        			var queryString= {"action":"insert", "hostID":hostID, "lsViewNum":lsViewNum};
        			return queryString;
        	 }

        }

        function handleSuccess(stream) {
          recordButton.disabled = false;
          downloadButton.disabled = true;
          console.log('getUserMedia() got stream:', stream);
          window.stream = stream;

        }

        async function init(constraints) {
            const stream = await navigator.mediaDevices.getUserMedia(constraints);
            handleSuccess(stream);
        }

        document.querySelector('#setup-new-broadcast').addEventListener('click', async () => {
          const hasEchoCancellation = document.querySelector('#echoCancellation').checked;
          const constraints = {
            audio: {
              echoCancellation: {exact: hasEchoCancellation}
            },
            video: {
              width: 1280, height: 720
            }
          };
          console.log('Using media constraints:', constraints);
          await init(constraints);
        });
// =============================================以下為webSocket聊天室=============================================
// -->

var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
// var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;
// http上線請使用https , webSocket請使用wss --%>
var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;
//    var endPointURL = "wss://da106g4.tk/RTCPeerConnection_Ver3/MyEchoServer/";
console.log(endPointURL);
	var webSocket;
	
	function connect() {
		var rtcroomName = document.getElementById('broadcast-name').value;
		alert(rtcroomName);
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL+"/"+rtcroomName);
		document.getElementById('broadcasting-option').style.display = 'none';
        document.getElementById('broadcast-name').style.display = 'none';
        document.getElementById('setup-new-broadcast').style.display = 'none';
		webSocket.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;
		};

		webSocket.onmessage = function(event) {
		  if (event.data.indexOf('count=') == 0) {
			document.getElementById("WebSocket-count").innerHTML  = "目前在線人數"+" "+event.data.substring(6);
		  } else {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        var showCount = jsonObj.showCount;
	        messagesArea.value = messagesArea.value + message;
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		  }
		};

		webSocket.onclose = function(event) {
			var hostID = document.getElementById("messagesArea");
		     var jsonObj = {"hostID" : userName, "message" : message};
		        webSocket.send(JSON.stringify(jsonObj));
		};
	}

	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"type":"sendText","userName" : document.getElementById("userName").value, "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}
    