package contacto.humano.com.m_fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.get_data_async.getHome;
import contacto.humano.com.m_adapters.home.rv_h_partners;
import contacto.humano.com.m_adapters.home.rv_h_th_adapter;
import contacto.humano.com.m_adapters.home.rv_hp_adapter;
import contacto.humano.com.m_interfaces.home.i_home_achieve;
import contacto.humano.com.m_interfaces.home.i_home_news_post;
import contacto.humano.com.m_interfaces.home.i_home_test;
import contacto.humano.com.m_interfaces.i_general_array;
import contacto.humano.com.m_interfaces.i_general_string;
import contacto.humano.com.utils.BitmapWorkerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_getHome.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_getHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_getHome extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change ypes of parameters
    private String mParam1;
    private String mParam2;

    private Button b_;

//    GoogleMap mMap;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private RecyclerView rv_testimonials;
    private WebView webView;
    private FragmentManager fm;
//    private SupportMapFragment fragment;
    private ArrayList<TextView> decorTvs;
    private ArrayList<LinearLayout> decorLls;
    private Animation bot_top;
    private Animation top_bot;
    private ArrayList<TextView> decorUps, tv_achievement;
    private ArrayList<Object> interfaces_home;
    private RecyclerView rv_partners;
    private ArrayList<ImageView> iv_partners;
    private RecyclerView rv_home_news_post;
    private static ImageView fadeIv;
    private static Animation fadeAnim;
    public static ArrayList<Bitmap> fadeBitmap;
    private static int bitmap_num = 0;
    private static double lat = 37.803343, lon = -122.472639;
    private TextView tvwithbutton;
    private Button bwithtv;
    private ArrayList<ImageView> d_ivs;
    private ArrayList<TextView> decorDetailTvs;
    private ArrayList<TextView> achiName;
    private RecyclerView rv_p_pics;
    private LinearLayout fh_l_partners;

    public Frag_getHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_getHome.
     */

    // TODO: Rename and change types and number of parameters
    public static Frag_getHome newInstance(String param1, String param2) {
        Frag_getHome fragment = new Frag_getHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.frag_home, container, false);
//        setUpMapIfNeeded();
        initVars();
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction("");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    private void setUpMapIfNeeded() {
//        if (mMap == null) {
//            fm = getChildFragmentManager();
//            fragment = (SupportMapFragment)fm.findFragmentById(R.id.home_map);
//            if (fragment == null) {
//                fragment = SupportMapFragment.newInstance();
//                fm.beginTransaction().replace(R.id.home_map, fragment).commit();
//            }
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
//        interfaces_home.add(new i_home_decor() {
//            @Override
//            public void onDecorItemsLoaded(ArrayList items) {
//                int len = items.size();
//                if(len > 0 && len % 2 == 0){
////                int half = len / 2;
//                    for (int i = 0; i < len; i++){
//                        decorTvs.get(i).setText(items.get(i).toString());
////                        System.out.println("Text = "+items.get(i).toString());
//                    }
//                    System.out.println("Decor Loaded");
//                }
//            }
//        });
        interfaces_home.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(final ArrayList list) {
                int len = list.size();
                for (int i = 0, j = 0; j < len; i++, j+=4){
                    final int k = i, l = j;
                    decorTvs.get(i).post(new Runnable() {
                        @Override
                        public void run() {
                            decorTvs.get(k).setText(list.get(l).toString());
                        }
                    });
                    decorDetailTvs.get(i).post(new Runnable() {
                        @Override
                        public void run() {
                            decorDetailTvs.get(k).setText(list.get(l+1).toString());
                        }
                    });
                    new BitmapWorkerTask(d_ivs.get(i), list.get(j+2).toString(), null);
                    decorDetailTvs.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("Detail View Clicked");
                        }
                    });
