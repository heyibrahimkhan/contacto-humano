package contacto.humano.com.m_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.get_data_async.getBlog;
import contacto.humano.com.m_adapters.blog.adapter_blog_si;
import contacto.humano.com.m_adapters.blog.adapter_short_blog;
import contacto.humano.com.m_interfaces.i_general_array;
import contacto.humano.com.m_interfaces.i_general_string;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_getBlog.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_getBlog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_getBlog extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private ArrayList<View> subViews;
    private ArrayList<RecyclerView> rvs;
    private ArrayList mInterfaces;
    private ArrayList<TextView> sb_tv;
    private ArrayList<adapter_blog_si.blog_side_item> mListArchives, mListCategories;
    private RecyclerView rv_sb;
    private i_general_string igs;

    public Frag_getBlog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_getBlog.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_getBlog newInstance(String param1, String param2) {
        Frag_getBlog fragment = new Frag_getBlog();
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
        mView = inflater.inflate(R.layout.frag_blog, container, false);
        initVars(inflater);
        return mView;
    }

    private void initVars(LayoutInflater inflater) {
        igs = new i_general_string() {
            @Override
            public void onStringTransfer(String string) {
//                System.out.println("This is the string I got = "+string);
                mListener.forNewFragment("Blog", "Blog", string);
            }
        };
        rv_sb = (RecyclerView) mView.findViewById(R.id.fb_rv_sb);
        rv_sb.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_sb.setItemAnimator(new DefaultItemAnimator());
        sb_tv = new ArrayList<>();
        subViews = new ArrayList<>();
        subViews.add(inflater.inflate(R.layout.item_blog_side, null));
        subViews.add(inflater.inflate(R.layout.item_blog_side, null));
        sb_tv.add((TextView) subViews.get(0).findViewById(R.id.isb_title));
        sb_tv.add((TextView) subViews.get(1).findViewById(R.id.isb_title));
        LinearLayout l = ((LinearLayout) mView.findViewById(R.id.fb_l));
        int len = subViews.size();
        for (int i = 0; i < len; i++){
            l.addView(subViews.get(i));
        }
        mInterfaces = new ArrayList<>();
        rvs = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        int len = subViews.size();
        for (int i = 0; i < len; i++){
            rvs.add((RecyclerView) subViews.get(i).findViewById(R.id.isb_rv));
            rvs.get(i).setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            rvs.get(i).setItemAnimator(new DefaultItemAnimator());
        }
        mInterfaces.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(final ArrayList list) {
                String title = list.get(0).toString();
                int len = list.size();
                if(len >= 3) {
                    mListArchives = new ArrayList<adapter_blog_si.blog_side_item>();
                    for (int i = 1; i < len; i+=2) {
                        mListArchives.add(new adapter_blog_si.blog_side_item(list.get(i).toString() ,list.get(i+1).toString()));
                    }
                    if(mListArchives.size() > 0) {
                        sb_tv.get(0).post(new Runnable() {
                            @Override
                            public void run() {
                                sb_tv.get(0).setText(list.get(0).toString());
                            }
                        });
//                        if (title.equalsIgnoreCase("Archives")) {
                        rvs.get(0).post(new Runnable() {
                            @Override
                            public void run() {
                            rvs.get(0).setAdapter(new adapter_blog_si(getActivity().getApplicationContext(), mListArchives, igs));
                            subViews.get(0).requestLayout();
                            }
                        });
//                        }
                    }
                }
            }
        });
        mInterfaces.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(final ArrayList list) {
                String title = list.get(0).toString();
                int len = list.size();
                if(len >= 3) {
                    mListCategories = new ArrayList<adapter_blog_si.blog_side_item>();
                    for (int i = 1; i < len; i+=2) {
                        mListCategories.add(new adapter_blog_si.blog_side_item(list.get(i).toString() ,list.get(i+1).toString()));
                    }
                    if(mListCategories.size() > 0) {
                        sb_tv.get(1).post(new Runnable() {
                            @Override
                            public void run() {
                                sb_tv.get(1).setText(list.get(0).toString());
                            }
                        });
                        if (title.equalsIgnoreCase("Categories")) {
                            rvs.get(1).post(new Runnable() {
                                @Override
                                public void run() {
                                    rvs.get(1).setAdapter(new adapter_blog_si(getActivity().getApplicationContext(), mListCategories, igs));
                                    subViews.get(1).requestLayout();
                                }
                            });
                        }
                    }
                }
            }
        });
        mInterfaces.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(final ArrayList list) {
                rv_sb.post(new Runnable() {
                    @Override
                    public void run() {
                        rv_sb.setAdapter(new adapter_short_blog(list, new i_general_string() {
                            @Override
                            public void onStringTransfer(String string) {
//                                mListener.onFragmentInteraction(string);
                                mListener.forNewFragment("Blog", "BlogPage", string);
                            }
                        }));
                    }
                });
            }
        });
        new getBlog(mParam2, mInterfaces).execute();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri.toString());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
        void forNewFragment(String type_source, String type_destination, String param);
    }
}
