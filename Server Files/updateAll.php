<?php
$servername = "<Enter your details>";
$username = "<Enter your details>";
$password = "<Enter your details>";
$dbname = "<Enter your details>";

$conn = new mysqli($servername, $username, $password, $dbname);

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		;
		
		$username=$_POST['username'];
		$timeStamp=$_POST['timestamp'];
		
		$newMathsLevel=$_POST['newMathsLevel'];
		$newVerbalLevel=$_POST['newVerbalLevel'];
		$newLogicalLevel=$_POST['newLogicalLevel'];
		
		$mathsPointer=$_POST['mathsPointer'];
		$verbalPointer=$_POST['verbalPointer'];
		$logicalPointer=$_POST['logicalPointer'];
		
		$currentMathsLevel=$_POST['currentMathsLevel'];
		$currentVerbalLevel=$_POST['currentVerbalLevel'];
		$currentLogicalLevel=$_POST['currentLogicalLevel'];
		
		$mathsCorrect=$_POST['mathsCorrect'];
		$verbalCorrect=$_POST['verbalCorrect'];
		$logicalCorrect=$_POST['logicalCorrect'];		
		
				
	$sql = "INSERT INTO user_performance (username, time_stamp, maths_correct, verbal_correct, logical_correct)
VALUES ('$username', '$timeStamp', '$mathsCorrect', '$verbalCorrect', '$logicalCorrect')";

if ($conn->query($sql) === TRUE) {
//    echo "New record created successfully in User Performance";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}	

$sql1 = "UPDATE user SET maths_level='$newMathsLevel', verbal_level='$newVerbalLevel',logical_level='$newLogicalLevel' WHERE username='$username'";

if ($conn->query($sql1) === TRUE) {
    //echo "Updated User Table";
} else {
    echo "Error: " . $sql1 . "<br>" . $conn->error;
}	

$sql2 = "UPDATE user_track SET maths_qid='$mathsPointer' WHERE username='$username' 
 AND maths_level='$currentMathsLevel'";

if ($conn->query($sql2) === TRUE) {
    //echo "New Record in user_track";
} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}

$sql3 = "UPDATE user_track SET verbal_qid='$verbalPointer' WHERE username='$username' 
 AND verbal_level='$currentVerbalLevel'";

if ($conn->query($sql3) === TRUE) {
   // echo "New Record in user_track";
} else {
    echo "Error: " . $sql3 . "<br>" . $conn->error;
}

$sql4 = "UPDATE user_track SET logical_qid='$logicalPointer' WHERE username='$username' 
 AND logical_level='$currentLogicalLevel'";

if ($conn->query($sql4) === TRUE) {
   // echo "New Record in user_track";
} else {
    echo "Error: " . $sql4 . "<br>" . $conn->error;
}

}
