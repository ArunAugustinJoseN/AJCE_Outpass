<?php
require_once 'DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['admno'])) {
 
    // receiving the post params
    $admno = $_POST['admno'];
	
	// get student information
	$student = $db->getStudentInformation($admno);
	
	if ($student != false) {
        // student is found
		
        $response1["error"] = FALSE;
        $response1["student_id"] = $student["student_id"];
        $response1["student"]["fname"] = $student["fname"];
        $response1["student"]["lname"] = $student["lname"];
		$response1["student"]["address"] = $student["address"];
        $response1["student"]["dob"] = $student["dob"];
		$response1["student"]["gender"] = $student["gender"];
        $response1["student"]["mobno"] = $student["mobile_no"];
		$response1["student"]["pmobno"] = $student["parent_mobile"];
        $response1["student"]["email"] = $student["email"];
		$response1["student"]["batch"] = $student["batch"];
		$response1["student"]["stream_id"] = $student["stream_id"];
        $response1["student"]["branch_id"] = $student["branch_id"];
        echo json_encode($response1);
    } else {
        // student is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg1"] = "Admission No. do not pass to getStudentInformation";
        echo json_encode($response);
    }
	
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameter admission_no is missing!";
    echo json_encode($response);
}
?>