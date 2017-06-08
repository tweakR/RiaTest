package ria.com.riatest.ui.fragment.mainscreen.view;

import java.util.List;

import ria.com.riatest.api.response.ListResponse;
import ria.com.riatest.ui.core.view.CoreView;

public interface MainScreenView extends CoreView {

    void setWeatherList(List<ListResponse> list);
}
