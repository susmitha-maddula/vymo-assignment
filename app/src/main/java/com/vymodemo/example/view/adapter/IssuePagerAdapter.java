package com.vymodemo.example.view.adapter;

import static com.vymodemo.example.constants.VymoConstants.ARG_OWNER_NAME;
import static com.vymodemo.example.constants.VymoConstants.ARG_REPO_NAME;
import static com.vymodemo.example.constants.VymoConstants.ARG_STATE;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.vymodemo.example.view.IssueListFragment;
import java.util.ArrayList;

public class IssuePagerAdapter extends FragmentPagerAdapter {

  private ArrayList<String> states;
  private String ownerName, repoName;

  private IssuePagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  public IssuePagerAdapter(FragmentManager fragmentManager,
      String ownerName, String repoName, ArrayList<String> states) {
    super(fragmentManager);
    this.states = states;
    this.ownerName = ownerName;
    this.repoName = repoName;
  }

  // Returns total number of pages
  @Override
  public int getCount() {
    if (states == null || states.size() == 0) {
      return 0;
    } else {
      return states.size();
    }
  }

  // Returns the fragment to display for that page
  @Override
  public Fragment getItem(int position) {
    IssueListFragment issueListFragment = new IssueListFragment();
    Bundle extras = new Bundle();
    extras.putString(ARG_STATE, states.get(position));
    extras.putString(ARG_OWNER_NAME, ownerName);
    extras.putString(ARG_REPO_NAME, repoName);
    issueListFragment.setArguments(extras);
    return issueListFragment;
  }

  // Returns the page title for the top indicator
  @Override
  public CharSequence getPageTitle(int position) {
    return states.get(position).toUpperCase();
  }
}
