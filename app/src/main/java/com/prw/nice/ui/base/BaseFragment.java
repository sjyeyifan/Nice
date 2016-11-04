package com.prw.nice.ui.base;

import android.support.v4.app.Fragment;

import com.prw.nice.app.BaseApplication;
import com.squareup.leakcanary.RefWatcher;

/**
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
