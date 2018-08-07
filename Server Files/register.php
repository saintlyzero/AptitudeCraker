<?php
$servername = "<Enter your details>";
$username = "<Enter your details>";
$password = "<Enter your details>";
$dbname = "<Enter your details>";

$conn = new mysqli($servername, $username, $password, $dbname);

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		;
		$name=$_POST['name'];
		$username=$_POST['username'];
		$password=$_POST['password'];
				
		
				
	$sql = "INSERT INTO user (name, username, password, maths_level, verbal_level, logical_level)
VALUES ('$name', '$username', '$password', '1', '1', '1')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}	

$sql1 = "INSERT INTO user_track (username, maths_qid, maths_level, verbal_qid, verbal_level, logical_qid, logical_level)
VALUES ('$username', 0, 1, 0, 1, 0, 1)";

if ($conn->query($sql1) === TRUE) {
    echo "User Registered";
} else {
    echo "Error: " . $sql1 . "<br>" . $conn->error;
}	
		
$sql1 = "INSERT INTO user_track (username, maths_qid, maths_level, verbal_qid, verbal_level, logical_qid, logical_level)
VALUES ('$username', 0, 2, 0, 2, 0, 2)";

if ($conn->query($sql1) === TRUE) {
    echo "User Registered";
} else {
    echo "Error: " . $sql1 . "<br>" . $conn->error;
}	

$sql1 = "INSERT INTO user_track (username, maths_qid, maths_level, verbal_qid, verbal_level, logical_qid, logical_level)
VALUES ('$username', 0, 3, 0, 3, 0, 3)";

if ($conn->query($sql1) === TRUE) {
    echo "User Registered";
} else {
    echo "Error: " . $sql1 . "<br>" . $conn->error;
}	

}
