package com.vanskarner.adapters.ui.multi_adapters;

class MultiPaginationPresenter implements MultiPaginationContract.presenter {
    private final MultiPaginationContract.view view;
    private final MultiPaginationModel model;

    public MultiPaginationPresenter(MultiPaginationContract.view view) {
        this.view = view;
        model = new MultiPaginationModel();
    }


    @Override
    public void loadMore() {
        view.addList(model.sampleData2());
    }

}
