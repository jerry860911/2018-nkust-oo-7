
<?php
$serverName="163.18.42.32";
        $connectionInfo=array("Database"=>"Demo", "UID"=>"test", "PWD"=>"54321", "CharacterSet"=>"UTF-8");
        $conn=sqlsrv_connect($serverName, $connectionInfo);


$Email = $_POST['Email'];
$Phone = $_POST['Phone'];
$Birthday = $_POST['Birthday'];
$Gender = $_POST['Gender'];
$Id = $_POST['Id'];
$Password = $_POST['Password'];





$sql = "INSERT INTO Memberprofile (Id,Password,Email,Phone,Gender,Birthday)
values('$Id', '$Password', '$Email', '$Phone', '$Gender', '$Birthday')";

$insert = sqlsrv_query($conn,$sql);
if($insert){
	echo"會員註冊成功!!";
}else{
	echo"會員註冊失敗!!";
}


?>
