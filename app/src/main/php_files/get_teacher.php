<?php


require "init.php";

$name=$_GET['name'];
$user=$_GET['type'];

$sql_query="SELECT * FROM $user WHERE name LIKE '$name';";


$result=mysqli_query($connection, $sql_query);

$row=mysqli_fetch_assoc($result);

if($user=="teachers"){
	$response['user_id']=$row['T_ID'];
	$response['name']=$row['name'];
	$response['institution']=$row['institute'];
	$response['department']=$row['dept'];
	$response['cell']=$row['cell'];
	$response['email']=$row['email'];

	$t_id=$row['T_ID'];

	$response2=array();

	$sql_query_project="SELECT * FROM projects natural join students where T_ID ='$t_id';";  			
	$result=mysqli_query($connection, $sql_query_project);
	while($row = mysqli_fetch_array($result)){	
		$response1=array();
		$response1['title']=$row['title'];
		$response1['project_id']=$row['P_ID'];
		$response1['teacher_id']=$t_id;
		$response1['description']=$row['description'];
		$response1['stu_name']=$row['name'];
		$response1['adviser_name']=$row['advisor'];
		$response1['platform']=$row['platform'];
		$response1['technology']=$row['technology'];
		$response1['github']=$row['github'];
		$response1['first_seg']=$row['first_seg'];
		$response1['second_seg']=$row['second_seg'];
		$response1['third_seg']=$row['third_seg'];
		$response1['accepted']=$row['accepted'];
		$response1['comment']=$row['project_comment']; 			  			
		array_push($response2, $response1);	
	}
	$response['projects']=$response2;


}
else{
	$response['user_id']=$row['S_ID'];
	$response['name']=$row['name'];
	$response['institution']=$row['institute'];
	$response['department']=$row['dept'];
	$response['cell']=$row['cell'];
	$response['email']=$row['email'];
	$response['reg']=$row['reg'];

	$s_id=$row['S_ID'];

	$response2=array();

	$sql_query_project="SELECT * FROM projects natural join students where S_ID ='$s_id';";			
	$result=mysqli_query($connection, $sql_query_project);
	while($row = mysqli_fetch_array($result)){	
		$response1=array();
		$response1['title']=$row['title'];
		$response1['project_id']=$row['P_ID'];
		$response1['teacher_id']=$row['T_ID'];
		$response1['description']=$row['description'];
		$response1['stu_name']=$row['name'];
		$response1['adviser_name']=$row['advisor'];
		$response1['platform']=$row['platform'];
		$response1['technology']=$row['technology'];
		$response1['github']=$row['github'];
		$response1['first_seg']=$row['first_seg'];
		$response1['second_seg']=$row['second_seg'];
		$response1['third_seg']=$row['third_seg'];
		$response1['accepted']=$row['accepted'];
		$response1['comment']=$row['project_comment']; 			  			
		array_push($response2, $response1);	
	}
	$response['projects']=$response2;



}			

$output= json_encode($response);
echo $output;
mysqli_close($connection);

?>