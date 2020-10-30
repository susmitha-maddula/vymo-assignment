package com.vymodemo.example.view.presenter;

import com.vymodemo.example.view.interfaces.MainContract.MainEventHandler;
import com.vymodemo.example.view.interfaces.MainContract.MainView;

public class MainPresenter implements MainEventHandler {

  private MainView view;

  public MainPresenter(MainView view) {
    this.view = view;
  }

  @Override
  public void onStart() {
    view.showInputView();
  }
}
