<?php
require_once 'DB_Functions.php';
$db = new DB_Functions();

 require_once 'DB_Connect.php';
        // connecting to database
        $db1 = new Db_Connect();
		$conn = $db1->connect();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['oid'])) {
 
    // receiving the post params
    $oid = $_POST['oid'];
	
	
	
        $response["error"] = FALSE;
        $response["error_msg1"] = "Your outpass request is successfull..:-";
		
		$sql = mysqli_query($conn, "UPDATE table_outpass SET status = 1 WHERE outpass_id = '$oid'");
        echo json_encode($response);
	
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters are missing!";
    echo json_encode($response);
}
?>