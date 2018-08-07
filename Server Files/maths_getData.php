<?php

$servername = "localhost";
$username = "id3069806_root";
$password = "password";
$dbname = "id3069806_aptitude";

$conn = new mysqli($servername, $username, $password, $dbname);

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		;
		$username=$_POST['username'];
				
		
	$return_arr = array();
	$sql = "(SELECT * FROM maths
WHERE level = (SELECT maths_level FROM user where username='$username')
AND qid > (SELECT maths_qid FROM user_track where username='$username' 
AND maths_level=(SELECT maths_level FROM user where username='$username'))
LIMIT 5)
UNION
(SELECT * FROM verbal
WHERE level = (SELECT verbal_level FROM user where username='$username')
AND qid > (SELECT verbal_qid FROM user_track where username='$username' 
AND verbal_level=(SELECT verbal_level FROM user where username='$username'))
LIMIT 5)
UNION
(SELECT * FROM logical
WHERE level = (SELECT logical_level FROM user where username='$username')
AND qid > (SELECT logical_qid FROM user_track where username='$username' 
AND logical_level=(SELECT logical_level FROM user where username='$username'))
LIMIT 5);";
	$result = mysqli_query($conn,$sql);
	
	$response = array(); 
	
	$response = array(); 
	
	while($row = mysqli_fetch_array($result)){
		$temp = array(); 
		$temp['qid']=$row['qid'];
		$temp['question']=$row['question'];
		$temp['option1']=$row['opt1'];
		$temp['option2']=$row['opt2'];
		$temp['option3']=$row['opt3'];
		$temp['option4']=$row['opt4'];
		$temp['answer']=$row['answer'];
		$temp['level']=$row['level'];
			
		array_push($response,$temp);
	}

echo json_encode($response );
		
		
	}else{
echo 'error';
}