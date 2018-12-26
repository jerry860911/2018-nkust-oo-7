<?php
$serverName="163.18.42.32";
        $connectionInfo=array("Database"=>"Demo", "UID"=>"test", "PWD"=>"54321", "CharacterSet"=>"UTF-8");
        $conn=sqlsrv_connect($serverName, $connectionInfo);

$Id = $_POST['Id'];
$Password = $_POST['Password'];

if ($_POST['Id'] === '' || $_POST['Password'] === ''){
  header('Location: AddMemberGUI.php');
}else{
  $Id = $_POST['Id'];
  $Password = $_POST['Password'];
  $sql ="SELECT * FROM Memberprofile Where Id = '$Id' and Password = '$Password'";
  $stmt = sqlsrv_query( $conn, $sql );
    if( $stmt === false) {
    die( print_r( sqlsrv_errors(), true) );
    }
	  $row = sqlsrv_fetch_array( $stmt, SQLSRV_FETCH_ASSOC);
    if($row != NULL){
      header('Location: searchGUI.php');
    }
    else{
      header('Location: nologin.php');
    }
}

?>
