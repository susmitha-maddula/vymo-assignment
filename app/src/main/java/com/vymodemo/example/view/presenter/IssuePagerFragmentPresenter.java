package com.vymodemo.example.view.presenter;

import static com.vymodemo.example.constants.VymoConstants.STATE_CLOSED;
import static com.vymodemo.example.constants.VymoConstants.STATE_OPEN;

import com.vymodemo.example.view.interfaces.communication.IssuePagerFragmentContract.IssuePagerFragmentEventHandler;
import com.vymodemo.example.view.interfaces.communication.IssuePagerFragmentContract.IssuePagerFragmentView;
import java.util.ArrayList;

public class IssuePagerFragmentPresenter implements IssuePagerFragmentEventHandler {

  private IssuePagerFragmentView view;

  public IssuePagerFragmentPresenter(IssuePagerFragmentView issuePagerFragmentView) {
    this.view = issuePagerFragmentView;
  }

  @Override
  public void onReceiveOwnerAndRepoName(String ownerName, String repoName) {
    ArrayList<String> states = new ArrayList<>();
    states.add(STATE_OPEN);
    states.add(STATE_CLOSED);
    view.showIssuesForGivenStates(ownerName, repoName, states);
  }
}
