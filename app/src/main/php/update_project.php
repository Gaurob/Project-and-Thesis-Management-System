<?php

require "init.php";

$first_seg=$_GET['first_seg'];
$second_seg=$_GET['second_seg'];
$third_seg=$_GET['third_seg'];
$accepted=$_GET['accepted'];
$project_comment=$_GET['project_comment'];
$project_id=$_GET['project_id'];

$false=false;

$response=array();

$sql_project="UPDATE projects
				SET first_seg = $first_seg, second_seg=$second_seg, third_seg=$third_seg, accepted= $accepted, project_comment='$project_comment'
				where P_ID = $project_id;";

if(mysqli_query($connection,$sql_project)){
 $status="ok";
}
 else {
  $status = "error";
}
$response["response"]=$status;;
$output= json_encode($response);
echo $output;
mysqli_close($connection);
?>