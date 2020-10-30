package com.vymodemo.example.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import com.vymodemo.example.R;
import com.vymodemo.example.view.interfaces.InputFragmentContract.InputFragmentEventHandler;
import com.vymodemo.example.view.interfaces.InputFragmentContract.InputFragmentView;
import com.vymodemo.example.view.interfaces.communication.FragmentCallback;
import com.vymodemo.example.view.presenter.InputFragmentPresenter;

public class InputFragment extends Fragment implements InputFragmentView {

  private AppCompatEditText edtOwnerName, edtRepoName;
  private AppCompatButton btnSubmit;
  private InputFragmentEventHandler presenter;
  private FragmentCallback callback;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.input_fragment_layout, container, false);
    edtOwnerName = rootView.findViewById(R.id.edtOwnerName);
    edtRepoName = rootView.findViewById(R.id.edtRepoName);
    btnSubmit = rootView.findViewById(R.id.btnSubmit);
    presenter = new InputFragmentPresenter(this);
    return rootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    callback = (FragmentCallback) getActivity();
    btnSubmit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onSubmitButtonClicked(edtOwnerName.getText().toString(),
            edtRepoName.getText().toString());
      }
    });
  }

  @Override
  public void showOwnerNameMustMessage() {
    Toast.makeText(getContext(), getString(R.string.ux_owner_name_must), Toast.LENGTH_LONG).show();
  }

  @Override
  public void showRepoNameMustMessage() {
    Toast.makeText(getContext(), getString(R.string.ux_repo_name_must), Toast.LENGTH_LONG).show();
  }

  @Override
  public void proceedToIssueListPage(String ownerName, String repoName) {
    callback.onOwnerAndRepoInfoReceived(ownerName, repoName);
  }
}
