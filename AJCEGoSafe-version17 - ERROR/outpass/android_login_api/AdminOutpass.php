<?php

require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
		$conn = $db->connect();

// json response array
$response = array("error" => FALSE);

    // receiving the post params
	
	$sql= "Select * from table_outpass, table_studentinfo where status = 0 and table_outpass.admission_no = table_studentinfo.admission_no";
	
	$result = mysqli_query($conn ,$sql);
	
	while ($row = mysqli_fetch_assoc($result)) {
		
		$array[] = $row;
		
	}
	
	echo json_encode($array);
 
    mysqli_free_result($result);
 
    mysqli_close($conn);
		
	
?>