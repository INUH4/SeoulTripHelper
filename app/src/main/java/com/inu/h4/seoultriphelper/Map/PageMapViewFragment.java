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
        //초기 화면위치, 스케일 지정
        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.5446907, 126.9887732), true);
        mapView.setZoomLevel(4, true);


        int i;
        final double x = 37.5651791;
        final double y = 126.9779692;

        // 마커를 배열로 생성할때.
        MapPOIItem[] markers = new MapPOIItem[5];
        for (i = 0; i < 5; i++) {
            markers[i] = new MapPOIItem();
        }

        for (i = 0; i < 5; i++) {
            markers[i].setItemName(i+"번");
            markers[i].setMapPoint(MapPoint.mapPointWithGeoCoord(x + i * 0.001, y + i * 0.001));
            markers[i].setMarkerType(MapPOIItem.MarkerType.BluePin);
        }
        mapView.addPOIItems(markers);



        return layout;
    }


     @Override
      public void onStart() {
          super.onStart();
     }

    @Override
    public void onPause(){
        super.onPause();
    }
}