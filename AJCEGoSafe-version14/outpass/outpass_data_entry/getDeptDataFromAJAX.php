<?php
include_once "dbcon.php";
echo "daa";
if (!empty($_POST["stream_id"])) {
	$stream_id = $_POST["stream_id"];

	$query = "SELECT * FROM table_branch WHERE stream_id = $stream_id";
	$results = mysqli_query($con, $query);
	
    ?>
    <option >Select Branch</option>
	<?php

	foreach($results as $branch){
		?>
		<option value="<?php echo $branch ["branch_id"]; ?>"> <?php echo $branch["branch_name"]; ?></option>
		<?php
	}
}
else{
	echo "Invalid";
}
?>