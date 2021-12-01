package com.vanskarner.adapters.ui.multi_adapters.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.databinding.MultiViewOneBinding;
import com.vanskarner.adapters.databinding.MultiViewSecondBinding;
import com.vanskarner.adapters.databinding.MultiViewThirdBinding;

@SuppressWarnings("rawtypes")
class ViewHolderVisitor implements Visitor {

    @Override
    public int visit(Person.PersonOne model) {
        return R.layout.multi_view_one;
    }

    @Override
    public int visit(Person.PersonSecond model) {
        return R.layout.multi_view_second;
    }

    @Override
    public int visit(Person.PersonThird model) {
        return R.layout.multi_view_third;
    }

    @Override
    public BasicVH create(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == R.layout.multi_view_one) {
            return new MultiAdapter.OneVH(MultiViewOneBinding.inflate(inflater, parent, false));
        } else if (viewType == R.layout.multi_view_second) {
            return new MultiAdapter.SecondVH(MultiViewSecondBinding.inflate(inflater, parent, false));
        }
        return new MultiAdapter.ThirdVH(MultiViewThirdBinding.inflate(inflater, parent, false));
    }


}
