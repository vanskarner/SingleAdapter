package com.vanskarner.adapters.ui.multi_adapters.news;

import android.view.ViewGroup;

@SuppressWarnings("rawtypes")
interface Visitor {
    int visit(Person.PersonOne model);
    int visit(Person.PersonSecond model);
    int visit(Person.PersonThird model);
    BasicVH create(ViewGroup parent, int viewType);
}
