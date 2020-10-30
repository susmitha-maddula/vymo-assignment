package com.vymodemo.example.view.interfaces.communication;

import java.util.ArrayList;

public interface IssuePagerFragmentContract {

  interface IssuePagerFragmentEventHandler {

    void onReceiveOwnerAndRepoName(String ownerName, String repoName);
  }

  interface IssuePagerFragmentView {

    void showIssuesForGivenStates(String ownerName, String repoName, ArrayList<String> states);
  }

}
