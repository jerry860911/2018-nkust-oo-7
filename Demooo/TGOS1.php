<!--程式碼範例-->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<title>高雄各大醫院(多標記點呈現)</title>
	<!--呼叫TGOS MAP API (lite)-->
	<script type="text/javascript" src="http://api.tgos.nat.gov.tw/TGOS_API/tgos?ver=2&AppID=rFwh/KYG8bGqnE6pjd5y8cm3CCAmKjASpBgm2b9I++svTq3ozprKtw==&APIKey=cGEErDNy5yN/1fQ0vyTOZrghjE+jIU6urgO8RuOp1qWYQr82HCP0PiLYGtwfRcm0zVAJhfvLhOG8XUh0jZpM7IguR+QleeiO5BKfctb59BYJnq61vzH/kuDn41+orjMmuwGniraWnzu/wg7VwicUTPj/BS7B1DuOFgwPiF9MBQMVeBbUWCXR/fOHpPwCaE4/g5DEtArHsiuDfFnRkO1r8WY28d5PMU+2uLjLmffQMbvE1CCsTBtpbtMNQBZ38JKn/YaFpxIrRfQ15wBUrHIqk0z4YNOlkD99wHdOavTJRlU1IWXtEaPedVca6Z/A9JYmnBJhL121/QmeWC46TZPOJO4eApN8KwOSs3lBig+Rsv0SZnNx0Y2bzdT4uTzcW506qGj5mRN8fGXCOrSykFfyeIev9AkA6nlD" charset="utf-8"></script>
<!--下載後請將yourID及yourkey取代為您申請所取得的APPID及APIKEY方能正確顯示服務-->
	<script type="text/javascript"> 
		var messageBox;		//訊息視窗物件	
		var pMap;			//初始化地圖物件
		//------------------------------須自行修改的參數,包含點位坐標,訊息視窗內容及圖示檔案來源設定------------------------------
        var infotext =  ['<B>高雄阮綜合醫院'
						, '<B>高雄健仁醫院'
						, '<B>高雄醫學大學附設中和紀念醫院'
						, '<B>高雄榮民總醫院'
						, '<B>高雄義大醫院'
						, '<B>高雄長庚紀念醫院'
						];	//依序填入地標名稱及訊息視窗內容, 可自行增減數量
		var markerPoint = [new TGOS.TGPoint(177768.882, 2501886.87)
						, new TGOS.TGPoint(181072.654, 2513835.178)
						, new TGOS.TGPoint(179100.175, 2505373.10)
						, new TGOS.TGPoint(180357.98, 2508753.116)
						, new TGOS.TGPoint(184735.44, 2518459.04)
						, new TGOS.TGPoint(183814.98, 2505653.7)
						];	//依序填入地標坐標位置, 坐標數須與標記數一致
		var imgUrl = ["https://api.tgos.tw/TGOS_API/images/marker2.png"
					, "https://api.tgos.tw/TGOS_API/images/marker2.png"
					, "https://api.tgos.tw/TGOS_API/images/marker2.png"
					, "https://api.tgos.tw/TGOS_API/images/marker2.png"
					, "https://api.tgos.tw/TGOS_API/images/marker2.png"
					, "https://api.tgos.tw/TGOS_API/images/marker2.png"
					];		//依序設定標記點圖示來源
		
		//------------------------------若網頁介面依照範例網頁的預設設定,以下程式碼可不修改---------------------------------------
		function InitWnd()
		{
			//------------------初始化地圖--------------------
			var pOMap = document.getElementById("OMap");
			var mapOptiions = {
				scaleControl: false,		//不顯示比例尺
				navigationControl: true,	//顯示地圖縮放控制項
				navigationControlOptions: {	//設定地圖縮放控制項
					controlPosition: TGOS.TGControlPosition.TOP_LEFT,	//控制項位置
					navigationControlStyle: TGOS.TGNavigationControlStyle.SMALL	//控制項樣式
				},
				mapTypeControl: false		//不顯示地圖類型控制項
			};
			pMap = new TGOS.TGOnlineMap(pOMap, TGOS.TGCoordSys.EPSG3826, mapOptiions);
            pMap.fitBounds(new TGOS.TGEnvelope(200100, 2504373, 170100, 2506373));			//建立地圖,選擇TWD97坐標
							//初始地圖縮放層級
			//pMap.setCenter(中心點X坐標, 中心點Y坐標);	//初始地圖中心點
			
            for(var i = 0; i < infotext.length; i++) {
                //------------------建立標記點---------------------
                var markerImg = new TGOS.TGImage(imgUrl[i], new TGOS.TGSize(38, 33), new TGOS.TGPoint(0, 0), new TGOS.TGPoint(10, 33));	//設定標記點圖片及尺寸大小
                var pTGMarker = new TGOS.TGMarker(pMap, markerPoint[i],'', markerImg, {flat:false});	//建立機關單位標記點
                //-----------------建立訊息視窗--------------------
                var InfoWindowOptions = {
                      maxWidth:4000,	//訊息視窗的最大寬度
                      pixelOffset: new TGOS.TGSize(5, -30)	//InfoWindow起始位置的偏移量, 使用TGSize設定, 向右X為正, 向上Y為負  
                };		
                
                messageBox= new TGOS.TGInfoWindow(infotext[i], markerPoint[i], InfoWindowOptions);	//建立訊息視窗							
				TGOS.TGEvent.addListener(pTGMarker, "mouseover", function (pTGMarker, messageBox) {
					return function () {                   
						messageBox.open(pMap, pTGMarker);
					}
                } (pTGMarker, messageBox));//滑鼠監聽事件--開啟訊息視窗
               
				TGOS.TGEvent.addListener(pTGMarker, "mouseout", function (messageBox) {
					return function () {
						messageBox.close();
					}
				} (messageBox));
			}     
		}
	</script>
</head>
<body style="margin:0px" onload="InitWnd();">
	<div id="OMap" style="position:absolute; top:5px; left:5px; width:1400px; height:800px; border:1px solid #000000;"></div>
</html>                            