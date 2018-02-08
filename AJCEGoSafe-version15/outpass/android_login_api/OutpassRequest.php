<?php
require_once 'DB_Functions.php';
$db = new DB_Functions();

 require_once 'DB_Connect.php';
        // connecting to database
        $db1 = new Db_Connect();
		$conn = $db1->connect();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['purpose']) && isset($_POST['date']) && isset($_POST['admno'])) {
 
    // receiving the post params
    $purpose = "hiii";
	$date = "08-02-2018";
	$admno = "9050";
	
	// get student information
	$outpass = $db->saveOutpassRequest($admno);
	
	if ($outpass != false) {
        // student is found
		
        $response1["error"] = FALSE; 
        echo json_encode($response1);
    } else {
        // student is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg1"] = "Your outpass request is successfull..:-";
		
		$sql = mysqli_query($conn, "INSERT INTO `table_outpass`(`admission_no`, `purpose`, `date_of_return`) VALUES ('$admno', '$purpose', '$date')");
        echo json_encode($response);
    }
	
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters are missing!";
    echo json_encode($response);
}
?>