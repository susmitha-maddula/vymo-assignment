package com.vymodemo.example.view.presenter;

import android.text.TextUtils;
import com.vymodemo.example.application.VymoDemoApplication;
import com.vymodemo.example.model.IssueModel;
import com.vymodemo.example.network.VymoDemoEndpointInterface;
import com.vymodemo.example.view.interfaces.IssueListFragmentContract.IssueListFragmentEventHandler;
import com.vymodemo.example.view.interfaces.IssueListFragmentContract.IssueListFragmentView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssueListFragmentPresenter implements IssueListFragmentEventHandler {

  private IssueListFragmentView view;
//  private String ownerName, repoName;

  public IssueListFragmentPresenter(IssueListFragmentView issueListFragmentView) {
    view = issueListFragmentView;
  }

  @Override
  public void onReceiveData(String ownerName, String repoName, String state) {
    getIssuesBasedOnState(ownerName, repoName, state);
  }

  private void getIssuesBasedOnState(String ownerName, String repoName, String state) {
    VymoDemoEndpointInterface apiService = VymoDemoApplication.getInstance().getRetrofit()
        .create(VymoDemoEndpointInterface.class);
    Call<ArrayList<IssueModel>> call = apiService.getIssuesBasedOnState(ownerName, repoName, state);
    call.enqueue(new Callback<ArrayList<IssueModel>>() {
      @Override
      public void onResponse(Call<ArrayList<IssueModel>> call,
          Response<ArrayList<IssueModel>> response) {
        if (response.isSuccessful()) {
          ArrayList<IssueModel> issueModelList = response.body();
          if (issueModelList != null && !issueModelList.isEmpty()) {
            view.showIssues(issueModelList);
          }
        } else {
          if(!TextUtils.isEmpty(response.message())){
            view.showError(response.message());
          }
        }
      }

      @Override
      public void onFailure(Call<ArrayList<IssueModel>> call, Throwable t) {
        view.showError(t.getMessage());
      }
    });
  }
}
