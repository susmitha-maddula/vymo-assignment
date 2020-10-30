package com.vymodemo.example.view.adapter;

import static com.vymodemo.example.constants.VymoConstants.STATE_OPEN;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.vymodemo.example.R;
import com.vymodemo.example.model.IssueModel;
import com.vymodemo.example.utils.VymoUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IssueListAdapter extends RecyclerView.Adapter<IssueListAdapter.IssueViewHolder> {

  private ArrayList<IssueModel> issueList;

  public IssueListAdapter(ArrayList<IssueModel> issueList) {
    this.issueList = issueList;
  }

  @Override
  public IssueListAdapter.IssueViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_issue, parent, false);
    return new IssueViewHolder(view);
  }

  @Override
  public void onBindViewHolder(IssueViewHolder holder, int position) {
    holder.tvValueTitle.setText(issueList.get(position).title);
    holder.tvValuePrNumber.setText(String.valueOf(issueList.get(position).number));
    if (issueList.get(position).state.equalsIgnoreCase(STATE_OPEN)) {
      holder.tvValuePullStatus
          .setCompoundDrawablesWithIntrinsicBounds(R.drawable.red_circle, 0, 0, 0);
    } else {
      holder.tvValuePullStatus
          .setCompoundDrawablesWithIntrinsicBounds(R.drawable.green_circle, 0, 0, 0);
    }
    holder.tvValuePullStatus.setText(" " + issueList.get(position).state);
    String dateStr = issueList.get(position).updated_at;
    try {
      Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
          .parse(issueList.get(position).updated_at);
      dateStr = VymoUtils.getCalendarTime(date);
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      holder.tvValueCreated.setText(dateStr);
    }
  }

  @Override
  public int getItemCount() {
    if (issueList == null || issueList.size() == 0) {
      return 0;
    } else {
      return issueList.size();
    }
  }

  public static class IssueViewHolder extends RecyclerView.ViewHolder {

    public AppCompatTextView tvValueTitle, tvValuePrNumber, tvValuePullStatus, tvValueCreated;

    public IssueViewHolder(View itemView) {
      super(itemView);
      tvValueTitle = itemView.findViewById(R.id.tv_value_title);
      tvValuePrNumber = itemView.findViewById(R.id.tv_value_pr_number);
      tvValuePullStatus = itemView.findViewById(R.id.tv_value_pull_status);
      tvValueCreated = itemView.findViewById(R.id.tv_value_created);
    }
  }
}
