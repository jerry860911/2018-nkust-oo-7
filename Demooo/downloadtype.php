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
					    <th>病床名稱</th>
						<th>病床類別</th>						
					</tr>
				</thead>
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
					echo ("<tr>");
							echo ("<td>").$row["Bed"].("</td>");
							echo ("<td>").$row["Code"].("</td>");
					echo ("<tr />");
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