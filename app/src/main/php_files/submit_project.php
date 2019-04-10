<?php

require "init.php";

$adviser=$_GET['adviser'];
$description=$_GET['description'];
$platform=$_GET['platform'];
$technology=$_GET['technology'];
$github=$_GET['github'];
$title=$_GET['title'];
$S_ID=$_GET['s_id'];
$T_ID=$_GET['t_id'];
$false=false;

$response=array();

$sql_project="insert into projects (
title,advisor,S_ID,T_ID,description,platform,technology,github,first_seg, second_seg,third_seg,accepted,project_comment,final_sub) values ('$title','$adviser',$S_ID,$T_ID,'$description','$platform','$technology','$github',false,false,false,false,'',false);";

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