<!--程式碼範例-->
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>TGOS MAP API for Web 點資料結構</title>
	<script type="text/javascript" src="http://api.tgos.nat.gov.tw/TGOS_API/tgos?ver=2&AppID=rFwh/KYG8bGqnE6pjd5y8cm3CCAmKjASpBgm2b9I++svTq3ozprKtw==&APIKey=cGEErDNy5yN/1fQ0vyTOZrghjE+jIU6urgO8RuOp1qWYQr82HCP0PiLYGtwfRcm0zVAJhfvLhOG8XUh0jZpM7IguR+QleeiO5BKfctb59BYJnq61vzH/kuDn41+orjMmuwGniraWnzu/wg7VwicUTPj/BS7B1DuOFgwPiF9MBQMVeBbUWCXR/fOHpPwCaE4/g5DEtArHsiuDfFnRkO1r8WY28d5PMU+2uLjLmffQMbvE1CCsTBtpbtMNQBZ38JKn/YaFpxIrRfQ15wBUrHIqk0z4YNOlkD99wHdOavTJRlU1IWXtEaPedVca6Z/A9JYmnBJhL121/QmeWC46TZPOJO4eApN8KwOSs3lBig+Rsv0SZnNx0Y2bzdT4uTzcW506qGj5mRN8fGXCOrSykFfyeIev9AkA6nlD" charset="utf-8"></script>
<!--下載後請將yourID及yourkey取代為您申請所取得的APPID及APIKEY方能正確顯示服務-->
    <script type="text/javascript">
        var pMap;
        var markerPoint; //Marker位置
        var markerTitle; //Marker標題
        var markerImg; //Marker圖片
		var pTGMarker = null; //Marker物件
		var line = null;
		var polygon = null;
        function InitWnd() {
            var pOMap = document.getElementById("TGMap");
            pMap = new TGOS.TGOnlineMap(pOMap, TGOS.TGCoordSys.EPSG3826); //宣告TGOnlineMap地圖物件並設定坐標系統
			pMap.setCenter(new TGOS.TGPoint(302430, 2770552));
			pMap.setZoom(7);
            //點
          	markerPoint = new TGOS.TGPoint(302430.958, 2770552.360);
            markerImg = new TGOS.TGImage("https://api.tgos.tw/TGOS_API/images/marker.png", new TGOS.TGSize(38, 33), new TGOS.TGPoint(0, 0), new TGOS.TGPoint(10, 33));
            pTGMarker = new TGOS.TGMarker(pMap, markerPoint, "內政部", markerImg);
          	
          	//線
          	var p1 = new TGOS.TGPoint(304874, 2770503);
          	var p2 = new TGOS.TGPoint(304207, 2770499);
            var p3 = new TGOS.TGPoint(303776, 2770576);			
            var p4 = new TGOS.TGPoint(303783, 2771647);
          	var path1 = [p1, p2, p3, p4];
            var s1 = new TGOS.TGLineString(path1);
            line = new TGOS.TGLine(pMap, s1, {
                strokeColor: '#00AA88',
                strokeWeight: 5
            });
          
          	//面
          	// 定義範圍資料
            var p1 = new TGOS.TGPoint(306276, 2770473);
            var p2 = new TGOS.TGPoint(306667, 2770462);
            var p3 = new TGOS.TGPoint(306665, 2770065);
			var p4 = new TGOS.TGPoint(306271, 2770084);
			var path1 = [p1, p2, p3, p4, p1];
			var path2 = new TGOS.TGLineString(path1);
            var boarder = new TGOS.TGLinearRing(path2);
			var district1 = new TGOS.TGPolygon([boarder]);
            //畫出一個 polygon
            polygon = new TGOS.TGFill(pMap, district1, {
                fillColor: '#00FFFF',
				fillOpacity: 0.5,
				strokeColor: '#00FF00',
				strokeWeight: 3,
				strokeOpacity: 0.5
            });
        }
		
		function locate(str)
		{
			if(str == "point")
			{
				if(pTGMarker != null)
				{
					pMap.setCenter(pTGMarker.getPosition());
					pMap.setZoom(10);
				}
			}
			else if(str == "line")
			{
				if(line != null)
				{
					pMap.fitBounds(line.getEnvelope());
					pMap.setZoom(10); //指定地圖層級
				}
			}
			else if(str =="polygon")
			{
				if(polygon != null)
				{
					pMap.fitBounds(polygon.getEnvelope());
					pMap.setZoom(10); //指定地圖層級
				}
			}
		}

    </script>
</head>
<body style="margin: 0px" onload="InitWnd();">
	<!--用來存放地圖的DIV物件-->
    <div id="TGMap" style="height: 580px; width: 890px;float:left; margin-right:5px;">
    </div>
    <div>
		<table border="1" style="border:1px solid #959595;border-collapse: collapse;">
			<tr>
				<td>id</td>
				<td>name</td>
			</tr>
			<tr>
				<td>1</td>
				<td><a href="#" onclick="locate('point');">內政部</a></td>
			</tr>
			<tr>
				<td>2</td>
				<td><a href="#" onclick="locate('line');">路線</a></td>
			</tr>
			<tr>
				<td>3</td>
				<td><a href="#" onclick="locate('polygon');">國父紀念館</a></td>
			</tr>
		</table>
	</div>
    
</body>
</html>                                                        