//                    System.out.println("Text = "+items.get(i).toString());
                }
            }
        });
        interfaces_home.add(new i_home_achieve() {
            @Override
            public void onHomeAchiLoaded(final ArrayList<String> list) {
                System.out.println("Achievments Loaded");
                int len = list.size();
                if(len > 0) {
                    for (int i = 0, k = 0; k < len; i++, k+=2) {
                        final int j = i;
                        final int l = k;
                        achiName.get(j).post(new Runnable() {
                            @Override
                            public void run() {
                                achiName.get(j).setText("    "+list.get(l));
                            }
                        });
                        tv_achievement.get(j).post(new Runnable() {
                            @Override
                            public void run() {
                                tv_achievement.get(j).setText(list.get(l+1));
                            }
                        });
                    }
                }
            }
        });
        interfaces_home.add(new i_home_test() {
            @Override
            public void onHomeTestLoaded(final ArrayList list) {
                System.out.println("Testimonials Loaded");
                rv_testimonials.post(new Runnable() {
                    @Override
                    public void run() {
                        rv_testimonials.setAdapter(new rv_h_th_adapter(list));
                    }
                });
            }
        });
        interfaces_home.add(new i_home_news_post() {
            @Override
            public void onHomeNewsPostLoaded(final ArrayList<rv_hp_adapter.class_home_post> list) {
                System.out.println("OnNewsPostLoaded");
                rv_home_news_post.post(new Runnable() {
                    @Override
                    public void run() {
                        rv_home_news_post.setAdapter(new rv_hp_adapter(list, new i_general_string() {
                            @Override
                            public void onStringTransfer(String string) {
                                mListener.forNewFragment("home", "BlogPage", string);
                            }
                        }));
                    }
                });
            }
        });
        interfaces_home.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(final ArrayList list) {
                tvwithbutton.post(new Runnable() {
                    @Override
                    public void run() {
                        tvwithbutton.setText(list.get(0).toString());
                    }
                });
                bwithtv.post(new Runnable() {
                    @Override
                    public void run() {
                        bwithtv.setText(list.get(1).toString());
                    }
                });
                bwithtv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.forNewFragment("home", "contact");
                    }
                });
            }
        });
        interfaces_home.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(final ArrayList list) {
                fh_l_partners.post(new Runnable() {
                    @Override
                    public void run() {
//                        for (Object s:list){
//                            System.out.println("s = "+s.toString());
//                        }
                        rv_p_pics.setAdapter(new rv_h_partners(list, new i_general_string() {
                            @Override
                            public void onStringTransfer(String string) {
                                mListener.forNewFragment("home", "Partners");
                            }
                        }));
                    }
                });
            }
        });
//        if (mMap == null) {
//            fragment.getMapAsync(this);
//        }
        new getHome(interfaces_home).execute();
//        onResumeDone = true;
    }

    @Override
    public void onClick(View view) {

    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        if(googleMap != null) {
//            mMap = googleMap;
//            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Storey Ave"));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 16.0f));
//        }
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String string);
        void forNewFragment(String type_source, String type_destination);
        void forNewFragment(String type_source, String type_destination, String param);
    }

    private void initVars() {
        achiName = new ArrayList<>();
        achiName.add((TextView) mView.findViewById(R.id.fh_cc_tv));
        achiName.add((TextView) mView.findViewById(R.id.fh_c_tv));
        achiName.add((TextView) mView.findViewById(R.id.fh_aw_tv));
        achiName.add((TextView) mView.findViewById(R.id.fh_sc_tv));
        d_ivs = new ArrayList<>();
        d_ivs.add((ImageView) mView.findViewById(R.id.h_d_iv1));
        d_ivs.add((ImageView) mView.findViewById(R.id.h_d_iv2));
        d_ivs.add((ImageView) mView.findViewById(R.id.h_d_iv3));
        tvwithbutton = (TextView) mView.findViewById(R.id.fh_tvwithbutton);
        bwithtv = (Button) mView.findViewById(R.id.fh_bwithtv);

        interfaces_home = new ArrayList<>();

        bot_top = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.bot_top);
        top_bot = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.top_bot);

        decorLls = new ArrayList<LinearLayout>();
        decorLls.add((LinearLayout) mView.findViewById(R.id.decor_l1));
        decorLls.add((LinearLayout) mView.findViewById(R.id.decor_l2));
        decorLls.add((LinearLayout) mView.findViewById(R.id.decor_l3));
//        decorLls.add((LinearLayout) mView.findViewById(R.id.decor_l4));
//        decorLls.add((LinearLayout) mView.findViewById(R.id.decor_l5));
//        decorLls.add((LinearLayout) mView.findViewById(R.id.decor_l6));

//        int len_lls = decorLls.size();
//        for (int i = 0; i < len_lls; i++){
//            decorLls.get(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(final View view) {
//                    Animation topToBot = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.top_bot);
//                    view.startAnimation(topToBot);
//                    topToBot.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {}
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//                            view.setVisibility(View.GONE);
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {}
//                    });
//                }
//            });
//        }

        decorTvs = new ArrayList<TextView>();
        decorTvs.add((TextView) mView.findViewById(R.id.home_decor1_title));
        decorTvs.add((TextView) mView.findViewById(R.id.home_decor2_title));
        decorTvs.add((TextView) mView.findViewById(R.id.home_decor3_title));
