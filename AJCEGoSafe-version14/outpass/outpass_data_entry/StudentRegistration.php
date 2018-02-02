    <?php
include 'dbcon.php';

if(isset($_POST['submit']))
{
$admno=$_POST["admno"];
$fname=$_POST["fname"];
$lname=$_POST["lname"];
$address=$_POST["addr"];
$dob=$_POST["dob"];
$mob=$_POST["mob"];
$pmob=$_POST["parentmob"];
$email=$_POST["email"];
$gender=$_POST["gen"];
$stream=$_POST["selectStream"];
$dept=$_POST["selectdept"];
$batch=$_POST["selectbatch"];
$sphoto="photo/".time()."".htmlspecialchars($_FILES['photo']['name']);
               move_uploaded_file($_FILES['photo']['tmp_name'], $sphoto);

 
$sql1="INSERT INTO `table_login`(`admission_no`,`role_id`) VALUES ('$admno','3')";
$result1=mysqli_query($con,$sql1);

if($stream == 1){
	$a = (int)$batch+4;
	$b = $batch."-".$a;
}
if($stream == 2){
	$a = (int)$batch+2;
	$b = $batch."-".$a;
}
if($stream == 3 & $dept == 7){
	$a = (int)$batch+5;
	$b = $batch."-".$a;
}
if($stream == 3 & $dept == 8){
	$a = (int)$batch+3;
	$b = $batch."-".$a;
}
if($stream == 3 & $dept == 9){
	$a = (int)$batch+2;
	$b = $batch."-".$a;
}
$sql2="SELECT `login_id` FROM `table_login` order by login_id desc limit 1";
$result2=mysqli_query($con,$sql2);
$row=mysqli_fetch_array($result2);
$log_id=$row["login_id"];

  $sql2="INSERT INTO `table_studentinfo`( `login_id`, `stream_id`, `branch_id`, `admission_no`, `fname`, `lname`, `address`, `dob`, `gender`, `mobile_no`, `parent_mobile`, `email`, `batch`, `photo`) VALUES ('$log_id', '$stream', '$dept', '$admno', '$fname', '$lname', '$address', '$dob', '$gender', '$mob', '$pmob', '$email', '$b', '$sphoto')";
  $res = mysqli_query($con, $sql2);
}
?>

<html>
<body>
<form  method="POST" action="#" id="myform" name="myform" enctype="multipart/form-data">
 <table  cellspacing="20" id="register">
 <div style="border:2px solid;">
 	<tr>
    	<td><div  style="border:2px solid;width:600px;height:300px;"><center>
        	<table><br/>
            	<tr>
                	<td><div class="reg">Admission No.</div></td>
        			<td><div class="reg"><input type="text" name="admno" id="regno" placeholder="Admission No."></div>
                    </td>
                   
                 </tr>
                 <tr>
                 	 <td><div class="reg">First Name</div></td>
                     <td><div class="reg"><input type="text" name="fname" id="fname" placeholder="First Name"></div></td>
                  </tr>
                  
                  <tr>
                 	 <td><div class="reg">Last Name</div></td>
                     <td><div class="reg"><input type="text" name="lname" id="lname" placeholder="Last Name"></div></td>
                  </tr>
                  <tr>
                 	 <td><div class="reg">Date of Borth</div></td>
                     <td><div class="reg"><input type="date" name="dob" id="dob" placeholder="dd/mm/yyyy"></div></td>
                  </tr>
				  <tr>
                    	<td><div style="height:100px;">Address</div></td>
                    	<td><div style="height:100px;"><textarea name="addr" id="addr" placeholder="Address" rows="5"></textarea></div></td>
                    </tr>
                  <tr>
                 	 <td><div class="reg">Mobile No.</div></td>
                     <td><div class="reg"><input type="number" name="mob" id="mob" maxleght="10" minlength="10" placeholder="Mobile Number"></div></td>
                  </tr>	
				  <tr>
                 	 <td><div class="reg">Parants Mobile No.</div></td>
                     <td><div class="reg"><input type="number" name="parentmob" id="mob" maxleght="10" minlength="10" placeholder="Parent Mobile Number"></div></td>
                  </tr>					  
                  <tr>
                 	 <td><div class="reg">Email</div></td>
                     <td><div class="reg"><input type="Email" name="email" id="email" placeholder="Email"></div></td>
                  </tr>
              </table>
          </center></div></td>
          <td> <div style="border:2px solid;width:600px;height:300px;"><center>
          		<table><br/>
                	
                    <tr>
                    	<td><div class="reg">Gender</div></td>
                        <td><div class="reg"><input type="radio" name="gen" id="gen" value="male">Male
                        	<input type="radio" name="gen" id="gend" value="female">Female</div></td>
                    </tr>
					<tr>
                    	<td><div class="reg">Stream</div></td>
                        <td><div class="reg"><select name="selectStream" onchange="getId(this.value);">
							<option value="">Select Stream</option>
							
								<?php
								
									$query = "SELECT * FROM table_stream";
									echo $query;
									$results = mysqli_query($con, $query);
								
									foreach ($results as $stream) {
								?>
							<option value="<?php echo $stream["stream_id"]; ?>"> <?php echo $stream["stream_name"]; ?></option>

							<?php
								}
							?>
							</select>
							</div>
                        </td>
                     </tr>
                    <tr>
                    	<td><div class="reg">Department</div></td>
                        <td><div class="reg"><select name="selectdept" id="selectdept">
								<option value=" ">Select Branch</option>
							</select>
							</div>
                        </td>
                    </tr>
                    <tr>
                    	<td><div class="reg">Batch</div></td>
                        <td><div class="reg"><select name="selectbatch" id="select">
                        	<option placeholder="batch"></option>
                            <option id="2014">2014</option>
                            <option id="2015">2015</option>
                            <option id="2016">2016</option>
                            <option id="2017">2017</option>
                            <option id="2018">2018</option>
                            
                            </select>
                        </div>
                        </td>
                     </tr>
                    <tr>
                    	<td><div class="reg">Photo</div></td>
                        <td><div class="reg"><input type="file" name="photo" id="photo"></div></td>
                    </tr>
            	</table>
           </center> </div></td>
                        
                            
 		</tr>
        <tr>
        	<td><div style="position:relative;left:500px;">
            	<input type="submit" name="submit" value="Submit"></div></td>
            <td>
            	<div><input type="reset" id="reset" value="clear"></div></td>
        </tr>
    </div>
   </table>
  </form> 
<script src="js/jquery.js"></script>  
 <script>
	function getId(val){
		$.ajax({
			type: "POST",
			url: "getDeptDataFromAJAX.php",
			data: "stream_id="+val,
			success: function(data){
				$("#selectdept").html(data);
			}
		});
	}
 </script>
</body>
</html>