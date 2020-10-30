package com.vymodemo.example.view;

import static com.vymodemo.example.constants.VymoConstants.ARG_OWNER_NAME;
import static com.vymodemo.example.constants.VymoConstants.ARG_REPO_NAME;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.vymodemo.example.R;
import com.vymodemo.example.view.interfaces.communication.IssuePagerFragmentContract.IssuePagerFragmentEventHandler;
import com.vymodemo.example.view.interfaces.communication.IssuePagerFragmentContract.IssuePagerFragmentView;
import com.vymodemo.example.view.presenter.IssuePagerFragmentPresenter;
import com.vymodemo.example.view.adapter.IssuePagerAdapter;
import java.util.ArrayList;

public class IssuePagerFragment extends Fragment implements IssuePagerFragmentView {

  IssuePagerFragmentEventHandler presenter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.issue_pager_fragment, container, false);
    presenter = new IssuePagerFragmentPresenter(this);
    return rootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (getArguments() != null) {
      String ownerName = getArguments().getString(ARG_OWNER_NAME, "");
      String repoName = getArguments().getString(ARG_REPO_NAME, "");
      presenter.onReceiveOwnerAndRepoName(ownerName, repoName);
    }
  }

  @Override
  public void showIssuesForGivenStates(String ownerName, String repoName,
      ArrayList<String> states) {
    ViewPager issuePager = getView().findViewById(R.id.issuesPager);
    TabLayout tabLayoutSpecification = getView().findViewById(R.id.tabLayoutPager);

    IssuePagerAdapter issuePagerAdapter = new IssuePagerAdapter(getFragmentManager(), ownerName,
        repoName, states);
    issuePager.setAdapter(issuePagerAdapter);
    tabLayoutSpecification.setupWithViewPager(issuePager);

  }
}
