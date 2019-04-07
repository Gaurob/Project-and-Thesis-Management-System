<?php


require "init.php";

$type=$_GET['type'];

	
$sql_query_teachers="SELECT name,T_ID FROM teachers";
$sql_query_students="SELECT name FROM students";
$sql_query="SELECT name FROM teachers";


	if($type=="Teachers"){
		$response2=array();
			$result=mysqli_query($connection, $sql_query_teachers);
			while($row = mysqli_fetch_array($result)){	
			$response=array();
			$response['name']=$row['name'];						
			$response['t_id']=$row['T_ID'];  			  			
  			array_push($response2, $response);	
			}
	}
	else if($type=="Students"){
		$response2=array();
			$result=mysqli_query($connection, $sql_query_students);
			while($row = mysqli_fetch_array($result)){	
			$response=array();
			$response['name']=$row['name'];  			
  			array_push($response2, $response);	
			}
	}
		
					
			$output= json_encode($response2);
			echo $output;


mysqli_close($connection);

?>



