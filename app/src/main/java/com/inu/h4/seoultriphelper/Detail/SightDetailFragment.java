package com.inu.h4.seoultriphelper.Detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inu.h4.seoultriphelper.DataBase.SelectDB_REVIEW1000;
import com.inu.h4.seoultriphelper.DataBase.SelectDB_SIGHT1000ForDetailSight;
import com.inu.h4.seoultriphelper.DataBase.SelectDB_SIGHT1100ForDetailImage;
import com.inu.h4.seoultriphelper.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SightDetailFragment extends Fragment implements View.OnClickListener {
    static Context mContext;
    static SightDetailItem item;            // 관광지의 정보를 저장.
    static List<String> imageUris;          // 관광지 이미지들의 Uri을 저장.
    static List<Bitmap> imageBitmaps;       // 관광지 이미지들의 비트맵을 저장. (출력용)
    static List<SightDetailReviewListViewItem> reviewList;      // 관광지 리뷰들을 저장.
    static SightDetailReviewListViewAdapter adapter;

    int placeId;
    SightDetailReviewDialog dialog;

    static int pos;        // 메인이미지의 index를 저장.
    static ImageView mainImage;
    static LinearLayout mLayout;

    MapView mapView;
    static View header;

    static TextView sightName, sightInfo, sightRecommendCount;

    final String API_KEY = "9b534daa5413298e965b0542249e5456";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SelectDB_SIGHT1100ForDetailImage.synk = SelectDB_SIGHT1100ForDetailImage.START;
        pos = 0;

        View layout = inflater.inflate(R.layout.detail, container, false) ;             // 리뷰 리스트뷰를 담당
        header = inflater.inflate(R.layout.detail_header, null, false);      // 리뷰 리스트뷰 윗부분을 담당

        mContext = getContext();

        Bundle bundle = getArguments();
        if(bundle != null) {
            placeId = (Integer) bundle.get("placeId");
            getItemById(placeId);
        }

        if(item != null) {      // 정상적으로 정보를 받아왔다면.
            ListView reviewListView;
            ImageButton leftImageButton, rightImageButton;
            RatingBar ratingBar;

            // 좌우 버튼을 통해 pos 값 변경.
            leftImageButton = (ImageButton) header.findViewById(R.id.btn_left_image);
            leftImageButton.setOnClickListener(this);
            rightImageButton = (ImageButton) header.findViewById(R.id.btn_right_image);
            rightImageButton.setOnClickListener(this);

            ratingBar = (RatingBar) header.findViewById(R.id.detail_ratingBar);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    TextView tv = (TextView) header.findViewById(R.id.detail_current_point);
                    tv.setText(String.valueOf(rating));
                    dialog = new SightDetailReviewDialog(getContext(), rating, placeId, reviewList); // 다이얼로그 생성
                    dialog.show();
                }
            });

            // 지도를 위한 MapView 초기화.
            initMapView();

            RelativeLayout mapViewContainer = (RelativeLayout) header.findViewById(R.id.detail_map_layout);
            mapViewContainer.addView(mapView);

            // 리뷰 출력을 위한 세팅.
            adapter = new SightDetailReviewListViewAdapter();

            reviewListView = (ListView) layout.findViewById(R.id.detail_review_list_view);

            reviewListView.addHeaderView(header);
            reviewListView.setAdapter(adapter);

        }

        return layout;
    }

    public void getItemById(int placeId) {
        String str_place_id = String.valueOf(placeId);
        Log.d("LOG/DETAIL", "관광지 아이디 : ".concat(str_place_id));

        // 관광지 이미지 Uri을 받아오고 그 Uri를 비트맵으로 변환.
        imageUris = new ArrayList<>();
        imageBitmaps = new ArrayList<>();
        SelectDB_SIGHT1100ForDetailImage.getData(str_place_id, imageUris);

        // 관광지 이름, 설명, 추천 수를 받아옴
        item = new SightDetailItem();
        SelectDB_SIGHT1000ForDetailSight.getData(str_place_id, item);

        // 관광지의 리뷰들을 불러옴.
        reviewList = new ArrayList<>();
        SelectDB_REVIEW1000.getData(str_place_id, reviewList);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_left_image:
                if (pos > 0)
                    pos--;
                break;
            case R.id.btn_right_image:
                if (pos < imageUris.size()-1) {
                    pos++;
                }
                break;
        }
        mainImage.setImageBitmap(imageBitmaps.get(pos));
    }
    public void initMapView() {
        mapView = new MapView(getActivity());
        mapView.setDaumMapApiKey(API_KEY);

        //중심점 및 줌 레벨 변경
        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

    }

    // 받아온 리뷰 목록을 화면에 띄움.
    public static void notifyReviewData() {
        if(adapter != null) {
            adapter.initListData();
            for(int i=0; i<reviewList.size(); i++) {
                adapter.addItem(reviewList.get(i));
            }
            adapter.notifyDataSetChanged();
            Log.d("LOG/DETAIL", "Notify");
        }
    }

    // 받아온 관광지 정보(이미지 제외)를 화면에 띄움.
    public static void notifySightData() {
        LinearLayout layout = (LinearLayout) header.findViewById(R.id.detail_header_layout);

        // 관광지의 이름, 추천 수, 정보를 화면에 띄워줌.
        sightName = (TextView) header.findViewById(R.id.detail_sight_name);
        sightRecommendCount = (TextView) header.findViewById(R.id.detail_sight_recommend_count);
        sightInfo = (TextView) header.findViewById(R.id.detail_sight_info);

        sightName.setText(item.getSightName());
        sightRecommendCount.setText(String.valueOf(item.getRecommendCount()));
        sightInfo.setText(item.getSightInfo());

        layout.setVisibility(View.VISIBLE);
    }

    // 받아온 관광지 이미지를 화면에 띄움.
    public static void notifySightImage() {
        // 메인이미지뷰에 이미지 세팅.
        mainImage = (ImageView) header.findViewById(R.id.detail_main_image);
        mainImage.setImageBitmap(imageBitmaps.get(pos));
        mainImage.setScaleType(ImageView.ScaleType.FIT_XY);

        // 서브이미지들 생성하는 과정.
        mLayout = (LinearLayout) header.findViewById(R.id.detail_subimage_layout); // 서브이미지들을 담을 레이아웃.
        mLayout.setOrientation(LinearLayout.HORIZONTAL);        // 가로 정렬.

        // 불러온 이미지들을 레이아웃에 세팅.
        for(int i=0;i<imageUris.size();i++) {
            ImageView image = new ImageView(mContext);
            image.setId(Integer.valueOf(i));
            image.setImageBitmap(imageBitmaps.get(i));
            image.setLayoutParams(new LinearLayout.LayoutParams(250, 170));
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setPadding(20, 10, 20, 10);

            // 서브 이미지를 터치했을 때, 메인 이미지로의 전환 이벤트.
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos = v.getId();
                    mainImage.setImageBitmap(imageBitmaps.get(pos));
                }
            });
            mLayout.addView(image);
        }
    }


    // Uri -> 비트맵으로의 전환 메서드.
    public static void LoadBitmapfromUrl(List<String> uris) {
        class LoadClass extends AsyncTask< Object, Void, Bitmap> {
            @Override
            protected Bitmap doInBackground( Object... params ) {
                List<String> uri = (ArrayList<String>) params[0];
                return loadBitmap( uri );
            }

            @Override
            protected void onPostExecute( Bitmap result ) {
                if(result != null)
                    notifySightImage();
            }

            public Bitmap loadBitmap( List<String> uri ) {
                Bitmap bitmap = null;

                for(int i=0; i<uri.size(); i++) {
                    if(SelectDB_SIGHT1100ForDetailImage.synk == SelectDB_SIGHT1100ForDetailImage.MIDTERM) {
                        URL newurl = null;
                        bitmap = null;
                        try {
                            newurl = new URL(uri.get(i));
                            bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                        Log.d("LOG/DETAIL", "Get Bitmap");
                        imageBitmaps.add(bitmap);
                    } else {
                        Log.d("LOG/DETAIL", "Bitmap Clear");
                        imageBitmaps.clear();
                        return null;
                    }
                }
                return bitmap;
            }
        }
        LoadClass inner = new LoadClass();
        inner.execute(uris);
    }

}
