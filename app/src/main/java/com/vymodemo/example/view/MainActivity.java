package com.vymodemo.example.view;

import static com.vymodemo.example.constants.VymoConstants.ARG_OWNER_NAME;
import static com.vymodemo.example.constants.VymoConstants.ARG_REPO_NAME;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.vymodemo.example.R;
import com.vymodemo.example.view.interfaces.MainContract.MainEventHandler;
import com.vymodemo.example.view.interfaces.MainContract.MainView;
import com.vymodemo.example.view.interfaces.communication.FragmentCallback;
import com.vymodemo.example.view.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView, FragmentCallback {

  private MainEventHandler mainPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mainPresenter = new MainPresenter(this);
    mainPresenter.onStart();
  }

  @Override
  public void showInputView() {
    InputFragment inputFragment = new InputFragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.container, inputFragment);
    transaction.commitAllowingStateLoss();
  }

  @Override
  public void onOwnerAndRepoInfoReceived(String ownerName, String repoName) {
    Bundle arguments = new Bundle();
    arguments.putString(ARG_OWNER_NAME, ownerName);
    arguments.putString(ARG_REPO_NAME, repoName);
    IssuePagerFragment issueListFragment = new IssuePagerFragment();
    issueListFragment.setArguments(arguments);
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.container, issueListFragment);
    transaction.commitAllowingStateLoss();
  }
}