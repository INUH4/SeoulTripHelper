package com.inu.h4.seoultriphelper.Detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inu.h4.seoultriphelper.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

public class SightDetailFragment extends Fragment implements View.OnClickListener{
    int placeId;
    int pos;        // 메인이미지의 index를 저장.
    LinearLayout mLayout;
    ImageView mainImage;
    ImageButton leftImageButton, rightImageButton;
    RatingBar ratingBar;
    SightDetailItem item;
    TextView sightName, sightInfo, sightRecommendCount;
    MapView mapView;
    List<SightDetailReviewListViewItem> reviewList;
    SightDetailReviewListViewAdapter adapter;
    ListView reviewListView;
    SightDetailReviewDialog dialog;


    final String API_KEY = "9b534daa5413298e965b0542249e5456";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.detail, container, false);

        item = null;

        Bundle bundle = getArguments();
        if(bundle != null) {
            placeId = (Integer) bundle.get("placeId");
            getItemById(placeId);
        }

        if(item != null) {      // 정상적으로 정보를 받아왔다면.
            pos = 0;

            // 메인이미지뷰에 이미지 세팅.
            mainImage = (ImageView) layout.findViewById(R.id.detail_main_image);
            mainImage.setImageResource(item.getImageIds().get(pos));
            mainImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

            // 서브이미지들 생성하는 과정.
            mLayout = (LinearLayout) layout.findViewById(R.id.detail_subimage_layout); // 서브이미지들을 담을 레이아웃.
            mLayout.setOrientation(LinearLayout.HORIZONTAL);        // 가로 정렬.

            setSubImageToLayout();         // 불러온 이미지들을 레이아웃에 세팅.

            // 좌우 버튼을 통해 pos 값 변경.
            leftImageButton = (ImageButton) layout.findViewById(R.id.btn_left_image);
            leftImageButton.setOnClickListener(this);
            rightImageButton = (ImageButton) layout.findViewById(R.id.btn_right_image);
            rightImageButton.setOnClickListener(this);

            ratingBar = (RatingBar) layout.findViewById(R.id.detail_ratingBar);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    TextView tv = (TextView) layout.findViewById(R.id.detail_current_point);
                    tv.setText(String.valueOf(rating));
                    dialog = new SightDetailReviewDialog(getContext(), rating); // 오른쪽 버튼 이벤트
                    dialog.show();
                }
            });

            // 관광지의 이름, 추천 수, 정보를 화면에 띄워줌.
            sightName = (TextView) layout.findViewById(R.id.detail_sight_name);
            sightRecommendCount = (TextView) layout.findViewById(R.id.detail_sight_recommend_count);
            sightInfo = (TextView) layout.findViewById(R.id.detail_sight_info);

            sightName.setText(item.getSightName());
            sightRecommendCount.setText(String.valueOf(item.getRecommendCount()));
            sightInfo.setText(item.getSightInfo());

            // 지도를 위한 MapView 초기화.
            initMapView();

            RelativeLayout mapViewContainer = (RelativeLayout) layout.findViewById(R.id.detail_map_layout);
            mapViewContainer.addView(mapView);

            // 리뷰 출력을 위한 세팅.
            adapter = new SightDetailReviewListViewAdapter();
            setReviewData();

            reviewListView = (ListView) layout.findViewById(R.id.detail_review_list_view);
            reviewListView.setAdapter(adapter);

            listViewHeightSet(adapter, reviewListView);
        }

        return layout;
    }

    public void getItemById(int placeId) {

        // placeId로 관광지 이미지를 불러옴.
        List<Integer> ids = new ArrayList<>();
        ids.add(R.drawable.page_setting_icon);
        ids.add(R.drawable.page_map_icon);
        ids.add(R.drawable.page_bucket_icon);
        ids.add(R.drawable.page_prefer_icon);
        ids.add(R.drawable.page_home_icon);
        ids.add(R.drawable.page_planner_icon);
        ids.add(R.drawable.left_black_arrow);
        ids.add(R.drawable.icon_search);

        item = new SightDetailItem();

        // placeId로 관광지 이름, 이미지, 상세정보, 추천수, 위치를 불러옴.
        item.setSightName("국립중앙박물관");
        item.setImageIds(ids);
        item.setSightInfo("초중생들이 많더라구요~");
        item.setRecommendCount(30);
        item.setSightX(37.5240867);
        item.setSightY(126.9803881);

        // placeId로 리뷰들을 불러옴.
        reviewList = new ArrayList<>();
        SightDetailReviewListViewItem item = new SightDetailReviewListViewItem();
        item.setWriter("MKH");
        item.setRecommendRating((float) 4.5);
        item.setContent("중2때 가본곳이에요!\n중2때 가본곳이에요!중2때 가본곳이에요!\n중2때 가본곳이에요!중2때 가본곳이에요!중2때 가본곳이에요!중2때 가본곳이에요!중2때 가본곳이에요!중2때 가본곳이에요!중2때 가본곳이에요!중2때 가본곳이에요!중2때 가본곳이에요!중2때 가본곳이에요!");
        reviewList.add(item);

        item = new SightDetailReviewListViewItem();
        item.setWriter("MYMY");
        item.setRecommendRating((float)2.5);
        item.setContent("넘나 시끄러운 것");
        reviewList.add(item);
    }

    public void setSubImageToLayout() {
        for(int i=0;i<item.getImageIds().size();i++) {
            ImageView image = new ImageView(getContext());
            image.setImageResource(item.getImageIds().get(i));
            image.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setPadding(25, 10, 25, 10);
            mLayout.addView(image);
        }
    }

    public void setReviewData() {
        for(int i=0; i<reviewList.size(); i++) {
            adapter.addItem(reviewList.get(i));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_left_image:
                if (pos > 0)
                    pos--;
                break;
            case R.id.btn_right_image:
                if (pos < item.getImageIds().size()-1) {
                    pos++;
                }
                break;
        }
        mainImage.setImageResource(item.getImageIds().get(pos));
    }
    public void initMapView() {
        mapView = new MapView(getActivity());
        mapView.setDaumMapApiKey(API_KEY);


        // 중심점 및 줌 레벨 변경
        //mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(item.getSightX(), item.getSightY()), 7, true);
        /*// 줌인
        mapView.zoomIn(true);
        // 줌아웃
        mapView.zoomOut(true);*/

    }
    public void listViewHeightSet(BaseAdapter listAdapter, ListView listView){
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++){
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            Log.d("LOG/DETAIL",String.valueOf(listItem.getMeasuredHeight()));
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
