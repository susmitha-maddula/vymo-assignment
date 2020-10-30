package com.vymodemo.example.network;

import com.vymodemo.example.model.IssueModel;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VymoDemoEndpointInterface {

  @GET("{ORG_NAME}/{REPO_NAME}/issues")
  Call<ArrayList<IssueModel>> getIssuesBasedOnState(@Path("ORG_NAME") String orgName,
      @Path("REPO_NAME") String repoName, @Query("state") String state);
}
