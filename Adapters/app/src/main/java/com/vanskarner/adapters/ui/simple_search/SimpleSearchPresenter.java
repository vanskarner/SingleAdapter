package com.vanskarner.adapters.ui.simple_search;

class SimpleSearchPresenter implements SimpleSearchContract.presenter {

    private final SimpleSearchContract.view view;
    private final SimpleSearchModel model;

    public SimpleSearchPresenter(SimpleSearchContract.view view) {
        this.view = view;
        this.model = new SimpleSearchModel();
    }

    @Override
    public void loadList() {
        view.loadList(model.sampleData());
    }
}
