<?php

require "init.php";




$email=$_GET['email'];
$password=$_GET['password'];
$name=$_GET['name'];
$institution=$_GET['institution'];
$department=$_GET['department'];
$cell=$_GET['cell'];
$reg=$_GET['reg'];



$response=array();


$sql="select * from students where password like '$password' and email like '$email';";

$sql_reg="insert into students (name,institute,dept,reg,cell,email,PASSWORD)
values ('$name','$institution','$department','$reg','$cell','$email','$password');";



$result=mysqli_query($connection, $sql);

if(mysqli_num_rows($result)>0){
  $status="exist";
}else{
  if(mysqli_query($connection,$sql_reg)){

    $new=mysqli_query($connection, $sql);
    while($row = mysqli_fetch_array($new)){

    $response['user_id']=$row['S_ID'];
    $response['user']="Student";
    $response['reg']=$reg;
    $response['institution']=$institution;
    $response['department']=$department;

    $status="ok";
    $response['name']=$name;
    $response['email']=$email;
}

  }else {
      $status = "error";
  }
}



$response["response"]=$status;;
$output= json_encode($response);
echo $output;
mysqli_close($connection);

?>