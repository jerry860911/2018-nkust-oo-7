<!DOCTYPE HTML>
<html lang="zh-TW">
	<head>
		<title>醫院病床查詢系統</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />		
        <link href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.2/chosen.min.css" rel="stylesheet" />
		<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.2/chosen.jquery.min.js"></script>
	</head>
	<body>
		<!-- Header -->
			<header id="header">
				<div class="logo"><a href="index.php">hospital searching <span>by NKUST</span></a></div>
				<a href="#menu">Menu</a>
			</header>
		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="index.php">首頁</a></li>
					<li><a href="addmember.php">申請帳號</a></li>
					<li><a href="map.php">GOOGLE MAP</a></li>
					<li><a href="TGOS1.php">TGOS</a></li>
				</ul>
			</nav>
		<!-- Main -->
		    <Script Language="JavaScript"> 
						    function search() 
						    { 
						    document.form1.action="search.php"; 
						    document.form1.submit(); 
						    }
						    function showHideCode()
							{
                            $("#showdiv").toggle();
                            }      
			</Script>
			<div id="main">
				<section class="wrapper">
					<div class="inner">
						<header class="align-center">
							<h1>病床查詢系統</h1>
							<p>請選擇需查詢的醫院或病床資訊</p>
						</header>
						<form name="form1" method="GET" action="">
							<div class="row uniform">
							<script language="javascript">
