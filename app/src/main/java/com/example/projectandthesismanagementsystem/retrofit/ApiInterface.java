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

    @GET ("submit_project.php")
    Call<User> submitProject (@Query("title") String title,@Query("adviser") String adviser,@Query("description") String description,@Query("platform") String platform,
                                       @Query("technology") String technology,@Query("github") String github,@Query("s_id") int S_ID,@Query("t_id") int T_ID);
    @GET ("update_project.php")
    Call<User> updateProject (@Query("project_id") int projectId,@Query("first_seg") Boolean f,@Query("second_seg") Boolean s,@Query("third_seg") Boolean t,
                              @Query("accepted") Boolean accepted,@Query("project_comment") String comment);




}
