<html lang="zh-TW">
<head>
	<meta charset="utf-8" />
</head>
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
				$arr_hospitalName = ['Edah', 'Vghks', 'Kumh', 'Cgmh', 'Jiannren'];
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
												
			    header("Content-type: text/html; charset=utf8");
				header("Content-Type:application/msword");
				header("Content-Disposition:attachment;filename=".mb_convert_encoding("test","gbk","utf8").".doc");	
               			
				?>	
				
			</table>
		</div>
	</div>
					
</section>
</html>