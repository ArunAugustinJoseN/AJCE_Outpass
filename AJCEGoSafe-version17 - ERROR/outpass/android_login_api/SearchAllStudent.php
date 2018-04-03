<?php

require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
		$conn = $db->connect();

// json response array
$response = array("error" => FALSE);
 

 
    // receiving the post params
	$sql= "Select * from table_studentinfo";
	
	$result = mysqli_query($conn ,$sql);
	
	while ($row = mysqli_fetch_assoc($result)) {
		
		$array[] = $row;
		
	}
	
	echo json_encode($array);
 
    mysqli_free_result($result);
 
    mysqli_close($conn);
		
	
/* else {
    required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameter admission_no is missing!";
    echo json_encode($response);
} */
?>