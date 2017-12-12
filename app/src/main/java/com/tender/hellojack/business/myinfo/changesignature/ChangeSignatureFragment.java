package com.tender.hellojack.business.myinfo.changesignature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tender.hellojack.R;
import com.tender.hellojack.base.BaseFragment;

public class ChangeSignatureFragment extends BaseFragment implements ChangeSignatureContract.View {

    private ChangeSignatureContract.Presenter mPresenter;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hj_fragment_change_signature, container, false);

        return root;
    }

    @Override
    public void initUIData() {

    }

    @Override
    public void setPresenter(ChangeSignatureContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected boolean onBackPressed() {
        return false;
    }
}