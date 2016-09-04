package com.inu.h4.seoultriphelper.Map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.inu.h4.seoultriphelper.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class PageMapViewFragment extends Fragment {

    public PageMapViewFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.map_view, container, false);

        getActivity().setTitle("지도");

        // MapView 객체생성 및 API Key 설정
        MapView mapView = new MapView(getActivity());
        mapView.setDaumMapApiKey("753615f093d763e50b6a87a0a0f25f05");



        LinearLayout mapViewContainer = (LinearLayout) layout.findViewById(R.id.mapViewContainer);
        mapViewContainer.addView(mapView);
       // mapView.setMapCenterPoint(MapPoint.mapPointWithCONGCoord(37.5780,126.9768),true);


        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("test");
        marker.setMapPoint(MapPoint.mapPointWithCONGCoord(40,126));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mapView.addPOIItem(marker);

        return layout;
    }



    @Override
    public void onStart() {
        super.onStart();
    }
}