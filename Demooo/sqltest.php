<?php

$serverName = "163.18.42.32";
$connectionInfo = array( "Database"=>"Demo", "UID"=>"test", "PWD"=>"54321", "CharacterSet" => "UTF-8");
$conn = sqlsrv_connect( $serverName, $connectionInfo );

if( $conn ) {
     echo "Connection established.<br />";
}else{
     echo "Connection could not be established.<br />";
     die( print_r( sqlsrv_errors(), true));
}
// Close the connection.
sqlsrv_close($conn);
?>

