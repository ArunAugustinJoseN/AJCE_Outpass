<?php
require_once 'DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['stream_id'])) {
 
    // receiving the post params
    $stream_id = $_POST['stream_id'];
	
	// get student information
	$stream = $db->getStreamInformation($stream_id);
	
	if ($stream != false) {
        // student is found
		
        $response1["error"] = FALSE;
        $response1["stream_id"] = $stream["stream_id"];
        $response1["stream"]["streams"] = $stream["stream_name"];
        
        echo json_encode($response1);
    } else {
        // student is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg1"] = "stream_id. do not pass to getStreamInformation";
        echo json_encode($response);
    }
	
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameter admission_no is missing!";
    echo json_encode($response);
}
?>