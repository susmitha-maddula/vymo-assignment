package com.vymodemo.example.view.presenter;

import android.text.TextUtils;
import com.vymodemo.example.view.interfaces.InputFragmentContract.InputFragmentEventHandler;
import com.vymodemo.example.view.interfaces.InputFragmentContract.InputFragmentView;

public class InputFragmentPresenter implements InputFragmentEventHandler {

  InputFragmentView view;

  public InputFragmentPresenter(InputFragmentView inputFragmentView) {
    this.view = inputFragmentView;
  }

  @Override
  public void onSubmitButtonClicked(String ownerName, String repoName) {
    boolean isValidInput = true;
    if (TextUtils.isEmpty(ownerName)) {
      isValidInput = false;
      view.showOwnerNameMustMessage();
    }

    if (TextUtils.isEmpty(repoName)) {
      isValidInput = false;
      view.showRepoNameMustMessage();
    }

    if (isValidInput) {
      view.proceedToIssueListPage(ownerName, repoName);
    }

  }
}
