package com.example.projectandthesismanagementsystem.retrofit;

import com.example.projectandthesismanagementsystem.models.Name;
import com.example.projectandthesismanagementsystem.models.Project;
import com.example.projectandthesismanagementsystem.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("login.php")
    Call<User> performLogin(@Query("email") String email, @Query("password") String password);

    @GET("set_appointment.php")
    Call<User> setAppointment(@Query("teacher_id") int teacher_id,@Query("student_id") int student_id,
                              @Query("subject") String subject,@Query("description") String description,
                              @Query("message") String message,@Query("date") String date, @Query("time") String time);
    @GET("request_appointment.php")
    Call<User> requestAppointment(@Query("teacher_id") int teacher_id, @Query("student_id") int student_id,
                                  @Query("subject") String subject, @Query("description") String description);

    @GET ("register_teach.php")
    Call<User> performRegisterTeacher (@Query("email") String email,@Query("password") String password,@Query("name") String name,
                                       @Query("institution") String institution,@Query("department") String department,@Query("cell") String phone);
    @GET ("register_stu.php")
    Call<User> performRegisterStudent (@Query("email") String email,@Query("password") String password,@Query("name") String name,
                                       @Query("institution") String institution,@Query("reg") String reg,@Query("department") String department,@Query("cell") String phone);

    @GET("get_teachers.php")
    Call<ArrayList<Name>> getNames(@Query("type") String type);

    @GET("get_teacher.php")
    Call<User> getUserDetails(@Query("name") String name,@Query("type") String type);

    @GET("get_teachers.php")
    Call<ArrayList<Project>> getProjectList(@Query("type") String type,@Query("user_id") int id);


}
