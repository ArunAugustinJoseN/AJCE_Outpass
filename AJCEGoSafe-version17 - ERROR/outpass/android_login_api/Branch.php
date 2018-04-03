<?php
require_once 'DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['branch_id'])) {
 
    // receiving the post params
    $branch_id = $_POST['branch_id'];
	
	// get student information
	$branch = $db->getBranchInformation($branch_id);
	
	if ($branch != false) {
        // student is found
		
        $response1["error"] = FALSE;
        $response1["branch_id"] = $branch["branch_id"];
        $response1["branch"]["branches"] = $branch["branch_name"];
        
        echo json_encode($response1);
    } else {
        // student is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg1"] = "branch_id. do not pass to getBranchInformation";
        echo json_encode($response);
    }
	
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameter admission_no is missing!";
    echo json_encode($response);
}
?>