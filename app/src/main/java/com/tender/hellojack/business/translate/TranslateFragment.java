package com.tender.hellojack.business.translate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tender.hellojack.R;
import com.tender.hellojack.base.BaseFragment;

public class TranslateFragment extends BaseFragment implements TranslateContract.View {

    private TranslateContract.Presenter mPresenter;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hj_fragment_translate, container, false);
        mToolbar = (Toolbar) root.findViewById(R.id.hj_toolbar);
        mTitle = (TextView) mToolbar.findViewById(R.id.tv_toolbar_title);

        return root;
    }

    @Override
    public void initUIData() {

    }

    @Override
    public void showNetLoading(String tip) {
        super.showWaitingDialog(tip);
    }

    @Override
    public void hideNetLoading() {
        super.hideWaitingDialog();
    }

    @Override
    public void setPresenter(TranslateContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void initToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle("");
            mToolbar.setNavigationIcon(R.mipmap.hj_toolbar_back);
            mActivity.setSupportActionBar(mToolbar);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.onBackPressed();
                }
            });

            mTitle.setText("咕咚翻译");
        }
    }
}
