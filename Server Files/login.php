<?php
$servername = "localhost";
$username = "id3069806_root";
$password = "password";
$dbname = "id3069806_aptitude";

$conn = new mysqli($servername, $username, $password, $dbname);

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		;
		$username=$_POST['username'];
		$password=$_POST['password'];

	$sql = "SELECT name FROM user WHERE username = '$username' and password = '$password'";
		$result = $conn->query($sql);

		if ($result->num_rows > 0) {

 	while($row = $result->fetch_assoc()) {
    echo $row["name"];
}
} else {
echo "-1";
}
						
}	
		

