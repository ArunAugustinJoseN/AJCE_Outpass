<?php

require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
		$conn = $db->connect();

// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['admno'])) {
 
    // receiving the post params
    $admno = $_POST['admno'];
	$sql= "Select * from table_outpass where admission_no = '$admno'";
	
	$result = mysqli_query($conn ,$sql);
	
	while ($row = mysqli_fetch_assoc($result)) {
		
		$array[] = $row;
		
	}
	
	echo json_encode($array);
 
    mysqli_free_result($result);
 
    mysqli_close($conn);
		
	
} 
/* else {
    required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameter admission_no is missing!";
    echo json_encode($response);
} */
?>