　							var Today=new Date();
　							document.write(Today.getFullYear()+ " 年 " + (Today.getMonth()+1) + " 月 " + Today.getDate() + " 日");
							</script>
							<div class="12u$">
							<h4>依照醫院查詢：</h4>
								<div class="select-wrapper">
									<select class="example" name="hospital" id="hospital">
										<option value="">未指定(null)</option>
										<?php
										$arr_hospitalName = ['Edah', 'Vghks', 'Kumh', 'Cgmh', 'Jiannren'];
										$arr_hospitalChiName = ['義大醫院', '榮總醫院', '高雄醫學大學附設中和紀念醫院', '長庚醫院', '健仁醫院' ];
										for($i = 0 ; $i < count($arr_hospitalName); $i++){
											echo "<option value=".$arr_hospitalName[$i].">".$arr_hospitalChiName[$i]."(".$arr_hospitalName[$i].")</option>";
										}
										?>
										
									</select>
									<script>
	                                $( ".example" ).chosen();
                                    </script>
								</div>
							</div>
							<div class="12u$">
							<h4>依照病床種類查詢：</h4>
								<div class="select-wrapper">
								    <select class="example" name="Type" id="Type">
											<option value="">未指定(null)</option>
											<?php
											$serverName="163.18.42.32";
											$connectionInfo=array("Database"=>"test", "UID"=>"test", "PWD"=>"54321", "CharacterSet"=>"UTF-8");
											$conn=sqlsrv_connect($serverName, $connectionInfo);
											$sql = "SELECT * FROM BedTypeCode";
											$stmt = sqlsrv_query( $conn, $sql );
											if( $stmt === false) {
												die( print_r( sqlsrv_errors(), true) );
											}
											while( $row = sqlsrv_fetch_array( $stmt, SQLSRV_FETCH_ASSOC) ) {
													echo "<option value=".$row["Code"].">".$row["Bed"]."(".$row["Code"].")</option>";
											}
											?>
									</select>
									<script>
	                                $( ".example" ).chosen();
                                    </script>
								</div>
							</div>
							
							<ul class="actions">
								<li><input type="submit" value="查詢" onClick="search()"/></li>
								<li><input type="button" value="地圖搜尋(TGOS)" onclick="location.href='TGOS1.php'"/></li><p>
								<li><a href="downloadtype.php" class="button alt">下載種類資料表(.doc)</a></li>
			                </ul>
							</div>
						</form>
					</div>
				</section>
				<!-- Section -->
				<section class="wrapper">
					<div class="inner">
						<div class="table-wrapper">
							<table>
								<thead>
									<tr>
									    <th>醫院名稱</th>
										<th>病床類別</th>
										<th>總床數</th>
										<th>使用中</th>
										<th>空床中</th>
										<th>佔床率</th>

									</tr>
								</thead>
								<?php
								$serverName="163.18.42.32";
										$connectionInfo=array("Database"=>"test", "UID"=>"test", "PWD"=>"54321", "CharacterSet"=>"UTF-8");
								$conn=sqlsrv_connect($serverName, $connectionInfo);
								date_default_timezone_set("Asia/Taipei");
								$datetime = date ("Y-m-d",strtotime("-20day")) ;
								/*if ( date ("G")  == '0' or '1' or  '2'or  '3' or  '4' or  '5' or  '6' or  '7' or  '8' or  '9' or  '10'or  '11'or  '12'or  '13'or  '14' ){
									
	                            }else{
									$datetime = date ("Y-m-d");
								}*/

								if ($_GET['hospital'] === '') {
									$_GET['hospital'] = NULL;
								}else{
									$hospital = $_GET['hospital'];
								}

								if ($_GET['Type'] === '') {
									$_GET['Type'] = NULL;
								}else{
									$Type = $_GET['Type'];
								}
							if (@$hospital === NULL and @$Type === NULL){
								for($i = 0 ; $i < count($arr_hospitalName); $i++){
										$sql = "SELECT * FROM ".$arr_hospitalName[$i]." Where Datetimes = '$datetime'";
										$stmt = sqlsrv_query( $conn, $sql );
										if( $stmt === false) {
											die( print_r( sqlsrv_errors(), true) );
										}

										while( $row = sqlsrv_fetch_array( $stmt, SQLSRV_FETCH_ASSOC) ) {
												echo ("<tr>");
												echo ("<td>").$row["Hos"].("</td>");
												echo ("<td>").$row["Bed"].("</td>");
												echo ("<td>").$row["TotBed"].("</td>");
												echo ("<td>").$row["BedUs"].("</td>");
												echo ("<td>").$row["BedUnUs"].("</td>");
												echo ("<td>").$row["TotBedUsp"].("</td>");
												echo ("<tr />");
										}
									}
								
							}	
							else{
								if (@$hospital === NULL){
									for($i = 0 ; $i < count($arr_hospitalName); $i++){
										$sql = "SELECT * FROM ".$arr_hospitalName[$i]." Where Type ='$Type'and Datetimes = '$datetime'";
										$stmt = sqlsrv_query( $conn, $sql );
										if( $stmt === false) {
											die( print_r( sqlsrv_errors(), true) );
										}

										while( $row = sqlsrv_fetch_array( $stmt, SQLSRV_FETCH_ASSOC) ) {
												echo ("<tr>");
												echo ("<td>").$row["Hos"].("</td>");
												echo ("<td>").$row["Bed"].("</td>");
												echo ("<td>").$row["TotBed"].("</td>");
												echo ("<td>").$row["BedUs"].("</td>");
												echo ("<td>").$row["BedUnUs"].("</td>");
												echo ("<td>").$row["TotBedUsp"].("</td>");

												echo ("<tr />");
										}
									}
								}else{
									if(@$Type === NULL){
									$sql = "SELECT * FROM $hospital Where Datetimes = '$datetime'";
									$stmt = sqlsrv_query( $conn, $sql );
									if( $stmt === false) {
										die( print_r( sqlsrv_errors(), true) );
									}

									while( $row = sqlsrv_fetch_array( $stmt, SQLSRV_FETCH_ASSOC) ) {
											echo ("<tr>");
											echo ("<td>").$row["Hos"].("</td>");
											echo ("<td>").$row["Bed"].("</td>");
											echo ("<td>").$row["TotBed"].("</td>");
											echo ("<td>").$row["BedUs"].("</td>");
											echo ("<td>").$row["BedUnUs"].("</td>");
											echo ("<td>").$row["TotBedUsp"].("</td>");
											echo ("<tr />");
								}
									}else{
										$sql = "SELECT * FROM $hospital Where Type ='$Type'and Datetimes = '$datetime'";
									$stmt = sqlsrv_query( $conn, $sql );
									if( $stmt === false) {
									die( print_r( sqlsrv_errors(), true) );
								}

									while( $row = sqlsrv_fetch_array( $stmt, SQLSRV_FETCH_ASSOC) ) {
										echo ("<tr>");
											echo ("<td>").$row["Hos"].("</td>");
											echo ("<td>").$row["Bed"].("</td>");
											echo ("<td>").$row["TotBed"].("</td>");
											echo ("<td>").$row["BedUs"].("</td>");
											echo ("<td>").$row["BedUnUs"].("</td>");
											echo ("<td>").$row["TotBedUsp"].("</td>");
											echo ("<tr />");
								}
									}
								}
							}
								sqlsrv_free_stmt( $stmt);
								?>
							<form  action="./download.php" method="GET">
								<input type="hidden" name="hospital" value="<?=$_GET['hospital']?>">
								<input type="hidden" name="Type" value="<?=$_GET['Type']?>">
                            <ul class="actions">
								<li><input class="button alt" type="Submit" value="下載此病床資料表(.doc)"/></li>
			                </ul>
							</form>
							</table>
						</div>
					</div>
				</section>
				<!-- Section -->
				<section class="wrapper style1">
						<div class="inner">
							<header class="align-center">
								<h2>醫院官網</h2>
							</header>
							<div class="flex flex-3">
								<div class="col align-center">
									<div class="image round fit">
										<img src="images/Eda.jpg" alt="" />
									</div>
									<p>義大醫療財團法人 義大醫院 </p>
									<a href="https://www.edah.org.tw/" target="_blank" class="button">前往</a>
								</div>
								<div class="col align-center">
									<div class="image round fit">
										<img src="images/Kumh.jpg" alt="" />
									</div>
									<p>高雄醫學大學附設中和紀念醫院</p>
									<a href="http://www.kmuh.org.tw/KMUHWeb/Pages/Index.aspx" target="_blank" class="button">前往</a>
								</div>
								<div class="col align-center">
									<div class="image round fit">
										<img src="images/Vghks.jpg" alt="" />
									</div>
									<p>高雄榮民總醫院</p>
									<a href="https://www.vghks.gov.tw/" target="_blank" class="button">前往</a>
								</div>
							</div><Br/><Br/>
							<div class="flex flex-3" id = "showdiv" style="display:none;">
								<div class="col align-center">
									<div class="image round fit">
										<img src="images/Cgmh.jpg" alt="" />
									</div>
									<p>高雄長庚紀念醫院</p>
									<a href="https://www.cgmh.org.tw/" target="_blank" class="button">前往</a>
								</div>
								<div class="col align-center">
									<div class="image round fit">
										<img src="images/Jiannren.jpg" alt="" />
									</div>
									<p>高雄健仁醫院</p>
									<a href="http://www.jiannren.org.tw/jiannren/index.php" target="_blank" class="button">前往</a>
								</div>
								<div class="col align-center">
									<div class="image round fit">
										<img src="images/Yuanhosp.jpg" alt="" />
									</div>
									<p>阮綜合醫療社團法人阮綜合醫院</p>
									<a href="http://www.yuanhosp.com.tw/front/bin/home.phtml" target="_blank" class="button">前往</a>
								</div>
							</div><Br/><Br/>
							<input type="button" value="查看更多" class="button alt fit small" onClick="showHideCode()"/>
						</div>
					</section>
			</div>
		<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-snapchat"><span class="label">Snapchat</span></a></li>
					</ul>
				</div>
			</footer>
		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>
