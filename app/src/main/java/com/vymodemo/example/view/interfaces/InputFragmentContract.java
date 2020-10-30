package com.vymodemo.example.view.interfaces;

public interface InputFragmentContract {

  interface InputFragmentEventHandler {

    void onSubmitButtonClicked(String ownerName, String repoName);
  }

  interface InputFragmentView {

    void proceedToIssueListPage(String ownerName, String repoName);

    void showRepoNameMustMessage();

    void showOwnerNameMustMessage();
  }

}
