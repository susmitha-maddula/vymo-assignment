package com.vymodemo.example.view;

import static android.widget.Toast.LENGTH_LONG;
import static com.vymodemo.example.constants.VymoConstants.ARG_OWNER_NAME;
import static com.vymodemo.example.constants.VymoConstants.ARG_REPO_NAME;
import static com.vymodemo.example.constants.VymoConstants.ARG_STATE;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.vymodemo.example.R;
import com.vymodemo.example.view.adapter.IssueListAdapter;
import com.vymodemo.example.view.interfaces.IssueListFragmentContract.IssueListFragmentEventHandler;
import com.vymodemo.example.view.interfaces.IssueListFragmentContract.IssueListFragmentView;
import com.vymodemo.example.model.IssueModel;
import com.vymodemo.example.view.presenter.IssueListFragmentPresenter;
import java.util.ArrayList;

public class IssueListFragment extends Fragment implements IssueListFragmentView {

  private IssueListFragmentEventHandler presenter;
  private ProgressDialog dialog;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.issue_list_fragment_layout, container, false);
    presenter = new IssueListFragmentPresenter(this);
    return rootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (getArguments() != null) {
      String ownerName = getArguments().getString(ARG_OWNER_NAME, "");
      String repoName = getArguments().getString(ARG_REPO_NAME, "");
      String state = getArguments().getString(ARG_STATE, "");
      dialog = new ProgressDialog(getActivity());
      dialog.setMessage(getString(R.string.ux_progress));
      dialog.show();
      presenter.onReceiveData(ownerName, repoName, state);
    }
  }

  @Override
  public void showIssues(ArrayList<IssueModel> issueModelList) {
    if (dialog.isShowing()) {
      dialog.dismiss();
    }
    RecyclerView rvIssues = getView().findViewById(R.id.rv_issues);
    IssueListAdapter issueListAdapter = new IssueListAdapter(issueModelList);
    rvIssues.setAdapter(issueListAdapter);
    issueListAdapter.notifyDataSetChanged();
  }

  @Override
  public void showError(String message) {
    if (dialog.isShowing()) {
      dialog.dismiss();
    }
    Toast.makeText(getContext(),
        TextUtils.isEmpty(message) ? getString(R.string.ux_error) : message, LENGTH_LONG)
        .show();
  }

  @Override
  public void onStop() {
    super.onStop();
    if (dialog.isShowing()) {
      dialog.dismiss();
    }
  }
}
