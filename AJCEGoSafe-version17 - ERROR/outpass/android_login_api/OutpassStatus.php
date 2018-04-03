<?php
require_once 'DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['admno'])) {
 
    // receiving the post params
    $admno = $_POST['admno'];
	
	// get student information
	$outpass_status = $db->getOutpassStatus($admno);
	
	if ($outpass_status != false) {
        // student is found
		
        $response1["error"] = FALSE;
        $response1["outpass_id"] = $outpass_status["outpass_id"];
        $response1["outpass_status"]["status"] = $outpass_status["status"];
        
        echo json_encode($response1);
    } else {
        // student is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg1"] = "admission_no. do not pass to getOutpassStatus";
        echo json_encode($response);
    }
	
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameter admission_no is missing!";
    echo json_encode($response);
}
?>