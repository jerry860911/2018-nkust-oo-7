<!--程式碼範例-->
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>TGOS MAP API for Web 初始化地圖設定值 </title>
<script type="text/javascript" src="http://api.tgos.tw/TGOS_API/tgos?ver=2&AppID=x+JLVSx85Lk=&APIKey=

in8W74q0ogpcfW/STwicK8D5QwCdddJf05/7nb+OtDh8R99YN3T0LurV4xato3TpL/fOfylvJ9Wv/khZEsXEWxsBmg+GEj4AuokiNXCh14

Rei21U5GtJpIkO++Mq3AguFK/ISDEWn4hMzqgrkxNe1Q==" charset="utf-8"></script>
	<!--下載後請將yourID及yourkey取代為您申請所取得的APPID及APIKEY方能正確顯示服務-->
    <script type="text/javascript">
        var pMap;
        var MapOptions;
        function InitWnd() {
            var pOMap = document.getElementById("TGMap");
            pMap = new TGOS.TGOnlineMap(pOMap, TGOS.TGCoordSys.EPSG3826); //宣告TGOnlineMap地圖物件並設定坐標系統
			pMap.setZoom(1)
        }
        function setOptions() {
            MapOptions = {
                backgroundColor: "#CD0000",  //backgroundColor(設定地圖背景顏色)
                disableDefaultUI: true,  //disableDefaultUI(是否關閉地圖物件的使用者介面)
                scrollwheel: true,  //scrollwheel(是否允許使用者對地圖物件滑鼠滾輪)
                mapTypeControl: false,  //mapTypeControl(是否開啟地圖類型控制項)
                mapTypeControlOptions: {  //mapTypeControlOptions(提供指定地圖類型)
                    //mapTypeId: TGOS.TGMapTypeId.ROADMAP,  //mapTypeId(設定預設顯示地圖類型ROADMAP/SATELLITE)
                    //style: TGOS.TGMapTypeControlStyle.DEFAULT
                    mapTypeIds: [TGOS.TGMapTypeId.ROADMAP],  //mapTypeId(設定預設顯示地圖類型ROADMAP/SATELLITE)
                    controlPosition: TGOS.TGControlPosition.LEFT_TOP,  //position(設定地圖類型控制項在地圖的位置)
                    mapTypeControlStyle: TGOS.TGMapTypeControlStyle.DEFAULT
                    //style(設定地圖類型控制項樣式,DEFAULT/HORIZONTAL_BAR)
                },
                navigationControl: true,  //navigationControl(是否開啟縮放控制列)
                navigationControlOptions: {  //navigationControlOptions(提供指定縮放控制列)
                    controlPosition: TGOS.TGControlPosition.RIGHT_TOP,  //position(設定縮放控制列在地圖的位置)
                    navigationControlStyle: TGOS.TGNavigationControlStyle.SMALL  //style(設定縮放控制列樣式,完整版/縮小版)
                },
                scaleControl: true,  //scaleControl(是否開啟比例尺控制項)
                scaleControlOptions: {  //scaleControlOptions(提供指定比例尺控制項)
                    scaleControlStyle: TGOS.TGControlPosition.LEFT_BOTTOM  //position(設定比例尺控制項在地圖的位置)
                }
                //position位置參數有以下八種
                //(TGControlPosition.LEFT_TOP,TGControlPosition.LEFT_CENTER,TGControlPosition.LEFT_BOTTOM)
                //(TGControlPosition.RIGHT_TOP,TGControlPosition.RIGHT_CENTER,TGControlPosition.RIGHT_BOTTOM)
                //(TGControlPosition.CENTER_TOP,TGControlPosition.CENTER_BOTTOM)
            };
            pMap.setOptions(MapOptions);
        }
    </script>
</head>
<body style="margin: 0px" onload="InitWnd();">
    <div id="TGMap" style="width: 1024px; height: 768px; border: 1px solid #CD0000;">
    </div>
    <br />
    <input type="button" onclick="setOptions()" value="改變地圖設定值" /><br />
</body>
</html>
                            