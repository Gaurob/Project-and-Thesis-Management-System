<?php


require "init.php";



$user_id=$_GET['user_id'];
$type=$_GET['type'];


$sql_query="SELECT * FROM projects join students where S_ID ='$user_id';";
$sql_query2="SELECT * FROM projects join students where T_ID ='$user_id';";


$response2=array();

if($type=="teachers"){

	$result=mysqli_query($connection, $sql_query2);
	while($row = mysqli_fetch_array($result)){

		$response=array();
		$response['title']=$row['title'];
		$response['project_id']=$row['P_ID'];
		$response['description']=$row['description'];
		$response['stu_name']=$row['name'];
		$response['adviser_name']=$row['advisor'];
		$response['platform']=$row['platform'];
		$response['technology']=$row['technology'];
		$response['github']=$row['github'];
		$response['first_seg']=$row['first_seg'];
		$response['second_seg']=$row['second_seg'];
		$response['third_seg']=$row['third_seg'];
		$response['accepted']=$row['accepted'];
		$response['comment']=$row['project_comment'];  			
		array_push($response2, $response);	
	}

}
else{
	$result=mysqli_query($connection, $sql_query);
	while($row = mysqli_fetch_array($result)){

		$response=array();
		$response['title']=$row['title'];
		$response['project_id']=$row['P_ID'];
		$response['description']=$row['description'];
		$response['stu_name']=$row['name'];
		$response['adviser_name']=$row['advisor'];
		$response['platform']=$row['platform'];
		$response['technology']=$row['technology'];
		$response['github']=$row['github'];
		$response['first_seg']=$row['first_seg'];
		$response['second_seg']=$row['second_seg'];
		$response['third_seg']=$row['third_seg'];
		$response['accepted']=$row['accepted'];
		$response['comment']=$row['project_comment'];  			
		array_push($response2, $response);	
	}
}

$output= json_encode($response2);
echo $output;


mysqli_close($connection);

?>