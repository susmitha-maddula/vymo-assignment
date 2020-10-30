package com.vymodemo.example.view.interfaces;

import com.vymodemo.example.model.IssueModel;
import java.util.ArrayList;

public interface IssueListFragmentContract {

  interface IssueListFragmentEventHandler {

    void onReceiveData(String ownerName, String repoName, String state);

  }

  interface IssueListFragmentView {

    void showError(String message);

    void showIssues(ArrayList<IssueModel> issueModelList);
  }

}