//        decorTvs.add((TextView) mView.findViewById(R.id.home_decor4_title));
//        decorTvs.add((TextView) mView.findViewById(R.id.home_decor5_title));
//        decorTvs.add((TextView) mView.findViewById(R.id.home_decor6_title));
        decorDetailTvs = new ArrayList<TextView>();
        decorDetailTvs.add((TextView) mView.findViewById(R.id.home_decor1_detail));
        decorDetailTvs.add((TextView) mView.findViewById(R.id.home_decor2_detail));
        decorDetailTvs.add((TextView) mView.findViewById(R.id.home_decor3_detail));
//        decorTvs.add((TextView) mView.findViewById(R.id.home_decor4_detail));
//        decorTvs.add((TextView) mView.findViewById(R.id.home_decor5_detail));
//        decorTvs.add((TextView) mView.findViewById(R.id.home_decor6_detail));
        decorUps = new ArrayList<TextView>();
        decorUps.add((TextView) mView.findViewById(R.id.decor_up1));
        decorUps.add((TextView) mView.findViewById(R.id.decor_up2));
        decorUps.add((TextView) mView.findViewById(R.id.decor_up3));
//        decorUps.add((TextView) mView.findViewById(R.id.decor_up4));
//        decorUps.add((TextView) mView.findViewById(R.id.decor_up5));
//        decorUps.add((TextView) mView.findViewById(R.id.decor_up6));

//        int len = decorUps.size();
//        for (int i = 0; i < len; i++){
//            final int j = i;
//            decorUps.get(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    decorLls.get(j).setVisibility(View.VISIBLE);
//                    Animation botToTop = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.bot_top);
//                    decorLls.get(j).startAnimation(botToTop);
//                }
//            });
//        }

        tv_achievement = new ArrayList<>();
        tv_achievement.add((TextView) mView.findViewById(R.id.fh_cc));
        tv_achievement.add((TextView) mView.findViewById(R.id.fh_c));
        tv_achievement.add((TextView) mView.findViewById(R.id.fh_aw));
        tv_achievement.add((TextView) mView.findViewById(R.id.fh_sc));

        rv_testimonials = (RecyclerView) mView.findViewById(R.id.fh_rv_testimonials);
        rv_testimonials.setLayoutManager(new LinearLayoutManager(
                getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_testimonials.setItemAnimator(new DefaultItemAnimator());

        iv_partners = new ArrayList<ImageView>();
//        iv_partners.add((ImageView) mView.findViewById(R.id.partnerImage1));
//        iv_partners.add((ImageView) mView.findViewById(R.id.partnerImage2));
//        iv_partners.add((ImageView) mView.findViewById(R.id.partnerImage3));
//        iv_partners.add((ImageView) mView.findViewById(R.id.partnerImage4));
//        iv_partners.add((ImageView) mView.findViewById(R.id.partnerImage5));
//        iv_partners.add((ImageView) mView.findViewById(R.id.partnerImage6));
//        iv_partners.add((ImageView) mView.findViewById(R.id.partnerImage7));

        rv_home_news_post = (RecyclerView) mView.findViewById(R.id.rv_home_post);
        rv_home_news_post.setLayoutManager(new LinearLayoutManager(
                getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_home_news_post.setItemAnimator(new DefaultItemAnimator());

        fadeIv = (ImageView) mView.findViewById(R.id.home_ad_post);
        fadeAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_in_out_repeat);
        fadeBitmap = new ArrayList<>();

        rv_p_pics = (RecyclerView) mView.findViewById(R.id.rv_p_pics);
        rv_p_pics.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_p_pics.setItemAnimator(new DefaultItemAnimator());

        fh_l_partners = (LinearLayout) mView.findViewById(R.id.fh_l_partner);
    }

    public static void startFadeAnim(){
        bitmap_num = 0;
        fadeAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("End Fade Anim");
                System.out.println("fadeBitmapSize = "+fadeBitmap.size());
                bitmap_num = (++bitmap_num) % fadeBitmap.size();
                System.out.println("Bitmap Num = "+bitmap_num);
                fadeIv.setImageBitmap(fadeBitmap.get(bitmap_num));
                fadeIv.clearAnimation();
                fadeIv.startAnimation(fadeAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//                        fadeIv.setImageBitmap();
                System.out.println("Repeat Fade Anim");
                fadeAnim.setRepeatCount(fadeAnim.getRepeatCount() + 1);
                bitmap_num = (bitmap_num++) % fadeBitmap.size();
                fadeIv.setImageBitmap(fadeBitmap.get(bitmap_num));
            }
        });
        fadeIv.startAnimation(fadeAnim);
    }
}
