package com.vanskarner.adapters.ui.multi_adapters;

import com.vanskarner.adapters.common.adaptersothers.AdapterItem;

import java.util.List;

interface MultiPaginationContract {

    interface view {

        void addList(List<AdapterItem> list);

    }

    interface presenter {

        void loadMore();
    }
}
