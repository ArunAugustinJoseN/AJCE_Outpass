<?php
require_once 'DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['admno']) && isset($_POST['password'])) {
 
    // receiving the post params
    $admno = $_POST['admno'];
    $password = $_POST['password'];
 
    // get the user by email and password
    $user = $db->getUserByEmailAndPassword($admno, $password);
 
    if ($user != false) {
        // user is found
		
        $response["error"] = FALSE;
        $response["login_id"] = $user["login_id"];
        $response["user"]["admno"] = $user["admission_no"];
        $response["user"]["role_id"] = $user["role_id"];
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Admission No. or Password are wrong. Please try again!";
        echo json_encode($response);
    }
	
	
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters admission_no or password is missing!";
    echo json_encode($response);
}
?>